<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>メインメニュー</title>
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
</head>
<body>
		<nav class="navbar navbar-inverse navbar-fixed-top">
			<div class="navbar-header">
				<a class="navbar-brand"><b>勤怠管理システム</b></a>
			</div>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="./Workmain?logoutflg=1">ログアウト</a></li>
			</ul>
		</nav>
		<div class="container" style="padding:50px 0">
			<h1 class="page-header">メインメニュー</h1>
			<div class="row" style="padding:20px">
				<div class="col-sm-6">
					<a href="./main.jsp" class="btn btn-large btn-block btn-primary">勤務表</a>
				</div>
				<div class="col-sm-6">
					<a href="" class="btn btn-large btn-block btn-primary">掲示板</a>
				</div>
			</div>
			<div class="row" style="padding:20px">
				<div class="col-sm-6">
					<a href="" class="btn btn-large btn-block btn-primary">予備1</a>
				</div>
				<div class="col-sm-6">
					<a href="" class="btn btn-large btn-block btn-primary">予備2</a>
				</div>
			</div>
		</div>		
		<script src="http://code.jquery.com/jquery.js"></script>
		<script src="js/bootstrap.min.js"></script>
</body>
</html>