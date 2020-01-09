package gestionnaire.managers;

import java.util.List;
import java.util.Map;

import gestionnaire.entities.Activity;
import gestionnaire.entities.CV;
import gestionnaire.entities.Person;

public interface ICvManager {
	public List<Person> getAllPersons();
	public Person getPersonById(long idPerson);
	public List<Activity> getAllActivities();
	public CV getActivitiesPerson(Person p);
	public Activity getActivityById(long idActivity);
	public List<Person> getRangePersons(int first, int nbResult, Map<String, Object> filters);
	public List<Activity> getRangeActivities(int first, int nbResult, Map<String, Object> filters);
	public void savePerson(Person p);
	public void saveActivity(Activity a);
	public boolean mailExistant(String mail);
	public void removePerson(Person p);
	public void removeActivity(Activity a);
}
