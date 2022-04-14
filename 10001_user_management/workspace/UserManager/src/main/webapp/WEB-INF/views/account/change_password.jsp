<%@ include file="../init.jsp" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page session="false" %>
<html>
	<head>
		<title>User Manager - ForgotID</title>
	</head>
	<body>
		<section>
			<div class="container">
				<div class="account-box">
					<div class="image-box">
						<img />
					</div>
					
					<div class="form-box">
						<form action="/change-password" method="post">
							<input type="hidden" name="userId" value="${userId}" />
							<h2>Change Password</h2>
							<input type="password" id="password" name="password" value="" />
							<input type="password" id="password2" name="password2" value="" />
							<button type="button" class="duplicate-check" data-target="id" onclick="isMatched();">
								중복확인
							</button>
							<input type="submit" name="" value="Change Password" />
							<p class="go-to-home-text">
								<a href="/">Go to Home</a>
							</p>
						</form>
					</div>
				</div>
			</div>
		</section>
	</body>
	
	<script>
		function isMatched() {
			const password = $('#password').val();
			const password2 = $('#password2').val();
			if(password == password2) {
				alert('일치합니다.');
			} else {
				alert('비밀번호를 다시 확인해주세요.');
			}
		}
	</script>
</html>
