<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>尚硅谷會員註冊頁面</title>
    <%-- 靜態包含 base標籤、css樣式、jQuery文件 --%>
    <%@ include file="/pages/common/head.jsp" %>
    <script type="text/javascript">
        // 頁面加載完成之後
        $(function () {
// 綁定用戶名失去焦點事件
            $("#username").blur(function () {
                // 取得輸入的用戶名
                var username = this.value;
                $.getJSON("${pageScope.basePath}UserServlet", "action=existsUsername&username=" + username, function
                    (data) {
                    if (data.existsUsername) {
                        $("span.errorMsg").text(data.msg)
                    } else {
                        $("span.errorMsg").text("用戶名可用!")
                    }
                })
            })
            // 綁定刷新驗證碼
            $("#code_img").click(function () {
                this.src = "${pageScope.basePath}captcha.jpg?d=" + new Date();
            });
            // 給註冊綁定單擊事件
            $("#sub_btn").click(function () {
                // 驗證用戶名：必須由字母，数字下劃線組成，並且長度為3到12位
                //1 獲取用戶名輸入框里的內容
                var usernameText = $("#username").val();
                //2 創建正則表達式對象
                var usernamePatt = /^\w{3,12}$/;
                //3 使用test方法驗證
                if (!usernamePatt.test(usernameText)) {
                    //4 提示用戶結果
                    $("span.errorMsg").text("用戶名不合法！");
                    return false;
                }
                // 驗證密碼：必須由字母，数字下劃線組成，並且長度為3到12位
                //1 獲取用戶名輸入框里的內容
                var passwordText = $("#password").val();
                //2 創建正則表達式對象
                var passwordPatt = /^\w{3,12}$/;
                //3 使用test方法驗證
                if (!passwordPatt.test(passwordText)) {
                    //4 提示用戶結果
                    $("span.errorMsg").text("密碼不合法！");
                    return false;
                }
                // 驗證確認密碼：和密碼相同
                //1 獲取確認密碼內容
                var repwdText = $("#repwd").val();
                //2 和密碼相比較
                if (repwdText != passwordText) {
                    //3 提示用戶
                    $("span.errorMsg").text("確認密碼和密碼不一致！");
                    return false;
                }
                // 郵箱驗證：xxxxx@xxx.com
                //1 獲取郵箱里的內容
                var emailText = $("#email").val();
                //2 創建正則表達式對象
                var emailPatt = /^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/;
                //3 使用test方法驗證是否合法
                if (!emailPatt.test(emailText)) {
                    //4 提示用戶
                    $("span.errorMsg").text("郵箱格式不合法！");
                    return false;
                }
                // 驗證碼：現在只需要驗證用戶已輸入。因為還沒講到服務器。驗證碼生成。
                var codeText = $("#code").val();
                //去掉驗證碼前後空格
                // alert("去空格前：["+codeText+"]")
                codeText = $.trim(codeText);
                // alert("去空格后：["+codeText+"]")
                if (codeText == null || codeText == "") {
                    //4 提示用戶
                    $("span.errorMsg").text("驗證碼不能為空！");
                    return false;
                }
                // 去掉錯誤信息
                $("span.errorMsg").text("");
            });
        });
    </script>
    <style type="text/css">
        .login_form {
            height: 420px;
            margin-top: 25px;
        }
    </style>
</head>
<body>
<div id="login_header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
</div>
<div class="login_banner">
    <div id="l_content">
        <span class="login_word">歡迎註冊</span>
    </div>
    <div id="content">
        <div class="login_form">
            <div class="login_box">
                <div class="tit">
                    <h1>註冊尚硅谷會員</h1>
                    <span class="errorMsg">
                        ${ requestScope.msg }
                    </span>
                </div>
                <div class="form">
                    <form action="UserServlet" method="post">
                        <input type="hidden" name="action" value="regist">
                        <label>用戶名稱：</label>
                        <input class="itxt" type="text" placeholder="請輸入用戶名"
                               value="${requestScope.username}"
                               autocomplete="off" tabindex="1" name="username" id="username"/>
                        <br/>
                        <br/>
                        <label>用戶密碼：</label>
                        <input class="itxt" type="password" placeholder="請輸入密碼"
                               autocomplete="off" tabindex="1" name="password" id="password"/>
                        <br/>
                        <br/>
                        <label>確認密碼：</label>
                        <input class="itxt" type="password" placeholder="確認密碼"
                               autocomplete="off" tabindex="1" name="repwd" id="repwd"/>
                        <br/>
                        <br/>
                        <label>郵箱地址：</label>
                        <input class="itxt" type="text" placeholder="請輸入郵箱地址"
                               value="${requestScope.email}"
                               autocomplete="off" tabindex="1" name="email" id="email"/>
                        <br/>
                        <br/>
                        <label>驗證碼：</label>
                        <input class="itxt" type="text" name="code" style="width: 150px;" id="code"/>
                        <img id="code_img" src="${pageScope.basePath}captcha.jpg" style="float: right;
                        margin-right:
                        40px">
                        <br/>
                        <br/>
                        <input type="submit" value="註冊" id="sub_btn"/>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<%--靜態包含頁腳內容--%>
<%@include file="/pages/common/footer.jsp" %>
</body>
</html>