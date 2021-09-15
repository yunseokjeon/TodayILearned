package com.cos.controllerdemo.web;

import com.cos.controllerdemo.domain.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HttpResponseJsonController {

    @GetMapping("/resp/json")
    public String respJson(){
        return "{\"username\":\"cos\"}\n";
    }

    @GetMapping("/resp/json/object")
    public String respJsonObject(){
        User user = new User();
        user.setUsername("홍길동");
        String data = "{\"username\":\""+user.getUsername()+"\"}";
        return data;
    }

    @GetMapping("/resp/json/javaobject")
    public User respJsonJavaObject(){
        User user = new User();
        user.setUsername("홍길동");
        // 1. MessageConverter가 자동으로 Java Object를 JSON으로 변경해서 응답을 한다.
        // 2. @RestController 일때만 MessageConverter가 작동한다.
        return user;
    }
}
