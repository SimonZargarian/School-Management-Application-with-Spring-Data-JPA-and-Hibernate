package com.kokabmedia.jpa.school;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import com.kokabmedia.jpa.school.entity.Course;
import com.kokabmedia.jpa.school.entity.Passport;
import com.kokabmedia.jpa.school.entity.Student;
import com.kokabmedia.jpa.school.repository.CourseRepository;
import com.kokabmedia.jpa.school.repository.StudentRepository;

@SpringBootTest
class StudentRepositoryTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/* 
	 * The @Autowired annotation tells the Spring framework that repository instance (bean)
	 * is an dependency of StudentRepositoryTest class, it is a mechanism for implementing Spring 
	 * dependency injection.
	 * 
	 * The CourseRepository bean is now a dependency of the CourseRepositoryTest class.
	 * 
	 * The Spring framework creates an instance (bean) of the StudentRepository and autowires 
	 * as a dependency to the StudentRepositoryTest class object when it is instantiated.
	 */
	@Autowired
	StudentRepository repository;
	
	@Autowired
	EntityManager em;
	
	@Test
	@Transactional// The session will terminate at the end of the method.
	public void retrieveStudentAndPassport() {
		
		Student student = em.find(Student.class, 20001);
		logger.info("student -> {}", student);
		logger.info("passport -> {}", student.getPassport());

	}
	
	@Test
	@Transactional
	public void retrievePassportAndAssosiatedStudent() {
		
		Passport passport = em.find(Passport.class, 30001L);
		logger.info("passport -> {}", passport);
		logger.info("student -> {}", passport.getStudent());
	}
	
 
	
	}
