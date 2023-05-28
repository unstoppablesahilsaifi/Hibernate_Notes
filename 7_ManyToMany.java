Emp.java

package com.map1;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Emp {
	@Id
 private int eid;
 private String name;
 
 // One Employee can have many Projects.
 @ManyToMany
 private List<Project> projects;

public int getEid() {
	return eid;
}

public void setEid(int eid) {
	this.eid = eid;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public List<Project> getProjects() {
	return projects;
}

public void setProjects(List<Project> projects) {
	this.projects = projects;
}

public Emp(int eid, String name, List<Project> projects) {
	super();
	this.eid = eid;
	this.name = name;
	this.projects = projects;
}

public Emp() {
	super();
	// TODO Auto-generated constructor stub
}
 
 
}
--------------------------------------
  
  Project.java
  
  package com.map1;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Project {
	@Id
   private int pid;
	@Column(name="project_name")
   private String projectName;
	
	//One Project can be assigned to many Employee
	@ManyToMany(mappedBy = "projects")
	private List<Emp> emps;

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public List<Emp> getEmps() {
		return emps;
	}

	public void setEmps(List<Emp> emps) {
		this.emps = emps;
	}

	public Project(int pid, String projectName, List<Emp> emps) {
		super();
		this.pid = pid;
		this.projectName = projectName;
		this.emps = emps;
	}

	public Project() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
---------------------------------
  
  MappingDemo.java
  
  package com.map1;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;

import org.hibernate.Transaction;

public class MappingDemo {

	public static void main(String[] args) {
		Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory factory=cfg.buildSessionFactory();
		
		Emp e1=new Emp();
		Emp e2=new Emp();
		e1.setEid(1);
		e1.setName("Sahil");
		e2.setEid(2);
		e2.setName("Rishabh");
		
		Project p1=new Project();
		Project p2=new Project();
		
		p1.setPid(11);
		p1.setProjectName("Advia");
		
		p2.setPid(22);
		p2.setProjectName("WBT");
		
		
		ArrayList <Emp> list1=new ArrayList<Emp>();
		ArrayList <Project> list2=new ArrayList<Project>();
		
		list1.add(e1);
		list1.add(e2);
		
		list2.add(p1);
		list2.add(p2);
		
		//Here we assigned two projects to emp1
		e1.setProjects(list2);
		//Here, We assigned 2 employees to project 2
		p2.setEmps(list1);
		
		Session s=factory.openSession();
		Transaction tx=s.beginTransaction();
		
		s.save(e1);
		s.save(e2);
		s.save(p1);
		s.save(p2);
		
		
		
		tx.commit();
		s.close();
		factory.close();
	}
}
