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
        <div>
            <h2>You can&apos;t write that!</h2>
        </div>
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
