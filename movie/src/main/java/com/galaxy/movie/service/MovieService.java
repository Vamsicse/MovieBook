package com.galaxy.movie.service;

import com.galaxy.movie.constants.MessageConstants;
import com.galaxy.movie.constants.MessageInfo;
import com.galaxy.movie.exception.MovieException;
import com.galaxy.movie.model.Movie;
import com.galaxy.movie.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

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

    public Movie createMovie(Movie movie) {
        System.out.println(MessageConstants.INFO + MessageInfo.flow + className + "." + new Throwable().getStackTrace()[0].getMethodName());
        return movieRepository.save(new Movie(movie));
    }

    public List<Movie> getAllMovies(){
        System.out.println(MessageConstants.INFO + MessageInfo.flow + className + "." + new Throwable().getStackTrace()[0].getMethodName());
        return (List<Movie>) movieRepository.findAll();
    }

    public Movie getMovieByName(String name) {
        System.out.println(MessageConstants.INFO + MessageInfo.flow + className + "." + new Throwable().getStackTrace()[0].getMethodName());
        return movieRepository.findByName(name);
    }

    @Cacheable("movie")
    public Movie getMovieById(String id) {
        System.out.println(MessageConstants.INFO + MessageInfo.flow + className + "." + new Throwable().getStackTrace()[0].getMethodName());
        System.out.println(MessageConstants.INFO + "Caching Data...");
        return fetchMovie(id);
    }

    public Movie updateMovie(String id, Movie updatedMovie) {
        System.out.println(MessageConstants.INFO + MessageInfo.flow + className + "." + new Throwable().getStackTrace()[0].getMethodName());
        Movie newMovie = fetchMovie(id);
        newMovie.setName(updatedMovie.getName());
        newMovie.setDescription(updatedMovie.getDescription());
        newMovie.setReleaseYear(updatedMovie.getReleaseYear());
        return movieRepository.save(newMovie);
    }

    public void deleteAllMovies() {
        System.out.println(MessageConstants.WARNING + MessageInfo.flow + className + "." + new Throwable().getStackTrace()[0].getMethodName());
        movieRepository.deleteAll();
    }

    public void deleteMovie(String id) {
        System.out.println(MessageConstants.INFO + MessageInfo.flow + className + "." + new Throwable().getStackTrace()[0].getMethodName());
        Movie p = fetchMovie(id);
        movieRepository.delete(p);
    }

    public Movie fetchMovie(String id){
        Optional<Movie> movieOptional = movieRepository.findById(id);
        if(movieOptional.isPresent()){
            return movieOptional.get();
        }
        else{
            throw new MovieException("Could not find movie with id: " + id);
        }
    }
}