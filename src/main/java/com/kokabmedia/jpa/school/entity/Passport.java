package com.kokabmedia.jpa.school.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/*
 * This is a entity class for the purpose of retrieving, creating, updating, deleting 
 * data with a database.
 * 
 * The @Entity annotation from javax.persistence enables the JPA framework to manage 
 * the Passport class as a JPA entity. The Passport class is an entity and will be mapped to a 
 * database table named Passport_Details. 
 * 
 * The @Entity annotation will automatically with Hibernate, JPA and Spring auto configuration 
 * create a Passport_Details table in the H2 in memory database. 
 * 
 */
@Entity 
@Table(name="PassportDetails")// Define the name of the database table
public class Passport {
	
	/*
	 * The @Id annotation makes this field a primary key in the database table.
	 * 
	 * The @GeneratedValue annotation makes the Hibernate generate the primary key value.
	 * 
	 * Primary key will uniquely identify each row in a database table.
	 */
	@Id
	@GeneratedValue
	private Long id;
	
	// The name of the column is passport_name and the field cannot have a null value 
	@Column(name="passportNumber", nullable = false) 
	private String number;
	 
	/*
	 * Creates a OneToOne relationship mapping with the @OneToOne annotation, Student can have
	 * one Passport and one Passport can be associated with one Student.
	 * 
	 * The select Passport query will also get the passport details from the database with 
	 * Eager Fetch from both the Student and the Passport table.
	 * 
	 * With the LazyType.Fetch parameter the student details will be collected only when called with 
	 * the passport.getStudent() method using the Entity Manager.
	 * 
	 * The mappedBy parameter is describing which table will be owning the relationship, The Student 
	 * table will have a passport_id column with a foreign key value and will be owning the relationship.
	 * The passport_id column in the Student table will have a link to specific row in the Passport table. 
	 * 
	 * The mappedBy parameter is set to the non owning side of the relationship. The mappedBy
	 * parameter makes sure that a user_id column is not created in the Passport table.
	 * 
	 * Passport has bidirectional association with Student. OneToOne annotation makes it possible 
	 * to navigate to the Student table even with mappedBy parameter indicating that Student is the 
	 * owning side of the relationship.
	 */
	@OneToOne(fetch=FetchType.LAZY, mappedBy="passport") 
	private Student student;
	
	/*
	 * JPA mandates a default no argument constructor, this constructor will be
	 * used by JPA to create this specific bean.
	 */
	public Passport() {}
	
	public Passport(String number) {
		this.number = number;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Long getId() {
		return id;
	}
	
	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	/*
	 * The purpose of this method is to returns a textual representation 
	 * of the object, instead of for example hash code in the logger.
	 */
	@Override
	public String toString() {
		return "Passport [id=" + id + ", number=" + number + "]";
	}
	
	
	

}
