<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>微信公众号</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
<!--讨论区滚动条begin-->
	<link rel="stylesheet" type="text/css" href="resources/css/jscrollpane1.css" />
	<link rel="stylesheet" type="text/css" href="resources/css/all.css">
	<script type="text/javascript" src="resources/js/common/jquery-1.8.0.min.js"></script>
	<!-- the mousewheel plugin -->
	<script type="text/javascript" src="resources/js/common/jquery.mousewheel.js"></script>
	<!-- the jScrollPane script -->
	<script type="text/javascript" src="resources/js/common/jquery.jscrollpane.min.js"></script>
	<script type="text/javascript" src="resources/js/common/scroll-startstop.events.jquery.js"></script>
<!--讨论区滚动条end-->
	<script type="text/javascript" src="resources/js/front/talk.js"></script>
  </head>
  
  <body>
		<input type="hidden" value="<%= basePath %>" id="basePath"/>
		<br/>
		<div class="talk">
			<div class="talk_title"><span>正在与公众号对话</span></div>
			
			<div class="talk_record"><!-- 这个div用于记录用户和公众号的对话 -->
				<div id="jp-container" class="jp-container">
					
				</div>
			</div>
			
			<div class="talk_word"><!-- 这里是聊天页输入对话的地方 -->
				<input class="add_face" id="facial" type="button" title="添加表情" value="" />
				<input id="content" class="messages emotion"   />
				<input class="talk_send" onclick="send();" type="button" title="发送" value="发送" />
			</div>
		</div>
		<div style="text-align:center;margin:50px 0; font:normal 14px/24px 'MicroSoft YaHei';"></div>
	</body>
</html>
