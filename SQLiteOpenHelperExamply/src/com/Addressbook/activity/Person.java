package com.Addressbook.activity;

public class Person {
	private Integer id;
	private String name;
	private Integer age;
	
	public Person() {
		
	}
	
	public Person(String name, Integer age) {
		setName(name);
		setAge(age);
	}
	
	Person(Integer age) {
		
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getId() {
		return this.id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getAge() {
		return age;
	}
	
	public void setAge(Integer age) {
		this.age = age;
	}
	
	@Override
	public String toString() {
		return "Person [age=" + age + ", id=" + id + ", name=" + name + "]";
	}
}
