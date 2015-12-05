package base;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import domain.PersonDomainModel;

public class Person_test {
	
	private static PersonDomainModel myPerson;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		Date dDate = null;
		try {
			dDate = new SimpleDateFormat("yyyy-MM-dd").parse("1972-07-31");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		myPerson = new PersonDomainModel();
		myPerson.setFirstName("Parth");
		myPerson.setLastName("Modi");
		myPerson.setBirthday(dDate);
		myPerson.setCity("Newark");
		myPerson.setPostalCode(19713);
		myPerson.setStreet("University of Delaware");
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		PersonDomainModel per;	
		PersonDAL.deletePerson(myPerson.getPersonID());
		per = PersonDAL.getPerson(myPerson.getPersonID());
		assertNull("The Person shouldn't have been in the database",per);		
	}

	@Test
	public void Add_Person_test() {		
		PersonDomainModel person;						
		PersonDAL.addPerson(myPerson);			
		person = PersonDAL.getPerson(myPerson.getPersonID());
		
		System.out.println("The id of the person added is " + person);
	}
	
	@Test
	public void AddPersonTest()
	{		
		PersonDomainModel per;		
		per = PersonDAL.getPerson(myPerson.getPersonID());		
		assertNull("The Person shouldn't have been in the database",per);		
		PersonDAL.addPerson(myPerson);	
		
		per = PersonDAL.getPerson(myPerson.getPersonID());
		System.out.println(myPerson.getPersonID() + " found");
	}
	
	@Test
	public void UpdatePersonTest()
	{		
		PersonDomainModel per;
		final String C_LASTNAME = "Smith";
		
		per = PersonDAL.getPerson(myPerson.getPersonID());		
		assertNull("The Person shouldn't have been in the database",per);		
		PersonDAL.addPerson(myPerson);	
		
		myPerson.setLastName(C_LASTNAME);
		PersonDAL.updatePerson(myPerson);
		
		per = PersonDAL.getPerson(myPerson.getPersonID());

		assertTrue("Name Didn't Change",myPerson.getLastName() == C_LASTNAME);
	}

	@Test
	public void DeletePersonTest()
	{		
		PersonDomainModel per;		
		per = PersonDAL.getPerson(myPerson.getPersonID());		
		assertNull("The Person shouldn't have been in the database",per);	
		
		PersonDAL.addPerson(myPerson);			
		per = PersonDAL.getPerson(myPerson.getPersonID());
		System.out.println(myPerson.getPersonID() + " found");
		
		PersonDAL.deletePerson(myPerson.getPersonID());
		per = PersonDAL.getPerson(myPerson.getPersonID());		
		assertNull("The Person shouldn't have been in the database",per);	
		
	}
}
