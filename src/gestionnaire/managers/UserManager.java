package gestionnaire.managers;

import javax.ejb.Stateful;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import gestionnaire.entities.Activity;
import gestionnaire.entities.CV;
import gestionnaire.entities.Person;

@Stateful
public class UserManager implements IUserManager {

	@PersistenceContext(unitName = "myData2")
	EntityManager em;
	
	private boolean connected = false;
	private Person user;
		
	@Override
	public ConnectStatus connect(String user, String pwd) {
		Person p;
		try {
			p = em.createQuery("Select p From Person p Where mail = :mail", Person.class).setParameter("mail", user).getSingleResult();
			Query query = em.createQuery("Select a From Activity a Where owner = :p", Activity.class).setParameter("p", p);
			CV cv = new CV(query.getResultList());
			p.setCv(cv);
		} catch(NoResultException e) {
			p = null;
		}

		if(p == null)
			return ConnectStatus.UNKNOWN;
		else if(!p.getPassword().equals(pwd))
			return ConnectStatus.WRONGPWD;
		this.user = p;
		this.connected = true;
		return ConnectStatus.OK;
	}

	@Override
	public Person getUser() {
		return user;
	}


	public void setUser(Person user) {
		this.user = user;
	}


	@Override
	public boolean isConnected() {
		return connected;
	}
	
	@Override
	public void logout() {
		user = null;
		connected = false;
	}
}
