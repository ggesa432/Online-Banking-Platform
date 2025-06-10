<%@ page isELIgnored="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head><title>Role Form</title></head>
<body>
<h1>Role Form</h1>
<p>
    <strong>Next Available Role ID:</strong> ${nextRoleId}
</p>

<form method="post" action="${pageContext.request.contextPath}/roles<c:if test="${not empty role.roleId}">/${role.roleId}</c:if>">
    <input type="hidden" name="roleId" value="${role.roleId}">
    <table>
        <tr>
            <td>Role Name:</td>
            <td>
                <input type="text" name="roleName" value="${role.roleName}"/>
                <!-- Error display using Spring's bind path -->
                <c:if test="${not empty errors}">
                    <span style="color: red;">
                        <c:forEach items="${errors.fieldErrors}" var="error">
                            <c:if test="${error.field == 'roleName'}">${error.defaultMessage}</c:if>
                        </c:forEach>
                    </span>
                </c:if>
            </td>
        </tr>
    </table>
    <input type="submit" value="Save"/>
</form>
<a href="${pageContext.request.contextPath}/roles">Back to Role List</a>
</body>
</html>
