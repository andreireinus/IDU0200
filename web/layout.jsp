<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<title>Kliendid</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<link href="/bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen" />
	<script src="/scripts/jquery.js"></script>
	<script src="/scripts/angular.js"></script>
	<script src="/scripts/app.js"></script>

	<style type="text/css">
		body {
			padding-top: 60px;
		}
	</style>

	<base href="/" />
</head>
<body ng-app="App">
<ng-include src="'layout/navbar.html'"></ng-include>

<div class="content" ng-controller="Main" ng-view />

<script src="/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>