<%--User: �׼�--%>
<%@ page contentType="text/html;charset=GBK" language="java"%>
<html>
  <head>
  		<title>��߲˵�</title>
		<link href="/WebLibary/css/main.css" rel="stylesheet" type="text/css">
		<script type="text/javascript">
			function dis(){//���ػ���ʾ��߲˵�
				var obj = parent.document.getElementById("mainSet");
				if(obj.cols == "195,8,*"){
					barImg.title = "��ʾ�˵�";
					barImg.src="/images/frame_open_02.gif";
					obj.cols="0,8,*";
				} else{
					barImg.title = "���ز˵�";
					barImg.src="/images/frame_close_02.gif";
					obj.cols="195,8,*";
				}
			}
		</script>
  </head>
  
  <body style="background-color: #08377B;">
  		<div style="position:absolute; bottom:50%;">
			<img title = "���ز˵�" name="barImg" src="/images/frame_close_02.gif" style="margin-top: 50px;" onclick="dis();">
		</div>
  </body>
</html>
