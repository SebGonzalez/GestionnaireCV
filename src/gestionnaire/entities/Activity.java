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
	 * Constructeur vide
	 */
	public Activity() {
		super();
		nature = ActivityType.EXPERIENCE;
	}
	
	public Activity(int year, ActivityType nature, String title, String description, String website, Person owner) {
		super();
		this.year = year;
		this.nature = nature;
		this.title = title;
		this.description = description;
		this.website = website;
		this.owner = owner;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getYear() {
		return year;
	}
	
	public void setYear(int year) {
		this.year = year;
	}
	
	public ActivityType getNature() {
		return nature;
	}
	
	public void setNature(ActivityType nature) {
		this.nature = nature;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getWebsite() {
		return website;
	}
	
	public void setWebsite(String website) {
		this.website = website;
	}
	
	public Person getOwner() {
		return owner;
	}
	
	public void setOwner(Person owner) {
		this.owner = owner;
	}
	
	public String toString() {
		return "Id : " + id + " ,year : " + year + " ,nature : " + nature + " ,title : " + title + " ,description : " + description + " owner : " + owner;
	}
}
