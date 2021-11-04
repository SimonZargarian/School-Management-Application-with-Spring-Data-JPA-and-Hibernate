package com.kokabmedia.jpa.school.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

/*
 * This is a entity class for the purpose of retrieving, creating, updating, deleting 
 * data with a database.
 * 
 * The @Entity annotation from javax.persistence enables the JPA framework to manage 
 * the Course class as a JPA entity. The Course class is an entity and will be mapped to a 
 * database table named Course_Details. 
 * 
 * The @Entity annotation will automatically with Hibernate, JPA and Spring auto configuration
 * create a Course_Details table in the H2 in memory database. 
 * 
 * This class acts as an mapped entity class for handling data in a database with the 
 * CourseRepository class.
 */
@Entity 
@Table(name="CourseDetails")// Define the name of the database table
/*
 * @Cacheable annotation enables Second Level Cache for common data of the Course entity 
 * across multiple Transactions. Hibernate will look at the Second Level Cache to find Course
 * details.
 * 
 * Hibernate has two levels of caching: FirstLevelCache and SecondLevelCache.
 * 
 * FirstLevelCache: When a query is made to retrieve data Hibernate will retrieve the data from 
 * the database and store it in the Persistence Context, the second that same query is called 
 * Hibernate will pick up from the Persistence Context, this is the FirstLevelCache that Hibernate
 * and Persistence Context provide. The FIrstLevelCache is within the boundaries of a single Transaction. 
 * 
 * SecondLevelCache: SecondLevelCa	che operates across multiple Transactions, and stores related 
 * information about an object. SecondLevelCache stores common information for all the users of an 
 * application. The data is collected from the database and stored in the SecondLevelCache.
 */
@Cacheable
/*
 * The @SQLDelete annotation enables soft deleting with the isDeleted boolean field by updating the 
 * value of isDelted to true.
 * 
 * The @Where annotation restricts the retrieve  scope to where isDeleted has a false value.
 */
@SQLDelete(sql="upate course set is_deleted=true where id=?")
@Where(clause="is_deleted=false")
public class Course {
	
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
	
	 
	// The name of the column is course_name and the field cannot have a null value 
	@Column(name="courseName", nullable = false) 
	private String name;
	 
	@CreationTimestamp // Store created time of row 
	private LocalDateTime createdDate;
	
	@UpdateTimestamp// Every time this row is changed in the database update this time stamp with sysdate();
	private LocalDateTime lastUpdatedDate;
	
	// If a Course row is deleted then this field will updated to true
	private boolean isDeleted;
	
	/* 
	 * This field is for relation mapping purposes, a course can have a list of
	 * multiple reviews.
	 * 
	 * The @OneToMany annotation indicates that Course has one to many relationship 
	 * mapping with Review, one course can have multiple reviews. The many side of the
	 * relationship will be the owning side. 
	 * 
	 * The select Course query will also get the Review details from the database with 
	 * Eager Fetch from both the Course and the Review table.
	 * 
	 * The mappedBy parameter is describing which table will be owning the relationship,
	 * The Review table will have a course_id column with a foreign key value and will 
	 * be owning the relationship. The course_id column in the Review table will have a 
	 * link to specific row in the Course table. Multiple reviews can be associated with
	 * the same course.
	 * 
	 * The mappedBy parameter is set to the non owning side of the relationship. The
	 * mappedBy parameter makes sure that a rewiev_id column is not created in the 
	 * Course table with a foreign key value.
	 * 
	 * The fetch strategy for the OneToMany side of the relations is Lazy Fetch, if we want 
	 * to get the Review details as well we need to call the course.getReviews method. We can 
	 * change the the fetch type parameter fetch=FetchType.Eager this will change the fetching 
	 * strategy of the Review entity so that the fetch type is Eager and Hibernate will fetch 
	 * the Review data when fetching the Course data.
	 */
	@OneToMany(mappedBy="course")
	private List<Review> reviews = new ArrayList<>();
	
	/*
	 * The @ManyToMany annotation indicates that Course can have many Students and a Student
	 * can be associated with many Courses. Course can have a list of multiple students.
	 *
	 * The @ManyToMany annotation creates a new Student_Course joint table in the H2 
	 * database with students_id and courses_id columns with foreign key values.
	 *
	 * The mappedBy parameter and the the @JoinTable annotation makes the Student entity 
	 * the owning side of the relationship.
	 * 
	 * The fetch strategy for the ManyToMany side of the relations is Lazy Fetch.
	 */
	@ManyToMany(mappedBy="courses")
	private List<Student> students = new ArrayList<>();
	
	
	/*
	 * JPA mandates a default no argument constructor, this constructor will be
	 * used by JPA to create this specific bean.
	 */
	public Course() {}
	
	public Course(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	// Add a single review one at the time to the list
	public void addReview(Review reviews) {
		this.reviews.add(reviews);
	}
	
	// Remove a single review one at the time from the list
	public void removeReview(Review reviews) {
		this.reviews.remove(reviews);
	}

	public List<Student> getStudents() {
		return students;
	}

	public void addStudent(Student student) {
		this.students.add(student);
	}

	/*
	 * The purpose of this method is to returns a textual representation 
	 * of the object, instead of for example hash code in the logger.
	 */
	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + "]";
	}
	
	
	

}
