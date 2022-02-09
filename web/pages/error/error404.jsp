<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>出錯啦！</title>
	<%-- 靜態包含 base標籤、css樣式、jQuery文件 --%>
	<%@ include file="/pages/common/head.jsp"%>

</head>
<body>
<div style="text-align:center; color:red;"><h2>很抱歉，您訪問的頁面不存在，或已經被刪除</h2></div>
<div style="text-align:center; color:red;"><h1><a href="https://www.google.com">查找原因</a></h1></div>
<div style="text-align:center; color:red;"><h1><a href="index.jsp">返回首頁</a></h1></div>

</body>
</html>