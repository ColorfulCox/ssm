<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<title>学生管理<sitemesh:write property='title' /></title>
		<sitemesh:write property='css'/>
		<meta name="description" content="overview &amp; stats" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

		<!-- bootstrap & fontawesome -->
		<link rel="stylesheet" href="${ctx}/assets/css/bootstrap.css" />
		<link rel="stylesheet" href="${ctx}/assets/css/font-awesome.css" />

		<!-- page specific plugin styles -->

		<!-- text fonts -->
		<link rel="stylesheet" href="${ctx}/assets/css/ace-fonts.css" />
		<link href="${ctx}/assets/css/toastr.min.css" rel="stylesheet" media="screen">

		<!-- ace styles -->
		<link rel="stylesheet" href="${ctx}/assets/css/ace.css" class="ace-main-stylesheet" id="main-ace-style" />

		<!--[if lte IE 9]>
			<link rel="stylesheet" href="${ctx}/assets/css/ace-part2.css" class="ace-main-stylesheet" />
		<![endif]-->

		<!--[if lte IE 9]>
		  <link rel="stylesheet" href="${ctx}/assets/css/ace-ie.css" />
		<![endif]-->

		<!-- inline styles related to this page -->

		<!-- ace settings handler -->
		<script src="${ctx}/assets/js/ace-extra.js"></script>

		<!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->

		<!--[if lte IE 8]>
		<script src="${ctx}/assets/js/html5shiv.js"></script>
		<script src="${ctx}/assets/js/respond.js"></script>
		<![endif]-->
	</head>

	<body class="no-skin">
		<!-- #section:basics/navbar.layout -->
		<div id="navbar" class="navbar navbar-default">
			<script type="text/javascript">
				try{ace.settings.check('navbar' , 'fixed')}catch(e){}
			</script>

			<div class="navbar-container" id="navbar-container">
				<!-- #section:basics/sidebar.mobile.toggle -->
				<button type="button" class="navbar-toggle menu-toggler pull-left" id="menu-toggler" data-target="#sidebar">
					<span class="sr-only">Toggle sidebar</span>

					<span class="icon-bar"></span>

					<span class="icon-bar"></span>

					<span class="icon-bar"></span>
				</button>

				<!-- /section:basics/sidebar.mobile.toggle -->
				<div class="navbar-header pull-left">
					<!-- #section:basics/navbar.layout.brand -->
					<a href="#" class="navbar-brand">
						<small>
							<i class="fa fa-leaf"></i>
							
						</small>
					</a>

					<!-- /section:basics/navbar.layout.brand -->

					<!-- #section:basics/navbar.toggle -->

					<!-- /section:basics/navbar.toggle -->
				</div>

				<!-- #section:basics/navbar.dropdown -->
				<div class="navbar-buttons navbar-header pull-right" role="navigation">
					<ul class="nav ace-nav">
						<!-- #section:basics/navbar.user_menu -->
						<li class="light-blue">
							<a data-toggle="dropdown" href="#" class="dropdown-toggle">
								<img class="nav-user-photo" src="${ctx}/assets/avatars/user.jpg" alt="Jason's Photo" />
								<span class="user-info">
									<small>欢迎您,</small>
									${sessionScope.currentAccount.account}
								</span>

								<i class="ace-icon fa fa-caret-down"></i>
							</a>

							<ul class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
								<li>
									<a href="#">
										<i class="ace-icon fa fa-cog"></i>
										设置
									</a>
								</li>

								<li>
									<a href="profile.html">
										<i class="ace-icon fa fa-user"></i>
										个人信息
									</a>
								</li>

								<li class="divider"></li>

								<li>
									<a href="${ctx}/signOut">
										<i class="ace-icon fa fa-power-off"></i>
										退出
									</a>
								</li>
							</ul>
						</li>

						<!-- /section:basics/navbar.user_menu -->
					</ul>
				</div>

				<!-- /section:basics/navbar.dropdown -->
			</div><!-- /.navbar-container -->
		</div>

		<!-- /section:basics/navbar.layout -->
		<div class="main-container" id="main-container">
			<script type="text/javascript">
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
			</script>

			<!-- #section:basics/sidebar -->
			
			
			<%@include file="_sidebar.jsp" %>

			<!-- /section:basics/sidebar -->
			<div class="main-content">
			
			
				<div class="main-content-inner">
					<!-- #section:basics/content.breadcrumbs -->
					<div class="breadcrumbs" id="breadcrumbs">
						<script type="text/javascript">
							try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
						</script>

						<ul class="breadcrumb">
							<li>
								<i class="ace-icon fa fa-home home-icon"></i>
								<a href="${ctx }/students">Home</a>
							</li>
							<li class="active">Dashboard</li>
						</ul><!-- /.breadcrumb -->

						<!-- #section:basics/content.searchbox -->
						<div class="nav-search" id="nav-search">
							<form class="form-search">
								<span class="input-icon">
									<input type="text" placeholder="Search ..." class="nav-search-input" id="nav-search-input" autocomplete="off" />
									<i class="ace-icon fa fa-search nav-search-icon"></i>
								</span>
							</form>
						</div><!-- /.nav-search -->

						<!-- /section:basics/content.searchbox -->
					</div>
<sitemesh:write property='body'/>
					<!-- /section:basics/content.breadcrumbs -->
				</div>
				
				
			</div><!-- /.main-content -->

			<div class="footer">
				<div class="footer-inner">
					<!-- #section:basics/footer -->
					<div class="footer-content">
						<span class="bigger-120">
							<span class="blue bolder">Ace</span>
							Application &copy; 2013-2014
						</span>

						&nbsp; &nbsp;
						<span class="action-buttons">
							<a href="#">
								<i class="ace-icon fa fa-twitter-square light-blue bigger-150"></i>
							</a>

							<a href="#">
								<i class="ace-icon fa fa-facebook-square text-primary bigger-150"></i>
							</a>

							<a href="#">
								<i class="ace-icon fa fa-rss-square orange bigger-150"></i>
							</a>
						</span>
					</div>

					<!-- /section:basics/footer -->
				</div>
			</div>

			<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
				<i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
			</a>
		</div><!-- /.main-container -->

		<!-- basic scripts -->

		<!--[if !IE]> -->
		<script type="text/javascript">
			window.jQuery || document.write("<script src='${ctx}/assets/js/jquery.js'>"+"<"+"/script>");
		</script>

		<!-- <![endif]-->

		<!--[if IE]>
<script type="text/javascript">
 window.jQuery || document.write("<script src='../assets/js/jquery1x.js'>"+"<"+"/script>");
</script>
<![endif]-->
		<script type="text/javascript">
			if('ontouchstart' in document.documentElement) document.write("<script src='../assets/js/jquery.mobile.custom.js'>"+"<"+"/script>");
		</script>
		<script src="${ctx}/assets/js/bootstrap.js"></script>
		<script src="${ctx}/assets/js/toastr.min.js"></script>

		<!-- page specific plugin scripts -->

		<!--[if lte IE 8]>
		  <script src="${ctx}/assets/js/excanvas.js"></script>
		<![endif]-->
		<script src="${ctx}/assets/js/jquery-ui.custom.js"></script>
		<script src="${ctx}/assets/js/jquery.ui.touch-punch.js"></script>
		<script src="${ctx}/assets/js/jquery.easypiechart.js"></script>
		<script src="${ctx}/assets/js/jquery.sparkline.js"></script>
		<script src="${ctx}/assets/js/flot/jquery.flot.js"></script>
		<script src="${ctx}/assets/js/flot/jquery.flot.pie.js"></script>
		<script src="${ctx}/assets/js/flot/jquery.flot.resize.js"></script>

		<!-- ace scripts -->
		<script src="${ctx}/assets/js/ace/elements.scroller.js"></script>
		<script src="${ctx}/assets/js/ace/elements.colorpicker.js"></script>
		<script src="${ctx}/assets/js/ace/elements.fileinput.js"></script>
		<script src="${ctx}/assets/js/ace/elements.typeahead.js"></script>
		<script src="${ctx}/assets/js/ace/elements.wysiwyg.js"></script>
		<script src="${ctx}/assets/js/ace/elements.spinner.js"></script>
		<script src="${ctx}/assets/js/ace/elements.treeview.js"></script>
		<script src="${ctx}/assets/js/ace/elements.wizard.js"></script>
		<script src="${ctx}/assets/js/ace/elements.aside.js"></script>
		<script src="${ctx}/assets/js/ace/ace.js"></script>
		<script src="${ctx}/assets/js/ace/ace.ajax-content.js"></script>
		<script src="${ctx}/assets/js/ace/ace.touch-drag.js"></script>
		<script src="${ctx}/assets/js/ace/ace.sidebar.js"></script>
		<script src="${ctx}/assets/js/ace/ace.sidebar-scroll-1.js"></script>
		<script src="${ctx}/assets/js/ace/ace.submenu-hover.js"></script>
		<script src="${ctx}/assets/js/ace/ace.widget-box.js"></script>
		<script src="${ctx}/assets/js/ace/ace.settings.js"></script>
		<script src="${ctx}/assets/js/ace/ace.settings-rtl.js"></script>
		<script src="${ctx}/assets/js/ace/ace.settings-skin.js"></script>
		<script src="${ctx}/assets/js/ace/ace.widget-on-reload.js"></script>
		<script src="${ctx}/assets/js/ace/ace.searchbox-autocomplete.js"></script>

		<!-- inline scripts related to this page -->
		

		<!-- the following scripts are used in demo only for onpage help and you don't need them -->
		<link rel="stylesheet" href="${ctx}/assets/css/ace.onpage-help.css" />
		<link rel="stylesheet" href="${ctx}/docs/assets/js/themes/sunburst.css" />
		<sitemesh:write property='js'/>
		<script type="text/javascript"> ace.vars['base'] = '..'; </script>
		<script src="${ctx}/assets/js/ace/elements.onpage-help.js"></script>
		<script src="${ctx}/assets/js/ace/ace.onpage-help.js"></script>
		<script src="${ctx}/docs/assets/js/rainbow.js"></script>
		<script src="${ctx}/docs/assets/js/language/generic.js"></script>
		<script src="${ctx}/docs/assets/js/language/html.js"></script>
		<script src="${ctx}/docs/assets/js/language/css.js"></script>
		<script src="${ctx}/docs/assets/js/language/javascript.js"></script>
	</body>
</html>
