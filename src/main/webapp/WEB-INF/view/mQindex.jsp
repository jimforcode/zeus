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
<style type="text/css">
body {
	text-align: center;
}

table.gridtable {margin：0 auto;
	font-family: verdana, arial, sans-serif;
	font-size: 11px;
	color: #333333;
	border-width: 1px;
	border-color: #666666;
	border-collapse: collapse;
	text-align: center;
	align: center;
}

table.gridtable th {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #dedede;
}

table.gridtable td {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #ffffff;
}
</style>

</head>
<body>
	<div style="text-align: center;">
		<table class="gridtable">
			<tr>
				<th>Info Header 1</th>
				<th>Info Header 2</th>
				<th>Info Header 3</th>
			</tr>
			<c:forEach items="${data}" var="item">
				<tr>
					<td>${item.hostid}</td>
					<td>Text 1B</td>
					<td>Text 1C</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<input type="hidden" name="total" id="total" value="${page.total}">
	<input type="text" name="keyword" id="keyword" value="${keyword}">
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
			var keyword = $("#keyword").val();
			var totalRecords = 39;
			var pageNo = getParameter('page');
			//var keyword = getParameter('keyword');
 			kkpager.generPageHtml({
				pno : pageNo,
				//总页码
				total : totalPage,
				//总数据条数
				totalRecords : totalRecords,
				//链接前部
				hrefFormer : '../demo/page?',
				mode				: 'click', //模式(link 或者 click)
				//链接尾部
				hrefLatter : '',
				getLink : function(n, keyword) {
					return this.hrefFormer + this.hrefLatter + "&page=" + n
				},
				click	: function(n){
					var keyword = $("#keyword").val();
					window.location.href=this.hrefFormer + this.hrefLatter + "&page=" + n+'&keyword='+keyword;
					return true;
				}
			});
		});
	</script>

</body>
</html>