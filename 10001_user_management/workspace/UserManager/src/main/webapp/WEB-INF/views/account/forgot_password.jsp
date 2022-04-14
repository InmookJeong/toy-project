<%@ include file="../init.jsp" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page session="false" %>
<html>
	<head>
		<title>User Manager - ForgotPassword</title>
		
	</head>
	
	<body>
		
		<section>
			<div class="container">
				<div class="account-box">
					<div class="image-box">
						<img />
					</div>
					
					<div class="form-box">
						<form action="/forgot-password" method="post">
							<h2>Find Password</h2>
							<input type="text" name="userId" value="" placeholder="Please type your ID." />
							<input type="text" name="name" value="" placeholder="Please type your name." />
							<input type="text" name="email" value="" placeholder="Please type your email." />
							<input type="submit" name="" value="Find Password" />
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
