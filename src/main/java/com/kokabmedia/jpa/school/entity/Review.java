package com.kokabmedia.jpa.school.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/*
 * This is a entity class for the purpose of retrieving, creating, updating, deleting 
 * data with a database.
 * 
 * The @Entity annotation from javax.persistence enables the JPA framework to manage 
 * the Review class as a JPA entity. The Review class is an entity and will be mapped to a 
 * database table named Review_Details. 
 * 
 * The @Entity annotation will automatically with Hibernate, JPA and Spring auto 
 * configuration create a Review_Details table in the H2 in memory database.
 */
@Entity 
@Table(name="ReviewDetails")// Define the name of the database table
public class Review {
	
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
	
	// The name of the column is description_review
	@Column(name="descriptionReview") 
	private String description;
	
	// The field cannot have a null value 
	@Column(nullable = false) 
	private String rating;

	/*
	 * This field is for relation mapping purposes, it will hold an Course object
	 * when it is mapped to it with the @OneToMany(mappedBy) annotation in the 
	 * Course class and when the addReview() method is called. The Review table will 
	 * have link to the Course table with a course_id column containing foreign 
	 * key value.
	 * 
	 * The @ManyToOne annotation indicates that Review has a many to one relation 
	 * to Course. One course can have many reviews. 
	 * 
	 * JPA and Hibernate will with the @ManyToOne annotation on this field create
	 * a course_id column in the Review table. The Review table is now owning the 
	 * relationship.
	 * 
	 * The course_id column will link to a specific row in Course table. Multiple 
	 * reviews objects (rows) can be linked to the same Course row with course_id
	 * column. This entity is the owning side of the relationship.
	 * 
	 * The fetch strategy for the ManyToOne side of the relations is Eager Fetch
	 * and the details of Course entity will be fetched with the Review entity 
	 * automatically. The fetch type parameter indicates that the fetch type is 
	 * Lazy so if we do not call review.getCourse method the framework will not 
	 * retrieve the details of Course.
	 */
	@ManyToOne(fetch=FetchType.LAZY)
	private Course course;
	
	/*
	 * JPA mandates a default no argument constructor, this constructor will be
	 * used by JPA to create this specific bean.
	 */
	public Review() {}
	
	public Review(String description, String rating) {
		this.description = description;
		this.rating = rating;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public Long getId() {
		return id;
	}
	
	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	/*
	 * The purpose of this method is to returns a textual representation 
	 * of the object, instead of for example hash code in the logger.
	 */
	@Override
	public String toString() {
		return "Review [id=" + id + ", description=" + description + ", rating=" + rating +"]";
	}
	
	
	

}
