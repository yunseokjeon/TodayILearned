package com.cos.controllerdemo.web;

import com.cos.controllerdemo.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class JavaToJspController {

    @GetMapping("/jsp/java")
    public String jspToJava() {
        return "d";
    }

    @GetMapping("/jsp/java/model")
    public String jspToJavaModel(Model model) {
        User user = new User();
        user.setUsername("ssar");

        model.addAttribute("username", user.getUsername());

        return "e";
    }
}
