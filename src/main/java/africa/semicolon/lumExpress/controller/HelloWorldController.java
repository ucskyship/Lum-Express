package africa.semicolon.lumExpress.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloWorldController {
    @PostMapping("/hello")
    public String hello(){
        return "Hello world";
    }

    @GetMapping("/bye")
    public String bye(){
        return "bye bye";
    }
}
