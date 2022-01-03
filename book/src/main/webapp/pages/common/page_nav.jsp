<%--
  Created by IntelliJ IDEA.
  User: Yoziming
  Date: 2021/12/31
  Time: 下午 09:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="page_nav">
    共${requestScope.page.pageTotalCount}條記錄
    <c:if test="${requestScope.page.pageNo>1}">
        <a href="${requestScope.page.url}&pageNo=1">首頁</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo-1}">上一頁</a>
    </c:if>
    第【${requestScope.page.pageNo}】頁
    <c:if test="${requestScope.page.pageNo<requestScope.page.pageTotal}">
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo+1}">下一頁</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageTotal}">末頁</a>
    </c:if>
    共${requestScope.page.pageTotal}頁
    到第<input value="${param.pageNo}" name="pn" id="pn_input"/>頁
    <input id="searchPageBtn" type="button" value="確定">
    <script type="text/javascript">
        $(function () {
            $("#searchPageBtn").click(function () {
                var pageNo = $("#pn_input").val();
                var pageTotal =${requestScope.page.pageTotal};
                // 限制pageNo，比1小就是1，比pageTotal大就是pageTotal
                pageNo = Math.max(1, Math.min(pageNo, pageTotal));
                location.href = "${pageScope.basePath}${requestScope.page.url}&pageNo=" + pageNo;
            });
        });
    </script>
</div>
