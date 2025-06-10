<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head><title>Transfer</title></head>
<body>
<%@ include file="/WEB-INF/views/navbar.jsp" %>
<h1>Transfer</h1>
<form:form modelAttribute="transferRequest"
           action="${pageContext.request.contextPath}/transactions/transfer"
           method="post">
    <table>
        <tr>
            <td>From Account ID: </td>
            <td><form:input path="fromAccountId"/>
                <form:errors path="fromAccountId" cssClass="error"/></td>
        </tr>
        <tr>
            <td>To Account ID:</td>
            <td><form:input path="toAccountId"/>
                <form:errors path="toAccountId" cssClass="error"/></td>
        </tr>
        <tr>
            <td>Amount:</td>
            <td><form:input path="amount"/>
                <form:errors path="amount" cssClass="error"/></td>
        </tr>
    </table>
    <input type="submit" value="Transfer"/>
</form:form>
<style>
    .error {
        color: red;
        font-size: 0.9em;
    }
</style>
</body>
</html>

