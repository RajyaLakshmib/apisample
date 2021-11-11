package com.qa.data;
//POJO - plain old java object
public class UsersData {
	
	String name,job;
	
	public UsersData() {
		
	}
	public UsersData(String n,String j) {
		this.name= n;
		this.job=j;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	

}
