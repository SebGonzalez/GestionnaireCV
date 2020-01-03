package gestionnaire.managers;

import java.util.List;

import gestionnaire.entities.Activity;
import gestionnaire.entities.CV;
import gestionnaire.entities.Person;

public interface ICvManager {
	public List<Person> getAllPersons();
	public Person getPersonById(long idPerson);
	public List<Activity> getAllActivities();
	public CV getActivitiesPerson(long idPerson);
	public CV getActivitiesPerson(Person p);
	public Activity getActivityById(long idActivity);
	public List<Person> getRangePersons(int first, int nbResult);
	public void savePerson(Person p);
	public void saveActivity(Activity a);
	public boolean mailExistant(String mail);
}
