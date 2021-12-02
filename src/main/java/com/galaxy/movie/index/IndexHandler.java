package com.galaxy.movie.index;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Vamsi Krishna Myalapalli
 * @since 12/27/2019
 */
@RestController
public class IndexHandler {

    @GetMapping("/")
    public String indexPageMethod(){
        return "   Hey What's Up!   ";
    }

}
