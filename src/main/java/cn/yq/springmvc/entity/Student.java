package cn.yq.springmvc.entity;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import cn.yq.springmvc.entity.enums.Gender;

public class Student extends BaseEntity implements Serializable {
	@NotEmpty(message="姓名不能为空")
	private String name;
	private Gender gender=Gender.Male;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date birthday;

	
	private String[] favourite;
	
	private Account account;
	
	
	public Student(String name, Gender gender, Date birthday) {
		super();
		this.name = name;
		this.gender = gender;
		this.birthday = birthday;
	}
	
	

	public String[] getFavourite() {
		return favourite;
	}



	public void setFavourite(String[] favourite) {
		this.favourite = favourite;
	}



	public Student() {
		super();
	}


	
	
	public Account getAccount() {
		return account;
	}



	public void setAccount(Account account) {
		this.account = account;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}
	

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}


	@Override
	public String toString() {
		return "Student [name=" + name + ", gender=" + gender + ", birthday=" + birthday + ", getId()=" + getId() + "]";
	}
	
}