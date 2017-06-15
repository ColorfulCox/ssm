<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<title>添加学生</title>

<!-- 会生成html的 form标签 
		使用springmvc的form标签最大的好处是 可以与后台的 对象进行绑定
		-->
<div style="margin-top:130px"></div>
<h3>添加学生</h3>
<hr/>
<%@include file="_form.jsp"%>