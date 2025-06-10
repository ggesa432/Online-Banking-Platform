<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Access Denied</title>
</head>
<body>
<div class="container">
    <h1>Access Denied</h1>
    <p>Sorry, you do not have permission to access this page.</p>
    <p>
        <a href="<c:url value='/' />">Go back to the homepage</a> or
        <a href="<c:url value='/login' />">login</a> with a different account.
    </p>
</div>
</body>
</html>
