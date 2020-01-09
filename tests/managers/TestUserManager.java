package managers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.ejb.EJB;
import javax.ejb.embeddable.EJBContainer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import gestionnaire.entities.Person;
import gestionnaire.managers.ICvManager;
import gestionnaire.managers.IUserManager;

public class TestUserManager {
	@EJB
	ICvManager cm;
	
	@EJB
	IUserManager um;
	
	

	@Before
	public void setUp() throws Exception {
		EJBContainer.createEJBContainer().getContext().bind("inject", this);

		if (!cm.mailExistant("lamblino@hotmail.fr")) {
			Calendar c1 = GregorianCalendar.getInstance();
			c1.set(1997, Calendar.FEBRUARY, 25);

			Person person = new Person("Lamblino", "Sébastien", "lamblino@hotmail.fr", "lamblino.fr", "azerty");
			person.setBirthDay(c1.getTime());
			cm.savePerson(person);
		}
	}

	@After
	public void tearDown() throws Exception {
		EJBContainer.createEJBContainer().close();
	}
	
	@Test
	public void testConnect() {
		um.connect("lamblino@hotmail.fr", "azerty");
		
		assertTrue(um.isConnected());
		
		um.logout();
	}
	
	public void testUser() {
		um.connect("lamblino@hotmail.fr", "azerty");
		
		assertEquals("lamblino@hotmail.fr" , um.getUser().getMail());
		
		um.logout();
	}
}
