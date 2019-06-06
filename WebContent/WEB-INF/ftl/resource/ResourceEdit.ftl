<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title><#if (0 == resource.resourceId)>新建<#else>修改</#if>资源</title>
		<link type="text/css" rel="styleSheet" href="${request.contextPath}/css/style.css" />
	</head>

	<body>
		<div id="content">
			<div id="header">
				<span class="icon_flag"><#if (0 == resource.resourceId)>新建<#else>修改</#if>资源：</span>
			</div>
			
			<form action="${request.contextPath}/manage/resource/save" method="post" enctype="multipart/form-data">
				<table width="900px" cellspacing="0" cellpadding="0">
					<tr>
						<td class="left1">
							<div class="label">资源分类：</div>
							<div>
								<@s.formSingleSelect "resource.category.categoryId" categoryMap "class='selectStyle'" />
							</div>
						</td>
						<td class="left1">
							<div class="label">资源名称：</div>
							<div>
								<@s.formInput "resource.resourceName" "class='input'" /><@s.showErrors classOrStyle="red" />
							</div>
						</td>
					</tr>
					<tr>
						<td class="left1">
							<div class="label">资源作者：</div>
							<div>
								<@s.formInput "resource.resourceAuthor" "class='input'" /><@s.showErrors classOrStyle="red" />
							</div>
						</td>
						<td class="left1">
							<div class="label">资源出版时间：（格式为：2012-01-01）</div>
							<div>
								<@s.formInput "resource.resourcePublishDate" "class='input'" /><@s.showErrors classOrStyle="red" />
							</div>
						</td>
					</tr>
					<tr>
						<td class="left1">
							<div class="label">资源ISBN：</div>
							<div>
								<@s.formInput "resource.resourceISBN" "class='input'" /><@s.showErrors classOrStyle="red" />
							</div>
						</td>
						<td class="left1">
							<div class="label">资源价格：</div>
							<div>
								<@s.formInput "resource.resourcePrice" "class='input'" /><@s.showErrors classOrStyle="red" />
							</div>
						</td>
					</tr>
					<tr>
						<td class="left1">
							<div class="label">资源出版社：</div>
							<div>
								<@s.formInput "resource.resourcePublisher" "class='input'" /><@s.showErrors classOrStyle="red" />
							</div>
						</td>
						<td class="left1">
							<div class="label">资源版次：</div>
							<div>
								<@s.formInput "resource.resourceEdition" "class='input'" /><@s.showErrors classOrStyle="red" />
							</div>
						</td>
					</tr>
					<tr>
						<td class="left1">
							<div class="label">资源页数：</div>
							<div>
								<@s.formInput "resource.resourcePage" "class='input'" /><@s.showErrors classOrStyle="red" />
							</div>
						</td>
						<td class="left1">
							<div class="label">资源装帧：</div>
							<div>
								<@s.formInput "resource.resourceFrame" "class='input'" /><@s.showErrors classOrStyle="red" />
							</div>
						</td>
					</tr>
					<tr>
						<td class="left1">
							<div class="label">资源开本：</div>
							<div>
								<@s.formInput "resource.resourceFormat" "class='input'" /><@s.showErrors classOrStyle="red" />
							</div>
						</td>
						<td class="left1">
							<div class="label">资源印张：</div>
							<div>
								<@s.formInput "resource.resourceSheet" "class='input'" /><@s.showErrors classOrStyle="red" />
							</div>
						</td>
					</tr>
					<tr>
						<td colspan="2" class="left1">
							<div class="label">资源封面：</div>
							<div>
								<input type="file" class="input" style="width: 585px;" name="fileCover" />
								<@s.formHiddenInput "resource.resourceCover" />
								
								版税税率：<select name="resourceRoyalty" class="selectStyle" style="width: 100px;">
									<#list 1..99 as i>
										<option>${i}</option>
									</#list>
								</select>
							</div>
						</td>
					</tr>
					<tr>
						<td colspan="2" class="left1">
							<div class="label">资源附件：</div>
							<div>
								<input type="file" class="input" style="width: 585px;" name="file" />
								<@s.formHiddenInput "resource.resourceFile" />
								<@s.formHiddenInput "resource.resourceLocation" />
							</div>
						</td>
					</tr>
				</table>
				
				<div id="operation">
					<input type="submit" value="<#if (0 == resource.resourceId)>保 存<#else>修 改</#if>" class="button icon_save" />&nbsp;&nbsp;
					<input type="button" value="返 回" class="button icon_return" onClick="javascript:history.back();" />
				</div>
				
				<@s.formHiddenInput "resource.resourceId" />
				<@s.formHiddenInput "resource.resourceCreateTime" />
			</form>
		</div>
		<script language="javascript" src="${request.contextPath}/js/jquery.js"></script>
		<script type="text/javascript">
		<!--
		$(function() {
			$(".selectStyle").val("${resource.categoryId}");
		});
		//-->
		</script>
	</body>
</html>
