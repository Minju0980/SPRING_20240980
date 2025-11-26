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

- 버튼을 클릭하면 현재 창을 닫는다.
- 창을 닫을지 물어본다

```html
<a id="btnClose" class="btn btn-primary py-3 px-5" href="javascript(0);">되돌아가기</a>
```

-> 부트스트랩 버튼 형태 링크이며 사용자가 클릭할 수 있는 버튼의 이름은 '되돌아가기'이다.

-> 이 버튼은 자바스크립트로 클릭 이벤트를 연결해 동작하도록 만든 코드이다.

```javascript
function confirmClose() {
        if (confirm("창을 닫으시겠습니까?")) {
            window.close();
            window.location.href = "/";
        }
    }

    document.addEventListener("DOMContentLoaded", function(){
        var btnClose = document.getElementById("btnClose");
        if (btnClose) {
            btnClose.addEventListener("click", confirmClose);
        }
    });
```
-> confirmClose()으로 사용자가 클릭했을 때 실행될 기능을 함수로 정의한다. 

-> confirm()은 브라우저에서 확인/취소 대화상자를 띄우는 함수이다. 확인을 누르면 true를 반환하고 취소를 누르면 false를 반환한다. 확인을 누른 경우에만 {}안의 코드가 실행된다.

-> btnClose 버튼이 클릭될 때마다 confirmClose() 함수를 실행하도록 연결한다.

-> '되돌아가기' 버튼을 눌렀을 때 창을 닫을지 물어보는 확인창을 띄우고 확인을 누르면 창을 닫고 홈으로 이동하게 만들었다.

## 4주차 (데이터베이스 연동 및 테스트)

### 4주차 퀴즈 (사용자 추가 및 출력하기)

- 2명의 사용자를 db에서 직접 추가한다. INSERT 문을 활용한다.
홍길동 이외의 토마토, 기러기를 추가했다.

```java
@GetMapping("/testdb")
    public String getAllTestDBs(Model model) {
        TestDB user1 = testService.findByName("홍길동");
        TestDB user2 = testService.findByName("토마토");
        TestDB user3 = testService.findByName("기러기");

        model.addAttribute("data1", user1);
        model.addAttribute("data2", user2);
        model.addAttribute("data3", user3);

        System.out.println("데이터 출력 디버그 : " + user1);
        System.out.println("데이터 출력 디버그 : " + user2);
        System.out.println("데이터 출력 디버그 : " + user3);
        return "testdb";
    }
```

-> 사용자가 브라우저에서 /testdb 경로로 GET 요청을 보내면 메서드 getAllTestDBs()가 실행되도록 매핑한다.

-> TestService를 통해 DB에서 이름이 "홍길동"인 데이터를 찾아서 반환한다. 결과는 TestDB 타입의 객체로 저장된다./ 같은 방식으로 "토마토", "기러기" 데이터도 각각 조회한다.

```html
<h1>User List</h1>

    <p>아이디(기본키) : <span th:text="${data1.id}"></span></p>
    <p>이름 : <span th:text="${data1.name}"></span></p>

    <p>아이디(기본키) : <span th:text="${data2.id}"></span></p>
    <p>이름 : <span th:text="${data2.name}"></span></p>

    <p>아이디(기본키) : <span th:text="${data3.id}"></span></p>
    <p>이름 : <span th:text="${data3.name}"></span></p>
```
-> TestDB 테이블에서 조회한 세 명의 사용자 데이터를 data1, data2, data3 모델 속성으로 받아와 각각의 id와 이름을 표시한다.

## 5주차 (블로그 게시판 - 1)

### 5주차 퀴즈 (페이지 리다이렉트)

- 기존 RestController는 주석처리/BlogController.java에 맵핑을 추가한다.
- 리턴 타입은 String/ 리턴: return "redirect:/article_list";

```java
@PostMapping("/api/articles")
    public String addArticle(@ModelAttribute AddArticleRequest request) {
        blogService.save(request);              
        return "redirect:/article_list";       
    }
```
-> 브라우저가 /api/articles 경로로 POST 요청을 보냈을 때 addArticle()메서드가 실행되도록 매핑한다.

-> blogService의 save()를 호출하여 전달받은 request 데이터를 DB에 저장하는 역할을 한다.

-> 게시글을 DB에 저장한 후, 리다이렉트로 사용자를 /article_list 페이지로 자동 이동시킨다.


## 6주차 (블로그 게시판 - 2)

### 6주차 퀴즈



## 7주차

### 7주차 퀴즈

## 9주차

### 9주차 퀴즈

## 10주차

### 10주차 퀴즈
