<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Branch Form</title>
</head>
<body>
<h1>Branch Form</h1>
<p>
    <strong>Next Available Branch ID:</strong> ${nextBranchId}
</p>

<form action="${pageContext.request.contextPath}/branches${branch.branchId != null ? '/' : ''}${branch.branchId != null ? branch.branchId : ''}" method="post">
    <table>
        <tr>
            <td>Name:</td>
            <td><input type="text" name="branchName" value="${branch.branchName}"/></td>
        </tr>
        <tr>
            <td>Address Line 1:</td>
            <td><input type="text" name="branchAddress.addressLine1" value="${branch.branchAddress.addressLine1}"/></td>
        </tr>
        <tr>
            <td>Address Line 2:</td>
            <td><input type="text" name="branchAddress.addressLine2" value="${branch.branchAddress.addressLine2}"/></td>
        </tr>
        <tr>
            <td>City:</td>
            <td><input type="text" name="branchAddress.city" value="${branch.branchAddress.city}"/></td>
        </tr>
        <tr>
            <td>State:</td>
            <td><input type="text" name="branchAddress.state" value="${branch.branchAddress.state}"/></td>
        </tr>
        <tr>
            <td>Zip Code:</td>
            <td><input type="text" name="branchAddress.zipCode" value="${branch.branchAddress.zipCode}"/></td>
        </tr>
        <tr>
            <td>Country:</td>
            <td><input type="text" name="branchAddress.country" value="${branch.branchAddress.country}"/></td>
        </tr>
        <tr>
            <td>Phone:</td>
            <td><input type="text" name="branchAddress.phoneNumber" value="${branch.branchAddress.phoneNumber}"/></td>
        </tr>
    </table>
    <input type="submit" value="Save"/>
</form>


<a href="${pageContext.request.contextPath}/branches">Back to List</a>

</body>
</html>
