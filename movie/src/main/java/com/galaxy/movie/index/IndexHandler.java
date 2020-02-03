package com.galaxy.movie.index;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexHandler {

    @GetMapping("/")
    public String indexPageMethod(){
        return "<h1>Hey What's Up!</h1>";
    }

}
