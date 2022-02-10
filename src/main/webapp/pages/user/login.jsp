<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>會員登入</title>
	<%-- 靜態包含 base標籤、css樣式、jQuery文件 --%>
	<%@ include file="/pages/common/head.jsp"%>
</head>
<body>
		<div id="login_header">
			<%--<img class="logo_img" alt="" src="static/img/logo.gif" >--%>
			<span class="wel_word">柚子書城</span>
		</div>

			<div class="login_banner">

				<div id="l_content">
					<span class="login_word">歡迎登入</span>
					<div style="text-align:center; color:red;"><h2><a href="pages/user/regist.jsp">沒有帳號? 立即註冊</a> </h2></div>
				</div>

				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>書城會員</h1>
								<a href="index.jsp">返回首頁</a>
							</div>
							<div class="msg_cont">
								<b></b>
								<span class="errorMsg">
									${ empty requestScope.msg ? "請輸入用戶名和密碼":requestScope.msg }
								</span>
							</div>
							<div class="form">
								<form action="userServlet" method="post">
									<input type="hidden" name="action" value="login" />
									<label>用戶名稱：</label>
									<input class="itxt" type="text" placeholder="請輸入用戶名"
										   autocomplete="off" tabindex="1" name="username"
										   value="${requestScope.username}" />
									<br />
									<br />
									<label>用戶密碼：</label>
									<input class="itxt" type="password" placeholder="請輸入密碼"
										   autocomplete="off" tabindex="1" name="password" />
									<br />
									<br />
									<input type="submit" value="登入" id="sub_btn" />
								</form>
							</div>

						</div>
					</div>
				</div>
			</div>

		<%--靜態包含頁腳內容--%>
		<%@include file="/pages/common/footer.jsp"%>


</body>
</html>