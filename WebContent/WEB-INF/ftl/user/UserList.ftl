<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>用户管理</title>
		<link type="text/css" rel="styleSheet" href="${request.contextPath}/css/style.css" />
	</head>

	<body>
		<div id="content">
			<div id="new">
				<a class="button icon_add" href="${request.contextPath}/manage/user/import">导入JSON用户</a>
				<span id="tips"><#if tips??>${tips}</#if></span>
			</div>
			
			<div id="header">
				<span class="icon_flag">用户信息列表</span>
				<span id="tips"></span>
			</div>
			
	  		<table width="100%" border="0" cellspacing="0" cellpadding="0">
	  			<tr id="thead">
					<td width="15%">用户Id</td>
					<td width="35%" class="left">用户姓名</td>
					<td width="15%">用户状态</td>
					<td width="15%">用户角色</td>
					<td width="20%">操作</td>
  				</tr>
				<#if (userList?? && 0 < userList?size)>
					<#list userList as u>
						<tr bgColor="#<#if (0 == u_index % 2)>F9F9F9<#else>FFFFFF</#if>" onMouseOver="changeBgColor(this, '#ECECEC');" onMouseOut="changeBgColor(this, '#<#if (0 == u_index % 2)>F9F9F9<#else>FFFFFF</#if>');">
							<td>${u.userId}</td>
							<td class="left">${u.username}</td>
							<td class="status"><#if (0 == u.userStatus)><span class="red">待审核</span><#elseif (1 == u.userStatus)>已审核</#if></td>
							<td>
								<#if (0 == u.userRole)>普通用户
								<#elseif (1 == u.userRole)><span class="red">待定</span>
								</#if>
							</td>
							<td>
								<a href="javascript:;" class="audit" value="${u.userId}"><#if (0 == u.userStatus)>用户审核</#if></a>&nbsp;&nbsp;
								<a href="javascript:;" class="reset" value="${u.userId}">重置密码</a>&nbsp;&nbsp;
								<a href="${request.contextPath}/manage/user/del/${u.userId}" onClick="return confirm('确定删除吗？');">删除</a>
							</td>
						</tr>
					</#list>
				<#else>
					<tr bgColor="F9F9F9">
						<td colspan="8">没有数据！</td>
					</tr>
				</#if>
	  		</table>
	  		<div id="pageNav">
	  			<#include "Pager.ftl" />
	  		</div>
		</div>
		
		<script language="javascript" src="${request.contextPath}/js/jquery.js"></script>
		<script language="javascript" src="${request.contextPath}/js/public.js"></script>
		<script language="javascript">
		<!--
		$(function() {
			$(".audit").on("click", function() {
				if (confirm('确定审核该用户吗？')) {
					var This = $(this);
					$.post("${request.contextPath}/manage/user/audit", { "id" : This.attr("value") }, function(data) {
						if ("success" == data) {
							$("#tips").show().html("审核成功！").fadeOut(10000);
							This.parent().siblings(".status").html("已审核");
							This.html("");
						} else {
							alert("发生了未知的错误，请与管理员联系！");
						}
					});
				}
			});
			$(".reset").on("click", function() {
				if (confirm('确定重置该用户密码吗？')) {
					var This = $(this);
					$.post("${request.contextPath}/manage/user/reset", { "id" : This.attr("value") }, function(data) {
						if ("success" == data) {
							$("#tips").show().html("密码重置成功！").fadeOut(15000);
							alert("密码已被重置为：123456");
						} else {
							alert("发生了未知的错误，请与管理员联系！");
						}
					});
				}
			});
		});
		//-->
		</script>
	</body>
</html>
