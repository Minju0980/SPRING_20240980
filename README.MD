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
-> Thymeleaf 템플릿 엔진을 사용하겠다고 선언한다./ 브라우저에 "안녕하세요!"라는 큰 제목을 출력한다.

-> th:text = "${data}"에서 컨트롤러가 전달한 모델 객체의 데이터를 data라는 이름으로 받아 HTML에 출력한다.

-> 링크 클릭시 서버의 /hello2 경로로 이동하며 두번째 헬로 페이지를 보여준다.

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

-> @GetMapping("/hello2")는 /hello2라는 URI 경로를 hello2 메서드에 매핑하는 어노테이션이다.

-> addAttribute 메서드는 모델에 데이터를 추가한다./ 5개의 속성을 각자 추가했다.

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

-> 5개의 속성변수(data1~data5)을 출력한다./ 링크 클릭시 /hello 경로로 이동하며 홈페이지 메인으로 돌아가게 된다.


## 3주차 (포트폴리오 작성하기 - 프론트)

### 3주차 퀴즈 (상세 페이지 수정하기)

```html
<a id="btnClose" class="btn btn-primary py-3 px-5" href="javascript(0);">되돌아가기</a>
```


```javascript
document.addEventListener("DOMContentLoaded", function(){
        var btnClose = document.getElementById("btnClose");
        if(btnClose){      //버튼이 있을때만 이벤트 실행
            btnClose.addEventListener("click", function(){
                if(confirm("창을 닫으시겠습니까?")) {
                    window.close();
                    window.location.href="/";
                }
           })
        }
    })

})(jQuery);
```

## 4주차 (데이터베이스 연동 및 테스트)

### 4주차 퀴즈 (사용자 추가 및 출력하기)

## 5주차 (블로그 게시판 - 1)

### 5주차 퀴즈 (페이지 리다이렉트)

## 6주차 (블로그 게시판 - 2)
