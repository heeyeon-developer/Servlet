<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<!-- 상대경로 사용 == [현재 URL이 속한 계층경로 + /save]
     보통은 절대경로로 함, 지금은 코드를 재활용 하기 위해서 상대경로를 사용 -->
  <form action="save" method="post">
    username : <input type="text" name="username"/>
    age : <input type="text" name="age"/>
    <button type="submit">전송</button>
  </form>
</body>
</html>