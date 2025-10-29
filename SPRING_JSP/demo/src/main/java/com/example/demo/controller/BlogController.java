package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.domain.Article;
import com.example.demo.model.service.AddArticleRequest;
// import com.example.demo.model.domain.TestDB;
import com.example.demo.model.service.BlogService;
// import com.example.demo.model.service.TestService; // 최상단 서비스 클래스 연동 추가

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Controller // 컨트롤러 어노테이션 명시
public class BlogController{

    // 클래스 하단 작성
    @Autowired
    BlogService blogService; // DemoController 클래스 아래 객체 생성

    @GetMapping("/article_list") // 게시판 링크 지정
    public String article_list(Model model) {
        List<Article> list = blogService.findAll(); // 게시판 리스트
        model.addAttribute("articles", list); // 모델에 추가
        return "article_list"; // .HTML 연결
    }

    @GetMapping("/article_edit/{id}") // 게시판 링크 지정
    public String article_edit(Model model, @PathVariable String id) {
        
        // 숫자인지 아닌지 확인
        if (!id.matches("\\d+")) { 
        return "error_page/article_error"; 
        }

        // 문자열을 숫자로 바꿔 변환
        Long longId = Long.parseLong(id);

        //DB에서 게시글 찾기
        Optional<Article> list = blogService.findById(longId);  // 선택한 게시판 글

        //게시글이 없으면 에러 페이지
        if (list.isEmpty()) {
        return "error_page/article_error";
        }

        //정상일 때는 모델에 추가
        model.addAttribute("article", list.get());
        return "article_edit";
    }

    @PutMapping("/api/article_edit/{id}")
    public String updateArticle(@PathVariable Long id, @ModelAttribute AddArticleRequest request) {
        blogService.update(id, request);
        return "redirect:/article_list"; // 글 수정 이후 .html 연결
    }

    @DeleteMapping("/api/article_delete/{id}")
    public String deleteArticle(@PathVariable Long id) {
        blogService.delete(id);
        return "redirect:/article_list";
    }

    //5주차 퀴즈
    @PostMapping("/api/articles")
    public String addArticle(@ModelAttribute AddArticleRequest request) {
        blogService.save(request);              
        return "redirect:/article_list";       
    }
}
