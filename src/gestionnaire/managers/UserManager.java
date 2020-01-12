package gestionnaire.managers;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import gestionnaire.entities.Activity;
import gestionnaire.entities.CV;
import gestionnaire.entities.Person;

/**
 * Manager de l'application. Permet de représenter l'utilisateur de
 * l'application Conserve l'état d'authenfication de celui-ci
 *
 */
@Stateful
public class UserManager implements IUserManager {

	@PersistenceContext(unitName = "myData2")
	EntityManager em;

	private boolean connected = false;
	private Person user;

	/**
	 * Méthode de connexion à l'application
	 * @param user le mail de l'utilisateur
	 * @param pwd le mot de passe de l'utilisateur
	 * @return le status du résultat de la connexion
	 */
	@Override
	public ConnectStatus connect(String user, String pwd) {
		Person p;
		try {
			p = em.createQuery("Select p From Person p Where mail = :mail", Person.class).setParameter("mail", user)
					.getSingleResult();
			Query query = em.createQuery("Select a From Activity a Where owner = :p", Activity.class).setParameter("p",
					p);
			CV cv = new CV(query.getResultList());
			p.setCv(cv);
		} catch (NoResultException e) {
			p = null;
		}

		if (p == null)
			return ConnectStatus.UNKNOWN;
		else if (!p.getPassword().equals(pwd))
			return ConnectStatus.WRONGPWD;
		this.user = p;
		this.connected = true;
		return ConnectStatus.OK;
	}

	/**
	 * Retourne l'instance Person de l'utilisateur
	 * @return l'instance Person de l'utilisateur
	 */
	@Override
	public Person getUser() {
		return user;
	}

	/**
	 * Modifie l'instance de l'utilisateur de l'application
	 * @param user l'instance de l'utilisateur
	 */
	public void setUser(Person user) {
		this.user = user;
	}

	/**
	 * Retourne l'état de connexion de l'utilisateur
	 * @return true si l'utilisateur est connecté, false sinon
	 */
	@Override
	public boolean isConnected() {
		return connected;
	}

	/**
	 * Déconnecte l'utilisateur de l'application
	 */
	@Override
	public void logout() {
		user = null;
		connected = false;
	}
}
