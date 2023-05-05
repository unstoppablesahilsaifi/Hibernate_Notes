Student.java

package com.mavenlearn;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Student {
	@Id
    private int id;
    private  String name;
    private String city;
    private  Certificate certi;
	public Student(int id, String name, String city) {
		super();
		this.id = id;
		this.name = name;
		this.city = city;
	}
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.id+" : "+this.name+" : "+this.city;
	}
	public Certificate getCerti() {
		return certi;
	}
	public void setCerti(Certificate certi) {
		this.certi = certi;
	}
	
	
    
}



----------------------------------------
  
  Certificate.java
  
  package com.mavenlearn;

import javax.persistence.Embeddable;

@Embeddable
public class Certificate {
       private String course;
       private String duration;
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public Certificate() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Certificate(String course, String duration) {
		super();
		this.course = course;
		this.duration = duration;
	}
       
       
}


====================================
  EmbedDemo.java
  
  package com.mavenlearn;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class EmbedDemo {
     public static void main(String[] args) {
    	 Configuration cfg=new Configuration();
         cfg.configure("hibernate.cfg.xml");
         SessionFactory factory=cfg.buildSessionFactory();
         
         Student  student1=new Student();
         student1.setId(111);
         student1.setName("Suhail");
         student1.setCity("UP");
         
         Certificate certificate=new Certificate();
         certificate.setCourse("Android");
         certificate.setDuration("2 Months");
         
         student1.setCerti(certificate);
         
         
         Student  student2=new Student();
         student2.setId(222);
         student2.setName("Rishabh");
         student2.setCity("Y Vihar");
         
         Certificate certificate1=new Certificate();
         certificate1.setCourse("C Sharp");
         certificate1.setDuration("6 Months");
         
         student2.setCerti(certificate);
         
         
         Session s=factory.openSession();
         Transaction tx=s.beginTransaction();
         
         //objects save
         
         s.save(student1);
         s.save(student2);
         
         
         tx.commit();
         s.close();
         
         
         factory.close();
	}
      
}

OP-
  select * from student;

111	Android	2 Months	UP	Suhail
222	Android	2 Months	Y Vihar	Rishabh
