package cn.yq.springmvc.web.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController extends BaseController {
	
	@RequestMapping({"/","","/index","/hello"})
	public String index(Model model){
		return "hello";
	}


}
