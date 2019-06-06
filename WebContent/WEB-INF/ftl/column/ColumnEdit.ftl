<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" column="text/html; charset=UTF-8" />
		<title><#if (0 == column.columnId)>新建<#else>修改</#if>栏目</title>
		<link type="text/css" rel="styleSheet" href="${request.contextPath}/css/style.css" />
	    <script language="javascript" src="${request.contextPath}/js/jquery.js"></script>
	    <script type="text/javascript">
	$(document).ready(function(){
    $("#websiteId").change(function(){
    changeChannels();
    });
    })
   function changeChannels(){
	         $.ajax({
            type : "POST",
             url : "${request.contextPath}/manage/column/changeChannels",
            data : {
                   websiteId:$("#websiteId").val()
            },
            dataType: 'json',
            success : function(data) {
            $("#channelId").empty();
                jQuery.each(data.channelList, function(i,item){  
                $("#channelId").append("<option value='"+item.channelId+"'>"+item.channelTitle+"</option>");  
            });
            },
            error : function(data) {
                alert("操作失败！");
            }
        });
	    }
	    </script>

	</head>

	<body>
		<div id="content">
			<div id="header">
				<span class="icon_flag"><#if (0 == column.columnId)>新建<#else>修改</#if>栏目：</span>
			</div>
			
			<form action="${request.contextPath}/manage/column/save" method="post" id="form">
				<div class="label">站点：</div>
				<div>
					<@s.formSingleSelect "channel.websiteId" websiteMap "class='selectStyle'"/>
				</div>
				<div class="label">频道：</div>
				<div>
					<@s.formSingleSelect "column.channelId" channelMap "class='selectStyle'" /><@s.showErrors classOrStyle="red" />
				</div>
				<div class="label">栏目标题：</div>
				<div>
					<@s.formInput "column.columnTitle" "class='input'" /><@s.showErrors classOrStyle="red" />
				</div>
				
				<div id="operation">
					<input type="submit" value="<#if (0 == column.columnId)>保 存<#else>修 改</#if>" class="button icon_save"/>&nbsp;&nbsp;
					<input type="button" value="返 回" class="button icon_return" onClick="javascript:history.back();" />
				</div>
				<@s.formHiddenInput "column.columnId" />
			</form>
		</div>
	</body>
</html>
