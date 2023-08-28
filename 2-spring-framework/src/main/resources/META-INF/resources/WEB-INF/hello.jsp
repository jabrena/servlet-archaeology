<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Hello</title>
</head>
<body>
    <div id="wrapper">
        <h1>Hello Servlet</h1>
        <form action="<c:url value="/hello" />">
            <input name="name" placeholder="Name"/>
            <input type="submit"/>
        </form>
        <p><c:out value="${greeting}"/></p>
    </div>
</body>
</html>