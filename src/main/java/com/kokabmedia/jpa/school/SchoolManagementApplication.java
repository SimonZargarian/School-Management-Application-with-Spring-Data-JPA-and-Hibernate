package com.kokabmedia.jpa.school;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.kokabmedia.jpa.school.entity.Course;
import com.kokabmedia.jpa.school.repository.CourseRepository;
import com.kokabmedia.jpa.school.repository.StudentRepository;

/*
 * This class is the main thread class of the application, with the main method that 
 * launches the application with the Spring framework.
 * 
 * The @SpringBootApplication annotation initialises the Spring framework and starts (launches) 
 * the Application Context of the Spring framework which is the implementation of the Spring 
 * IOC Container that manages all of the beans. It also initialises Spring Boot framework and auto 
 * configuration and enables component scanning of this package and sub-packages to locate beans,
 * this is all done automatically. 
 */
@SpringBootApplication
public class SchoolManagementApplication implements CommandLineRunner{
	
	// For logging purposes
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/*
	 * The @Autowired annotation tells the Spring framework that CourseRepository
	 * instance (bean) is an dependency of SchoolManagementApplication class, it is a 
	 * mechanism for implementing Spring dependency injection.
	 * 
	 * The CourseRepository class is now a dependency of the SchoolManagementApplication class.
	 * 
	 * Spring framework creates an instance (bean) of the CourseRepository and autowires it to
	 * the SchoolManagementApplication class object as a dependency.
	 */
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private StudentRepository studentRepository;

	public static void main(String[] args) {
		
		// Returns Application Context
		SpringApplication.run(SchoolManagementApplication.class, args);
	}

	/*
	 * With the CommandLineRunner the code in this run() method will be executes as soon as the 
	 * Application Context is launched.
	 */
	@Override
	public void run(String... args) throws Exception {
		Course course = courseRepository.findById(10001L);
		
		logger.info("Course 10001 -> {}", course );
				
		// creating and persisting a new Course object (row) in the database
		courseRepository.save(new Course("MS in 50 Steps"));
		
		studentRepository.saveStudentWithPassport();
	}
}
