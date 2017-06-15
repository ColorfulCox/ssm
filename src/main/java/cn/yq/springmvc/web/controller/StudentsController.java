package cn.yq.springmvc.web.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.yq.springmvc.entity.Student;
import cn.yq.springmvc.entity.enums.Gender;
import cn.yq.springmvc.service.StudentService;

@Controller
@RequestMapping("/students")
public class StudentsController extends BaseController{

	@Autowired
	private StudentService studentService;
	
	@GetMapping
	public String index(Model model){
		log.info(parameters());
		model.addAttribute("students",studentService.findBySearchFilter(parameters()));
		return "student/index";
	}
	/**
	 * 新增表单
	 * @param model
	 * @return
	 */
	@GetMapping("/create")
	public String _new(Model model){
		
		Student student=new Student();
		model.addAttribute("student",student);
		model.addAttribute("genders", Gender.values());
		return "student/create";
	}
	/**
	 * 新增
	 * @param student
	 * @param bindResult
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@PostMapping("/create")
	public String create(@Valid @ModelAttribute Student student,
			BindingResult bindResult,
			Model model,
			RedirectAttributes redirectAttributes){
		
		if(bindResult.hasErrors()){
			model.addAttribute("genders", Gender.values());
			return "student/create";
		}
		
		studentService.save(student);
		/**
		 * 这个属性只保存一次
		 */
		redirectAttributes.addFlashAttribute("toastMsg", "保存"+student.getName()+"成功！");
		
		return "redirect:/students";
	}
	
	/**
	 * 修改获取表单
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/{id}/update")
	public String endit(@PathVariable Long id, Model model){
		//log.info(">>>>>>>>>>>findById>>>>"+id);
		Student student=studentService.findById(id);
		model.addAttribute("student",student);
		model.addAttribute("genders", Gender.values());
		return "student/edit";
	}
	/**
	 * 修改提交
	 * @param id
	 * @param student
	 * @param bindResult
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@PostMapping("/{id}/update")
	public String update(@PathVariable Long id,
			@Valid @ModelAttribute Student student,
			BindingResult bindResult,
			Model model,
			RedirectAttributes redirectAttributes){
		if(bindResult.hasErrors()){
			model.addAttribute("genders", Gender.values());
			return "student/edit";
		}
		student.setId(id);
		studentService.update(student);
		redirectAttributes.addFlashAttribute("toastMsg", "更新"+student.getName()+"成功！");
		return "redirect:/students";
	}
/*	/**
	 * 查看详情
	 * @param id
	 * @param model
	 * @return
	 *//*
	@GetMapping("/{id}/query")
	public String query (@PathVariable Long id,Model model){
		Student student = studentService.queryById(id);
		//log.info(student.getAccount()+"-------------");
		log.info(student.getAccount().getAccount()+"----------");
		model.addAttribute("student", student);
		model.addAttribute("genders",Gender.values());
		return "student/show";
	}
	*/
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	//@DeleteMapping("/{id}")
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	@ResponseBody
	public Student destory(@PathVariable Long id){
		Student student=studentService.findById(id);
		studentService.delete(id);
		return student;
	}


	
}
