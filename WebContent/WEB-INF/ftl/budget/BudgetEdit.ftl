<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title><#if (0 == budget.budgetId)>新建<#else>修改</#if>预算</title>
		<link type="text/css" rel="styleSheet" href="${request.contextPath}/css/style.css" />
	</head>

	<body>
		<div id="content">
			<div id="header">
				<span class="icon_flag"><#if (0 == budget.budgetId)>新建<#else>修改</#if>预算：</span>
			</div>
			
			<form action="${request.contextPath}/manage/budget/save" method="post">
				<div class="label">预算项目名称：</div>
				<div>
					<#if (0 == type)><@s.formInput "budget.budgetItem" "class='input'" /><@s.showErrors classOrStyle="red" /></#if>
					<#if (1 == type)><@s.formInput "budget.budgetItem" "class='input' readonly" /><@s.showErrors classOrStyle="red" /></#if>
				</div>
				
				<div class="label">预算单价：</div>
				<div>
					<#if (0 == type)><@s.formInput "budget.budgetPrice" "class='input'" /><@s.showErrors classOrStyle="red" /></#if>
					<#if (1 == type)><@s.formInput "budget.budgetPrice" "class='input' readonly" /><@s.showErrors classOrStyle="red" /></#if>
				</div>
				
				<div class="label">预算数量：</div>
				<div>
					<#if (0 == type)><@s.formInput "budget.budgetQuantity" "class='input'" /><@s.showErrors classOrStyle="red" /></#if>
					<#if (1 == type)><@s.formInput "budget.budgetQuantity" "class='input' readonly" /><@s.showErrors classOrStyle="red" /></#if>
				</div>
				
				<div class="label">预算备注：</div>
				<div>
					<#if (0 == type)><@s.formTextarea "budget.budgetRemarks" "class='input'" /><@s.showErrors classOrStyle="red" /></#if>
					<#if (1 == type)><@s.formTextarea "budget.budgetRemarks" "class='input' readonly" /><@s.showErrors classOrStyle="red" /></#if>
				</div>
				
				<#if (1 = type)>
					<div class="label">核算方式：</div>
					<div>
						<@s.formRadioButtons "budget.budgetCheckMethod" methodMap />
					</div>
					
					<div class="label">核算总价：</div>
					<div>
						<@s.formInput "budget.budgetCheckMoney" "class='input'" /><@s.showErrors classOrStyle="red" />
					</div>
				</#if>
				
				<div id="operation">
					<input type="submit" value="<#if (0 == budget.budgetId)>保 存<#else>修 改</#if>" class="button icon_save" />&nbsp;&nbsp;
					<input type="button" value="返 回" class="button icon_return" onClick="javascript:history.back();" />
				</div>
				
				<@s.formHiddenInput "budget.budgetId" />
				<@s.formHiddenInput "budget.budgetCheckMethod" />
				<@s.formHiddenInput "budget.budgetCheckMoney" />
				<input type="hidden" name="type" value="${type}" />
			</form>
		</div>
	</body>
</html>
