package ru.itis.habitio.web.rest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/test")
public class TestController {


    @GetMapping(produces = MediaType.TEXT_PLAIN_VALUE)
    public String test() {
        return "Hello world!";
    }
}
