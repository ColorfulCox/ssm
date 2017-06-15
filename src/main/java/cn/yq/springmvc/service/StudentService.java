package cn.yq.springmvc.service;

import java.util.List;
import java.util.Map;

import cn.yq.springmvc.entity.Student;
import cn.yq.springmvc.vo.PageRequest;
import cn.yq.springmvc.vo.Pageable;

public interface StudentService {
	public List<Student> findBySearchFilter(Map<String,String> searchFilter);
	public void save(Student student);
	public Student findById(Long id);
	public void update(Student student);
	public void delete(Long id);
	public Student queryById(Long id);

}
