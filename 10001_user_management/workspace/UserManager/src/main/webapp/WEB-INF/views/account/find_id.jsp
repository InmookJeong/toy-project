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
						<form action="/forgot-id" method="post">
							<h2>가입하신 ID는 <p>'${userId}'</p> 입니다.</h2>
							<p class="go-to-home-text">
								<a href="/">Go to Home</a>
							</p>
						</form>
					</div>
				</div>
			</div>
		</section>
	</body>
</html>
