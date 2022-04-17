<%@ include file="./init.jsp" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page session="false" %>

<%
	request.setCharacterEncoding("utf-8");
%>

<html>
	<head>
		<title>File Manager</title>
	</head>
	
	<body>
		<section>
			<div class="container">
				<div class="file-upload">
					<form action="/file-upload" method="post" enctype="multipart/form-data" class="file-upload-form">
						<input type="file" id="file" name="file" />
						<button type="button" class="upload-btn">Upload</button>
					</form>
				</div>
				<div class="file-information">
				</div>
			</div>
		</section>
	</body>
	
	<script>
		$('body').on('click', '.upload-btn', function() {
			const file = $("#file")[0];
			const files = file.files;
			
			if(files.length == 0) {
				alert('파일 선택하세요.');
				return;
			}
			
			const formData = new FormData();
			formData.append("file", files[0]);
			
			$.ajax({
				type:"POST",
				url: "/file/upload",
				processData: false,
				contentType: false,
				data: formData,
				success: function(data){
					console.log('data : ', data)
				},
				error: function(err){
					console.log("err:", err)
				}
			});
		});
	</script>
</html>
