package com.galaxy.movie.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import javax.persistence.Id;
import javax.persistence.Entity;
import lombok.Data;

/**
 * @author Vamsi Krishna Myalapalli
 * @since 12/27/2019
 */
@Data
@JsonPropertyOrder
@JsonInclude(Include.NON_NULL)
@Entity
public class Movie {
    @Id
    private String id;
    private String name, description, releaseYear;

    public Movie(){}

    public Movie(String id, String name, String description, String releaseYear) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.releaseYear = releaseYear;
    }

    public Movie(Movie movie){
        this.id = movie.id;
        this.name = movie.name;
        this.description = movie.description;
        this.releaseYear = movie.releaseYear;
    }
}
