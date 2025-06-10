<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Deposit Result</title>
</head>
<body>
<%@ include file="/WEB-INF/views/navbar.jsp" %>
<h1>Deposit Successful</h1>

<p>You deposited: $${depositAmount}</p>
<p>Your new balance is: $${newBalance}</p>

<a href="${pageContext.request.contextPath}/transactions/deposit">Make Another Deposit</a>
</body>
</html>
