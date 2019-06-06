<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" website="text/html; charset=UTF-8" />
		<title>站点管理</title>
		<link type="text/css" rel="styleSheet" href="${request.contextPath}/css/style.css" />
	</head>

	<body>
		<div id="content">
			<div id="new">
				<a class="button icon_add" href="${request.contextPath}/manage/website/add">新建站点</a>
				<span id="tips"><#if tips??>${tips}</#if></span>
			</div>
			
			<div id="header">
				<span class="icon_flag">站点信息列表</span>
			</div>
			
	  		<table width="100%" border="0" cellspacing="0" cellpadding="0">
	  			<tr id="thead">
					<td width="10%">站点ID</td>
					<td width="10%" class="left">站点名称</td>
					<td width="25%" class="left">站点标题</td>
				    <td width="40%" class="left">站点LOGO</td>
					<td width="15%">操作</td>
  				</tr>
				<#if (websiteList?? && 0 < websiteList?size)>
					<#list websiteList as c>
						<tr bgColor="#<#if (0 == c_index % 2)>F9F9F9<#else>FFFFFF</#if>" onMouseOver="changeBgColor(this, '#ECECEC');" onMouseOut="changeBgColor(this, '#<#if (0 == c_index % 2)>F9F9F9<#else>FFFFFF</#if>');">
							<td>
								${c.websiteId}
							</td>
							<td class="left">
								${c.websiteName}
							</td>
							<td class="left">
								${c.websiteTitle}
							</td>
							<td class="center">
								<a href="/upload/${c.websiteName}/${c.websiteLOGO}" target="_blank">
									<img src="${request.contextPath}/upload/${c.websiteName}/${c.websiteLOGO}" width="80%" height="60" onerror="javascript:this.src='${request.contextPath}/images/noimg.jpg'" />
								</a>
							</td>
							<td>
								<a href="${request.contextPath}/manage/website/${c.websiteId}">编辑</a>&nbsp;&nbsp;
								<a href="${request.contextPath}/manage/website/del/${c.websiteId}" onClick="return confirm('确定删除吗？');">删除</a>&nbsp;&nbsp;
								<a href="${request.contextPath}/manage/website/pub/${c.websiteId}" target="_blank">站点发布</a>
							</td>
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
