<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>模板节点映射管理</title>
		<link type="text/css" rel="styleSheet" href="${request.contextPath}/css/style.css" />
	</head>

	<body>
		<div id="content">
			<div id="new">
			    <a class="button icon_minus" href="${request.contextPath}/manage/cnonix/delete">删除所有映射信息</a>
				<span id="tips"><#if tips??>${tips}</#if></span>
			</div>
			<div id="header">
				<span class="icon_flag">模版节点映射信息列表</span>
				<span id="tips"></span>
			</div>
			
	  		<table width="100%" border="0" cellspacing="0" cellpadding="0">
	  			<tr id="thead">
					<td width="10%">模版ID</td>
					<td width="50%" class="left">目标模版节点路径</td>
					<td width="40%" class="left">用户模版节点路径</td>
  				</tr>
				<#if (targetTemplateList?? && 0 < targetTemplateList?size)>
					<#list targetTemplateList as t>
						<tr bgColor="#<#if (0 == t_index % 2)>F9F9F9<#else>FFFFFF</#if>" onMouseOver="changeBgColor(this, '#ECECEC');" onMouseOut="changeBgColor(this, '#<#if (0 == t_index % 2)>F9F9F9<#else>FFFFFF</#if>');">
							<td>
								${t.templateNodeId}
							</td>
							<td class="left">
								${t.templateNodePath}
							</td>
							<td class="left">
								<select class="sel">
									<option value="0">请选择用户模版</option>
									<#list userTemplateList as u>
										<option value="${u.templateNodeId}" <#list MappingList as map><#if (t.templateNodeId == map.templateMappingTargetTemplateId&&u.templateNodeId == map.templateMappingUserTemplateId)>selected="selected"</#if></#list>>${u.templateNodePath}</option>
									</#list>
								</select>
								<span class="tid" value="${t.templateNodeId}"></span>
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
		<script language="javascript">
		<!--
		$(function() {
			$(".sel").on("change", function() {
				var This = $(this);
				$.post("${request.contextPath}/manage/cnonix/save", { "templateMappingUserTemplateId" : This.children("option:selected").val(), "templateMappingTargetTemplateId" : This.siblings(".tid").attr("value") }, function(data) {
					if ("savesuccess" == data) {
						$("#tips").show().html("映射保存成功！").fadeOut(5000);
					} else if ("updatesuccess" == data) {
						$("#tips").show().html("映射修改成功！").fadeOut(5000);
					} else if ("delsuccess" == data) {
						$("#tips").show().html("映射删除成功！").fadeOut(5000);
					} else {
						alert("发生了未知的错误，请与管理员联系！");
					}
				});
			});
		});
		//-->
		</script>
	</body>
</html>
