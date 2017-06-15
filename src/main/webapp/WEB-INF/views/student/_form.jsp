<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<form:form cssClass="form-horizontal" modelAttribute="student">

	<div class="form-group">
		<label class="col-sm-2 control-label">姓名</label>
		<div class="col-sm-10">
			<form:input path="name" cssClass="form-control" placeholder="请输入姓名" />
			<form:errors path="name" cssStyle="color:red"></form:errors>
		</div>
	</div>

	<div class="form-group">
		<label class="col-sm-2 control-label">性别</label>
		<div class="col-sm-10">
			<label class="checkbox-inline"> 
				<!-- path 表示student中的property -->
				<form:radiobuttons  cssStyle="margin-right:15px;" path="gender" items="${genders}" itemLabel="text" />
			</label>
		</div>
	</div>
		
	<div class="form-group">
		<label class="col-sm-2 control-label">生日</label>
		<div class="col-sm-10">
			<form:input path="birthday" cssClass="form-control input-date"   placeholder="请输入生日" /><!-- path 表示student中的property -->
			<form:errors path="birthday" cssStyle="color:red"></form:errors>
		</div>
	</div>
	
	<div class="form-group">
		<label class="col-sm-2 control-label"></label>
		<div class="col-sm-10">
			<input type="submit" class="btn btn-success btn-lg" value="保存" />
		</div>
	</div>


	
</form:form>
	