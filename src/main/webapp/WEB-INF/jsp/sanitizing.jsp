<%@ page import="java.net.InetAddress" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>${pageContext.servletContext.servletContextName}</title>
        <base href="${fn:replace(pageContext.request.requestURL, pageContext.request.requestURI, '')}${pageContext.request.contextPath}/" />
    </head>
    <body>
        <h1>
            Welcome to <%=InetAddress.getLocalHost().getHostName()%>
        </h1>
        <c:if test="${not empty param}">
            <div>
                <h3>Request Contents</h3>
                <table>
                    <thead>
                        <tr>
                            <th>Name</th>
                            <th>Value</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${param}" var="requestParam">
                            <!-- ${requestParam.key}: ${requestParam.value} -->
                        </c:forEach>
                        <tr><td><%=request.getParameter("name")%></td><td><%=request.getParameter("value")%></td></tr>
                    </tbody>
                </table>
            </div>
        </c:if>
        <div>
            <h3>Add an attribute to the session</h3>
            <form action="app/views/sanitizing" method="post">
                <input name="name" type="text" />
                <input name="value" type="text" />
                <input type="submit" />
            </form>
        </div>
    </body>
</html>
