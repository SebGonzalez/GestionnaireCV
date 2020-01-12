package gestionnaire.managers;

import java.util.List;
import java.util.Map;

import gestionnaire.entities.Activity;
import gestionnaire.entities.CV;
import gestionnaire.entities.Person;

/**
 * Interface définissant les fonction du manager. Contient nottemment les
 * méthodes CRUD des personnes et des activités
 *
 */
public interface ICvManager {
	/**
	 * Retourne la liste des personnes de la base de donnée
	 * @return la liste des personnes
	 */
	public List<Person> getAllPersons();

	/**
	 * Retourne la personne correspondant à l'id passé en paramètre
	 * @param idPerson l'id de la personne recherché
	 * @return la personne correspondant à l'id
	 */
	public Person getPersonById(long idPerson);

	/**
	 * Retourne la liste de l'ensemble des activités de la base de donnée
	 * @return la liste de l'ensemble des activités de la base de donnée
	 */
	public List<Activity> getAllActivities();

	/**
	 * Retourne le CV contenant la liste des activités d'une personne
	 * @param p la personne dont on veut le CV
	 * @return le CV de la personne
	 */
	public CV getActivitiesPerson(Person p);

	/**
	 * Retoune l'activité correspondant à l'id passé en paramètre
	 * @param idActivity l'id de l'activité recherché
	 * @return l'activité correspondant à l'id
	 */
	public Activity getActivityById(long idActivity);

	/**
	 * Retourne une liste de personne selon un intervalle définit
	 * @param first l'index de la première personne parmis l'ensemble de la base
	 * @param nbResult le nombre de personne à retourné
	 * @param filters les filtres
	 * @return la liste de personnes selon l'intervalle définit
	 */
	public List<Person> getRangePersons(int first, int nbResult, Map<String, Object> filters);

	/**
	 * Retourne une liste d'activité selon un intervalle définit
	 * @param first l'index de la première activité parmis l'ensemble de la base
	 * @param nbResult le nombre de personne à retourné
	 * @param filters les filtres
	 * @return la liste d'activités selon l'intervalle définit
	 */
	public List<Activity> getRangeActivities(int first, int nbResult, Map<String, Object> filters);

	/**
	 * Sauvegarde en base la personne passé en paramètre
	 * @param p la personne à sauvegarder
	 */
	public void savePerson(Person p);

	/**
	 * Sauvegarde en base l'activité passé en paramètre
	 * @param a l'activité
	 */
	public void saveActivity(Activity a);

	/**
	 * Vérifie si un mail existe déjà dans la base de donnée
	 * @param mail le mail à rechercher
	 * @return true si le mail existe false dans le cas contraire
	 */
	public boolean mailExistant(String mail);

	/**
	 * Supprime une activité de la base de donnée
	 * @param a l'activité à supprimer
	 */
	public void removeActivity(Activity a);
}
