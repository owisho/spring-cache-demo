package com.example.springcachedemo.controller;

import org.springframework.web.bind.annotation.*;

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