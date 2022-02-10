<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Security-Policy" content="upgrade-insecure-requests">
    <meta charset="UTF-8">
    <title>會員註冊</title>
    <%-- 靜態包含 base標籤、css樣式、jQuery文件 --%>
    <%@ include file="/pages/common/head.jsp" %>
    <script type="text/javascript">
        // 頁面加載完成之後
        $(function () {
            $("#username").blur(function () {
                // 1 獲取用戶名
                var username = this.value;
                if (username == "") {
                    $("span.errorMsg").text("用戶不可為空！");
                }else {
                    $.getJSON("${pageScope.basePath}userServlet", "action=ajaxExistsUsername&username=" + username, function (data) {
                        if (data.existsUsername) {
                            $("span.errorMsg").text("用戶名已存在！");
                        } else {
                            $("span.errorMsg").text("用戶名可用");
                        }
                    });
                }
            });
            // 給驗證碼的圖片，綁定單擊事件
            $("#code_img").click(function () {
                // 在事件響應的function函數中有一個this物件。這個this物件，是當前正在響應事件的dom物件
                // src屬性表示驗證碼img標籤的 圖片路徑。它可讀，可寫
                // alert(this.src);
                this.src = "${basePath}kaptcha.jpg?d=" + new Date();
            });
            // 給註冊綁定單擊事件
            $("#sub_btn").click(function () {
                // 驗證用戶名：必須由字母、數字和下劃線組成，並且長度為3到12位
                // 1 獲取用戶名輸入框里的內容
                var usernameText = $("#username").val();
                // 2 創建正則表達式物件
                var usernamePatt = /^\w{3,12}$/;
                // 3 使用test方法驗證
                if (!usernamePatt.test(usernameText)) {
                    //4 提示用戶結果
                    $("span.errorMsg").text("用戶名必須是3到12位英數字");
                    return false;
                }
                // 驗證密碼：必須由字母、數字和下劃線組成，並且長度為3到12位
                // 1 獲取用戶名輸入框里的內容
                var passwordText = $("#password").val();
                // 2 創建正則表達式物件
                var passwordPatt = /^\w{3,12}$/;
                // 3 使用test方法驗證
                if (!passwordPatt.test(passwordText)) {
                    //4 提示用戶結果
                    $("span.errorMsg").text("密碼必須是3到12位英數字");
                    return false;
                }
                // 驗證確認密碼：和密碼相同
                // 1 獲取確認密碼內容
                var repwdText = $("#repwd").val();
                // 2 和密碼相比較
                if (repwdText != passwordText) {
                    // 3 提示用戶
                    $("span.errorMsg").text("確認密碼和密碼不一致！");
                    return false;
                }
                // 電子信箱驗證：xxxxx@xxx.com
                // 1 獲取電子信箱里的內容
                var emailText = $("#email").val();
                // 2 創建正則表達式物件
                var emailPatt = /^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/;
                // 3 使用test方法驗證是否合法
                if (!emailPatt.test(emailText)) {
                    //4 提示用戶
                    $("span.errorMsg").text("電子信箱格式格式錯誤！");
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
    <%--<img class="logo_img" alt="" src="static/img/logo.gif" >--%>
    <span class="wel_word">柚子書城</span>
</div>
<div class="login_banner">
    <div id="l_content">
        <span class="login_word">歡迎註冊</span>
        <div style="text-align:center; color:red;"><h2><a href="pages/user/login.jsp">已有帳號? 立即登入</a></h2></div>
    </div>
    <div id="content">
        <div class="login_form">
            <div class="login_box">
                <div class="tit">
                    <h1>註冊會員</h1>
                    <span class="errorMsg">
                        ${ requestScope.msg }
                    </span>
                </div>
                <div class="form">
                    <form action="userServlet" method="post">
                        <input type="hidden" name="action" value="regist">
                        <label>用戶名稱：</label>
                        <input class="itxt" type="text" placeholder="請輸入用戶名(3-12位)"
                               value="${requestScope.username}"
                               autocomplete="off" tabindex="1" name="username" id="username"/>
                        <br/>
                        <br/>
                        <label>用戶密碼：</label>
                        <input class="itxt" type="password" placeholder="請輸入密碼(3-12位)"
                               autocomplete="off" tabindex="1" name="password" id="password"/>
                        <br/>
                        <br/>
                        <label>確認密碼：</label>
                        <input class="itxt" type="password" placeholder="確認密碼"
                               autocomplete="off" tabindex="1" name="repwd" id="repwd"/>
                        <br/>
                        <br/>
                        <label>電子信箱：</label>
                        <input class="itxt" type="text" placeholder="請輸入電子信箱地址"
                               value="${requestScope.email}"
                               autocomplete="off" tabindex="1" name="email" id="email"/>
                        <br/>
                        <br/>
                        <label>驗證碼：</label>
                        <input class="itxt" type="text" name="code" style="width: 80px;" id="code"/>
                        <img id="code_img" alt="" src="kaptcha.jpg"
                             style="float: right; margin-right: 40px; width: 110px; height: 30px;">
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