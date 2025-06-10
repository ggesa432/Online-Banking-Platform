<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head><title>Withdraw</title></head>
<body>
<%@ include file="/WEB-INF/views/navbar.jsp" %>
<h1>Withdraw</h1>
<form:form modelAttribute="withdrawRequest"
           action="${pageContext.request.contextPath}/transactions/withdraw"
           method="post">
    <table>
        <tr>
            <td>Account ID: </td>
                <td><form:input path="accountId"/>
                    <form:errors path="accountId" cssClass="error"/></td>
        </tr>
        <tr>
            <td>Amount: </td>
                <td><form:input path="amount"/>
                    <form:errors path="amount" cssClass="error"/></td>
        </tr>
    </table>
    <input type="submit" value="Withdraw"/>
</form:form>
<style>
    .error {
        color: red;
        font-size: 0.9em;
    }
</style>
</body>
</html>
