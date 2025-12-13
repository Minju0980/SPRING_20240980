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

### 6주차 퀴즈 (예외처리)

- 에러 페이지를 다시 확인, BlogController.java를 수정한다.
- 새로운 예외용 에러 페이지가 필요하다. (화면 디자인은 동일)/ @ControllerAdvice를 등록하여 구현한다.

```java
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

```
- BlogController.java 파일 수정

-> id 값이 숫자 형식인지 검사하고 \\d+ 정규식은 하나 이상의 숫자를 의미한다. 만약 사용자가 /article_edit/abc처럼 문자나 특수문자를 넣었다면 에러 페이지로 이동시킨다.

-> 데이터베이스의 Article 엔티티는 id가 Long타입이므로 변환이 필요하며 blogService.findById(longId)를 호출해서 데이터베이스에서 해당 id의 게시글을 찾는다.  

-> 반환타입이 Optional<Article>이므로 값이 존재하지 않을 수 있다. Optional 객체가 비어있으면 존재하지 않는 게시글이라는 의미로 에러페이지로 이동하고 게시글이 존재한다면 해당 게시글 객체를 모델에 "article"이라는 이름으로 담는다.

```java
@ControllerAdvice
public class GlobalControllerAdvice {

    
    @ExceptionHandler(Exception.class)
    public String handleGeneralException(Exception ex, Model model) {

        model.addAttribute("message", "잘못된 요청입니다. 다시 시도해주세요.");
        return "error_page/article_type_error"; 
    }
}

```
- GlobalControllerAdvice.java 파일을 만들었다.

-> @ControllerAdvice는 스프링의 전역 예외 처리용 어노테이션으로, 이 어노테이션이 붙은 클래스는 모든 컨트롤러에서 발생하는 예외를 공통적으로 처리할 수 있다.

-> 예외가 발생하면 스프링이 자동으로 ex 매개변수에 예외 객체를 담아서 handleGeneralException 메서드를 실행한다.

-> 예외 발생 시 이동할 HTML 페이지의 경로(error_page/article_type_error.html)를 지정한다.

- error_page/article_type_error.html를 화면 디자인은 동일하게 만들었다.

## 7주차 (블로그 게시판 - 추가수정)

### 7주차 퀴즈 (게시판 페이지 - 글 수정)

- 글 수정 버튼 동장을 위한 맵핑을 컨트롤러에 등록한다.
- /board_edit을 새로 등록한다.
- board_list.html로 연결한다.(참고: article_edit를 재활용한다.)

- BlogController.java
```java
@GetMapping("/board_edit/{id}")
    public String board_edit(Model model, @PathVariable Long id) {
        Optional<Board> opt = blogService.findById(id);
        if(opt.isEmpty()) {
            return "error_page/article_error";
        }
         model.addAttribute("boards", opt.get());
        return "board_edit";
    }
```
-> list라는 변수는 여러개의 데이터를 의미함, 단일 객체를 담는 Optional<Board>의 의미에 맞게 list를 opt(Optional)이라는 변수명으로 변경하였다.

-> 게시글 id를 전달받아서 DB에서 해당 글을 조회한다.

-> 해당 글이 있으면 수정화면(board_edit.html)으로 이동하고 없으면 에러 페이지로 보낸다.

```java
@PutMapping("/api/board_edit/{id}")
    public String updateBoard(@PathVariable Long id, @ModelAttribute AddArticleRequest request){
        blogService.updateBoard(id, request);
        return "redirect:/board_list";
    }
```

-> 수정 폼에서 넘어온 데이터를 AddArticleRequest로 전달받는다. 

-> 서비스 계층에서 해당 게시글의 정보를 DB에 반영하여 수정하고 수정완료 후 게시글 목록으로 이동한다.  

- 글 수정을 위한 화면을 만들었다.(참고: article_edit와 board_list.html을 재활용한다.)

- board_edit.html

```html
<form th:action="@{/api/board_edit/{id}(id=${boards.id})}" method="post">
                    <input type="hidden" name="_method" value="put">

                    <div class="form-group">
                        <label for="title">제목</label>
                        <input type="text" class="form-control" id="title" name="title" th:value = "${boards.title}" required>
                    </div>

                    ...

                    <button type="submit" class="btn btn-primary">수정완료</button>
                    <a th:href="@{/board_list}" class="btn btn-secondary">목록으로</a>
                </form>


```
-> article_edit과 board_list 구조를 재활용하였다. 기존 title, content 필드 외에도 Board에 있는 4개의 필드(user/newdate/count/likec)에 대한 입력 항목을 추가하였다.

-> 해당 폼은 수정된 데이터를 서버로 전송하는 폼이다. <input type="hidden" name="_method" value="put">은 HTML 폼의 POST 요청을 PUT 요청으로 변환하기 위해 사용하였다.

-> 게시글 제목을 수정할 수 있는 입력창을 만들었고 다른 필드들도 이와 같은 방식으로 입력필드를 만들었다. 모든 입력값은 AddArticleRequest의 필드와 매핑된다.

-> '수정완료' 버튼 클릭시 폼 전체가 서버로 전송된다./ 수정하지 않고 게시글 목록 화면으로 이동할 수 있도록 '목록으로'라는 버튼도 만들었다.

- 글 수정을 위해 서비스 로직을 수정한다.
- 선행: AddArticleRequest.java를 수정한다.(title과 content 이외 나머지 변수들도 추가)

- AddArticleRequest.java
```java
private String title;
    private String content;
    private String user;
    private String newdate;
    private String count;
    private String likec;

    public Board toEntity(){ // Article 객체 생성
        return Board.builder()
            .title(title)
            .content(content)
            .user(user)
            .newdate(newdate)
            .count(count)
            .likec(likec)
            .build();
```

-> 게시글에 필요한 데이터들을 필드로 정의하였다. HTML 수정/등록 폼에서 입력받은 값들이 해당 변수들에 저장된다. 이 클래스는 요청 데이터를 담는 DTO 역할을 한다.

-> toEntity() 메서드를 통해 DTO를 Entity로 변환, 빌더 패턴으로 객체를 생성하였다. 폼에 입력된 값들을 DTO에 담고 이를 Entity로 변환하여 DB에 저장되거나 수정하는 구조이다.

- update 메서드의 추가된 4개 나머지 필드 null을 수정한다.(참고: 추가된 필드가 null을 허용하지 않는다.)

- BlogService.java

```java
public void updateBoard(Long id, AddArticleRequest request) {
        Optional<Board> opt = blogRepository2.findById(id);
        if(opt.isPresent()){
            Board board = opt.get();

            String title = request.getTitle() !=null ? request.getTitle() : board.getTitle();
            String content = request.getContent() != null ? request.getContent() : board.getContent();
            String user    = request.getUser()    != null ? request.getUser()    : board.getUser();
            String newdate = request.getNewdate() != null ? request.getNewdate() : board.getNewdate();
            String count   = request.getCount()   != null ? request.getCount()   : board.getCount();
            String likec   = request.getLikec()   != null ? request.getLikec()   : board.getLikec();

            board.update(title, content, user, newdate, count, likec);
            blogRepository2.save(board);
        }
    }
```

-> 게시글이 존재하는 경우에만 수정 로직을 실행하도록 하였다. Optional 안에 있는 Board 엔티티를 꺼내어 수정 대상으로 사용한다.

-> request.getTitle() !=null ? request.getTitle() : board.getTitle()은 수정폼에서 새 값이 전달되었을 경우 해당 값을 사용한다. 값이 전달되지 않았을 경우(null) 기존 DB값을 그대로 유지하도록 한다. 같은 방식으로 추가된 모든 필드에 대해 null 처리를 적용하였다. null을 허용하지 않는다.

-> blogRepository2.save(board)로 변경된 Board 객체를 DB에 저장한다. 

## 9주차 (게시판 - 검색과 페이징)

### 9주차 퀴즈 (게시판 페이지 - 글번호 & 삭제)

- 컨트롤러로부터 시작 페이지를 전달
- BlogController.java의 /board_list를 수정한다. 시작 변수를 board_list.html 페이지를 전달한다.
- 예) int startNum = (page * pageSize) + 1;
- 페이지 이동 시에도 전체 목록 순서 기준으로 연속 번호를 출력한다(1-5, 6-10...)

- BlogController.java

```java
int pageSize = 5;
PageRequest pageable = PageRequest.of(page, pageSize); // 한 페이지의 게시글 수

...

int startNum = (page * pageSize) + 1; //페이지 시작 번호 계산
...
model.addAttribute("startNum", startNum); //추가된 시작 번호

```

- board_list.html
```html
<tr th:each="board, stat : ${boards}">
                        <td th:text="${startNum+stat.index}"></td>
...
</tr>
```
-> board.id는 DB의 기본키라서 사용자가 알 필요가 없다. 중간에 삭제되면 1,2,4,7.. 처럼 연속되지 않을 수 있다.

-> DB id 대신 목록의 순서를 나타내는 번호를 출력한다. startNum = (page * pageSize) + 1로 현재 페이지의 시작 번호를 계산해 board_list.html로 전달한다. 
startNum + stat.index(0부터 시작하는 인덱스)를 사용해 페이지 이동시에도 전체 순서 기준으로 연속 번호가 출력되도록 구현한다.

-> 예) 1페이지(page=0) startNum=1 -> 1~5 

-> 2페이지 (page=1) startNum=6 -> 6~10

- 컨트롤러에 삭제 맵핑을 추가한다.
- 맵핑 이름: api/board_delete(참고: 기존 삭제 기능과 동일)

- BlogController.java

```java
@DeleteMapping("/api/board_delete/{id}")
    public String deleteBoard(@PathVariable Long id) {
        blogService.deleteBoard(id);
        return "redirect:/board_list";
    }
```
-> 사용자가 삭제 버튼을 누르면 /api/board_delete/{id}로 DELETE 요청이 전달되고 컨트롤러는 해당 게시글을 DB에서 삭제한 뒤 목록 페이지로 다시 이동시킨다.

```java
public void deleteBoard(Long id) {
        blogRepository2.deleteById(id);
    }
```
-> BlogService.java에서 삭제 처리를 한다.

- board_view.html
```html
<form th:action="@{/api/board_delete/{id}(id=${boards.id})}" method="post" style="display:inline;">
                                <input type="hidden" name="_method" value="delete">
                                <button type="submit" class="btn btn-danger">삭제</button>
                            </form>
```
-> 기존 삭제 기능 방식과 동일하다. 폼은 DELETE 요청을 직접 보낼 수 없기 때문에 POST로 요청을 보내고 hidden 필드 _method=delete를 사용해 스프링에서 DELETE 요청으로 처리하도록 구현하였다.

## 10주차 (로그인과 로그아웃 - 1)

### 10주차 퀴즈 (입력값 필터링)

- AddMemberRequest.java 수정하기
- 입력값 필터링(내부 검증 동작 활성화)
- @Valid, @Validated 추가
- 각 필드마다 검증이 되는지 회원 가입 기능 확인하기(비밀번호, 나이 등 2가지 검증을 해보자)

- AddMemberRequest.java

```java

    @NotBlank(message = "이름은 필수 입력값입니다.")
    @Pattern(regexp = "^[^!@#$%^&*(),.?\":{}|<>]+$", message = "이름에는 특수문자를 사용할 수 없습니다.")
    private String name;

    @NotBlank(message = "이메일은 필수 입력값입니다.")
    @Email(message = "올바른 이메일 형식을 입력하세요.")
    private String email;

    @NotBlank(message = "비밀번호는 필수 입력값입니다.")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z]).{8,}$", message = "비밀번호는 8자 이상, 대문자와 소문자를 포함해야 합니다.")
    private String password;

    @Pattern(regexp = "^(19|[2-8][0-9]|90)$", message = "나이는 19세 이상 90세 이하만 입력 가능합니다.")
    private String age;

    private String mobile;

    private String address;

```
-> 이름은 공백X, 특수문자X이기 때문에 @NotBlank와 정규식 검사를 위해 @Pattern(regexp =)를 사용하였다. ^~$은 전체 문자열 검사이며 !@#$%^&*(),.?":{}|<> 대괄호 안에 적힌 문자들을 금지한다.

-> 이메일은 공백X, 이메일 형식이기 때문에 @NotBlank와 @Email을 사용하였다. 

-> 비밀번호는 패턴O(8글자이상이고 대소문자를 포함해야 한다.)이기 때문에 @NotBlank와 @Pattern(regexp =)을 사용하였다. (?=.*[a-z])은 소문자 최소 1개 포함이고, (?=.*[A-Z])은 대문자 최소 1개 포함이다. .{8,}은 전체 길이 8자 이상이라는 뜻이다.

-> 나이는 패턴O(19세이상 90세 이하)이기 때문에 @Pattern(regexp =)을 사용하였다. 19|[2-8][0-9]|90은 19, 20~89, 90을 허용한다는 의미이다.

-> 모바일과 주소는 공백O이다.

- Controller에 @Valid/ Service에 @Validated 및 메서드 파라미터 @Valid를 넣어서 DTO검증이 실제 실행되도록 하였다.

## 11주차 (로그인과 로그아웃 - 2)

### 11주차 퀴즈 (게시판 수정하기)

- 게시글 추가(글 저장하기)
- 현재 로그인한 사용자로 글의 작성자를 저장한다.

- BlogController.java

```java
@PostMapping("/api/boards") // 글쓰기 게시판 저장
public String addboards(@ModelAttribute AddArticleRequest request,HttpSession session) {

        //로그인 여부 확인 (안되어 있으면 로그인 페이지로)
        String email = (String) session.getAttribute("email");
        if(email == null){
            return "redirect:/member_login";
        }

        //작성자 = 현재 로그인한 사용자(email)
        request.setUser(email);

        //기존 저장 로직
        blogService.save(request);

        return "redirect:/board_list"; // .HTML 연결
    }

```
-> 로그인 성공 시 세션에 저장해둔 email값을 꺼낸다. 이 값이 있으면 로그인을 한 상태이고 없으면 로그인을 안 한 상태이다. 로그인하지 않은 사용자가 글 저장 요청을 하면 글 저장을 금지하고 로그인 페이지로 강제 이동한다.

-> request.setUser(email); 은 게시글의 작성자를 사용자가 폼에서 보낸 값이 아니라 서버에 있는 로그인 정보 email로 강제 설정한다는 것이다.

-> DTO를 Board 엔티티로 변환한 이후 DB에 게시글을 저장한다. 글 저장 완료후 게시글 목록 화면으로 이동한다.

- 게시글 내용 보기
- 글의 작성자만 수정/삭제 버튼을 보여준다.

- board_view.html

```html
<tr th:if ="${boards.user} == ${email}">
...
</tr>

<tr>
    <td>
        <a class = "btn btn-secondary" th:href="@{/board_list}">목록으로</a>
    </td>
</tr>
```
-> boards.user(글작성자) == email(자)일 때만 수정/삭제 버튼이 보이도록 하였다.

-> '목록으로'라는 버튼은 항상 보이도록 하였다.

- BlogController.java

```java
if(opt.isEmpty()) {
            return "/error_page/article_error"; //오류 처리 페이지로 이동
        }

        //게시글
        Board board = opt.get();
        model.addAttribute("boards",board); 

       //로그인한 사용자 이메일 추가
       String email = (String) session.getAttribute("email");
       model.addAttribute("email",email);

        return "board_view"; 

```
-> opt는 DB에서 id에 해당하는 게시글을 조회한 결과를 Optional로 감싼 객체이다.

-> 로그인 성공 시 세션에 저장해 둔 값을 꺼내온다. 로그인 상태이면 이메일이 있고 비로그인 상태이면 null이다.

-> model.addAttribute("email",email); -> board_view.html에서 이 값으로 권한 비교를 한다. 게시글 작성자와 현재 로그인 사용자가 같을 때만 수정/삭제 버튼이 보인다.

## 12주차 (포트폴리오 완성)

### 12주차 퀴즈 (세션 처리, 파일 업로드)

- 2명이상 로그인 되도록 코드를 수정한다.
- 로그인: 사용자마다 다른 이름 세션을 생성, 로그 아웃: 현재 사용자의 세션과 쿠키만 삭제

- MemberController.java

```java

String email = request.getEmail(); // 이메일 얻기

String sessionId = "USER_" + email + "_" + UUID.randomUUID().toString(); //이메일+랜덤값 섞어서-> 사용자마다 다른세션 이름
```
-> 이메일 + UUID를 조합하여 세션에 저장되는 userId(사용자 식별값)을 사용자별로 고유하게 생성하였다. 이를 통해 사용자 구분이 가능해졌다.

```java
if(session !=null){
            session.invalidate(); //현재 브라우저(현재 사용자)의 세션만 무효화
        }
```
-> 로그아웃을 수정하였다. invalidate()는 현재 브라우저(현재 사용자)의 세션을 무효화하고 JSESSIONID 쿠키를 MaxAge=0으로 설정해 삭제하였다. 또한 로그아웃 이후 getSession(true)로 새 세션을 만들지 않도록 하여 삭제된 쿠키가 다시 생성되지 않게 하였다.

- 동일 파일 업로드시에 다른 이름으로 업로드
- 파일 업로드 에러 페이지 처리

- FileController.java

```java
//동일파일 있으면 _1,_2 붙이기
int index = 1;
while(Files.exists(filePath)){
    filePath = uploadPath.resolve(sanitizedEmail + "_" + index + ".txt");
    index++;
}
```
-> int index = 1;은 파일명이 중복될 경우 붙일 번호 시작값이며 첫번째 중복 파일은 _1부터 시작이다.

-> File.exists(filePath)는 현재 filePath경로에 파일이 이미 존재하는지 검사한다. true이면 같은 이름 파일이 이미 있다는 것이다.

-> while문으로 파일이 존재하는 동안 계속 반복한다. index++으로 번호를 증가시켜 _1, _2와 같이 중복되지 않는 새로운 파일명을 생성한다. 동일 파일 업로드시에도 새 파일이 생성되도록 처리하였다.
