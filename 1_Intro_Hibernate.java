Hibernate-
          It is a Java framework that simplifies the development of java application to interact with the database.

ORM- Object Relational Modal

NOTE: We map our table to class, Class behaves like a table and class fields are like column names.
  
After creating the maven project, we need to add dependecies in POM.XML file. POM(Project Object Model)
1. Add hibernate dependecy in pom.xml
2. Add MySQL connector dependency with the same version as MySQL installed version.
  
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------
          
// Hibernate Configuration
Firstly we need to add DTD "Document Type Definition" in out xml.

          
  Hibernate.cfg.xml
  
  <?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE hibernate-configuration PUBLIC
"-//Hibernate/Hibernate Configuration DTD 3.0//EN"
"http://www.hibernate.org/dtd/hibernate-configuration-3.0.dtd">

<hibernate-configuration>
	<session-factory>
		<property name="connection.driver_class">com.mysql.cj.jdbc.Driver</property>
		<property name="connection.url">jdbc:mysql://localhost:3306/myhiber</property>
		<property name="connection.username">root</property>
		<property name="connection.password">root</property>
		<property name="dialect">org.hibernate.dialect.MySQL5Dialect</property>
		<property name="hbm2ddl.auto">update</property>
		<property name="show_sql">true</property>
		<mapping class="com.mavenlearn.Student"/>
	</session-factory>
</hibernate-configuration>
 
       
			
			
-> Session factory is an interface factory for providing sessions.
	

App.java

package com.mavenlearn;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Project started" );
	// session factory is an interface so we can not make the object so we would be required a class to make the object.
	    
	    //SessionFactory factory=new Configuration().configure().buildSessionFactory();
	    
	                  //   or below code seprately or we can code in one line as mentioned above.
	    
        Configuration cfg=new Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory=cfg.buildSessionFactory();
        System.out.println(factory);
        //creating student
        Student st=new Student();
        st.setId(101);
        st.setName("Sahil");
        st.setCity("Delhi");
        System.out.println(st);
        Session session=factory.openSession();
        
        Transaction tx= session.beginTransaction();
        session.save(st);
        tx.commit();
        
        session.close();
       
    }
}
