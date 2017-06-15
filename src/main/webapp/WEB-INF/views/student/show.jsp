<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>详细信息</title>

<div class="center-block">
<ul class="list-group">
  <li class="list-group-item">姓名：${student.name }</li>
  <li class="list-group-item">性别：${student.gender.text}</li>
  <li class="list-group-item">时间：<fmt:formatDate value="${student.birthday }"/></li>
  <li class="list-group-item">该学生账号：${student.account.account }</li>
  <div class="panel-sub-heading">
			<a href="${ctx}/students" class="btn btn-primary" style="color:#FFFFFF">返回</a>
  </div>
</ul>
</div>
