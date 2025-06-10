<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Home</title>
</head>
<body>
<%@ include file="/WEB-INF/views/navbar.jsp" %>
<h3>
<c:choose>
    <c:when test="${pageContext.request.userPrincipal != null}">
        <!-- The user is logged in -->
        <p>Welcome To OnlineBanking System !</p>
        <p>Dear: ${pageContext.request.userPrincipal.name}</p>
        <p>
            <%
                boolean isAdmin = request.isUserInRole("ROLE_ADMIN");
                boolean isManager = request.isUserInRole("ROLE_MANAGER");

                if (isAdmin) {
                    out.print("Role: Admin");
                } else if (isManager) {
                    out.print("Role: Manager");
                } else {
                    out.print("Role: User");
                }
            %></p>


    </c:when>
    <c:otherwise>
        <!-- The user is not logged in -->
        <p>You are not logged in. <a href="${pageContext.request.contextPath}/login">Login</a></p>
    </c:otherwise>
</c:choose>
</h3>
</body>
</html>
