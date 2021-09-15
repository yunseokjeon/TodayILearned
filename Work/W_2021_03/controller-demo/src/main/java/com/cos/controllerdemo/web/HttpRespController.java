package com.cos.controllerdemo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // 파일을 리턴할 것이기 때문에.
public class HttpRespController {

    @GetMapping("/txt")
    public String txt() {
        // 일반 정적 파일들은 resources/static이 디폴트 경로이다.
        return "a.txt";
    }

    @GetMapping("/mus")
    public String mus() {
        /*
        머스태치 템플릿 엔진 라이브러리 등록 완료.
        resources/templates에 .mustache를 두면 자동으로 사용됨.
         */
        return "b";
    }

    @GetMapping("/jsp")
    public String jsp(){
        // jsp 엔진 사용. src/main/webapp이 디폴트 경로가 됨.
        return "c";
    }
}
