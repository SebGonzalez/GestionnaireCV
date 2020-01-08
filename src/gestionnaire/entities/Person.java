package gestionnaire.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;


/**
 * Java bean représentant l'instance d'une personne
 * Une personne est représentée par son id, nom, prénom, mail, site, date de naissance, mot de passe et son cv.
 * 
 *
 */
@Entity(name = "Person")
public class Person implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id()
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Basic(optional = false)
	@Column(name = "name", length = 200, nullable = false)
	private String name;

	@Basic(optional = false)
	@Column(name = "first_name", length = 200, nullable = false)
	private String firstName;

	@Basic(optional = false)
	@Column(name = "mail", length = 200, nullable = false, unique = true)
	private String mail;

	@Basic()
	@Column(name = "site", length = 200, nullable = true, unique = true)
	private String site;

	@Basic(optional = false)
	@Column(name = "birth_day")
	@Temporal(TemporalType.DATE)
	private Date birthDay;

	@Basic(optional = false)
	@Column(name = "password", length = 200, nullable = false)
	private String password;

	@Transient
	private CV cv;

	/**
	 * Constructeur vide
	 */
	public Person() {
		super();
	}
	
	/**
	 * Constructeur paramétré
	 * @param name, le nom de la personne
	 * @param firstname, le prénom de la personne
	 * @param mail, le mail de la personne
	 * @param site, le site de la personne
	 * @param password, le mot de passe de la personne
	 */
	public Person(String name, String firstname, String mail, String site, String password) {
		super();
		this.name = name;
		this.firstName = firstname;
		this.mail = mail;
		this.site = site;
		this.password = password;
	}
	
	/**
	 * @return Getter retournant l'id de la personne
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Setter modifiant l'id de la personne
	 * @param id, l'id de la personne
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return Getter retournant le nom de la personne
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter modifiant le nom de la personne
	 * @param name, le nom de la personne
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return Getter retournant le prénom de la personne
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Setter modifiant le prénom de la personne
	 * @param firstName, le prénom du groupe
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return Getter retournant le mail de la personne
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * Setter modifiant le mail de la personne
	 * @param mail, le mail de la personne
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}

	/**
	 * @return Getter retournant le site de la personne
	 */
	public String getSite() {
		return site;
	}

	/**
	 * Setter modifiant le site de la personne
	 * @param site, le site de la personne
	 */
	public void setSite(String site) {
		this.site = site;
	}

	/**
	 * @return Getter retournant la date de naissance de la personne
	 */
	public Date getBirthDay() {
		return birthDay;
	}

	/**
	 * Setter modifiant la date de naissance de la personne
	 * @param birthDay, la date de naissance de la personne
	 */
	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

	/**
	 * @return Getter retournant le mot de passe de la personne
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Setter modifiant le mot de passe de la personne
	 * @param password, le mot de passe de la personne
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	public CV getCv() {
		return cv;
	}

	public void setCv(CV cv) {
		this.cv = cv;
	}
	
	@Override
	public String toString() {
		return id + " " + name + " " + firstName + " " + mail + " " + site + " " + password + " " + birthDay;
	}
}
