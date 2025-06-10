<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Login</title>
    <!-- Optionally, include any CSS/Bootstrap -->
</head>
<body>
<h1>Login</h1>
<!--
  Spring Security expects a POST to /login with fields named 'username' and 'password'.
  The action is set to /login, method=POST.
-->
<form method="post" action="${pageContext.request.contextPath}/login">
    <div>
        <label for="username">Username:</label>
        <input type="text" name="username" id="username"/>
    </div>
    <div>
        <label for="password">Password:</label>
        <input type="password" name="password" id="password"/>
    </div>
    <button type="submit">Login</button>
</form>
</body>
</html>
