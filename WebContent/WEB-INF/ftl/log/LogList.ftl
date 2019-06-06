<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>日志管理</title>
		<link type="text/css" rel="styleSheet" href="${request.contextPath}/css/style.css" />
	</head>

	<body>
		<div id="content">
			<div id="header">
				<span class="icon_flag">日志信息列表</span>
				<span id="tips"><#if tips??>${tips}</#if></span>
			</div>
			
	  		<table width="100%" border="0" cellspacing="0" cellpadding="0">
	  			<tr id="thead">
					<td width="10%">日志Id</td>
					<td width="20%">日志对象</td>
					<td width="40%" class="left">日志标题</td>
					<td width="20%">日志记录时间</td>
					<td width="10%">操作</td>
  				</tr>
				<#if (logList?? && 0 < logList?size)>
					<#list logList as l>
						<tr bgColor="#<#if (0 == l_index % 2)>F9F9F9<#else>FFFFFF</#if>" onMouseOver="changeBgColor(this, '#ECECEC');" onMouseOut="changeBgColor(this, '#<#if (0 == l_index % 2)>F9F9F9<#else>FFFFFF</#if>');">
							<td>${l.logId}</td>
							<td>${l.logObject}</td>
							<td class="left">${l.logTitle}</td>
							<td>${l.logCreateTime}</td>
							<td><a href="${request.contextPath}/manage/log/del/${l.logId}" onClick="return confirm('确定删除吗？');">删除</a></td>
						</tr>
					</#list>
				<#else>
					<tr bgColor="F9F9F9">
						<td colspan="5">没有数据！</td>
					</tr>
				</#if>
	  		</table>
	  		<div id="pageNav">
	  			<#include "Pager.ftl" />
	  		</div>
		</div>
		
		<script language="javascript" src="${request.contextPath}/js/jquery.js"></script>
		<script language="javascript" src="${request.contextPath}/js/public.js"></script>
	</body>
</html>
