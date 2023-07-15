package com.example.spring.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/test")
public class TestController {
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @RequestMapping("/post")
    public String post(@RequestBody(required = false) Req req) {
        if (req == null) {
            return "";
        }
        return req.content;
    }

    @RequestMapping("/resp/403")
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String resp403() {
        return "token invalid";
    }

    @RequestMapping("/resp/exception/403")
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String resp403ByException() {
        throw new RuntimeException("valid failed by exception");
    }
}

class Req {
    String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}