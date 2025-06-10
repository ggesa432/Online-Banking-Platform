<html>
<head><title>Transfer Result</title></head>
<body>
<%@ include file="/WEB-INF/views/navbar.jsp" %>
<h1>Transfer Successful</h1>
<p>You transferred:$${transferAmount} to Account:${toAccountId}</p>
<p>From Account: ${fromAccountId}</p>
<p>New balance in account ${fromAccountId} is: $${fromNewBalance}</p>
<a href="${pageContext.request.contextPath}/transactions/transfer">Make Another Transfer</a>
</body>
</html>