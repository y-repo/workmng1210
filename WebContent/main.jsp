<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>稼動時間入力</title>
<link href="css/main-css.css" rel="stylesheet" media="screen">
<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">
</head>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="navbar-header">
		<a class="navbar-brand"><b>勤怠管理システム</b></a>
	</div>
	<ul class="nav navbar-nav">
		<li>
			<a href="./MenuServlet">メインメニュー</a>
		</li>
	</ul>
	<ul class="nav navbar-nav navbar-right">
		<li><a href="./LogoutServlet">ログアウト</a></li>
	</ul>
	</nav>
	<div class="container" style="padding: 50px">
		<div class="row">
			<div class="col-sm-12">
				<h2 class="page-header">稼働時間入力</h2>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-2">
				<select class="form-control">
					<option>2013年</option>
					<option>2012年</option>
					<option>2011年</option>
					<option>2010年</option>
				</select>
			</div>
			<div class="col-sm-2">
				<select class="form-control">
					<option>1月</option>
					<option>2月</option>
					<option>3月</option>
					<option>4月</option>
					<option>5月</option>
					<option>6月</option>
					<option>7月</option>
					<option>8月</option>
					<option>9月</option>
					<option>10月</option>
					<option>11月</option>
					<option>12月</option>
				</select>
			</div>
		</div>
		<div class="row" style="padding: 20px">
			<div class="col-sm-8 col-sm-offset-2">
				<table class="table table-bordered table-hover">
					<thead>
						<tr>
							<th>日付</th>
							<th>曜日</th>
							<th>始業</th>
							<th>終業</th>
							<th>稼働時間</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>1</td>
							<td>月</td>
							<td><input type="text" size="2">：<input type="text"　size="2"></td>
							<td><input type="text" size="2">：<input type="text"　size="2"></td>
							<td>hh:mm</td>
						</tr>
						<tr>
							<td>2</td>
							<td>火</td>
							<td><input type="text" size="2">：<input type="text"　size="2"></td>
							<td><input type="text" size="2">：<input type="text"　size="2"></td>
							<td>hh:mm</td>
						</tr>
						<tr>
							<td>3</td>
							<td>水</td>
							<td><input type="text" size="2">：<input type="text"　size="2"></td>
							<td><input type="text" size="2">：<input type="text"　size="2"></td>
							<td>hh:mm</td>
						</tr>
						<tr>
							<td>4</td>
							<td>木</td>
							<td><input type="text" size="2">：<input type="text"　size="2"></td>
							<td><input type="text" size="2">：<input type="text"　size="2"></td>
							<td>hh:mm</td>
						</tr>
						<tr>
							<td>5</td>
							<td>金</td>
							<td><input type="text" size="2">：<input type="text"　size="2"></td>
							<td><input type="text" size="2">：<input type="text"　size="2"></td>
							<td>hh:mm</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-2">
				<button type="submit" class="btn btn-primary btn-lg btn-block">登録</button>
			</div>
		</div>
	</div>
	<script src="http://code.jquery.com/jquery.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>