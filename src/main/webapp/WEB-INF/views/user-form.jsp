<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
<head><title>User Form</title></head>
<body>
<h1>User Form</h1>
<p>
    <strong>Next Available User ID:</strong> ${nextUserId}
</p>


<form:form modelAttribute="user" method="post"
           action="${pageContext.request.contextPath}/users${user.userId != null ? '/' : ''}${user.userId != null ? user.userId : ''}">
    <form:hidden path="userId" />

<table>
    <tr>
        <td>Username:</td>
        <td>
            <c:choose>
                <c:when test="${pageContext.request.isUserInRole('ROLE_USER')}">
                    <!-- Display the username as disabled and include a hidden input for submission -->
                    <form:input path="username" disabled="true" />
                    <form:hidden path="username" />
                </c:when>
                <c:otherwise>
                    <!-- Editable username for other roles -->
                    <form:input path="username" />
                </c:otherwise>
            </c:choose>
            <form:errors path="username" cssClass="error" />
        </td>
    </tr>

    <tr>
        <td>Password:</td>
        <td>
            <c:choose>
                <c:when test="${not empty user.userId}">
                    <%-- Editing existing user: Ensure field is empty --%>
                    <form:input path="password" type="password"
                                placeholder="Leave blank to keep existing password"
                                autocomplete="new-password" />
                    <form:errors path="password" cssClass="error"/>
                </c:when>
                <c:otherwise>
                    <%-- Creating new user: No changes --%>
                    <form:input path="password" type="password"
                                placeholder="Enter password"
                                autocomplete="new-password" />
                    <form:errors path="password" cssClass="error"/>
                </c:otherwise>
            </c:choose>
        </td>
    </tr>

    <tr>
        <td>Email:</td>
        <td>
            <form:input path="email" />
            <form:errors path="email" cssClass="error" />
        </td>
    </tr>
    <tr>
        <td>Roles:</td>
        <td>
            <c:choose>
                <c:when test="${pageContext.request.isUserInRole('ROLE_ADMIN') or pageContext.request.isUserInRole('ROLE_MANAGER')}">
                    <c:forEach var="r" items="${allRoles}">
                        <input type="checkbox" name="roleIds" value="${r.roleId}"
                                <c:forEach var="ur" items="${user.roles}">
                                    <c:if test="${ur.roleId == r.roleId}">checked</c:if>
                                </c:forEach> />
                        ${r.roleName}<br/>
                    </c:forEach>
                    <form:errors path="roles" cssClass="error" />
                </c:when>
                <c:otherwise>
                    <c:forEach var="r" items="${allRoles}">
                        <c:forEach var="ur" items="${user.roles}">
                            <c:if test="${ur.roleId == r.roleId}">
                                <input type="checkbox" value="${r.roleId}" checked disabled />
                                <input type="hidden" name="roleIds" value="${r.roleId}" />
                            </c:if>
                        </c:forEach>
                        ${r.roleName}<br/>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </td>
    </tr>


</table>
    <input type="submit" value="Save"/>
</form:form>
<a href="${pageContext.request.contextPath}/users">Back to User List</a>
<style>
    .error {
        color: red;
    }
</style>
</body>
</html>