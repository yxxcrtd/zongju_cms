<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" website="text/html; charset=UTF-8" />
		<title>${w.websiteTitle}</title>
		<link type="text/css" rel="styleSheet" href="${request.contextPath}/css/style.css" />
	</head>

	<body>
		<div id="banner">
			<img src="${request.contextPath}/upload/${w.websiteName}/${w.websiteLOGO}" width="100%" height="120"/>
		</div>

		<div id="container">
			<div id="menu">
				<ul>
					<li><a class="a active" href="${request.contextPath}/upload/${w.websiteName}/">网站首页</a></li>
					<#if (channelList?? && 0 < channelList?size)>
						<#list channelList as c>
							<li><a class="a" href="${c.channelId}.html">${c.channelTitle}</a></li>
						</#list>
					</#if>
				</ul>
			</div>
			
			<div id="content">
				<#if (resourceList?? && 0 < resourceList?size)>
					<#list resourceList as s>
						<div class="classList">
				        	<div class="img"><img src="${request.contextPath}/upload/${s.resourceCover!}" width="120" height="160" onerror="javascript:this.src='${request.contextPath}/images/noimg.jpg'" /></div>
				            <div class="details">
				            	<p class="red">书名：<a class="red" href="${request.contextPath}/manage/resource/view/${s.resourceId}" title="${s.resourceName!}" target="_blank"><#if (15 < s.resourceName?length)>${s.resourceName[0..14]}...<#else>${s.resourceName}</#if></a></p>
				                <p class="gray">价格：<span class="price"><#if s.resourcePrice??>￥${s.resourcePrice!?string.currency?substring(1)}</#if></span></p>
				                <p class="gray">作者：${s.resourceAuthor!}</p>
				                <p class="gray">ISBN：${s.resourceISBN!}</p>
				                <p class="gray">版次：第${s.resourceEdition!}版&nbsp;&nbsp;装订：${s.resourceFrame!'平装'}</p>
				                <p class="gray">出版社：${s.resourcePublisher!}</p>
				                <p>
				                	<a class="nowBuy" href="${request.contextPath}/manage/resource/view/${s.resourceId}" target="_blank">在线阅读</a>
				                	<#if user??>
				                		<span class="buyit" value="${s.iresourceId}">加入购物车</span>
				                		<span class="status"></span>
				                	</#if>
				                </p>
				            </div>
				        </div>
					</#list>
				</#if>
			</div>
		</div>
		
		<div id="footer">
			<p>技术开发：英国出版科技集团中国公司 - 北京英捷特数字出版技术有限公司</p>
			<p>All Rights Reserved.</p>
		</div>
	</body>
</html>
