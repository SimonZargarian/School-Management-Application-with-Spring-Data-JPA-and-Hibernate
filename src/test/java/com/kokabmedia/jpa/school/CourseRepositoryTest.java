package com.kokabmedia.jpa.school;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.kokabmedia.jpa.school.entity.Course;
import com.kokabmedia.jpa.school.repository.CourseRepository;

@SpringBootTest
class CourseRepositoryTest {

	/* 
	 * The @Autowired annotation tells the Spring framework that repository instance (bean)
	 * is an dependency of CourseRepositoryTest class, it is a mechanism for implementing Spring 
	 * dependency injection.
	 * 
	 * The CourseRepository bean is now a dependency of the CourseRepositoryTest class.
	 * 
	 * The Spring framework creates an instance (bean) of the CourseRepository and autowires 
	 * as a dependency to the CourseRepositoryTest class object when it is instantiated.
	 */
	@Autowired
	CourseRepository repository;
	
	@Test
	public void findById_basic() {
		
		Course course = repository.findById(10001L);
		
		// Asserts the expected and the actual value
		assertEquals("JPA in 50 steps", course.getName());
	}
	
	@Test
	/*
	 * The @DirtiesContext enables that after the test is run the data will revert back to 
	 * original state so that other test that are depending on the data do not fail, this is 
	 * automatically done by Spring.
	 */
	@DirtiesContext 
	public void deteleById_basic() {
		
		repository.deleteById(10002L);
		
		// Assert if the object (row) is null
		assertNull(repository.findById(10002L));
	}
	
	@Test
	@DirtiesContext // Leaves the data in a consistent state as it was before the changes in this method
	public void save_basic() {
		
		// Retrieve a course from database
		Course course = repository.findById(10001L);
		assertEquals("JPA in 50 steps", course.getName());

		// Update the details of a course
		course.setName("JPA in 50 steps - updated");
		
		repository.save(course);
		
		
		Course courseUpdated = repository.findById(10001L);
		// Check the name of the the course
		assertEquals("JPA in 50 steps - updated", courseUpdated.getName());

	}

}
