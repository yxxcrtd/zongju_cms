<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" website="text/html; charset=UTF-8" />
		<title>${c.channelTitle}</title>
		<link type="text/css" rel="styleSheet" href="${request.contextPath}/css/style.css" />
	</head>

	<body>
		<div id="banner">
			<img src="${request.contextPath}/upload/${w.websiteName}/${w.websiteLOGO}" width="100%" height="120"/>
		</div>

		<div id="container">
			<div id="menu">
				<ul>
					<li><a class="a  <#if !active>active</#if>" href="${request.contextPath}/upload/${w.websiteName}/">网站首页</a></li>
					<#if (channelList?? && 0 < channelList?size)>
						<#list channelList as l>
							<li><a class="a <#if (c.channelId == l.channelId)>active</#if>" href="${l.channelId}.html">${l.channelTitle}</a></li>
						</#list>
					</#if>
				</ul>
			</div>
			
			<div id="main">
				<div id="main_left">
					<span class="list_title">栏目列表</span>
					
					<ul>
						<#if (columnList?? && 0 < columnList?size)>
							<#list columnList as l>
								<li class="li_title">
									<a href="javascript:;">${l.columnTitle}</a>
									<#if (contentList?? && 0 < contentList?size)>
										<ul>
											<#list contentList as c>
												<#if (l.columnId == c.columnId)>
													<li class="content_title">
														<a class="content_a" href="${l.columnId}_${c.contentId}.html">${c.contentTitle}</a>
													</li>
												</#if>
											</#list>
										</ul>
									</#if>
								</li>
							</#list>
						</#if>
					</ul>
				</div>
				<div class="gap_column">&nbsp;</div>
				<div id="main_right">
					<img src="${request.contextPath}/upload/${w.websiteName}/${c.channelLOGO}" width="700" />
					
					<div class="gap_row"></div>
					
					<div id="content_main">
						<div id="content_main_title">最新内容：</div>
						<div class="content_main_list">
							<#if (columnList?? && 0 < columnList?size)>
								<#list columnList as l>
									<#if (contentList?? && 0 < contentList?size)>
										<#list contentList as c>
											<#if (l.columnId == c.columnId)>
												<li class="content_title">
													<a class="content_a" href="#">${c.contentTitle}</a>
												</li>
											</#if>
										</#list>
									</#if>
								</#list>
							</#if>
						</div>
					</div>
				</div>
				
			</div>
		</div>
		
		<div id="footer">
			<p>技术开发：英国出版科技集团中国公司 - 北京英捷特数字出版技术有限公司</p>
			<p>All Rights Reserved.</p>
		</div>
	</body>
</html>
