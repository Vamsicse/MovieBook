package com.galaxy.movie.service;

import com.galaxy.movie.constants.MessageConstants;
import com.galaxy.movie.constants.MessageInfo;
import com.galaxy.movie.exception.MovieException;
import com.galaxy.movie.model.Movie;
import com.galaxy.movie.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.swing.text.html.Option;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;
/**
 * @author Vamsi Krishna Myalapalli
 * @since 12/27/2019
 */
@Service
public class MovieService {

    private MovieRepository movieRepository;
    private String className = this.getClass().getName();

    @Autowired
    public MovieService(MovieRepository movieRepository){
        this.movieRepository = movieRepository;
    }

    public ResponseEntity createMovie(Movie movie) {
        System.out.println(MessageConstants.INFO + MessageInfo.flow + className + "." + new Throwable().getStackTrace()[0].getMethodName());
        if(movieRepository.findById(movie.getId()).isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.TEXT_PLAIN).body("Movie with id " + movie.getId() + " is already present.");
        }
        return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(movieRepository.save(new Movie(movie)));
    }

    public ResponseEntity getAllMovies(){
        System.out.println(MessageConstants.INFO + MessageInfo.flow + className + "." + new Throwable().getStackTrace()[0].getMethodName());
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(movieRepository.findAll());
    }

    public Movie getMovieByName(String name) {
        System.out.println(MessageConstants.INFO + MessageInfo.flow + className + "." + new Throwable().getStackTrace()[0].getMethodName());
        return movieRepository.findByName(name);
    }

    @Cacheable("movie")
    public ResponseEntity getMovieById(String id) {
        System.out.println(MessageConstants.INFO + MessageInfo.flow + className + "." + new Throwable().getStackTrace()[0].getMethodName());
        System.out.println(MessageConstants.INFO + "Caching Data...");
        Optional<Movie> optionalMovie = movieRepository.findById(id);
        if(optionalMovie.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(optionalMovie.get());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.TEXT_PLAIN).body("Could not find Movie with id " + id);
    }

    public ResponseEntity updateMovie(String id, Movie updatedMovie) {
        System.out.println(MessageConstants.INFO + MessageInfo.flow + className + "." + new Throwable().getStackTrace()[0].getMethodName());
        Optional<Movie> optionalMovie = movieRepository.findById(id);
        if(optionalMovie.isPresent()) {
            Movie newMovie = optionalMovie.get();
            newMovie.setName(updatedMovie.getName());
            newMovie.setDescription(updatedMovie.getDescription());
            newMovie.setReleaseYear(updatedMovie.getReleaseYear());
            return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(movieRepository.save(newMovie));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.TEXT_PLAIN).body("Could not find Movie with id " + id);
    }

    public ResponseEntity deleteAllMovies() {
        System.out.println(MessageConstants.WARNING + MessageInfo.flow + className + "." + new Throwable().getStackTrace()[0].getMethodName());
        movieRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity deleteMovie(String id) {
        System.out.println(MessageConstants.INFO + MessageInfo.flow + className + "." + new Throwable().getStackTrace()[0].getMethodName());
        Optional<Movie> optionalMovie = movieRepository.findById(id);
        if(optionalMovie.isPresent()){
            movieRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.TEXT_PLAIN).body("Could not find Movie with id " + id);
    }

}