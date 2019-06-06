<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>水印管理</title>
		<link type="text/css" rel="styleSheet" href="${request.contextPath}/css/style.css" />
	</head>

	<body>
		<div id="content">
			<div id="new">
				<a class="button icon_add" href="${request.contextPath}/manage/watermark/add">新建水印</a>
				<span id="tips"><#if tips??>${tips}</#if></span>
			</div>
			
			<div id="header">
				<span class="icon_flag">水印信息列表</span>
			</div>
			
	  		<table width="100%" border="0" cellspacing="0" cellpadding="0">
	  			<tr id="thead">
					<td width="10%">水印ID：</td>
					<td width="25%">水印名称：</td>
					<td width="15%">水印字体大小：</td>
					<td width="15%">水印是否激活：</td>
					<td width="15%">水印修改时间：</td>
					<td width="20%">操作</td>
  				</tr>
				<#if (watermarkList?? && 0 < watermarkList?size)>
					<#list watermarkList as w>
						<tr bgColor="#<#if (0 == w_index % 2)>F9F9F9<#else>FFFFFF</#if>" onMouseOver="changeBgColor(this, '#ECECEC');" onMouseOut="changeBgColor(this, '#<#if (0 == w_index % 2)>F9F9F9<#else>FFFFFF</#if>');">
							<td>
								${w.watermarkId}
							</td>
							<td>
								${w.watermarkName}
							</td>
							<td>
								${w.watermarkFont}
							</td>
							<td>
								<#if (!w.watermarkActive)>否
								<#elseif (w.watermarkActive)><span class="red">是</span>
								<#else>出错了！
								</#if>
							</td>
							<td>
								${w.watermarkCreateTime}
							</td>
							<td>
								<a href="${request.contextPath}/manage/watermark/${w.watermarkId}">编辑</a>&nbsp;&nbsp;
								<a href="${request.contextPath}/manage/watermark/del/${w.watermarkId}" onClick="return confirm('确定删除吗？');">删除</a>
							</td>
						</tr>
					</#list>
				<#else>
					<tr bgColor="F9F9F9">
						<td colspan="6">没有数据！</td>
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
