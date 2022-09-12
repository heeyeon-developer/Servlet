Packaging을 War로 선택해야 JSP를 실행 가능
Jar은 내장 tomcat을 사용할때 주로 사용
War은 tomcat서버를 별도로 설치하 별도로 War로 빌드하고자 할때 사용, War일때도 내장 tomcat 사용 가능

resources - application.properties 에 'logging.level.org.apache.coyote.http11=debug' 코드를 추가하여 요청 로그를 확인 가능
** 로그를 자세히 확인할 수 있지만 운영서버에서 매번 로그정보를 확인한다면 성능저하가 발생할 수 있으니 개발시에만 추가하기

<Http 요청 메시지를 통해 클라이언트에서 서버로 데이터를 전달하는 방법은 크게 3가지>

GET : 메시지 바디 없이 URL의 쿼리 파라미터에 데이터를 전달하는 방식, [검색, 필터, 페이징등에서 많이 사용]
POST(HTML-Form) : 메시지 바디에 쿼리 파라미터 형식으로 전달하는 방식, [회원가입, 상품주문등에서 사용]
HTTP message body : 바디에 데이터를 직접 담아서 요청하는 것으로 HTTP API에서 주로 사용(JSON, XML, TEXT의 형태)
주로 JSON 데이터 형식 사용, [POST, PUT, PATCH 모두 사용 가능]

GET 방식
파라미터가 중복될 때에는 'request.getParameterValues()'를 사용해야 함
'request.getParameter()'는 단일파라미터에 대해서만 사용한다
'request.getParameter()'를 사용하면 'request.getParameterValues()'의 첫번째 값을 반환하는 것
** 중복으로 보내는 경우는 거의 없음!!

POST 방식 - HTML Form 사용
GET 방식에서 사용한 'request.getParameter()'가 POST 방식도 지원을 한다.
그 이유는 GET 방식과 POST 방식의 파마리터 형식이 같기 때문이다.

API 방식
데이터 형식은 거의 표준으로 JSON 사용
JSON 형태로 가져온 데이터를 파싱해서 객체로 변환하기 위해서는 'ObjectMapper'를 사용하여 변환



<Http 응답 메세지를 생성하는 방법 크게 3가지>
HTTP 응답코드 지정 ex)200, 400 ...
헤더 생성
바디 생성
+ 편의 기능 제공 : Content-Type, 쿠키, Redirect

HTTP 응답 데이터 단순하게 3가지
단순텍스트
HTML
HTTP API - MessageBody JSON 응답

HTTP API 형식으로 응답메세지를 사용할때 객체를 이용하여 json형태로 데이터를 생성하고 'ObjectMapper'를 이용하여
json데이터를 String으로 변환하여 화면으로 출력할 수 있음

<회원가입 예제>
Servlet을 사용할때 html을 자바코드에 넣기가 매우 불편
템플릿 엔진(JSP, Thymeleaf, Freemarker, Velocity 등)을 사용해서 HTML문서에 동적인 코드를 추가하는 것이 더 효율적
먼저 가장 고전적인 JSP를 배울것이지만 지금은 거의 사용되지 않음
그 후에 Thymleaf를 사용할 것

JSP로 개발하게 되면 화면코드와 로직코드가 공존하게 됨
JSP를 이용하여 동적인 작업이 필요한 부분만 자바코드를 입력하면 되지만 뷰와 로직이 공존하여 유지보수가 어려운 단점이 존재

--> MVC 패턴이 등장하게 됨
비즈니스 로직, 뷰 를 나누어 작업하도록 하는 패턴을 말한다
즉, 비즈니스 로직을 서블릿에서 로직을 개발하고, 화면을 보여주는 부분은 JSP에서 개발하도록 패턴이 생겨나게 되었다.

** 변경 주기가 다르다면 두 과정은 분리되어야 한다.
화면의 변경과 로직의 변경 주기는 보통 다르므로 MVC 패턴을 이용해 분리하는것이 더 좋다.

컨트롤러 : Servlet
뷰 : JSP
모델 : 비즈니스 로직을 통한 데이터를 담고 데이터를 뷰에 전달해주는 역할을 함
-> 모델은 HttpServletRequest 객체를 사용함, request 는 내부에 데이터 저장소를 가지고 있고 내부 저장소는
request.setAttribuet(), request.getAttribute() 를 사용하여 데이터를 보관, 조회 가능

Servlet에서 비즈니스 로직을 개발하여 컨트롤러로 사용하게 되면 컨트롤러가 하는 역할이 너무 커지게 된다.
그렇기 때문에 '서비스' 라는 계층에 비즈니스 로직을 만들고 컨트롤러가 서비스를 호출하여 비즈니스 로직이 실행되는 과정으로 개발이 진행됨

WEB-INF 경로에 담김 jsp 파일들은 항상 컨트롤러를 통해 jsp 파일을 부르고 싶은 jsp를 저장하는 경로로 was 규칙이다.
즉 WEB-INF 하위의 파일들은 외부에서 바로 호출이 불가능함

redirect VS forward
리다이렉트는 클라이언트로 응답이 나간 뒤 클라이언트의 요청에 의해 url 경로가 변경되면서 재 응답이 이루어 지기 때문에 클라이언트 인지 가능
포워드는 서버 내부에서 일어나는 호출로 url 이 변경되지 않고 클라이언트가 인지할 수 없음

MVC 패턴을 사용하여 servlet, jsp 로직을 개발할때 아래와 같이 중복적으로 사용되는 로직들이 존재한다.
ex)
    String viewPath = "/WEB-INF/views/new-form.jsp"
    RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
    dispatcher.forward(viewPath);

이렇게 중복적으로 사용되는 코드는 만약 viewPath 를 변경하게 된다면 viewPath 가 입력된 파일들을 일일히 변경해야 한다
이렇게 공통적으로 처리가 어려운 부분을 해결하기 위해 컨트롤러를 호출하기 전 '수문자 역할'을 하는 부분이 필요하다.
즉, 입구를 하나로 만들어 주는 '프론트 컨트롤러(Front Controller) 패턴' 을 도입하여 **공통 처리를 가능하게 할 쉬 있다.
스프링 MVC 의 핵심이 '프론트 컨트롤러 패턴'에 있다.

ControllerV1 인터페이스 컨트롤러를 생성하고 ControllerV1 를 상속받은 자식 컨트롤러들이 process 메소드를 오버라이딩 하게 구조를 만들기
그 후에 프론트 컨트롤러를 생성해서 HashMap 에 자식 컨트롤러들을 호출하여 사용하므로써 일괄성을 가질 수 있도록 개발

** 개발 팁 -- 구조를 변경할때는 구조만 변경하기! 구조 내부를 변경하는것은 구조 변경하여 잘 실행되는것을 확인 후 하는 과정!!

V3
Model 객체를 추가하여 서블릿의 종속성을 제거할 것
뷰 이름의 중복성도 제거할 것 -> 컨트롤러는 뷰의 논리적 이름을 반환하여 프론트 컨트롤러에서 물리이름으로 변경되도록 수정할 것
이렇게 수정하게 되면 변경은 프론트 컨트롤러만 일어나게 됨

V5
FrontControllerServletV5 과정의 순서
1. 클라이언트의 요청이 들어오면 FrontController 에서 요청이 들어온 핸들러를 조회
2. 조회한 핸들러를 처리할 수 있는 어댑터를 어댑터 목록에서 존재하는지 조회
3. 핸들러 어댑터가 존재한다면 해당 핸들러 어댑터를 찾고 어댑터에서 요청을 처리할 컨트롤러를 호출
4. 컨트롤러에서 모델뷰를 받아와서 FrontController 에 전달
5. FrontController 에서 받은 모델뷰를 이용하여 viewResolver 를 통해 응답



