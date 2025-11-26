package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.domain.Article;
import com.example.demo.model.domain.Board;
import com.example.demo.model.service.AddArticleRequest;
// import com.example.demo.model.domain.TestDB;
import com.example.demo.model.service.BlogService;
// import com.example.demo.model.service.TestService; // 최상단 서비스 클래스 연동 추가

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller // 컨트롤러 어노테이션 명시
public class BlogController{

    // 클래스 하단 작성
    @Autowired
    BlogService blogService; // DemoController 클래스 아래 객체 생성

    // @GetMapping("/article_list") // 게시판 링크 지정
    // public String article_list(Model model) {
    //     List<Article> list = blogService.findAll(); // 게시판 리스트
    //     model.addAttribute("articles", list); // 모델에 추가
    //     return "article_list"; // .HTML 연결
    // }

    // @GetMapping("/board_list") // 새로운 게시판 링크 지정
    // public String board_list(Model model) {
    //     List<Board> list = blogService.findAll(); // 게시판 전체 리스트, 기존 Article에서 Board로 변경됨
    //     model.addAttribute("boards", list); // 모델에 추가
    //     return "board_list"; // .HTML 연결
    // }

    @GetMapping("/board_list") // 새로운 게시판 링크 지정
    public String board_list(Model model, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "") String keyword, HttpSession session) {
        String userId = (String) session.getAttribute("userId"); //세션 아이디 존재확인
        String email = (String) session.getAttribute("email"); // 세션에서 이메일 확인

        if (userId == null) {
            return "redirect:/member_login"; // 로그인 페이지로 리다이렉션
        }
        System.out.println("세션 userId: " + userId); // 서버 IDE 터미널에 세션 값 출력
        int pageSize = 5;
        PageRequest pageable = PageRequest.of(page, pageSize); // 한 페이지의 게시글 수
        Page<Board> list; // Page를 반환

        if (keyword.isEmpty()) {
            list = blogService.findAll(pageable); // 기본 전체 출력(키워드 x)
        } else {
            list = blogService.searchByKeyword(keyword, pageable); // 키워드로 검색
        }

        int startNum = (page * pageSize) + 1; //페이지 시작 번호 계산
        model.addAttribute("email", email); // 로그인 사용자(이메일)
        model.addAttribute("boards", list); // 모델에 추가
        model.addAttribute("totalPages", list.getTotalPages()); // 페이지 크기
        model.addAttribute("currentPage", page); // 페이지 번호
        model.addAttribute("keyword", keyword); // 키워드
        model.addAttribute("startNum", startNum); //추가된 시작 번호
        return "board_list"; // .HTML 연결
    }

        // 글쓰기 게시판
    @GetMapping("/board_write")
    public String board_write() {
        return "board_write";
    }

    @PostMapping("/api/boards") // 글쓰기 게시판 저장
    public String addboards(@ModelAttribute AddArticleRequest request) {
    blogService.save(request);
    return "redirect:/board_list"; // .HTML 연결
    }


    @GetMapping("/board_view/{id}") // 게시판 링크 지정
    public String board_view(Model model, @PathVariable Long id) {
        Optional<Board> opt = blogService.findById(id); // 선택한 게시판 글

        if (opt.isPresent()) {
            model.addAttribute("boards", opt.get()); // 존재할 경우 실제 Board 객체를 모델에 추가
        } else {
            // 처리할 로직 추가 (예: 오류 페이지로 리다이렉트, 예외 처리 등)
            return "/error_page/article_error"; // 오류 처리 페이지로 연결
        }
        return "board_view"; // .HTML 연결
    }

    // @GetMapping("/article_edit/{id}") // 게시판 링크 지정
    // public String article_edit(Model model, @PathVariable String id) {
        
    //     // 숫자인지 아닌지 확인
    //     if (!id.matches("\\d+")) { 
    //     return "error_page/article_error"; 
    //     }

    //     // 문자열을 숫자로 바꿔 변환
    //     Long longId = Long.parseLong(id);

    //     //DB에서 게시글 찾기
    //     Optional<Article> list = blogService.findById(longId);  // 선택한 게시판 글

    //     //게시글이 없으면 에러 페이지
    //     if (list.isEmpty()) {
    //     return "error_page/article_error";
    //     }

    //     //정상일 때는 모델에 추가
    //     model.addAttribute("article", list.get());
    //     return "article_edit";
    // }

    // @PutMapping("/api/article_edit/{id}")
    // public String updateArticle(@PathVariable Long id, @ModelAttribute AddArticleRequest request) {
    //     blogService.update(id, request);
    //     return "redirect:/article_list"; // 글 수정 이후 .html 연결
    // }

    // @DeleteMapping("/api/article_delete/{id}")
    // public String deleteArticle(@PathVariable Long id) {
    //     blogService.delete(id);
    //     return "redirect:/article_list";
    // }

    // //5주차 퀴즈
    // @PostMapping("/api/articles")
    // public String addArticle(@ModelAttribute AddArticleRequest request) {
    //     blogService.save(request);              
    //     return "redirect:/article_list";       
    // }

    //7주차 퀴즈
    @GetMapping("/board_edit/{id}")
    public String board_edit(Model model, @PathVariable Long id) {
        Optional<Board> opt = blogService.findById(id);
        if(opt.isEmpty()) {
            return "error_page/article_error";
        }
         model.addAttribute("boards", opt.get());
        return "board_edit";
    }
       

    @PutMapping("/api/board_edit/{id}")
    public String updateBoard(@PathVariable Long id, @ModelAttribute AddArticleRequest request){
        blogService.updateBoard(id, request);
        return "redirect:/board_list";
    }

    @DeleteMapping("/api/board_delete/{id}")
    public String deleteBoard(@PathVariable Long id) {
        blogService.deleteBoard(id);
        return "redirect:/board_list";
    }
}
