<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>产品<#if (0 == type)>预算<#else>核算</#if></title>
		<link type="text/css" rel="styleSheet" href="${request.contextPath}/css/style.css" />
	</head>

	<body>
		<div id="content">
			<div id="new">
				<#if (0 == type)>
					<a class="button icon_add" href="${request.contextPath}/manage/budget/edit/0&type=${type}">新建预算</a>
				<#else>
					<a class="button icon_add" href="javascript:;"><span style="color: #909090">新建核算</span></a>
				</#if>
				<span id="tips"><#if tips??>${tips}</#if></span>
			</div>
			
			<div id="header">
				<span class="icon_flag">产品<#if (0 == type)>预算<#else>核算</#if>列表</span>
			</div>
			
	  		<table width="100%" border="0" cellspacing="0" cellpadding="0">
	  			<tr id="thead">
					<td width="10%">序号</td>
					<td width="<#if (0 == type)>4<#else>2</#if>5%" class="left">预算项目名称</td>
					<td width="10%">预算单价</td>
					<td width="10%">预算数量</td>
					<td width="10%">预算总价</td>
					<#if (1 == type)>
						<td width="10%" class="blue">核算总价</td>
						<td width="10%" class="blue">核算方式</td>
					</#if>
					<td width="15%">操作</td>
  				</tr>
				<#if (budgetList?? && 0 < budgetList?size)>
					<#list budgetList as b>
						<tr bgColor="#<#if (0 == b_index % 2)>F9F9F9<#else>FFFFFF</#if>" onMouseOver="changeBgColor(this, '#ECECEC');" onMouseOut="changeBgColor(this, '#<#if (0 == b_index % 2)>F9F9F9<#else>FFFFFF</#if>');">
							<td>
								${b_index + 1}
							</td>
							<td class="left" title="${b.budgetRemarks}">
								${b.budgetItem}
							</td>
							<td class="right">
								${b.budgetPrice?string.currency}
							</td>
							<td>
								${b.budgetQuantity}
							</td>
							<td class="right">
								${b.budgetMoney?string.currency}
							</td>
							<#if (1 == type)>
								<td class="right">
									<#if b.budgetCheckMoney?? && 0 < b.budgetCheckMoney>
										${b.budgetCheckMoney?string.currency}&nbsp;
										<span class="red">
											<#if (b.budgetCheckMoney = b.budgetMoney)>=
											<#elseif (b.budgetCheckMoney > b.budgetMoney)>&uarr;
											<#elseif (b.budgetCheckMoney < b.budgetMoney)>&darr;
											</#if>
										<span>
									</#if>
								</td>
								<td>
									<#if b.budgetCheckMoney?? && 0 < b.budgetCheckMoney>
										<#if ("0" == b.budgetCheckMethod)>其他方式
										<#elseif ("1" == b.budgetCheckMethod)>实洋价
										<#elseif ("2" == b.budgetCheckMethod)>折扣价
										</#if>
									</#if>
								</td>
							</#if>
							<td>
								<a href="${request.contextPath}/manage/budget/edit/${b.budgetId}&type=${type}">编辑</a>&nbsp;&nbsp;
								<#if (0 == type)>
									<a href="${request.contextPath}/manage/budget/del/${b.budgetId}&type=${type}" onClick="return confirm('确定删除吗？');">删除</a>
								<#else>
									<#if b.budgetCheckMoney?? && 0 < b.budgetCheckMoney>
										<a href="${request.contextPath}/manage/budget/reset/${b.budgetId}&type=${type}" onClick="return confirm('确定重置核算吗？');">重置</a>
									</#if>
								</#if>
							</td>
						</tr>
					</#list>
				<#else>
					<tr bgColor="F9F9F9">
						<td colspan="<#if (0 == type)>6<#else>8</#if>">没有数据！</td>
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
