### 入门
1. 引入spring 以及springmvc的jar包

2. 请求流程

3. 配置

  3.0  在src/main/resources新建一个 spring的配置文件 applicationContext.xml

  3.1 让web容器在启动的时候加载spring配置文件，创建spring IOC容器 。

      在web.xml中配置一个 context-param (web应用上下文参数),指定要加载的spring配置文件

     在web.xml中配置一个 DispatcherServlet ,将它设置为默认的Servlet,然后为该servelt配置一个对应的spring配置文件，这个配置文件在 WEB-INF/目录中, 名称规则是  [servletName]-servlet.xml

  3.2 在`[servletName]-servlet.xml` 为springMVC做配置

      配置点1: 打开annotation扫描

      配置点2: 配置视图模板相关的(视图解析器 ViewResolver)，比如 模板放在哪里， 前缀，后缀等等。

4. 开发

	1. 定义Controller 在Controller中定义方法.因为这个类要被spring容器实例化，所以要在类上
	添加 @Controller。

	2. 定义方法
		这个方法是请求到来的时候要调用的。所以应该在这个方法上 使用 @RequestMapping 来指定这个方法对应的请求地址
		方法上定义一个 Model参数，这个参数是 DispatcherServlet调用方法的时候传递进来的。因为我们需要将处理的结果保存到model中。
		返回的是一个String类型的字符串，它表示视图的地址。这个视图的地址 最终是  由视图解析器中配置的前缀 + 方法返回值+ 视图解析器中配置的后缀来 组合完成的。


### @RequestMapping

  做请求映射，最后调用的一定是 方法，所以在开发的时候，方法上是一定要加的。 如果它加载类上，那么最终的请求的地址就是 类上面的映射地址和 方法上的映射地址的组合

  [关于restful](http://www.ruanyifeng.com/blog/2011/09/restful)

  可以在@RequestMapping的请求路径中添加`路径变量`，从路径中取出变量应该使用`@PathVariable`,如果路径变量与参数名称一致，则不用添加参数，如果不一致，则需要在@PathVariable指出路径变量的名称。 如：

	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public String show(@PathVariable String id){
		return "student/show";
	}

    @RequestMapping(value="/{id}",method=RequestMethod.GET)
	public String show(@PathVariable("id") String id){
		return "student/show";
	}



### rest风格路由约定

以数据库表 users为例：

Method   | URL | Controller类名  | Controller方法名  | view文件名 | 说明
------------- | --- | ------------- | ------------ | ------- | ---
 GET   | /users | UsersController | index() | views/users/index.jsp | 列出所有用户
 GET   | /users/create | UsersController | _new() | views/users/new.jsp | 呈现新建用户的表单
 POST   | /users/create | UsersController | create() |   |  保存到数据库，create方法执行完毕后进行重定向，以免发生重复提交
 GET   | /users/{id} | UsersController | show() | views/users/show.jsp |  显示指定id的信息
 GET   | /users/{id}/update | UsersController | edit() | views/users/edit.jsp |  显示编辑页面
 PUT   | /users/{id}/update | UsersController | update() |  |  更新到数据库，update方法执行完毕后进行重定向，以免发生重复提交
 DELETE   | /users/{id} | UsersController | destory() |   |  从数据库中删除，destory方法执行完毕后进行重定向，以免发生重复提交

 ### handler methods

 controller中用来处理请求的方法被称为 Handler Method (处理器)

#### Handler method 可接受的参数类型

 SpringMVC 中的Front Controller （DispatcherServlet） 调用 Controller中的方法来处理请求，因为Controller被springIOC托管了，所以在调用Controller Handler方法的时候，就可以为方法注入参数。这些可以被注入的参数的类型常用的包含：

- ServletRequest or HttpServletRequest
- ServletResponse or HttpServletResponse
- HttpSession
- org.springframework.web.context.request.WebRequest or org.springframework.web.context.request.NativeWebRequest
- java.util.Locale  用于国际化
- java.io.InputStream / java.io.Reader 可以读取请求中的输入流
- java.io.OutputStream / java.io.Writer 响应的输出流
- @PathVariable 请求路径变量
- @RequestParam 请求参数
- @RequestHeader 请求头
- @RequestPart 用于文件上传，获取上传的文件流
- org.springframework.ui.Model 用来存储视图上用到的数据
- org.springframework.web.servlet.mvc.support.RedirectAttributes
- form objects to bind request parameters to bean properties （实体对象，SpringMVC可以将请求参数中的数据取出来后，创建实体对象，然后将这些数据设置到实体对象中）
- org.springframework.validation.BindingResult 用于数据验证的时候


#### Handler method 返回类型
- ModelAndView
- Model Object 可以直接将Model返回 常用于 ajax请求，返回 json数据
- Map  同上
- View object
- A String value 一般情况下表示视图的逻辑名称
- void
- method is annotated with @ResponseBody 如果处理器方法使用@ResponseBody表示，默认返回的是JSON格式的数据,首先需要引入jar包

		<dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-core</artifactId>
            <version>2.5.1</version>
        </dependency>
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
            <version>2.5.1</version>
        </dependency>

		<dependency>
			<groupId>org.codehaus.jackson</groupId>
			<artifactId>jackson-core-lgpl</artifactId>
			<version>1.9.6</version>
		</dependency>
		<dependency>
			<groupId>org.codehaus.jackson</groupId>
			<artifactId>jackson-core-asl</artifactId>
			<version>1.9.4</version>
		</dependency>
		<dependency>
			<groupId>org.codehaus.jackson</groupId>
			<artifactId>jackson-mapper-asl</artifactId>
			<version>1.9.5</version>
		</dependency>
		<dependency>
			<groupId>org.codehaus.jackson</groupId>
			<artifactId>jackson-mapper-lgpl</artifactId>
			<version>1.9.6</version>
		</dependency>

  然后在 applicationContext中配置转换器和打开mvc的annotation配置：

      <mvc:annotation-driven />
      <bean
      	class="org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter"
      	p:ignoreDefaultModelOnRedirect="true">
      	<property name="messageConverters">
      		<list>
      			<bean
      				class="org.springframework.http.converter.json.MappingJackson2HttpMessageConverter" />
      		</list>
      	</property>
      </bean>

### springmvc不拦截静态资源
	<!-- 两个* 表示后面可能后多个子目录 -->
	<mvc:resources mapping="/assets/**" location="/assets/" />


### SpringMVC标签库
1. 引入标签库
  ```
  <%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
  ```
2. form:form 生成html form表单，可以绑定后台的model
3. form:input 生成input标签，可以绑定model中的某个property
4. form:radioButtons 绑定一组 radio button


### springmvc的数据校验

1. 引入验证jar包

  ```
  	<dependency>
  		<groupId>org.hibernate</groupId>
  		<artifactId>hibernate-validator</artifactId>
  		<version>5.4.1.Final</version>
  	</dependency>
  ```
2. 在实体类上添加 annotation ，可以查看：org.hibernate.validator.constraints 和  javax.validation.constraints 包中的annotation

3. 在controller的处理器方法中添加
  ```
	public String create(@Valid Student student,BindingResult bindResult,Model model)
  ```
	注意： BindingResult一定要紧随 @Valid之后,然后可以在Controller中判断是否出错：
  ```
    if(bindResult.hasErrors()){

    }
  ```
	SPring框架执行的时候： 首先收到请求，然后将请求中的参数放到student的property中，最后验证，验证的结果放在 BindingResult对象中。所有的这些操作都是在调用 Controller方法之前完成的。

4. 在页面上使用 form:errors来显示错误信息
  ```
	<form:errors path="name" cssStyle="color:red"></form:errors>
  ```

### @ModelAttribute

1. 修饰处理器方法的参数。执行过程： 首先到model中查找对象，如果model中已经存在对象，那么直接使用，如果不存在，则需要创建一个新的对象，然后放到model中。如果没有指定value，则使用 类型的首字母小写作为key放入到范围，然后将请求参数中的数据设置到对象中。

### 乱码的问题

springMVC 提供了一个 filter，只需要在 web.xml中进行配置即可解决乱码的问题

```
  <!-- 解决乱码问题 -->
    <filter>  
        <filter-name>characterEncodingFilter</filter-name>  
        <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>  
        <init-param>  
            <param-name>encoding</param-name>  
            <param-value>UTF-8</param-value>  
        </init-param>  
        <init-param>  
            <param-name>forceEncoding</param-name>  
            <param-value>true</param-value>  
        </init-param>  
    </filter>  
    <filter-mapping>  
        <filter-name>characterEncodingFilter</filter-name>  
        <url-pattern>/*</url-pattern>  
    </filter-mapping>
```


### RedirectAttributes
  可以在session范围内保存数据，但是只要使用过一次之后，这个数据就会被删除。 说它特别适合在从定向的时候保存数据。

```
redirectAttributes.addFlashAttribute(attributeName, attributeValue)
```


### 自定义jsp标签
1. 编写一个类，继承SimpleTagSupport
	重写doTag方法，在这个方法中获取 PageContext，再从pageContext中获取输出流，然后用流输出

2. 在src/main/resources目录中新建文件夹META-INF，再新建一个 tld文件，这个文件是标签描述文件。

3. 在jsp中引入自定义标签，然后使用

