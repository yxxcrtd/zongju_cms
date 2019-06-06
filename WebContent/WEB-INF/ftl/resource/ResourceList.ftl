<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>资源管理</title>
		<link type="text/css" rel="styleSheet" href="${request.contextPath}/css/style.css" />
	</head>

	<body>
		<div id="content">
			<div id="new">
				<a class="button icon_add" href="${request.contextPath}/manage/resource/add">新建资源</a>
				<span id="tips"><#if tips??>${tips}</#if></span>
			</div>
			
			<div id="header">
				<span class="icon_flag">资源信息列表</span>
			</div>
			
			<div id="search">
				<form>
					<input type="text" class="input" name="k" value="${k}" onMouseOver="this.select();" placeholder="请输入查询的资源作者、资源名称、ISBN" />
					<input type="submit" value="查  询" class="button icon_query" />
				</form>
			</div>
			
	  		<table width="100%" border="0" cellspacing="0" cellpadding="0">
	  			<tr id="thead">
					<td width="10%">资源ID</td>
					<td width="10%">资源分类</td>
					<td width="20%" class="left">资源名称</td>
					<td width="15%">资源ISBN</td>
					<td width="10%">资源作者</td>
					<td width="10%" class="right">资源价格</td>
					<td width="25%">操作</td>
  				</tr>
				<#if (resourceList?? && 0 < resourceList?size)>
					<#list resourceList as r>
						<tr bgColor="#<#if (0 == r_index % 2)>F9F9F9<#else>FFFFFF</#if>" onMouseOver="changeBgColor(this, '#ECECEC');" onMouseOut="changeBgColor(this, '#<#if (0 == r_index % 2)>F9F9F9<#else>FFFFFF</#if>');">
							<td>${r.resourceId}</td>
							<td>${r.category.categoryName}</td>
							<td class="left">
								<#if (r.resourceFile?ends_with(".pdf"))><a href="${request.contextPath}/manage/resource/view/${r.resourceId}" target="_blank">${r.resourceName}</a>
								<#else>${r.resourceName}
								</#if>
							</td>
							<td>${r.resourceISBN}</td>
							<td>${r.resourceAuthor}</td>
							<td class="right">${r.resourcePrice?string.currency}</td>
							<td>
								<#if (r.resourceFile?ends_with(".pdf"))>
									<a href="${request.contextPath}/manage/resource/viewer/${r.resourceId}" target="_blank" title="多终端显示">PDA</a>&nbsp;&nbsp;
								</#if>
								<a href="${request.contextPath}/manage/resource/download/pdf/${r.resourceId}" title="下载PDF">PDF</a>&nbsp;
								<a href="${request.contextPath}/manage/resource/download/zip/${r.resourceId}" title="下载ZIP">ZIP</a>&nbsp;
								<a href="${request.contextPath}/manage/resource/product/${r.resourceId}" title="生成CNONIX">CNONIX</a>&nbsp;&nbsp;
								<a href="${request.contextPath}/manage/resource/${r.resourceId}" title="编辑">编辑</a>&nbsp;
								<a href="${request.contextPath}/manage/resource/del/${r.resourceId}" onClick="return confirm('确定删除吗？');" title="删除">删除</a>
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
	</body>
</html>
