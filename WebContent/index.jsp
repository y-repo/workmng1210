<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>ログイン</title>
<link href="./css/bootstrap.css" rel="stylesheet">
<link href="./css/index.css" rel="stylesheet">
</head>
<body>
	<div class="container" class="well">
		<form class="form-signin" action="./Workmain" method="post">
			<h2 class="form-signin-heading">ログイン</h2>
			<input type="text" class="input-block-level" placeholder="社員コード" name="scode">
			<input type="password" class="input-block-level" placeholder="パスワード" name="passwd">
			<p class="text-danger">${dto.errmsg}</p>
			<div class="control-group">
				<button class="btn btn-large btn-primary" type="submit">ログイン</button>
			</div>
		</form>
	</div>
	<script src="http://code.jquery.com/jquery.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>