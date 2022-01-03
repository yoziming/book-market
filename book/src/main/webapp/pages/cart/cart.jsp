<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>購物車</title>
    <%-- 靜態包含 base標籤、css樣式、jQuery文件 --%>
    <%@ include file="/pages/common/head.jsp" %>
    <script type="text/javascript">
        $(function () {
            // 給刪除的a標籤綁定單擊事件，用於刪除的確認提示操作
            $("a.deleteClass").click(function () {
                return confirm("你確定要刪除【" + $(this).parent().parent().find("td:first").text() + "】?");
                // return false// 阻止元素的默認行為===不提交請求
            });
            $("a.deleteClassB").click(function () {
                return confirm("你確定要清空購物車?");
                // return false// 阻止元素的默認行為===不提交請求
            });
            $("a.deleteClassC").click(function () {
                return confirm("確認下訂單?");
                // return false// 阻止元素的默認行為===不提交請求
            });
            // 給輸入框綁定 onchange內容發生改變事件
            $(".updateCount").change(function () {
                // 獲取商品名稱
                var name = $(this).parent().parent().find("td:first").text();
                var id = $(this).attr('bookId');
                // 獲取商品數量
                var count = this.value;
                // 限制商品數量
                count = Math.min(Math.max(parseInt(count), 1), 10)
                if (isNaN(count)) {
                    count = 1
                }
                if (confirm("你確定要將【" + name + "】商品修改數量為：" + count + " 嗎?\n\n(注:商品數量限制為1~10)")) {
                    //發起請求。給服務器保存修改
                    location.href = "CartServlet?action=updateCount&bookCount=" + count + "&bookId=" + id;
                } else {
                    // defaultValue屬性是表單項Dom對象的屬性。它表示默認的value屬性值。
                    this.value = this.defaultValue;
                }
            });
        });
    </script>
</head>
<body>
<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
    <span class="wel_word">購物車</span>
    <div>
        <%--靜態包含，登入 成功之後的菜單 --%>
        <%@ include file="/pages/common/login_success_menu.jsp" %>
    </div>
</div>
<div id="main">
    <table>
        <tr>
            <td>商品名稱</td>
            <td>數量</td>
            <td>單價</td>
            <td>金額</td>
            <td>操作</td>
        </tr>
        <c:if test="${empty sessionScope.cart || sessionScope.cart.totalPrice==0}">
            <tr>
                <td colspan="4">當前購物車為空!</td>
                <td><a href="">去購物</a></td>
            </tr>
        </c:if>
        <c:forEach items="${sessionScope.cart.items}" var="entry">
            <tr>
                <td>${entry.value.name}</td>
                <td>
                    <input class="updateCount" style="width: 30px;"
                           bookId="${entry.value.id}"
                           type="text" value="${entry.value.count}">
                </td>
                <td>${entry.value.price}</td>
                <td>${entry.value.totalPrice}</td>
                <td><a class="deleteClass" href="CartServlet?action=deleteItem&bookId=${entry.value.id}">刪除</a></td>
            </tr>
        </c:forEach>
    </table>
    <c:if test="${sessionScope.cart.totalPrice>0}">
        <div class="cart_info">
            <span class="cart_span">購物車中共有<span class="b_count">${sessionScope.cart.totalCount}</span>件商品</span>
            <span class="cart_span">總金額<span class="b_price">${sessionScope.cart.totalPrice}</span>元</span>
            <span class="cart_span"><a class="deleteClassB" href="CartServlet?action=clear">清空購物車</a></span>
            <span class="cart_span"><a class="deleteClassC"
                                       href="OrderServlet?action=createOrder">確認下單</a></span>
        </div>
    </c:if>
</div>
<%--靜態包含頁腳內容--%>
<%@include file="/pages/common/footer.jsp" %>
</body>
</html>