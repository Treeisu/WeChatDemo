<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<base href="<%=basePath%>">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta http-equiv="X-UA-Compatible"content="IE=9; IE=8; IE=7; IE=EDGE" />
	<title>内容列表页面</title>
	
	<link rel="stylesheet" type="text/css" href="resources/css/all.css">
	<script type="text/javascript" src="resources/js/common/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="resources/js/back/list.js"></script>
	
</head>

<body style="background: #e1e9eb;">
<form action="list.do" id="mainForm" method="post">
		<div class="right">
			<div class="current">当前位置：
				<a href="javascript:void(0)" style="color:#6E6E6E;">内容管理</a>&gt; 内容列表
			</div>
			<div class="rightCont">
				<p class="g_title fix">内容列表
					<a class="btn03" href="#">新 增</a>&nbsp;&nbsp;&nbsp;&nbsp;
					<a class="btn03" href="javascript:deleteBatch('<%=basePath%>')">删 除</a>
				</p>
				<!-- table1是用来查询的 -->
				
					<table class="tab1">
						<tbody>
							<tr>
								<td width="90" align="right">指令名称：</td>
								<td><input name="command" type="text" class="allInput" value="${command}"/></td>
								<td width="90" align="right">描述：</td>
								<td><input name="description" type="text" class="allInput" value="${description}"/></td>
								<td width="85" align="right"><input type="submit" class="tabSub" value="查 询" /></td>
							</tr>
						</tbody>
					</table>
				
				<!-- table2是用来查询完显示所有指令数据的表格 -->
				<div class="zixun fix">
					<table class="tab2" width="100%">
						<tbody>
							<tr>
								<th><input type="checkbox" id="all" /></th>
								<th>序号</th>
								<th>指令名称</th>
								<th>描述</th>
								<th>操作</th>
							</tr>
							<c:forEach items="${messageList}" var="message" varStatus="status">
								<tr <c:if test="${status.index%2!=0}">style="background-color:#ECF6EE;"</c:if>>
									<td><input type="checkbox" name="id" value="${message.id}"/></td>
									<td>${message.id}</td>
									<td>${message.command}</td>
									<td>${message.description}</td>
									<td><a href="#">修改</a>&nbsp;&nbsp;&nbsp; <a href="${bathPath}deleteone.do?id=${message.id}">删除</a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
		
					<div class='page fix'>
						共 <b>4</b> 条 <a href='###' class='first'>首页</a> <a href='###'
							class='pre'>上一页</a> 当前第<span>1/1</span>页 <a href='###'
							class='next'>下一页</a> <a href='###' class='last'>末页</a> 跳至&nbsp;<input
							type='text' value='1' class='allInput w28' />&nbsp;页&nbsp; <a
							href='###' class='go'>GO</a>
					</div>
				</div>
			</div>
		</div>
</form>
</body>
</html>