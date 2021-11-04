package com.kokabmedia.jpa.school.repository;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.kokabmedia.jpa.school.entity.Passport;
import com.kokabmedia.jpa.school.entity.Student;

/*
 * This class is used for handling data to and from the H2 in memory database and by managing
 * the Student entity.
 * 
 * The @Repository annotation allows the Spring framework to creates an instance (bean) 
 * of this interface and manage it with the Spring Application Context (the IOC container)
 * that maintains all the beans for the application.  
 *
 * The @Repository annotation lets the Spring framework manage the StudentRepository 
 * class as a Spring bean. The Spring framework will find the bean with auto-detection 
 * when scanning the class path with component scanning. It turns the class into a 
 * Spring bean at the auto-scan time.
 * 
 * @Repository annotation allows the StudentRepository class be wired in as dependency to a 
 * another object or a bean with the @Autowired annotation.
 * 
 * The @Repository annotation is a specialisation of @Component annotation for more specific 
 * use cases. 
 */
@Repository
/*
 * A Transaction involves multiple changes to the database data.
 * 
 * The @Transactional annotation on the class level means that all method in this class will run within the 
 * boundaries of an transaction. If code that apply changes to the database is outside the scope of an
 * transaction it will fail, without the Transaction we will not have a connection to the database.
 * 
 * Transaction handles the relationship with the different database tables (relations).
 * 
 * @Transactional annotation allow us to to make a change in data that effect the database data like updating 
 * a Student, the update method and and other Entity Manager methods will be contained within a transaction, 
 * the Transaction makes sure that all the actions (steps) that change data in the database are successful 
 * or else the transaction (the process) is rolled back (reversed), if the first step fails then second 
 * step will be reversed.
 * 
 * While a process is within the scope of a transaction the Entity Manager and the Persistence Context
 * keeps track to all the operations and modifications and persist them to the database. The entity  
 * instances and the changes to those instances will be stored in the Persistent Context while the 
 * Transaction is operating. At the start of a Transaction a Persistence Context is created and it is
 * closed at the end of the Transaction.
 * 
 * Only when a transaction (for example within a class or a method) is completed will the database 
 * changes sent out to the database by Hibernate. If there is no transaction then the Persistence 
 * Context will be closed after each Entity Manager method call as each method call will be its own 
 * Hibernate session.The Persistence Context will live though the length of a method or as long as the 
 * Transaction is running.
 * 
 * Each Transaction in the application is associated with it own Persistence Context that manage the 
 * entities within that specific transaction and every method call with this class will have its own 
 * transaction with its own Persistence Context.
 */
@Transactional
public class StudentRepository {

	/*
	 * The @Autowired annotation tells the Spring framework that the EntityManager bean 
	 * and its implementation is an dependency of StudentRepository class. It is a mechanism 
	 * for implementing Spring dependency injection.
	 * 
	 * @Autowired annotation enables dependency injection with Spring framework to avoid 
	 * tight coupling and enable loose coupling by calling a interface or the implementation 
	 * of an interface.
	 * 
	 * The Spring framework creates a instance (bean) of the EntityManager or its implementation 
	 * and inject (autowires) that instance into the StudentRepository object when it is instantiated 
	 * as a autowired dependency.
	 * 
	 * The EntityManager and its implementation is now a dependency of the StudentRepository class.
	 */
	@Autowired
	/*
	 * The Entity Manager is interface to the Persistence Context, the entities that are saved through
	 * the Entity Manager are saved to the Persistence Context, the Persistence Context with the Entity 
	 * Manager keeps track of all transactions and data that needs to be persisted to the database.
	 * 
	 * The Persistence Context is created at the start of the transaction and is ended when the 
	 * transaction ends. If there is no transaction then the Persistence Context will be closed after
	 * each Entity Manager method call, each Entity Manager method call as its own Hibernate session.
	 * 
	 * The Entity Manager and Persistence Context keep a record of all the transactions that occur 
	 * for all the entities, the Entity Manager keeps a record of all the changes that occur in relation 
	 * to the database. 
	 * 
	 * The Entity Manager provides query methods that transacts with the database. We interact with the 
	 * Persistent Context by using a Entity Manager. The Persistence Context acts as a storage facility for 
	 * the entities that are managed. Persistence Context gives us access to the database and the query 
	 * methods for the database.
	 * 
	 * If the Entity Manager and the Persistence Context manages a entity then they will manage all the changes 
	 * that effect that entity within a transaction, it will make sure that all changes to the state of that
	 * entity is persisted to the database. If a entity instance gets managed by the Entity Manager it will be
	 * managed by the entity manager during is whole life cycle and set methods will trigger a update queries to
	 * the database.
	 * 
	 * The Persistence Context will live though the length of a method or as long as the Transaction is running.
	 */
	EntityManager em;

	// Retrieve a specific Student with a primary key id
	public Student findById(Long id) {
		return em.find(Student.class, id);
	}
	
	// Insert and update Student object (row)
	 public Student save(Student student) {
		 
		 // If there is no Student, insert a Student else update the Student
		 if(student.getId()==null) {
			 em.persist(student); // The persist method creates a new entity, a new row in the database table and trigger a insert query
		 }else {	  
			em.merge(student); // The merge method updates a entity (row) in the database table and trigger a update query
		 }

		 return student;
	 }
	
	 // Delete a specific Student with a primary key id
	 public void deleteById(Long id) {
		 
		 Student Student = findById(id);
		 
		  em.remove(Student); // The remove method re a entity (row) in the database table and trigger a delete query.
		  
		  // em.flush() // The flush method sends the changes that occur to the database.
		  
		  // em.detach(Student) // The detach method enables the detachment of the Entity Manager from the Student entity.
		  
		  // em.clear() // The clear method clears every entity from the Entity Manager and the entities will no longer be tracked.
		  
		  // em.refresh(Student) // The refresh method refreshes (updates) the data with the content from the database and all the changes that are done to will be lost.
	 }
	 
	 // Saves a new Student with a new Passport to the database.
	 public void saveStudentWithPassport() {
		
		 // Persist a new Passport row to the database.
		 Passport passport = new Passport("z123456");
		 em.persist(passport);
		 
		 // Persist a new Student row with a passport_id column with a foreign key value to the database.
		 Student student = new Student("Mike", "Jordan");
		 student.setPassport(passport);
		 em.persist(student);
		 
	 }

}
