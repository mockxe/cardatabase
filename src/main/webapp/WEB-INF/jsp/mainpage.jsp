<!DOCTYPE HTML>
<html>
	<head>
		<meta charset="UTF8" /> 
		
		<title>Car Database</title>
		
		<style type="text/css">
			body {
				font-family: sans-serif;
				margin-right: 10%;
				margin-left: 10%;
			}
			
			table {
				border-collapse: collapse;
			}
			
			table, th, td {
				border: 1px solid black;
				padding: 5px;
				text-align: center;
			}
			
			.action {
				width: 100%;
			}
			
			
			.header {
				text-align: center;
				position: fixed;
				width: 100%;
				padding: 15px;
				left: 0;
				top: 0;
				background-color: #c6c6c6;
				border-top: 0px;
				border-left: 0px;
				border-right: 0px;
				border-bottom: 1px;
				border-style: solid;
				border-color: #000000;
				height: 10%;
			}
			
			.body {
				margin-top: 10%;
				margin-bottom: 7%;
			}
			
			.footer {
				height: 5%;
				position: fixed;
				width: 100%;
				padding: 15px;
				left: 0;
				bottom: 0;
				background-color: #c6c6c6;
				border-bottom: 0px;
				border-left: 0px;
				border-right: 0px;
				border-top: 1px;
				border-style: solid;
				border-color: #000000;
			}
			
			.toolBar {
				margin-bottom: 15px;
			}
			
			.error {
				color: #ff0000;
			}
			
		</style>
		
	</head>
	<body>
		<div class = header>
			<h1>The car database</h1>
		</div>
		<div class="body">
			<p class="error">${errorMsg}</p>
			<!-- Insert content here -->
			<jsp:include page="bodies/${body}.jsp"/>
		</div>
		<div class="footer">
			<p>Mockxe</p>
		</div>
	</body>
</html>