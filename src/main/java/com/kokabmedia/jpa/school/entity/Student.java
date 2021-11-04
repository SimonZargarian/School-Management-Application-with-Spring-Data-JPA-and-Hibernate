package com.kokabmedia.jpa.school.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/*
 * This is a entity class for the purpose of retrieving, creating, updating, deleting 
 * data with a database.
 * 
 * The @Entity annotation from javax.persistence enables the JPA framework to manage 
 * the Student class as a JPA entity. The Student class is an entity and will be mapped to a 
 * database table named Student_Details. 
 * 
 * The @Entity annotation will automatically with Hibernate, JPA and Spring auto 
 * configuration create a Student_Details table in the H2 in memory database. 
 * 
 * This class acts as an mapped entity class for handling data in a database with the 
 * StudentRepository class.
 */
@Entity 
@Table(name="StudentDetails")// Define the name of the database table
public class Student {
	
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
	
	// The name of the column is student_first_name and the field cannot have a null value 
	@Column(name="studentFirstName", nullable = false) 
	private String firstName;
	
	// The name of the column is student_last_name and the field cannot have a null value 
	@Column(name="studentLastName", nullable = false) 
	private String lastName;
	
	
	/*
	 * Creates a OneToOne relationship mapping with the @OneToOne annotation, Student can have
	 * one Passport and one Passport can be associated with one Student.
	 *  
	 * JPA and Hibernate will with the @OneToOne annotation on this field create a passport_id 
	 * column with a foreign key value in the Student_Details table. The Student_Details table 
	 * is now owning the relationship. The passport_id column will link to a specific row in the 
	 * Passport_Details table. 
	 * 
	 * The select Student query will also get the passport details from the database with 
	 * Eager Fetch from both the Student and the Passport table.
	 * 
	 * With the LazyType.Fetch parameter the passport details will be collected only when called with 
	 * the student.getPassport() method using the Entity Manager.
	 * 
	 * Student has a bidirectional association with Passport.
	 */
	@OneToOne(fetch=FetchType.LAZY) 
	private Passport passport;
	
	/*
	 * The @ManyToMany annotation indicates that Course can have many Students and a Student
	 * can be associated with many Courses. Student can have a list of multiple courses.
	 * 
	 * The @ManyToMany annotation creates a new Student_Details_Courses joint table in the H2 
	 * database with students_id and courses_id columns with foreign key values. This entity
	 * is the  owning side of the relationship because the .
	 * 
	 * The fetch strategy for the ManyToMany side of the relations is Lazy Fetch.
	 */
	@ManyToMany
	/* 
	 * The @JointTable annotation is added to the owning side of the relationship, it lets us
	 * define the name of the join table and the join column and the inverse join column.
	 */
	@JoinTable(name="STUDENT_COURSE",
	joinColumns = @JoinColumn(name="STUDENT_ID"),
	inverseJoinColumns = @JoinColumn(name="COURSE_ID")) 
	private List<Course> courses = new ArrayList<>();
	
	
	/*
	 * JPA mandates a default no argument constructor, this constructor will be
	 * used by JPA to create this specific bean.
	 */
	public Student() {}
	
	public Student(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Long getId() {
		return id;
	}

	public Passport getPassport() {
		return passport;
	}

	public void setPassport(Passport passport) {
		this.passport = passport;
	}
	

	
	public List<Course> getCourses() {
		return courses;
	}

	// Add one course at the time to the list of courses
	public void addCourse(Course course) {
		this.courses.add(course);
	}

	/*
	 * The purpose of this method is to returns a textual representation 
	 * of the object, instead of for example hash code in the logger.
	 */
	@Override
	public String toString() {
		return "Student [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName +"]";
	}
	
	
	

}
