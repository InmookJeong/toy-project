<%@ include file="../init.jsp" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page session="false" %>
<!-- 
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
 -->
<%
	request.setCharacterEncoding("utf-8");
%>

<html>
	<head>
		<title>User Manager - SignUp</title>
	</head>
	
	<body>
		<section>
			<div class="container">
				<div class="account-box">
					
					<div class="image-box">
						<img />
					</div>
					
					<div class="form-box" style="overflow: auto;">
						<form action="/sign-up" method="post">
							<input type="hidden" name="nation" value="ROK_code" />
							<input type="hidden" name="description" value="" />
							<input type="hidden" name="status" value="active_code" />
							<input type="hidden" name="profileImageId" value="0" />
							<input type="hidden" name="emailVerified" value="true" />
							<input type="hidden" name="siteTermsOfUse" value="true" />
							<input type="hidden" name="userInfoTermsOfUse" value="true" />
							<input type="hidden" name="eventTermsOfUse" value="true" />
							
							<h2>Sign Up</h2>
							
							<div>
								회원 정보 제공을 동의하십니까?<br/>
								- 제공한다고 치고<br/>
								- 대신 개인정보제공동의, 개인정보 활용동의 내용 필요
							</div>
							
							<!-- 아이디, 비밀번호, 비밀번호 재확인, 이름, 이메일, 휴대전화 번호, 성별, 생년월일, 주소 -->
							<input type="text" id="userId" name="userId" value="" placeholder="Please type your ID." />
							<button type="button" class="duplicate-check" data-target="id" onclick="duplicateCheck('userId');">
								중복확인
							</button>
							
							<input type="password" id="password" name="password" value="" placeholder="Please type your Password." />
							<input type="password" id="passwordCheck" name="passwordCheck" value="" placeholder="Please type your Password again." />
							<div>여기에 비밀번호 일치 여부 안내 메시지 출력</div>
							
							<input type="text" id="name" name="name" value="" placeholder="Please type your name." />
							<input type="text" id="email" name="email" value="" placeholder="Please type your email." />
							<button type="button" class="duplicate-check" data-target="email" onclick="duplicateCheck('email');">
								중복확인
							</button>
							
							<input type="text" id="phoneNumber" name="phoneNumber" value="" placeholder="Please type your phone number." />
							<button type="button" class="duplicate-check" data-target="phone" onclick="duplicateCheck('phoneNumber');">
								중복확인
							</button>
							
							<br/>
							<input type="radio" name="gender" value="MALE" />MALE
							<input type="radio" name="gender" value="FEMALE" />FEMALE
							
							<br/>
							<input type="text" name="birth" value="" placeholder="Please type your birth(yyyy-MM-dd)." />
							<input type="text" name="address" value="" placeholder="Please type your adress." />
							
							<input type="submit" name="" value="Sign Up" />
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
		function duplicateCheck(column) {
			const columnElement = document.getElementById(column);
			const value = columnElement.value;
			const url = 'http://localhost:8080/duplicate-check';
			const params = {
				'column' : column,
				'value' : value
			};
			
			const callback = function(data) {
				const isDuplicate = data.isDuplicate;
				if(isDuplicate) {
					alert("이거 못써!@!!!!");
					columnElement.focus();
				} else {
					alert("사용가능한 " + column + " 입니다.");
				}
			};
			const errorCallback = function() {
				alert("에러 발생!! 확인하세요.!!")
			};
			
			callAjax(url, 'POST', 'json', params, callback, errorCallback);
		}
		
		function callAjax(callUrl, httpType, returnType, params, callback, errorCallback) {
			$.ajax({
				url : callUrl,
				type : httpType,
				data : params,
				dataType : returnType,
				success:function(data){
					if(callback) {
						callback(data);
					}
				},
				error:function(request,status,error){
					if(errorCallback) {
						errorCallback();
					}
				}
			});
		}
		
	</script>
</html>
