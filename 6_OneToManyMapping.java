Question.java

package com.map;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Question {
	@Id
	@Column(name="question_id")
    private int questionId;
    private String question;
   
    @OneToMany(mappedBy="question")
    private List<Answer> answers;
    
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
	
	public List<Answer> getAnswers() {
		return answers;
	}
	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}
	
	public Question(int questionId, String question, List<Answer> answers) {
		super();
		this.questionId = questionId;
		this.question = question;
		this.answers = answers;
	}
	public Question() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
}
-----------------------------------
  
  Answer.java
  
  package com.map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
@Entity
public class Answer {
	@Id
	@Column(name="answer_id")
    private int answerId;
    private String answer;
    
   @ManyToOne                                
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
------------------------------
  
  MapDemo.java
  
  package com.map;

import org.hibernate.SessionFactory;

import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;

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
          
          Answer answer1=new Answer();
          answer1.setAnswerId(202);
          answer1.setAnswer("used to create webapp");
          answer1.setQuestion(q1);
          
          Answer answer2=new Answer();
          answer2.setAnswerId(203);
          answer2.setAnswer("It has multiple framework");
          answer2.setQuestion(q1);
          
          
          ArrayList<Answer> list= new ArrayList<Answer>();
          list.add(answer);
          list.add(answer1);
          list.add(answer2);
          
          q1.setAnswers(list);
          
          //we need session to save the data
          
          Session session=factory.openSession();
          Transaction tx=session.beginTransaction();
          
          //save
          session.save(q1);
          session.save(answer);
          session.save(answer1);
          session.save(answer2);
          
          
         tx.commit();
         
         
         //Fetch the details
         Question newQ=(Question)session.get(Question.class,101);
         System.out.println(newQ.getQuestion());
		 for(Answer a:newQ.getAnswers()) {
			 System.out.println(a.getAnswer());
		 }
         
          session.close();
          factory.close();
	}
}
