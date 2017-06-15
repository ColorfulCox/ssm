package cn.yq.springdemo.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.yq.springmvc.entity.Student;
import cn.yq.springmvc.service.StudentService;


public class StudentServiceTest extends BaseTest{
	
	@Autowired
	private StudentService studentService;
	
	@Test
	public void testFindBySearchFilter(){
		
		Map<String,String> searchFilter=new HashMap<>();
		
		List<Student> list=studentService.findBySearchFilter(null);
		for (Student student : list) {
			log.info(student.getId());
		}
		
	
	}
}
