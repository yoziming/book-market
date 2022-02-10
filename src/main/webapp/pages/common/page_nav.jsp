<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--分頁條的開始--%>
<div id="page_nav">
    <%--大於首頁，才顯示--%>
    <c:if test="${requestScope.page.pageNo > 1}">
        <a href="${ requestScope.page.url }&pageNo=1">首頁</a>
        <a href="${ requestScope.page.url }&pageNo=${requestScope.page.pageNo-1}">上一頁</a>
    </c:if>
    <%--頁碼輸出的開始--%>
    <c:choose>
        <%--情況1：如果總頁碼小於等於5的情況，頁碼的範圍是：1-總頁碼--%>
        <c:when test="${ requestScope.page.pageTotal <= 5 }">
            <c:set var="begin" value="1"/>
            <c:set var="end" value="${requestScope.page.pageTotal}"/>
        </c:when>
        <%--情況2：總頁碼大於5的情況--%>
        <c:when test="${requestScope.page.pageTotal > 5}">
            <c:choose>
                <%--小情況1：當前頁碼為前面3個：1，2，3的情況，頁碼範圍是：1-5.--%>
                <c:when test="${requestScope.page.pageNo <= 3}">
                    <c:set var="begin" value="1"/>
                    <c:set var="end" value="5"/>
                </c:when>
                <%--小情況2：當前頁碼為最後3個，8，9，10，頁碼範圍是：總頁碼減4 - 總頁碼--%>
                <c:when test="${requestScope.page.pageNo > requestScope.page.pageTotal-3}">
                    <c:set var="begin" value="${requestScope.page.pageTotal-4}"/>
                    <c:set var="end" value="${requestScope.page.pageTotal}"/>
                </c:when>
                <%--小情況3：4，5，6，7，頁碼範圍是：當前頁碼減2 - 當前頁碼加2--%>
                <c:otherwise>
                    <c:set var="begin" value="${requestScope.page.pageNo-2}"/>
                    <c:set var="end" value="${requestScope.page.pageNo+2}"/>
                </c:otherwise>
            </c:choose>
        </c:when>
    </c:choose>

    <c:forEach begin="${begin}" end="${end}" var="i">
        <c:if test="${i == requestScope.page.pageNo}">
            【${i}】
        </c:if>
        <c:if test="${i != requestScope.page.pageNo}">
            <a href="${ requestScope.page.url }&pageNo=${i}">${i}</a>
        </c:if>
    </c:forEach>
    <%--頁碼輸出的結束--%>


    <%-- 如果已經 是最後一頁，則不顯示下一頁，末頁 --%>
    <c:if test="${requestScope.page.pageNo < requestScope.page.pageTotal}">
        <a href="${ requestScope.page.url }&pageNo=${requestScope.page.pageNo+1}">下一頁</a>
        <a href="${ requestScope.page.url }&pageNo=${requestScope.page.pageTotal}">末頁</a>
    </c:if>

    共${ requestScope.page.pageTotal }頁，${ requestScope.page.pageTotalCount }條記錄
    到第<input value="${param.pageNo}" name="pn" id="pn_input"/>頁
    <input id="searchPageBtn" type="button" value="確定">

    <script type="text/javascript">

        $(function () {
            // 跳到指定的頁碼
            $("#searchPageBtn").click(function () {

                var pageNo = $("#pn_input").val();

                <%--var pageTotal = ${requestScope.page.pageTotal};--%>
                <%--alert(pageTotal);--%>

                // javaScript語言中提供了一個location地址欄物件
                // 它有一個屬性叫href.它可以獲取瀏覽器地址欄中的地址
                // href屬性可讀，可寫
                location.href = "${pageScope.basePath}${ requestScope.page.url }&pageNo=" + pageNo;

            });
        });

    </script>

</div>
<%--分頁條的結束--%>


