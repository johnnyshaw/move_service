<%@ page language="Java" contentType="text/html;charset=UTF-8"
	pageEncoding="utf-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="application/json;charset=UTF-8">
<style type="text/css">
.head-area {
	background: rgba(0, 0, 0, 0) url("images/banner-6.jpg") repeat scroll 0
		0;
	margin-left: 500px;
}

.head-area {
	height: 336px;
	padding-left: 13.6%;
	width: 600px;
}

.head-area .head-content {
	color: #fff;
	font-size: 14px;
	line-height: 30px;
	margin-bottom: 3.7%;
	width: 68.5%;
}

.head-area h2 {
	color: #fff;
	font-size: 36px;
	font-weight: normal;
	margin-bottom: 20px;
	padding-top: 50px;
	position: relative;
}

.head-area h2 img {
	padding-right: 20px;
}

.main-content .head-area .head-operation .tc-btn {
	font-size: 18px;
	font-weight: normal;
	height: 50px;
	line-height: 50px;
	padding: 0 7.8%;
}

.tc-btn-1 {
	background-color: #0071ce;
	color: #fff;
	text-decoration: none;
}

.tc-btn {
	border: medium none;
	color: #fff;
	cursor: pointer;
	display: inline-block;
	font-size: 14px;
	font-weight: bold;
	line-height: 35px;
	overflow: visible;
	padding: 0 20px;
	vertical-align: middle;
}

.inputText{
	border:2;border-color:red;
}
</style>
<title>迁移图片数据</title>
<script src="http://192.168.7.7:8082/ui/ec/js/jquery/jquery.js"></script>
<script type="text/javascript">
	function removeImage(str) {
		alert("开始迁移!");
		var row1 = "";
		if (str == "1") {
			row1 = $('#row').val();
			if (row1 == "") {
				alert("请输入需要迁移的数量!");
				$('#row').addClass('inputText');
				$('#row').focus();
				return false;
			}
		}
		var url = "move/imagemove";
		$.ajax({
			type : "GET",
			async : false,
			url : url,
			dataType : "html",
			contentType : "application/json",
			data : JSON.stringify({
				row : row1
			}),
			success : function(data) {
				alert(eval("("+data+")").result);
			},
			error:function(data){
				alert("迁移出现问题,请稍后重试!");				
			}
		});
	}
	function rowChange(){
		$('#row').removeClass('inputText');
	}
</script>
</head>
<body>
	<div style="text-align: center;">
		<h2>迁移图片数据至万象优图</h2>
	</div>
	<form action="move/imagemove" method="GET">
		<div class="head-area">
			<h2>
				<img src="images/ci-1.png" class="icon">万象优图&nbsp;CI
			</h2>
			<div class="head-content">万象优图（Cloud
				Image）为移动开发者提供高可靠的图片存储服务，高成功率、高速的图片上传、下载服务，多样灵活的图片加工服务，深度定制的图片内容处理服务，如黄图审核。
			</div>
			<div class="head-operation" style="color: white;">
				请输入需要迁移的数量:&nbsp;<input type="text" id="row" name="row" value="10" onkeydown="rowChange()"/>
				<a class="tc-btn tc-btn-1" href="javascript:void(0);"
					onclick="removeImage(1);">部分迁移</a> <a class="tc-btn tc-btn-1"
					href="javascript:void(0);" onclick="removeImage(2);">全部迁移</a> <br />
				<!-- <font color="red">*注:不输入视为迁移全部。</font> -->
			</div>
		</div>
	</form>
</body>


</html>
