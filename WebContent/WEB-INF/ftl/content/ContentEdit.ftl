<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title><#if (0 == content.contentId)>新建<#else>修改</#if>内容</title>
		<link type="text/css" rel="styleSheet" href="${request.contextPath}/css/style.css" />
	    <script language="javascript" src="${request.contextPath}/js/jquery.js"></script>
	    <script type="text/javascript">
	$(document).ready(function(){
    $("#websiteId").change(function(){
    changeChannels();
    });
    $("#channelId").change(function(){
    changeColumns();
    });
    })
   function changeChannels(){
	         $.ajax({
            type : "POST",
             url : "${request.contextPath}/manage/content/changeChannels",
            data : {
                   websiteId:$("#websiteId").val()
            },
            dataType: 'json',
            success : function(data) {
            $("#channelId").empty();
                jQuery.each(data.channelList, function(i,item){
                $("#channelId").append("<option value='"+item.channelId+"'>"+item.channelTitle+"</option>");  
            });  
            $("#columnId").empty();
                jQuery.each(data.columnList, function(i,item){  
                $("#columnId").append("<option value='"+item.columnId+"'>"+item.columnTitle+"</option>");  
            });
            },
            error : function(data) {
                alert("操作失败！");
            }
        });
	    }
	       function changeColumns(){
	         $.ajax({
            type : "POST",
             url : "${request.contextPath}/manage/content/changeColumns",
            data : {
                   channelId:$("#channelId").val()
            },
            dataType: 'json',
            success : function(data) {
            $("#columnId").empty();
                jQuery.each(data.columnList, function(i,item){  
                $("#columnId").append("<option value='"+item.columnId+"'>"+item.columnTitle+"</option>");  
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
				<span class="icon_flag"><#if (0 == content.contentId)>新建<#else>修改</#if>内容：</span>
			</div>
			
			<form action="${request.contextPath}/manage/content/save" method="post">
			    <div class="label">站点：</div>
				<div>
					<@s.formSingleSelect "channel.websiteId" websiteMap "class='selectStyle'"/>
				</div>
				<div class="label">频道：</div>
				<div>
					<@s.formSingleSelect "column.channelId" channelMap "class='selectStyle'" />
				</div>
				<div class="label">栏目：</div>
				<div>
					<@s.formSingleSelect "content.columnId" columnMap "class='selectStyle'" /><@s.showErrors classOrStyle="red" />
				</div>
				<div class="label">内容标题：</div>
				<div>
					<@s.formInput "content.contentTitle" "class='input'" /><@s.showErrors classOrStyle="red" />
				</div>
				
				<div class="label">内容主体：</div>
				<div>
					<@s.formTextarea "content.contentContent" "class='input', style='width: 960px; height: 500px;'" /><@s.showErrors classOrStyle="red" />
				</div>
				
				<div id="operation">
					<input type="submit" value="<#if (0 == content.contentId)>保 存<#else>修 改</#if>" class="button icon_save" />&nbsp;&nbsp;
					<input type="button" value="返 回" class="button icon_return" onClick="javascript:history.back();" />
				</div>
				
				<@s.formHiddenInput "content.contentId" />
				<@s.formHiddenInput "content.contentCreateTime" />
			</form>
		</div>
		
		<script language="javascript" src="${request.contextPath}/js/kindeditor.js"></script>
		<script language="javascript">
		<!--
		var editor;
		KindEditor.ready(function(K) {              
			editor = K.create("#contentContent");
		});
		//-->
		
		
		</script>
	</body>
</html>
