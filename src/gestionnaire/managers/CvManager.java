package gestionnaire.managers;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import gestionnaire.entities.Activity;
import gestionnaire.entities.CV;
import gestionnaire.entities.Person;

/**
 * Manager de l'application représenté par un EJB stateless. Contient nottemment
 * les méthodes CRUD des personnes et des activités
 *
 */
@Stateless
public class CvManager implements ICvManager {

	@PersistenceContext(unitName = "myData2")
	EntityManager em;

	/**
	 * Retourne la liste des personnes de la base de donnée
	 * 
	 * @return la liste des personnes
	 */
	@Override
	public List<Person> getAllPersons() {
		return em.createQuery("Select p From Person p", Person.class).getResultList();
	}

	/**
	 * Retourne une liste de personne selon un intervalle définit
	 * 
	 * @param first    l'index de la première personne parmis l'ensemble de la base
	 * @param nbResult le nombre de personne à retourné
	 * @param filters  les filtres
	 * @return la liste de personnes selon l'intervalle définit
	 */
	@Override
	public List<Person> getRangePersons(int first, int nbResult, Map<String, Object> filters) {

		String query = "Select p From Person p";

		if (filters != null && !filters.isEmpty()) {
			String where = "";
			for (String name : filters.keySet()) {
				String key = name.toString();
				String value = filters.get(name).toString();
				System.out.println(key + " OOO " + value);
				where += key + " LIKE '%" + value + "%' AND ";
			}

			where = where.substring(0, where.length() - 5);

			System.out.println(query + " Where " + where);
			query += " Where " + where;
		}

		return em.createQuery(query, Person.class).setFirstResult(first).setMaxResults(nbResult).getResultList();
	}

	/**
	 * Retourne la liste de l'ensemble des activités de la base de donnée
	 * 
	 * @return la liste de l'ensemble des activités de la base de donnée
	 */
	@Override
	public List<Activity> getAllActivities() {
		return em.createQuery("Select a From Activity a", Activity.class).getResultList();
	}

	/**
	 * Retourne une liste d'activité selon un intervalle définit
	 * 
	 * @param first    l'index de la première activité parmis l'ensemble de la base
	 * @param nbResult le nombre de personne à retourné
	 * @param filters  les filtres
	 * @return la liste d'activités selon l'intervalle définit
	 */
	@Override
	public List<Activity> getRangeActivities(int first, int nbResult, Map<String, Object> filters) {

		String query = "Select a From Activity a";

		if (filters != null && !filters.isEmpty()) {
			String where = "";
			for (String name : filters.keySet()) {
				String key = name.toString();
				String value = filters.get(name).toString();
				System.out.println(key + " OOO " + value);
				where += key + " LIKE '%" + value + "%' AND ";
			}

			where = where.substring(0, where.length() - 5);

			System.out.println(query + " Where " + where);
			query += " Where " + where;
		}

		return em.createQuery(query, Activity.class).setFirstResult(first).setMaxResults(nbResult).getResultList();
	}

	/**
	 * Retourne la personne correspondant à l'id passé en paramètre
	 * 
	 * @param idPerson l'id de la personne recherché
	 * @return la personne correspondant à l'id
	 */
	@Override
	public Person getPersonById(long idPerson) {
		Person p = em.createQuery("Select p From Person p Where id = :idPerson", Person.class)
				.setParameter("idPerson", idPerson).getSingleResult();
		p.setCv(getActivitiesPerson(p));
		return p;
	}

	/**
	 * Retourne le CV contenant la liste des activités d'une personne
	 * 
	 * @param p la personne dont on veut le CV
	 * @return le CV de la personne
	 */
	@Override
	public CV getActivitiesPerson(Person p) {
		Query query = em.createQuery("Select a From Activity a Where owner = :p", Activity.class).setParameter("p", p);
		CV cv = new CV(query.getResultList());
		return cv;
	}

	/**
	 * Retoune l'activité correspondant à l'id passé en paramètre
	 * 
	 * @param idActivity l'id de l'activité recherché
	 * @return l'activité correspondant à l'id
	 */
	@Override
	public Activity getActivityById(long idActivity) {
		return em.createQuery("Select a From Activity a Where id = :idActivity", Activity.class)
				.setParameter("idActivity", idActivity).getSingleResult();
	}

	/**
	 * Sauvegarde en base la personne passé en paramètre
	 * 
	 * @param p la personne à sauvegarder
	 */
	@Override
	public void savePerson(Person p) {
		if (p.getId() == null)
			em.persist(p);
		else
			em.merge(p);
	}

	/**
	 * Sauvegarde en base l'activité passé en paramètre
	 * 
	 * @param a l'activité
	 */
	@Override
	public void saveActivity(Activity a) {
		if (a.getId() == null)
			em.persist(a);
		else
			em.merge(a);
	}

	/**
	 * Vérifie si un mail existe déjà dans la base de donnée
	 * 
	 * @param mail le mail à rechercher
	 * @return true si le mail existe false dans le cas contraire
	 */
	@Override
	public boolean mailExistant(String mail) {
		try {
			em.createQuery("Select p From Person p Where mail = :mail", Person.class).setParameter("mail", mail)
					.getSingleResult();
		} catch (NoResultException e) {
			return false;
		}
		return true;
	}

	/**
	 * Supprime une activité de la base de donnée
	 * @param a l'activité à supprimer
	 */
	@Override
	public void removeActivity(Activity a) {
		em.remove(a);
	}

}
