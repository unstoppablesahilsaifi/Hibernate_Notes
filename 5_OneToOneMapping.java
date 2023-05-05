//Question.java

package com.map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Question {
	@Id
	@Column(name="question_id")
    private int questionId;
    private String question;
    @OneToOne
    private Answer answer;
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public Answer getAnswer() {
		return answer;
	}
	public void setAnswer(Answer answer) {
		this.answer = answer;
	}
	public Question(int questionId, String question, Answer answer) {
		super();
		this.questionId = questionId;
		this.question = question;
		this.answer = answer;
	}
	public Question() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
}

---------------------------------------------
  
  //Answer.java
  
  package com.map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
@Entity
public class Answer {
	@Id
	@Column(name="answer_id")
    private int answerId;
    private String answer;
    @OneToOne(mappedBy = "answer") // Yha per hmne bta diya h ki mapping Question vali class m answer s ho chuki h
                                   // and Answer vali class m mapping vala column yha nhi bnega mapped by ki vjh s
    private Question question;
	public int getAnswerId() {
		return answerId;
	}
	public void setAnswerId(int answerId) {
		this.answerId = answerId;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
	public Answer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Answer(int answerId, String answer) {
		super();
		this.answerId = answerId;
		this.answer = answer;
	}
    
    
    
}
----------------------------------
  
  MapDemo.java
  
  
  package com.map;

import org.hibernate.SessionFactory;

import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.Session;



public class MapDemo {
    public static void main(String[] args) {
    	  Configuration cfg=new Configuration();
          cfg.configure("hibernate.cfg.xml");
          SessionFactory factory=cfg.buildSessionFactory();
          //Creating question
          
          Question q1=new Question();
          q1.setQuestionId(101);
          q1.setQuestion("What is Java");
          
          //Creating answer
          
          Answer answer=new Answer();
          answer.setAnswerId(201);
          answer.setAnswer("Java is a programming language");
          answer.setQuestion(q1);
          q1.setAnswer(answer);
          
   //Creating question
          
          Question q2=new Question();
          q2.setQuestionId(102);
          q2.setQuestion("What is collection framework");
          
          //Creating answer
          
          Answer answer2=new Answer();
          answer2.setAnswerId(202);
          answer2.setAnswer("API to work with object in java");
          answer2.setQuestion(q2);
          q2.setAnswer(answer2);
          
          
          //we need session to save the data
          
          Session session=factory.openSession();
          Transaction tx=session.beginTransaction();
          
         session.save(q1);
       session.save(q2);
          session.save(answer);
          session.save(answer2);
          
          
         tx.commit();
         
         
         //Fetch the details
         
         Question newQ=(Question)session.get(Question.class,102);
         System.out.println(newQ.getQuestion());
         System.out.println(newQ.getAnswer().getAnswer());
         
          session.close();
          factory.close();
	}
}



