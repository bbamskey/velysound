<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8"/>
	<meta name="viewport"
	      content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
	<meta name="HandheldFriendly" content="true"/>
	<meta name="format-detection" content="telephone=no"/>
	<meta name="theme-color" content="#7047aa"/>
	<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
	<title>VELY SOUND</title>
	<link rel="shortcut icon" href="/assets/images/layout/favicon.ico" type="image/x-icon"/>
	<link rel="stylesheet" type="text/css" href="/assets/css/style.css"/>

	<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
	<script src="/assets/js/ui/swiper.min.js"></script>
	<script src="/assets/js/ui/template2.js"></script>
	<script src="/assets/js/ui/ui.js"></script>

	<script src="/assets/js/util/ajaxUtil.js"></script>
</head>
<body>
<div id="wrap">
	<tiles:insertAttribute name="header"/>
	<tiles:insertAttribute name="body"/>
	<tiles:insertAttribute name="footer"/>
</div>
</body>
</html>
