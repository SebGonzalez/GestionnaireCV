package gestionnaire.managers;

import gestionnaire.entities.Person;

/**
 * Interface définissant les fonction du manager. Permet de représenter
 * l'utilisateur de l'application Conserve l'état d'authenfication de celui-ci
 *
 */
public interface IUserManager {
	/**
	 * Méthode de connexion à l'application
	 * @param user le mail de l'utilisateur
	 * @param pwd le mot de passe de l'utilisateur
	 * @return le status du résultat de la connexion
	 */
	ConnectStatus connect(String user, String pwd);
	
	/**
	 * Retourne l'état de connexion de l'utilisateur
	 * @return true si l'utilisateur est connecté, false sinon
	 */
	public boolean isConnected();
	
	/**
	 * Déconnecte l'utilisateur de l'application
	 */
	void logout();
	
	/**
	 * Retourne l'instance Person de l'utilisateur
	 * @return l'instance Person de l'utilisateur
	 */
	Person getUser();
}
