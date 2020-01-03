package managers;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

import javax.ejb.EJB;
import javax.ejb.embeddable.EJBContainer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import gestionnaire.entities.Activity;
import gestionnaire.entities.ActivityType;
import gestionnaire.entities.Person;
import gestionnaire.managers.ICvManager;
import gestionnaire.managers.IUserManager;

public class TestCvManager {

	@EJB
	ICvManager cm;

	@EJB
	IUserManager um;

	@Before
	public void setUp() throws Exception {
		EJBContainer.createEJBContainer().getContext().bind("inject", this);

		int leftLimit = 97; // letter 'a'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = 10;
		Random random = new Random();

		Calendar c1 = GregorianCalendar.getInstance();
		c1.set(1997, Calendar.FEBRUARY, 25);

		for (int i = 0; i < 10; i++) {
			String generatedString = random.ints(leftLimit, rightLimit + 1).limit(targetStringLength)
					.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
			Person p = new Person(generatedString, generatedString, generatedString + "@hotmail.fr",
					generatedString + ".fr", "azerty");
			p.setBirthDay(c1.getTime());
			//um.savePerson(p);

			for (int y = 0; y < 2; y++) {
				String generatedString2 = random.ints(leftLimit, rightLimit + 1).limit(targetStringLength)
						.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
				Activity activity = new Activity(random.nextInt(2020 - 1950) + 1950, ActivityType.FORMATION,
						generatedString2, generatedString2, generatedString2, p);
				//um.saveActivity(activity);
			}

		}
	}

	@After
	public void tearDown() throws Exception {
		EJBContainer.createEJBContainer().close();
	}

	@Test
	public void testGetAllPersons() {
		assertEquals(10, cm.getAllPersons().size());
	}
	
	@Test
	public void testGetPersonById() {
		//assertEquals(2, cm.getPersonById(2).getId());
	}
	
	@Test
	public void testGetActivitiesPerson() {
		System.out.println("OOOOOOOI : " + cm.getAllActivities().get(0));
		System.out.println("OOOUUAIS : " + cm.getActivitiesPerson(cm.getPersonById(2)).getActivities().size());
	}

}