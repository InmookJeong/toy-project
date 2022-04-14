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
							<h2>Find ID</h2>
							<input type="text" name="name" id="name" value="" placeholder="Please type your name." />
							<input type="text" name="email" id="email" value="" placeholder="Please type your email." />
							<input type="submit" name="" class="find-id-button" value="Find ID" />
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
		$(document).ready(function() {
			if('${status}' == 'false') {
				alert('가입 정보를 다시 확인하세요.');
			}
		});
	</script>
</html>
