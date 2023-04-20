Annotations:

@Entity- use to mark any class as entity
@Table- use to change the table details.
@Id- use to mark column as id(Primary key)
@GeneratedValue- hibernate will automatically generate values for that using an internal sequence. Therefore, we don't have to set it manually.
@Column- can be used to specify column mapping. For example, to change the column name in the associated table in database.
@Transient- This tells hibernate not to save the fields.
@Temporal- @Temporal over a date field tells hibernate the format in which the data needs to be saved.
@Lob- this tells hibernate that this is a large object, not a simple object.

  
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------
  
  Annotations practical
  
  
  package com.mavenlearn;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
@Entity
@Table(name="student_address")
public class Address {
	
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="address_id")
	private int addressId;
    
    @Column(length = 50, name="STREET")
	private String street;
    
    @Column(length = 100, name="CITY")
	private String city;
    
    @Column(name="is_open")
	private boolean isOpen;
    
    @Transient
	private  double x;
    
    @Column(name="added_date")
    @Temporal(TemporalType.DATE)
	private Date addedDate;
    
    @Lob
	private byte[] image;
	
	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Address(int addressId, String street, String city, boolean isOpen, double x, Date addedDate, byte[] image) {
		super();
		this.addressId = addressId;
		this.street = street;
		this.city = city;
		this.isOpen = isOpen;
		this.x = x;
		this.addedDate = addedDate;
		this.image = image;
	}
	public int getAddressId() {
		return addressId;
	}
	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public boolean isOpen() {
		return isOpen;
	}
	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public Date getAddedDate() {
		return addedDate;
	}
	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	
	
	
}
-------------------------------------------------------------------------------------
  
  App.java
  
  
 
 package com.mavenlearn;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

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
    public static void main( String[] args )throws IOException
    {
        System.out.println( "Project started" );
        Configuration cfg=new Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory=cfg.buildSessionFactory();
        System.out.println(factory);
        //creating student
        Student st=new Student();
        st.setId(103);
        st.setName("Sahil");
        st.setCity("Delhi");
        System.out.println(st);
        //Creating object for  Address Class
        Address add= new Address();
        add.setStreet("street3");
        add.setCity("Delhi3");
        add.setOpen(true);
        add.setAddedDate(new Date());
        add.setX(123.67);
       
        //Reading Image
        
        FileInputStream fis=new FileInputStream("src/main/java/Test.png");
        
        byte[] data= new byte[fis.available()];
        fis.read(data);
        add.setImage(data);
       
        Session session=factory.openSession();
        
        Transaction tx= session.beginTransaction();
        session.save(st);
        session.save(add);
        tx.commit();
        System.out.println("Done...");
        
        session.close();
       
    }
}
