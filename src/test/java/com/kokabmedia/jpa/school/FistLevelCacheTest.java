package com.kokabmedia.jpa.school;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import com.kokabmedia.jpa.school.entity.Course;
import com.kokabmedia.jpa.school.repository.CourseRepository;

@SpringBootTest
class FistLevelCacheTest {

	/* 
	 * The @Autowired annotation tells the Spring framework that repository instance (bean)
	 * is an dependency of CourseRepositoryTest class, it is a mechanism for implementing Spring 
	 * dependency injection.
	 * 
	 * The CourseRepository bean is now a dependency of the FistLevelCacheTest class.
	 * 
	 * The Spring framework creates an instance (bean) of the CourseRepository and autowires 
	 * as a dependency to the FistLevelCacheTest class object when it is instantiated.
	 */
	@Autowired
	CourseRepository repository;
	
	// For logging purposes
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	/*
	 * Hibernate has two levels of caching: FirstLevelCache and SecondLevelCache.
	 * 
	 * FirstLevelCache: When a query is made to retrieve data Hibernate will retrieve the data from 
	 * the database and store it in the Persistence Context, the second that same query is called 
	 * Hibernate will pick up from the Persistence Context, this is the FirstLevelCache that Hibernate
	 * and Persistence Context provide. The FIrstLevelCache is within the boundaries of a single Transaction. 
	 * 
	 * SecondLevelCache: SecondLevelCache operates across multiple Transactions, and stores related 
	 * information about an object. SecondLevelCache stores common information for all the users of an 
	 * application. The data is collected from the database and stored in the SecondLevelCache.
	 */
	@Test
	@Transactional
	public void findById_FirstLevelCache() {
		
		Course course = repository.findById(10001L);
		logger.info("First Course Retrieved {}", course);
		
		/* 
		 * The second time we ask for the same data it is retrieved from the FirstLevelCache.
		 * 
		 * No query will be fired against the database the data is picked up from the FirstLevelCache that 
		 * exist within the boundaries of an single Transaction and within a Persistence Context, the data 
		 * for the entities is cached by Hibernate. Without the @Transactional annotation the FirstLevelCache
		 * will not work because every findById method call will happen within it own Transaction with its own
		 * Persistence Context from the CourseRepository class.
		 */
		Course course1 = repository.findById(10001L);
		logger.info("First Course Retrieved again {}", course1);

		// Asserts the expected and the actual value
		assertEquals("JPA in 50 steps", course.getName());
		assertEquals("JPA in 50 steps", course1.getName());
	}
	
	
}
