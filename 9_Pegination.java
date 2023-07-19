package com.pegination;

import java.util.List;

import org.hibernate.Query.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.mavenlearn.Student;

public class HQLPegination {

	public static void main(String[] args) {
		 SessionFactory factory= new Configuration().configure().buildSessionFactory();
		  Session session=factory.openSession();
		Query<Student> query=session.createQuery("from Student");
		
		//Immplementing pegination using hibernate
		// kaha s suru krna h
		query.setFirstResult(0);
		// Kitne result chahiye
		query.setMaxResults(3);
		 List<Student> list=query.list();
		 for(Student st:list) {
			 System.out.println(st.getId()+" : "+st.getName()+" : "+st.getCity());
		 }
		 factory.close();
		 
	}
}
