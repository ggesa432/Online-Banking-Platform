<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>Customer Form</title>
</head>
<body>
<h1>Customer Form</h1>
<p>
    <strong>Next Available Customer ID:</strong> ${nextCustomerId}
</p>

<c:choose>
    <c:when test="${customer.customerId != null}">
        <form:form modelAttribute="customer"
                   action="${pageContext.request.contextPath}/customers/${customer.customerId}"
                   method="post">
            <table>
                <tr>
                    <td>Customer Name:</td>
                    <td>
                        <c:choose>
                            <c:when test="${pageContext.request.isUserInRole('ROLE_USER')}">
                                <input type="text" name="customerName" value="${customer.customerName}" disabled/>
                            </c:when>
                            <c:otherwise>
                                <form:input path="customerName" />
                            </c:otherwise>
                        </c:choose>
                        <form:errors path="customerName" cssClass="error" />
                    </td>
                </tr>
                <tr>
                    <td>DOB:</td>
                    <td><form:input path="customerDOB" type="date"/>
                        <form:errors path="customerDOB" cssClass="error" /></td>
                </tr>
                <tr>
                    <td>Gender:</td>
                    <td>
                        <form:select path="customerGender">
                            <form:option value="">--Select--</form:option>
                            <form:option value="MALE">Male</form:option>
                            <form:option value="FEMALE">Female</form:option>
                            <form:option value="OTHER">Other</form:option>
                        </form:select>
                        <form:errors path="customerGender" cssClass="error" />
                    </td>
                </tr>

                <!-- Address Fields -->
                <tr>
                    <td>Address Line 1:</td>
                    <td><form:input path="customerAddress.addressLine1"/>
                        <form:errors path="customerAddress.addressLine1" cssClass="error" /></td>
                </tr>
                <tr>
                    <td>Address Line 2:</td>
                    <td><form:input path="customerAddress.addressLine2"/></td>
                </tr>
                <tr>
                    <td>City:</td>
                    <td><form:input path="customerAddress.city"/>
                        <form:errors path="customerAddress.city" cssClass="error" /></td>
                </tr>
                <tr>
                    <td>State:</td>
                    <td><form:input path="customerAddress.state"/>
                        <form:errors path="customerAddress.state" cssClass="error" /></td>
                </tr>
                <tr>
                    <td>Zip Code:</td>
                    <td><form:input path="customerAddress.zipCode"/>
                        <form:errors path="customerAddress.zipCode" cssClass="error" /></td>
                </tr>
                <tr>
                    <td>Country:</td>
                    <td><form:input path="customerAddress.country"/>
                        <form:errors path="customerAddress.country" cssClass="error" /></td>
                </tr>
                <tr>
                    <td>Phone:</td>
                    <td><form:input path="customerAddress.phoneNumber"/>
                        <form:errors path="customerAddress.phoneNumber" cssClass="error" /></td>
                </tr>

                <tr>
                    <td>SSN:</td>
                    <td>
                        <form:input path="customerSSN" />
                        <form:errors path="customerSSN" cssClass="error" />
                    </td>
                </tr>

                <!-- Assigned User -->
                <tr>
                    <td>Assigned User:</td>
                    <td>
                        <form:select path="user">
                            <form:option value="">-- Select User --</form:option>
                            <form:options items="${allUsers}"
                                          itemValue="userId"
                                          itemLabel="username" />
                        </form:select>
                        <form:errors path="user" cssClass="error" />
                    </td>
                </tr>
            </table>
            <input type="submit" value="Save"/>
        </form:form>
    </c:when>

    <c:otherwise>
        <form:form modelAttribute="customer"
                   action="${pageContext.request.contextPath}/customers"
                   method="post">
            <table>
                <tr>
                    <td>Customer Name:</td>
                    <td>
                        <c:choose>
                            <c:when test="${pageContext.request.isUserInRole('ROLE_USER')}">
                                <input type="text" name="customerName" value="${customer.customerName}" disabled/>
                            </c:when>
                            <c:otherwise>
                                <form:input path="customerName" />
                            </c:otherwise>
                        </c:choose>
                        <form:errors path="customerName" cssClass="error" />
                    </td>
                </tr>
                <tr>
                    <td>DOB:</td>
                    <td><form:input path="customerDOB" type="date"/>
                        <form:errors path="customerDOB" cssClass="error" /></td>
                </tr>
                <tr>
                    <td>Gender:</td>
                    <td>
                        <form:select path="customerGender">
                            <form:option value="">--Select--</form:option>
                            <form:option value="MALE">Male</form:option>
                            <form:option value="FEMALE">Female</form:option>
                            <form:option value="OTHER">Other</form:option>
                        </form:select>
                        <form:errors path="customerGender" cssClass="error" />
                    </td>
                </tr>

                <!-- Address Fields -->
                <tr>
                    <td>Address Line 1:</td>
                    <td><form:input path="customerAddress.addressLine1"/>
                        <form:errors path="customerAddress.addressLine1" cssClass="error" /></td>
                </tr>
                <tr>
                    <td>Address Line 2:</td>
                    <td><form:input path="customerAddress.addressLine2"/></td>
                </tr>
                <tr>
                    <td>City:</td>
                    <td><form:input path="customerAddress.city"/>
                        <form:errors path="customerAddress.city" cssClass="error" /></td>
                </tr>
                <tr>
                    <td>State:</td>
                    <td><form:input path="customerAddress.state"/>
                        <form:errors path="customerAddress.state" cssClass="error" /></td>
                </tr>
                <tr>
                    <td>Zip Code:</td>
                    <td><form:input path="customerAddress.zipCode"/>
                        <form:errors path="customerAddress.zipCode" cssClass="error" /></td>
                </tr>
                <tr>
                    <td>Country:</td>
                    <td><form:input path="customerAddress.country"/>
                        <form:errors path="customerAddress.country" cssClass="error" /></td>
                </tr>
                <tr>
                    <td>Phone:</td>
                    <td><form:input path="customerAddress.phoneNumber"/>
                        <form:errors path="customerAddress.phoneNumber" cssClass="error" /></td>
                </tr>

                <tr>
                    <td>SSN:</td>
                    <td><form:input path="customerSSN"/>
                        <form:errors path="customerSSN" cssClass="error" /></td>
                </tr>

                <!-- Assigned User -->
                <tr>
                    <td>Assigned User:</td>
                    <td>
                        <form:select path="user">
                            <form:option value="">-- Select User --</form:option>
                            <form:options items="${allUsers}"
                                          itemValue="userId"
                                          itemLabel="username" />
                        </form:select>
                        <form:errors path="user" cssClass="error" />
                    </td>
                </tr>
            </table>
            <input type="submit" value="Save"/>
        </form:form>
    </c:otherwise>
</c:choose>

<a href="${pageContext.request.contextPath}/customers">Back to List</a>

<style>
    .error {
        color: red;
        font-size: 0.9em;
    }
</style>
</body>
</html>

