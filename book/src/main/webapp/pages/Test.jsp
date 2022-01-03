<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:if test="${empty sessionScope.key2}">
</c:if>
<%--1 使用標籤設置 Locale 信息--%>
<fmt:setLocale value="${param.locale}"/>
<%--2 使用標籤設置 baseName--%>
<fmt:setBundle basename="i18n"/>
<%--3 設置切換語言的開關--%>
<a href="?locale=zh_TW">中文</a>
<a href="?locale=en_US">english</a>
<%--4 根據key顯示對應值--%>
<h1><fmt:message key="username"/></h1>
</body>
</html>
