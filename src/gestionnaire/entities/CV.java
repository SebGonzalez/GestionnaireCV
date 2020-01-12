package gestionnaire.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Java Bean représentant le CV d'une personne (liste d'activité). Inutile de le
 * sauvegardé en base de donnée, on peut reconstruire une instance d'un CV à
 * partir des tuples de la table ACTIVITY à partir de la propriété "owner"
 * 
 */
public class CV {
	private List<Activity> activities;

	/**
	 * Créer un CV avec une liste d'activité vide
	 */
	public CV() {
		this.activities = new ArrayList<Activity>();
	}

	/**
	 * Créer un CV avec une liste d'activité
	 * @param activities
	 */
	public CV(List<Activity> activities) {
		this.activities = activities;
	}

	/**
	 * Retourne la liste d'activités
	 * @return la liste d'activités
	 */
	public List<Activity> getActivities() {
		return activities;
	}

	/**
	 * Modifie la liste d'activités
	 * @param activities la liste d'activité à modifier
	 */
	public void setActivities(List<Activity> activities) {
		this.activities = activities;
	}

	/**
	 * Ajoute une activité au CV
	 * 
	 * @param l'activité
	 */
	public void addActivity(Activity a) {
		activities.add(a);
	}

	public void removeActivity(Activity a) {
		activities.remove(a);
	}

}
