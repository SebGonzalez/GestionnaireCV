package managers;

import static org.junit.Assert.assertTrue;
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

	private final int NB_PERSONS = 50;
	private final int NB_ACTIVITIES = 2;
	
	@EJB
	ICvManager cm;

	@EJB
	IUserManager um;

	@Before
	public void setUp() throws Exception {
		EJBContainer.createEJBContainer().getContext().bind("inject", this);

		if (cm.getAllPersons().size() == 0) {
			System.out.println("INITIALISATION");
			int leftLimit = 97; // letter 'a'
			int rightLimit = 122; // letter 'z'
			int targetStringLength = 10;
			Random random = new Random();

			Calendar c1 = GregorianCalendar.getInstance();
			c1.set(1997, Calendar.FEBRUARY, 25);

			Person person = new Person("Lamblino", "S�bastien", "lamblino@hotmail.fr", "lamblino.fr", "azerty");
			person.setBirthDay(c1.getTime());
			cm.savePerson(person);

			for (int i = 0; i < NB_PERSONS; i++) {
				String generatedString = random.ints(leftLimit, rightLimit + 1).limit(targetStringLength)
						.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
				Person p = new Person(generatedString, generatedString, generatedString + "@hotmail.fr",
						generatedString + ".fr", "azerty");
				p.setBirthDay(c1.getTime());
				cm.savePerson(p);

				for (int y = 0; y < NB_ACTIVITIES; y++) {
					String generatedString2 = random.ints(leftLimit, rightLimit + 1).limit(targetStringLength)
							.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
							.toString();
					Activity activity = new Activity(random.nextInt(2020 - 1950) + 1950, ActivityType.FORMATION,
							generatedString2, generatedString2, generatedString2, p);
					cm.saveActivity(activity);
				}
			}

		}
	}

	@After
	public void tearDown() throws Exception {
		EJBContainer.createEJBContainer().close();
	}

	@Test
	public void testGetAllPersons() {
		assertEquals(NB_PERSONS + 1, cm.getAllPersons().size());
	}
	
	@Test
	public void testGetActivitiesPerson() {
		Person p = cm.getPersonById(2);
		assertEquals(p.getCv().getActivities().size(), cm.getActivitiesPerson(p).getActivities().size());
	}
	
	@Test
	public void testGetRangePersons() {
		int first = 4;
		int nbResult = 8;

		assertEquals(nbResult, cm.getRangePersons(first, nbResult, null).size());
	}
	
	@Test
	public void testGetRangeActivities() {
		int first = 4;
		int nbResult = 8;

		assertEquals(nbResult, cm.getRangeActivities(first, nbResult, null).size());
	}

	@Test
	public void testGetPersonById() {
		assertEquals(Long.valueOf(2), cm.getPersonById(2).getId());
	}

	@Test
	public void testGetAllActivities() {
		assertEquals(NB_PERSONS * NB_ACTIVITIES, cm.getAllActivities().size());
	}

	@Test
	public void testGetActivityById() {
		assertEquals(Long.valueOf(50), cm.getActivityById(50).getId());

	}

	@Test
	public void testSavePerson() {
		int expected = cm.getAllPersons().size() + 1;

		Calendar c1 = GregorianCalendar.getInstance();
		c1.set(1997, Calendar.FEBRUARY, 25);

		Person person = new Person("Lamblino", "S�bastien", "lamblino2@hotmail.fr", "lamblino.fr", "azerty");
		person.setBirthDay(c1.getTime());
		cm.savePerson(person);

		assertEquals(expected, cm.getAllPersons().size());
		

	}

	@Test
	public void testSaveActivity() {
		int expected = cm.getAllActivities().size() + 1;

		Activity a = new Activity(2020, ActivityType.FORMATION, "LE TEST", "Cette activit� a �t� faite pour les tests", "websitedutest.com", cm.getPersonById(1));
		cm.saveActivity(a);

		assertEquals(expected, cm.getAllActivities().size());
		
	}

	@Test
	public void testMailExistant() {

		Calendar c1 = GregorianCalendar.getInstance();
		c1.set(1997, Calendar.FEBRUARY, 25);

		Person person = new Person("Lamblino", "S�bastien", "lamblino3@hotmail.fr", "lamblino.fr", "azerty");
		person.setBirthDay(c1.getTime());
		cm.savePerson(person);

		assertTrue(cm.mailExistant("lamblino3@hotmail.fr"));

	}

}