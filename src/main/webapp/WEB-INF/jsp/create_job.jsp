<!DOCTYPE HTML>
<%@ include file="/taglibs.jsp"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<html lang="en">
    <head>
    	<title>Tambah Job</title>
    </head>
    <body>
        <h3>Tambah Job</h3>
        <form:form method="POST" action="${path}/submit" modelAttribute="schedulerJobInfo">
             <table>
                <tr>
                    <td><form:label path="jobName">Nama</form:label></td>
                    <td><form:input path="jobName"/></td>
                </tr>
                <tr>
                    <td><form:label path="jobGroup">Group</form:label></td>
                    <td><form:input path="jobGroup"/></td>
                </tr>
                <tr>
                    <td><form:label path="jobClass">Class</form:label></td>
                    <td><form:input path="jobClass"/></td>
                </tr>
                <tr>
                    <td><form:label path="cronExpression">Cron Expression</form:label></td>
                    <td><form:input path="cronExpression"/></td>
                </tr>
                <tr>
                    <td><form:label path="repeatTime">Repeat Time</form:label></td>
                    <td><form:input path="repeatTime"/></td>
                </tr>
                <tr>
                    <td><form:label path="url">URL</form:label></td>
                    <td><form:input path="url"/></td>
                </tr>
                <tr>
                    <td><form:label path="cronJob">Cron Job</form:label></td>
                    <td><form:input path="cronJob"/></td>
                </tr>
                <tr>
                    <td><form:label path="httpMethod">Http Method</form:label></td>
                    <td><form:input path="httpMethod"/></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Submit"/></td>
                </tr>
            </table>
        </form:form>
    </body>
</html>