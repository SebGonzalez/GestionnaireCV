package gestionnaire.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 * Java bean représentant l'instance d'une activité Une personne est représentée
 * par son id, son année, sa nature, son titre, sa description (facultatif), son
 * site (facultatif) et la personne appartenant
 * 
 */
@Entity
public class Activity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id()
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable = false)
	private int year;
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private ActivityType nature;
	@Column(nullable = false)
	private String title;
	@Column(length = 500)
	private String description;
	@Column
	private String website;
	@OneToOne
	@JoinColumn(name = "owner_fk")
	private Person owner;

	/**
	 * Construit une activité vide
	 */
	public Activity() {
		super();
		nature = ActivityType.EXPERIENCE;
	}

	/**
	 * Construit une activité avec l'ensemble des informations
	 */
	public Activity(int year, ActivityType nature, String title, String description, String website, Person owner) {
		super();
		this.year = year;
		this.nature = nature;
		this.title = title;
		this.description = description;
		this.website = website;
		this.owner = owner;
	}

	/**
	 * Retourne l'id de l'activité
	 * @return l'id de l'activité
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Modifie l'id de l'activité
	 * @param id l'id de l'activité
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Retourne l'année de l'activité
	 * @return l'année de l'activité
	 */
	public int getYear() {
		return year;
	}

	/**
	 * Modifie l'année de l'activité
	 * @param year l'année de l'activité
	 */
	public void setYear(int year) {
		this.year = year;
	}

	/**
	 * Retourne la nature de l'activité
	 * @return la nature de l'activité
	 */
	public ActivityType getNature() {
		return nature;
	}

	/**
	 * Modifie la nature de l'activité
	 * @param nature la nature de l'activité
	 */
	public void setNature(ActivityType nature) {
		this.nature = nature;
	}

	/**
	 * Retourne le titre de l'activité
	 * @return le titre de l'activité
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Modifie le titre de l'activité
	 * @param title le titre de l'activité
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Retourne la description de l'activité
	 * @return la description de l'activité
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Modifie la description de l'activité
	 * @param description la description de l'activité
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Retourne le site de l'activité (information facultative)
	 * @return le site de l'activité
	 */
	public String getWebsite() {
		return website;
	}

	/**
	 * Modifie le site de l'activité (information facultative)
	 * @param website le site de l'activité
	 */
	public void setWebsite(String website) {
		this.website = website;
	}

	/**
	 * Retourne la personne ayant effectué cette activité
	 * @return la personne ayant effectué cette activité
	 */
	public Person getOwner() {
		return owner;
	}

	/**
	 * Modifie la personne ayant effectué cette activité
	 * @param owner la personne ayant effectué cette activité
	 */
	public void setOwner(Person owner) {
		this.owner = owner;
	}

	/**
	 * Méthode standarde toString
	 */
	public String toString() {
		return "Id : " + id + " ,year : " + year + " ,nature : " + nature + " ,title : " + title + " ,description : "
				+ description + " owner : " + owner;
	}
}
