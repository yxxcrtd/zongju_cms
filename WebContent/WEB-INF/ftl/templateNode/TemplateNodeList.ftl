<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>模板管理</title>
		<link type="text/css" rel="styleSheet" href="${request.contextPath}/css/style.css" />
	</head>

	<body>
		<div id="content">
			<div id="new">
			    <a class="button icon_add" href="${request.contextPath}/manage/templateNode/add/${typeId}">新增<#if (0 == typeId)>用户<#else>目标</#if>模板</a>
			    <a class="button icon_minus" href="${request.contextPath}/manage/templateNode/delete/${typeId}" onClick="return confirm('确定删除所有模板吗？');">删除<#if (0 == typeId)>用户<#else>目标</#if>模板</a>
				<span id="tips"><#if tips??>${tips}</#if></span>
			</div>
			
			<div id="header">
				<span class="icon_flag"><#if (0 == typeId)>用户<#else>目标</#if>模版信息列表</span>
			</div>
			
	  		<table width="100%" border="0" cellspacing="0" cellpadding="0">
	  			<tr id="thead">
					<td width="10%">模版ID</td>
					<td width="20%" class="left">模板名称</td>
					<td width="50%" class="left">模版节点路径</td>
					<td width="20%" class="left">模版节点名称</td>
  				</tr>
				<#if (templateNodeList?? && 0 < templateNodeList?size)>
					<#list templateNodeList as t>
						<tr bgColor="#<#if (0 == t_index % 2)>F9F9F9<#else>FFFFFF</#if>" onMouseOver="changeBgColor(this, '#ECECEC');" onMouseOut="changeBgColor(this, '#<#if (0 == t_index % 2)>F9F9F9<#else>FFFFFF</#if>');">
							<td>
								${t.templateNodeId}
							</td>
							<td class="left">
								${t.templateNodeOriginalName}
							</td>
							<td class="left">
								${t.templateNodePath}
							</td>
							<td class="left">
								${t.templateNodeName}
							</td>
						</tr>
					</#list>
				<#else>
					<tr bgColor="F9F9F9">
						<td colspan="7">没有数据！</td>
					</tr>
				</#if>
	  		</table>
		</div>
		
		<script language="javascript" src="${request.contextPath}/js/jquery.js"></script>
		<script language="javascript" src="${request.contextPath}/js/public.js"></script>
	</body>
</html>
