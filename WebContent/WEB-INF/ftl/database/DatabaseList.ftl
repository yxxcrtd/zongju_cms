<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>文件打包管理</title>
		<link type="text/css" rel="styleSheet" href="${request.contextPath}/css/style.css" />
	</head>

	<body>
		<div id="content">
			<div id="new">
				<a class="button icon_add" href="${request.contextPath}/manage/database/add">新建文件包</a>
				<span id="tips"><#if tips??>${tips}</#if></span>
			</div>
			
			<div id="header">
				<span class="icon_flag">打包信息列表</span>
			</div>
			
	  		<table width="100%" border="0" cellspacing="0" cellpadding="0">
	  			<tr id="thead">
					<td width="10%">产品包ID</td>
					<td width="30%">产品包名称</td>
					<td width="20%">是否推荐</td>
					<td width="20%">产品包创建时间</td>
					<td width="20%">操作</td>
  				</tr>
				<#if (databaseList?? && 0 < databaseList?size)>
					<#list databaseList as d>
						<tr bgColor="#<#if (0 == d_index % 2)>F9F9F9<#else>FFFFFF</#if>" onMouseOver="changeBgColor(this, '#ECECEC');" onMouseOut="changeBgColor(this, '#<#if (0 == d_index % 2)>F9F9F9<#else>FFFFFF</#if>');">
							<td>
								${d.databaseId}
							</td>
							<td>
								${d.databaseName}
							</td>
							<td>
								<#if (!d.databaseCommend)>否
								<#elseif (d.databaseCommend)><span class="red">是</span>
								<#else>出错了！
								</#if>
							</td>
							<td>
								${d.databaseCreateTime}
							</td>
							<td>
								<a href="${request.contextPath}/manage/database/edit/${d.databaseId}">添加文件</a>&nbsp;&nbsp;
								<a href="${request.contextPath}/manage/database/download/${d.databaseId}">打包下载</a>&nbsp;&nbsp;
								<a href="${request.contextPath}/manage/database/delete/${d.databaseId}" onClick="return confirm('确定删除吗？');">删除</a>
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
