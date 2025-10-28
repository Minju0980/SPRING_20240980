# C# 소스 코드 깃허브

새로운 시작! C#의 세계로 떠나보아요~

## Getting Started

This project is a starting point for a C# application.

A few resources to get you started if this is your first Flutter project:

- [프로젝트 메인 주소](https://github.com/Minju0980/SPRING_20240980)

## DAY 1 - 환경설정 및 깃 허브 연동, C# 기초 살펴보기

- 실행 및 깃 허브 연동 확인 완료!

## 2주차 (개발환경 설정 및 테스트)

### 2주차 퀴즈(URL 맵핑과 컨트롤 추가하기)

- 기존 hello.html 링크를 수정한다.

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Hello 페이지</title>
</head>
<body>
    <h1>안녕하세요!</h1>
    <p th:text="${data}"></p>
    <a href = "/hello2">두번째 헬로 페이지</a>
</body>
</html>
```
-> hello.html


```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Hello 페이지</title>
</head>
<body>
    <h1>안녕하세요!</h1>
    <p th:text="${data1}"></p>
    <p th:text="${data2}"></p>
    <p th:text="${data3}"></p>
    <p th:text="${data4}"></p>
    <p th:text="${data5}"></p>
    <a href = "/hello">홈페이지 메인</a>
</body>
</html>
```

-> hello2.html

```java
@GetMapping("/hello2")
    public String hello2(Model model){
        model.addAttribute("data1", "홍길동님.");
        model.addAttribute("data2", "방갑습니다.");
        model.addAttribute("data3", "오늘.");
        model.addAttribute("data4", "날씨는.");
        model.addAttribute("data5", "매우 좋습니다.");
        return "hello2";
    }
```

-> DemoController.java

## 3주차 (포트폴리오 작성하기 - 프론트)

### 3주차 퀴즈 (상세 페이지 수정하기)

## 4주차 (데이터베이스 연동 및 테스트)

### 4주차 퀴즈 (사용자 추가 및 출력하기)

## 5주차 (블로그 게시판 - 1)

### 5주차 퀴즈 (페이지 리다이렉트)

## 6주차 (블로그 게시판 - 2)
