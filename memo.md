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