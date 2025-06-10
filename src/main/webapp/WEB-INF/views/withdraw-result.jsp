<html>
<head><title>Withdrawal Result</title></head>
<body>
<%@ include file="/WEB-INF/views/navbar.jsp" %>
<h1>Withdrawal Successful</h1>
<p>You withdrew: $${withdrawnAmount}</p>
<p>Your new balance is: $${newBalance}</p>
<a href="${pageContext.request.contextPath}/transactions/withdraw">Make Another Withdrawal</a>
</body>
</html>
