<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>在线阅读 - ${resource.resourceName}</title>
		<link type="text/css" rel="styleSheet" href="${request.contextPath}/css/style.css" />
		<link type="text/css" rel="styleSheet" href="${request.contextPath}/css/flexpaper.css" />
		<script type="text/javascript" src="${request.contextPath}/js/jquery.js"></script>
		<script type="text/javascript" src="${request.contextPath}/js/flexpaper.js"></script>
	</head>
	
	<body>
		<div id="documentViewer" class="flexpaper_viewer flash_plugin"></div>
		<script type="text/javascript">
		<!--
		var fp = new FlexPaperViewer (
			"${request.contextPath}/images/FlexPaperViewer.swf",
			"documentViewer", {
				config : {
					DOC : "/upload/${swfFile}",
					ZoomTransition : "easeOut",
					ZoomTime : 0.5,
					ZoomInterval : 0.2,
					FitWidthOnLoad : true,
					PrintEnabled : false,
					SelectEnabled : false,
					FullScreenAsMaxWindow : false,
					ProgressiveLoading : false,
					MinZoomSize : 0.2,
					MaxZoomSize : 5,
					SearchMatchAll : false,
					InitViewMode : "window",
					ViewModeToolsVisible : true,
					ZoomToolsVisible : true,
					NavToolsVisible : true,
					CursorToolsVisible : true,
					SearchToolsVisible : true,
					SearchString : "",
					Reference : "The reference is test",
					SelectVisible : true,
					localeChain: "zh_CN",
					WMode : "transparent",
					key : "@4516c44a3b7f5ec2893$cc3be1a9ff661cfdef6"
				}
			}
		);
		//-->
		</script>
	</body>
</html>
