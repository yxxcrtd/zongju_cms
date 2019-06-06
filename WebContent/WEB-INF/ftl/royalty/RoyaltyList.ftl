<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>版税管理</title>
		<link type="text/css" rel="styleSheet" href="${request.contextPath}/css/style.css" />
	</head>

	<body>
		<div id="content">
			<div id="new">
				<a class="button icon_add" href="javascript:;"><span style="color: #909090">新建版税</span></a>
				<span id="tips"><#if tips??>${tips}</#if></span>
			</div>
			
			<div id="header">
				<span class="icon_flag">版税信息列表</span>
			</div>
			
	  		<table width="100%" border="0" cellspacing="0" cellpadding="0">
	  			<tr id="thead">
					<td width="10%">版税ID</td>
					<td width="35%" class="left">资源名称</td>
					<td width="10%" class="right">资源价格</td>
					<td width="10%">资源版税率</td>
					<td width="10%">版税印量</td>
					<td width="15%" class="right" title="版税金额 = 资源的单价 x 资源的印数或销量 x 资源的版税率">版税金额</td>
					<td width="10%">操作</td>
  				</tr>
				<#if (royaltyList?? && 0 < royaltyList?size)>
					<#list royaltyList as r>
						<tr bgColor="#<#if (0 == r_index % 2)>F9F9F9<#else>FFFFFF</#if>" onMouseOver="changeBgColor(this, '#ECECEC');" onMouseOut="changeBgColor(this, '#<#if (0 == r_index % 2)>F9F9F9<#else>FFFFFF</#if>');">
							<td>
								${r.resource.resourceId}
							</td>
							<td class="left">
								${r.resource.resourceName}
							</td>
							<td class="right">
								${r.resource.resourcePrice?string.currency}
							</td>
							<td>
								${(r.resource.resourceRoyalty / 100)?string.percent}
							</td>
							<td>
								<#if (r.royaltyTotal?? && 0 < r.royaltyTotal)>
									${r.royaltyTotal}
								</#if>
							</td>
							<td class="right" title="版税金额(${r.royaltyMoney?string.currency}) = 单价(${r.resource.resourcePrice?string.currency}) x 印数或销量(${r.royaltyTotal}) x 版税率(${(r.resource.resourceRoyalty / 100)?string.percent})">
								<#if (r.royaltyTotal?? && 0 < r.royaltyMoney)>
									${r.royaltyMoney?string.currency}
								</#if>
							</td>
							<td>
								<a href="${request.contextPath}/manage/royalty/${r.resource.resourceId}/${r.royaltyId}">编辑</a>&nbsp;&nbsp;
								<#if (r.royaltyTotal?? && 0 < r.royaltyMoney)>
									<a href="${request.contextPath}/manage/royalty/${r.royaltyId}" onClick="return confirm('确定删除吗？');">删除</a>
								</#if>
							</td>
						</tr>
					</#list>
				<#else>
					<tr bgColor="F9F9F9">
						<td colspan="7">没有数据！</td>
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
