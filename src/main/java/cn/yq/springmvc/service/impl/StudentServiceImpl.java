package cn.yq.springmvc.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.management.RuntimeErrorException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import cn.yq.springmvc.dao.StudentMapper;
import cn.yq.springmvc.entity.Student;
import cn.yq.springmvc.service.StudentService;
@Service
public class StudentServiceImpl implements StudentService {


	@Autowired
	private StudentMapper studentMapper;
	
	
	/**
	 * 分页
	 */
	@Override
	public List<Student> findBySearchFilter(Map<String,String> searchFilter) {
		
		if(searchFilter==null){
			searchFilter=new HashMap<>();
		}
			if(StringUtils.isBlank(searchFilter.get("pageNum"))){
				searchFilter.put("pageNum","1");
				
			}
			if(StringUtils.isBlank(searchFilter.get("pageSize"))){
				searchFilter.put("pageSize","3");
			}
				
		PageHelper.startPage(searchFilter); //map中需要包含 pageNum 和pageSize 
		return studentMapper.findBySearchFilter(searchFilter);
	}


	/**
	 * 新增
	 */
	@Override
	public void save(Student student) {
		studentMapper.insert(student);
	}

	/**
	 * 根据id查询
	 */
	@Override
	public Student findById(Long id) {
		return studentMapper.selectByPrimaryKey(id.intValue());
	}

	/**
	 * 修改
	 */
	@Override
	public void update(Student student) {
		studentMapper.updateByPrimaryKey(student);
	}

	/**
	 * 删除
	 */
	@Override
	public void delete(Long id) {
		studentMapper.deleteByPrimaryKey(id.intValue());
	}


	@Override
	public Student queryById(Long id) {
		
		return studentMapper.queryById(id.intValue());
	}

	
}
