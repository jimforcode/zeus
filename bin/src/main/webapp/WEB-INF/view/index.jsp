<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="common/taglibs.jsp"%>
<%@ include file="common/meta.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript"
	src="${ctx }/resources/js/third/kkpager.js"></script>
<link rel="stylesheet" type="text/css"
	href="${ctx }/resources/css/kkpager/kkpager_orange.css" />

<title>hi</title>
</head>
<body>
   <input type="hidden" name="total" id="total" value="${page.total}">
	<div style="width: 800px; margin: 0 auto;">
		<div id="kkpager"></div>
	</div>
	
	<script type="text/javascript">
		function getParameter(name) {
			var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
			var r = window.location.search.substr(1).match(reg);
			if (r != null)
				return unescape(r[2]);
			return null;
		}
       
		//init
		$(function() {
 			var totalPage = $("#total").val();
			var totalRecords = 39;
			var pageNo = getParameter('page');
			kkpager.generPageHtml({
				pno : pageNo,
				//总页码
				total : totalPage,
				//总数据条数
				totalRecords : totalRecords,
				//链接前部
				hrefFormer : '../demo/page',
				//链接尾部
				hrefLatter : '',
				getLink : function(n) {
					return this.hrefFormer + this.hrefLatter + "?page=" + n;
				}
			});
		});
	</script>

</body>
</html>