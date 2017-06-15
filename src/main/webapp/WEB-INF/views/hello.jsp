<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <c:set var="ctx" value="${pageContext.request.contextPath}"/>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<title>主页</title>

<div class="alert alert-warning alert-dismissible" role="alert">
  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
<c:if test="${empty  sessionScope.currentAccount.lastSignin}">
 <strong>您是第一次登录系统!</strong> 请<a>修改密码</a>
</c:if>
</div>
<div class="alert alert-success" role="alert">
<c:if test="${not empty sessionScope.currentAccount.lastSignin}">
	您好，${ sessionScope.currentAccount.account},您上一次登录系统是： 
	<fmt:formatDate value="${sessionScope.currentAccount.lastSignin }" pattern="yyyy-MM-dd HH:mm"/>
</c:if>
</div>