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
        <!-- Bootstrap -->
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
        <!-- Fontawesome -->
        <link rel="stylesheet" href="/webjars/font-awesome/css/font-awesome.min.css">
        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!--[if lt IE 9]>
        <script src="/webjars/html5shiv/html5shiv.min.js"></script>
        <script src="/webjars/respond/respond.min.js"></script>
        <![endif]-->
    </head>

    <body>
        <div class="container">
            <nav class="navbar navbar-expand-lg navbar-light bg-light">
                <a class="navbar-brand" href="#">Sample Application</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>


                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav mr-auto">
                        <li class="nav-item">
                            <a class="nav-link" href="hello">Vulnerable</a>
                        </li>
                        <li class="nav-item active">
                            <a class="nav-link" href="#" onclick="return false;">Sanitizing</a>
                            <span class="sr-only">(current)</span>
                        </li>
                    </ul>
                </div>
            </nav>

            <div class="page-header">
                <h1>Welcome to <%=InetAddress.getLocalHost().getHostName()%></h1>
            </div>

            <c:if test="${not empty param}">
                <div class="sub-header">
                    <h3>Current Request Contents</h3>
                </div>
                <div class="table-responsive">
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>Name</th>
                                <th>Value</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>${param['name']}</td>
                                <td>${param['value']}</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </c:if>

            <div class="jumbotron">
                <div class="sub-header">
                    <h3>Submit a request parameter</h3>
                </div>
                <form action="sanitizing" class="form-horizontal" method="post">
                    <div class="form-group">
                        <label class="control-label col-sm-2" for="name">Parameter Name:</label>
                        <div class="col-sm-10">
                            <input class="form-control" name="name" type="text" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-2" for="value">Parameter Value:</label>
                        <div class="col-sm-10">
                            <input class="form-control" name="value" type="text" />
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" class="btn btn-default">Submit</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <!-- Latest compiled and minified JavaScript -->
        <script src="/webjars/jquery/jquery.min.js"></script>
        <script src="/webjars/popper.js/1.12.9-1/umd/popper.min.js"></script>
        <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
    </body>

</html>
