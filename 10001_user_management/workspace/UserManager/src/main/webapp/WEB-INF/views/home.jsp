<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page session="false" %>
<%@ include file="./init.jsp" %>

<html>
	
	<head>
		<title>User Manager</title>
	</head>
	
	<body>
		<section>
			<div class="container">
				<div class="account-box">
					<div class="image-box">
						<img />
					</div>
					
					<div class="form-box">
						<form action="/sign-in" method="post" class="${isSignIn}">
							<h2>Sign In</h2>
							<input type="text" name="userId" value="" placeholder="Please type your ID." />
							<input type="password" name="password" value="" placeholder="Please type your Password." />
							<input type="submit" name="" value="Sign In" />
							<p class="sign-up-text">
								Forgot your account?&nbsp;
								<a href="/forgot-id">Forgot ID</a>&nbsp;
								/&nbsp;
								<a href="/forgot-password">Password</a>
							</p>
							
							<p class="sign-up-text">
								Don't have an account?&nbsp;
								<a href="/sign-up">Sign Up</a>
							</p>
						</form>
						
						<div class="welcome-message ${isSignIn}">
							<h2>Hello. ${userId}.</h2>
						</div>
					</div>
				</div>
			</div>
		</section>
		<button onclick="testFetch();">test</button>
	</body>
	
	<script>
		$(document).ready(function() {
			if('${failedSignIn}' == 'true') {
				alert("로그인 실패!!");
			}
		});
	</script>
</html>
