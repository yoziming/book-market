<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>書城首頁</title>
    <%-- 靜態包含 base標籤、css樣式、jQuery文件 --%>
    <%@ include file="/pages/common/head.jsp" %>
    <Script type="text/javascript">
        $(function () {
            // 給加入購物車綁定點擊事件
            $("button.addToCar").click(function () {
                var bookId = $(this).attr("bookId")
                location.href = "${pageScope.basePath}CartServlet?action=addItem&bookId=" + bookId;
            })
        })
    </Script>
</head>
<body>
<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
    <span class="wel_word">網上書城</span>
    <div>
        <c:if test="${empty sessionScope.user}">
            <a href="pages/user/login.jsp">登入</a> |
            <a href="pages/user/regist.jsp">註冊</a> &nbsp;&nbsp;
        </c:if>
        <c:if test="${not empty sessionScope.user}">
            <%@include file="/pages/common/login_success_menu.jsp" %>
        </c:if>
        <a href="pages/cart/cart.jsp">購物車</a>
        <a href="pages/manager/manager.jsp">後台管理</a>
    </div>
</div>
<div id="main">
    <div id="book">
        <div class="book_cond">
            <form action="IndexServlet" method="get">
                <input type="hidden" name="action" value="pageByPrice"/>
                價格：
                <input id="min" type="text" name="min" value="${param.min}"> 元 -
                <input id="max" type="text" name="max" value="${param.max}"> 元
                <input type="submit" value="查詢"/>
            </form>
        </div>
        <div style="text-align: center">
            <c:if test="${empty sessionScope.cart.items}">
                <span style="color: red">當前購物車為空<br/></span>
                <div>
                    開始選購喜歡的商品吧!
                </div>
            </c:if>
            <c:if test="${not empty sessionScope.cart.items}">
                <span id="cartTotalCount">您的購物車中有${sessionScope.cart.totalCount}件商品</span><span>&nbsp;&nbsp;</span><a
                    href="pages/cart/cart.jsp">去結帳</a>
                <div>
                    您剛剛將<span id="lastToCart" style="color: red">${sessionScope.lastAddBook}</span>加入到了購物車中
                </div>
            </c:if>
        </div>
        <c:forEach items="${requestScope.page.items}" var="book">
            <div class="b_list">
                <div class="img_div">
                    <img class="book_img" alt="書籍圖片" src="${book.imgPath}"/>
                </div>
                <div class="book_info">
                    <div class="book_name">
                        <span class="sp1">書名:</span>
                        <span class="sp2">${book.name}</span>
                    </div>
                    <div class="book_author">
                        <span class="sp1">作者:</span>
                        <span class="sp2">${book.author}</span>
                    </div>
                    <div class="book_price">
                        <span class="sp1">價格:</span>
                        <span class="sp2">$${book.price}</span>
                    </div>
                    <div class="book_sales">
                        <span class="sp1">銷量:</span>
                        <span class="sp2">${book.sales}</span>
                    </div>
                    <div class="book_amount">
                        <span class="sp1">庫存:</span>
                        <span class="sp2">${book.stock}</span>
                    </div>
                    <div class="book_add">
                        <button bookId="${book.id}" class="addToCar">加入購物車</button>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
    <%--靜態包含分頁導航--%>
    <%@include file="/pages/common/page_nav.jsp" %>
</div>
<%--靜態包含頁腳內容--%>
<%@include file="/pages/common/footer.jsp" %>
</body>
</html>