<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="lb" uri="http://www.itlaobing.cn/tags"%>

<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>

<title>学生列表</title>

<!-- 这段代码会被 sitemesh拷贝到application.jsp模板中 -->
<js> <script>
	//toast配置
	toastr.options = {
		"closeButton" : false,
		"debug" : false,
		"newestOnTop" : false,
		"progressBar" : false,
		"positionClass" : "toast-top-right",
		"preventDuplicates" : false,
		"onclick" : null,
		"showDuration" : "300",
		"hideDuration" : "1000",
		"timeOut" : "5000",
		"extendedTimeOut" : "1000",
		"showEasing" : "swing",
		"hideEasing" : "linear",
		"showMethod" : "fadeIn",
		"hideMethod" : "fadeOut"
	}
	//js代码中可以嵌入 java代码（包含JSTL，EL），但是只能在 jsp页面上，js文件中不起作用。
	$(function() {
		<c:if test="${not empty toastMsg}">
		toastr["success"]("${toastMsg}")
		</c:if>

		$(document).on("click", ".btn-delete", function(event) {

			//阻止事件冒泡
			event.stopPropagation();
			//阻止默认的事件
			event.preventDefault();

			//取出要提交的地址
			var href = $(this).prop("href");

			//发起ajax请求
			$.ajax({
				type : "POST",
				url : href,
				data : {
					_method : "DELETE"
				}
			}).success(function(data) {

				if (data) {
					$("#row" + data.id).remove();//移除表格的行
					toastr["success"]("删除" + data.name + "成功！");
				}

			});

		});

	})
</script> </js>

	<div class="panel panel-default">
		<div class="panel-sub-heading">
			<a href="${ctx}/students/create" class="btn btn-primary" style="color:#FFFFFF">添加</a>
		</div>


		<div class="panel-body">
			 <table class="table table-hover table-condensed table-bordered">
			<caption>学生列表</caption>
			<thead>
				<tr>
					<th>序号</th>
					<th>姓名</th>
					<th>性别</th>
					<th>生日</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="student" items="${students}" varStatus="stat">
					<tr id="row${student.id}">
						<th scope="row">${stat.index +1}</th>
						<td>${student.name }</td>
						<td>${student.gender.text }</td>
						<td><fmt:formatDate value="${student.birthday }"
								pattern="yyyy-MM-dd" /></td>
						<td>
							<div class="btn-group" role="group" aria-label="...">
								<a href="${ctx}/students/${student.id}/query" class="btn btn-info"> <i class="fa fa-eye fa-lg"
									aria-hidden="true"></i></a> 
<a href="${ctx}/students/${student.id}/update"
									class="btn btn-success"> <i class="fa fa-edit fa-lg"
									aria-hidden="true"></i></a> <a href="${ctx}/students/${student.id}"
									class="btn btn-danger btn-delete"> <i
									class="fa fa-trash-o fa-lg" aria-hidden="true"></i></a>
							</div>
						</td>
					</tr>
				</c:forEach>

			</tbody>
		</table>
		</div>
	</div>
<lb:pagination pager="${students}" />









