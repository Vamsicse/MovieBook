package com.galaxy.movie.repository;

import java.util.List;
import com.galaxy.movie.model.Movie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends CrudRepository<Movie, String> {

    Movie findByName(String name);
    Movie findByDescription(String description);
    List<Movie> findByReleaseYear(String releaseYear);

    /*
    private static Map<Integer, Movie> movies = new HashMap<>();

    static {
        movies.put(1, new Movie("1", "Titanic", "Drama/Disaster, 1997"));
        movies.put(2, new Movie("2", "Interstellar", "Drama/Mystery, 2014"));
        movies.put(3, new Movie("3", "CoCo", "Animated-Fantasy/Mystery, 2017"));
        movies.put(4, new Movie("4", "Avengers: Endgame", "Fantasy/Mystery, 2019"));
    }

    public Map<Integer,Movie> getMovies(){
        return movies;
    }*/

}
