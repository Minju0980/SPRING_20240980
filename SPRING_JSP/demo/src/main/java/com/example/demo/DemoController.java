package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // 컨트롤러 어노테이션 명시
public class DemoController{

    @GetMapping("/")
    public String index() {
        return "index1";  // index1.html을 렌더링
    }
    
    @GetMapping("/hello") // 전송 방식 GET
    public String hello(Model model) {
    model.addAttribute("data", " 방갑습니다."); // model 설정
    return "hello"; // hello.html 연결
    }

    //2주차 퀴즈
    //이걸로 인해서 hello.html에 지운 코드가 있음 나중에 피피티로 다시 확인
    @GetMapping("/hello2")
    public String hello2(Model model){
        model.addAttribute("data1", "홍길동님.");
        model.addAttribute("data2", "방갑습니다.");
        model.addAttribute("data3", "오늘.");
        model.addAttribute("data4", "날씨는.");
        model.addAttribute("data5", "매우 좋습니다.");
        return "hello2";
    }
}