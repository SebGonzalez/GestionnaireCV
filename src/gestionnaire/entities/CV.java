package gestionnaire.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Java Bean représentant le CV d'une personne (liste d'activité). Inutile de le
 * sauvegardé en base de donnée, on peut reconstruire une instance d'un CV à
 * partir des tuples de la table ACTIVITY à partir de
 * la propriété "owner"
 * 
 *
 */
public class CV {
	private List<Activity> activities;

	public CV() {
		this.activities = new ArrayList<Activity>();
	}

	public CV(List<Activity> activities) {
		this.activities = activities;
	}

	public List<Activity> getActivities() {
		return activities;
	}

	public void setActivities(List<Activity> activities) {
		this.activities = activities;
	}

}
