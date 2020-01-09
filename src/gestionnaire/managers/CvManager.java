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

@Stateless
public class CvManager implements ICvManager {

	@PersistenceContext(unitName = "myData2")
	EntityManager em;
	
	@Override
	public List<Person> getAllPersons() {
		return em.createQuery("Select p From Person p", Person.class).getResultList();
	}

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
			query +=  " Where " + where;
		}
		
		return em.createQuery(query, Person.class).setFirstResult(first).setMaxResults(nbResult)
				.getResultList();
	}
	
	@Override
	public List<Activity> getAllActivities() {
		return em.createQuery("Select a From Activity a", Activity.class).getResultList();
	}
	
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
			query +=  " Where " + where;
		}
		
		return em.createQuery(query, Activity.class).setFirstResult(first).setMaxResults(nbResult)
				.getResultList();
	}

	@Override
	public Person getPersonById(long idPerson) {
		Person p = em.createQuery("Select p From Person p Where id = :idPerson", Person.class)
				.setParameter("idPerson", idPerson).getSingleResult();
		p.setCv(getActivitiesPerson(p));
		return p;
	}

	@Override
	public CV getActivitiesPerson(Person p) {
		Query query = em.createQuery("Select a From Activity a Where owner = :p", Activity.class).setParameter("p", p);
		CV cv = new CV(query.getResultList());
		return cv;
	}

	@Override
	public Activity getActivityById(long idActivity) {
		return em.createQuery("Select a From Activity a Where id = :idActivity", Activity.class)
				.setParameter("idActivity", idActivity).getSingleResult();
	}
	
	@Override
	public void savePerson(Person p) {
		if(p.getId() == null)
			em.persist(p);
		else
			em.merge(p);
	}

	@Override
	public void saveActivity(Activity a) {
		if(a.getId() == null)
			em.persist(a);
		else
			em.merge(a);
	}
	
	@Override
	public void removePerson(Person p) {
		em.remove(p);
	}
	
	@Override
	public void removeActivity(Activity a) {
		em.remove(a);
	}

	@Override
	public boolean mailExistant(String mail) {
		try {
			em.createQuery("Select p From Person p Where mail = :mail", Person.class).setParameter("mail", mail).getSingleResult();
		} catch(NoResultException e) {
			return false;
		}
		return true;
	}

}
