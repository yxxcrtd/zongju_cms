<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>模版上传</title>
		<link type="text/css" rel="styleSheet" href="${request.contextPath}/css/style.css" />
	</head>

	<body>
		<div id="content">
			<div id="header">
				<span class="icon_flag">上传模版：</span>
			</div>
			
			<form action="${request.contextPath}/manage/templateNode/save" method="post" enctype="multipart/form-data">
				<table width="700px" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td colspan="2">
							<div class="label">模版上传：</div>
							<div>
								<input type="file" class="input" style="width: 652px;" name="file" />
							</div>
						</td>
						
					</tr>
				</table>
				<input type="hidden" name="typeId" value="${typeId}" />
				<div id="operation">
					<input type="submit" value="保 存" class="button icon_save" />&nbsp;&nbsp;
					<input type="button" value="返 回" class="button icon_return" onClick="javascript:history.back();" />
				</div>
			</form>
		</div>
		<script language="javascript" src="${request.contextPath}/js/jquery.js"></script>
	</body>
</html>
