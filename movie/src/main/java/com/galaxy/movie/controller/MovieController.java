package com.galaxy.movie.controller;

import com.galaxy.movie.constants.MessageConstants;
import com.galaxy.movie.constants.MessageInfo;
import com.galaxy.movie.model.Movie;
import com.galaxy.movie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class MovieController {

    private MovieService movieService;
    private String className = this.getClass().getName();

    @Autowired
    public MovieController(MovieService movieService){
        this.movieService = movieService;
    }

    @RequestMapping(method=RequestMethod.GET, value="movies/{id}")
    public Movie getMovie(@PathVariable String id) {
        // return movieService.getByFirstName(firstName);
        System.out.println(MessageConstants.INFO + MessageInfo.flow + className + "." + new Throwable().getStackTrace()[0].getMethodName());
        return movieService.getMovieById(id);
    }

    @RequestMapping(method=RequestMethod.GET, value="/movies")
    public List<Movie> getAllMovies(){
        System.out.println(MessageConstants.INFO + MessageInfo.flow + className + "." + new Throwable().getStackTrace()[0].getMethodName());
        return movieService.getAllMovies();
    }

    @RequestMapping(method = RequestMethod.POST, value="/movies")
    public Movie createMovie(@RequestBody Movie movie) {
        System.out.println(MessageConstants.INFO + MessageInfo.flow + className + "." + new Throwable().getStackTrace()[0].getMethodName());
        return movieService.createMovie(movie);
    }

    @RequestMapping(method=RequestMethod.PUT, value="/movies/{id}")
    public Movie updateMovie(@PathVariable("id") String id, @RequestBody Movie movie) {
        System.out.println(MessageConstants.INFO + MessageInfo.flow + className + "." + new Throwable().getStackTrace()[0].getMethodName());
        movie.setId(id);
        return movieService.updateMovie(id, movie);
    }

    @RequestMapping(method=RequestMethod.DELETE, value="/movies/{id}")
    public String deleteMovie(@PathVariable("id") String id) {
        System.out.println(MessageConstants.INFO + MessageInfo.flow + className + "." + new Throwable().getStackTrace()[0].getMethodName());
        movieService.deleteMovie(id);
        return "Deleted "+ id;
    }

    @RequestMapping(method=RequestMethod.DELETE, value="/movies/deleteAll")
    public String deleteAllMovies() {
        System.out.println(MessageConstants.INFO + MessageInfo.flow + className + "." + new Throwable().getStackTrace()[0].getMethodName());
        movieService.deleteAllMovies();
        return "Deleted all records";
    }

}