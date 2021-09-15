package com.cos.controllerdemo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

//@Controller // File을 응답하는 컨트롤러
@RestController // Data를 응답하는 컨트롤러
public class HttpController {

    @GetMapping("/get")
    public String get() {
        return "<h1>get 요청됨</h1>";
    }

    @PostMapping("/post")
    public String post() {
        return "post 요청됨";
    }

    @PutMapping("/put")
    public String put() {
        return "put 요청됨";
    }

    @DeleteMapping("/delete")
    public String delete() {
        return "delete 요청됨";
    }
}
