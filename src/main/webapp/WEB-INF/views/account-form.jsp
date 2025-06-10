<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>


<html>
<head><title>Account Form</title></head>
<body>
<h1>Account Form</h1>
<p>
    <strong>Next Available Account ID:</strong> ${nextAccountId}
</p>
<c:choose>

    <c:when test="${account.accountId != null}">
        <form:form modelAttribute="account"
                   action="${pageContext.request.contextPath}/accounts/${account.accountId}"
                   method="post">
            <table>
                <tr>
                    <td>Account Type:</td>
                    <td>
                        <form:select path="accountType">
                            <form:option value="">--Select--</form:option>
                            <form:option value="SAVINGS">SAVINGS</form:option>
                            <form:option value="CHECKING">CHECKING</form:option>
                            <form:option value="CREDIT">CREDIT</form:option>
                            <form:option value="OTHER">OTHER</form:option>
                        </form:select>
                        <form:errors path="accountType" cssClass="error" />
                    </td>
                </tr>

                <tr>
                    <td>Date Opened:</td>
                    <td><form:input path="accountDateOpened" type="date" />
                        <form:errors path="accountDateOpened" cssClass="error" /></td>
                </tr>
                <tr>
                    <td>Balance:</td>
                    <td><form:input path="accountBalance" />
                        <form:errors path="accountDateOpened" cssClass="error" /></td>
                </tr>
                <tr>
                    <td>Customer:</td>
                    <td>
                        <form:select path="accountCustomer" items="${customers}"
                                     itemValue="customerId" itemLabel="customerName">
                            <form:option value="">--Select Customer--</form:option>
                        </form:select>
                        <form:errors path="accountCustomer" cssClass="error" />
                    </td>
                </tr>
                <tr>
                    <td>Branch:</td>
                    <td>
                        <form:select path="accountBranch" items="${branches}"
                                     itemValue="branchId" itemLabel="branchName">
                            <form:option value="">--Select Branch--</form:option>
                        </form:select>
                        <form:errors path="accountBranch" cssClass="error" />
                    </td>
                </tr>
            </table>
            <input type="submit" value="Save"/>
        </form:form>
    </c:when>
    <c:otherwise>
        <form:form modelAttribute="account"
                   action="${pageContext.request.contextPath}/accounts"
                   method="post">
            <table>
                <tr>
                    <td>Account Type:</td>
                    <td>
                        <form:select path="accountType">
                            <form:option value="">--Select--</form:option>
                            <form:option value="SAVINGS">SAVINGS</form:option>
                            <form:option value="CHECKING">CHECKING</form:option>
                            <form:option value="CREDIT">CREDIT</form:option>
                            <form:option value="LOAN">LOAN</form:option>
                            <form:option value="OTHER">OTHER</form:option>
                        </form:select>
                        <form:errors path="accountType" cssClass="error" />
                    </td>
                </tr>
                <tr>
                    <td>Date Opened:</td>
                    <td><form:input path="accountDateOpened" type="date" />
                        <form:errors path="accountDateOpened" cssClass="error" /></td>
                </tr>
                <tr>
                    <td>Balance:</td>
                    <td><form:input path="accountBalance" />
                        <form:errors path="accountBalance" cssClass="error" /></td>
                </tr>
                <tr>
                    <td>Customer:</td>
                    <td>
                        <form:select path="accountCustomer" items="${customers}"
                                     itemValue="customerId" itemLabel="customerName">
                            <form:option value="">--Select Customer--</form:option>
                        </form:select>
                        <form:errors path="accountCustomer" cssClass="error" />
                    </td>
                </tr>
                <tr>
                    <td>Branch:</td>
                    <td>
                        <form:select path="accountBranch" items="${branches}"
                                     itemValue="branchId" itemLabel="branchName">
                            <form:option value="">--Select Branch--</form:option>
                        </form:select>
                        <form:errors path="accountBranch" cssClass="error" />
                    </td>
                </tr>
            </table>
            <input type="submit" value="Save"/>
        </form:form>
    </c:otherwise>
</c:choose>

<a href="${pageContext.request.contextPath}/accounts">Back to List</a>

<style>
    .error {
        color: red;
        font-size: 0.9em;
    }
</style>
</body>
</html>
