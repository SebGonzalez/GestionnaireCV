package gestionnaire.controllers;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import org.primefaces.PrimeFaces;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.LazyDataModel;

import gestionnaire.entities.Activity;
import gestionnaire.entities.ActivityType;
import gestionnaire.entities.CV;
import gestionnaire.entities.Person;
import gestionnaire.managers.ICvManager;

@ManagedBean(name = "gestionnairec")
@SessionScoped
public class GestionnaireController implements IGestionnaireController {

	private static final int NB_PERSON = 100_000;

	@ManagedProperty(value = "#{login}")
	LoginController login;

	@EJB
	ICvManager cm;

	Person p;

	Activity selectedActivity;

	private LazyDataModel<Person> lazyModel;

	@PostConstruct
	public void initBd() {
		if (cm.getAllPersons().size() == 0) {
			System.out.println("INITIALISATION BD");
			int leftLimit = 97; // letter 'a'
			int rightLimit = 122; // letter 'z'
			int targetStringLength = 10;
			Random random = new Random();

			Calendar c1 = GregorianCalendar.getInstance();
			c1.set(1997, Calendar.FEBRUARY, 25);

			Person person = new Person("Lamblino", "Sébastien", "lamblino@hotmail.fr", "lamblino.fr", "azerty");
			person.setBirthDay(c1.getTime());
			cm.savePerson(person);
			Activity a1 = new Activity(1965, ActivityType.PROJECT, "Création de site web",
					"Travaux pour différents particuliers", "lamblno@hotmail.fr", person);
			Activity a2 = new Activity(1965, ActivityType.EXPERIENCE, "Directeur adjoint d'un centre aéré",
					"Sécher les cours pour s'occuper des enfants", "lamblno@hotmail.fr", person);
			cm.saveActivity(a1);
			cm.saveActivity(a2);

			for (int i = 0; i < NB_PERSON; i++) {
				String generatedString = random.ints(leftLimit, rightLimit + 1).limit(targetStringLength)
						.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
				Person p = new Person(generatedString, generatedString, generatedString + "@hotmail.fr",
						generatedString + ".fr", "azerty");
				p.setBirthDay(c1.getTime());
				cm.savePerson(p);

				for (int y = 0; y < 10; y++) {
					String generatedString2 = random.ints(leftLimit, rightLimit + 1).limit(targetStringLength)
							.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
							.toString();
					Activity activity = new Activity(random.nextInt(2020 - 1950) + 1950, ActivityType.FORMATION,
							generatedString2, generatedString2, generatedString2, p);
					cm.saveActivity(activity);
				}

			}
		}

		// lazyModel = new LazyPersonDataModel(cm.getAllPersons());
		lazyModel = new LazyPersonDataModel(cm, cm.getAllPersons().size());
		this.login.setGestionnaire(this);
	}

	public LoginController getLogin() {
		return login;
	}

	public void setLogin(LoginController login) {
		this.login = login;
	}

	public LazyDataModel<Person> getLazyModel() {
		return lazyModel;
	}

	@Override
	public void savePerson(Person p) {
		p.getBirthDay().setHours(6);
		login.changeEdition();
		cm.savePerson(p);
	}

	@Override
	public void saveActivity(Activity a) {
		cm.saveActivity(a);

	}

	@Override
	public List<Person> getAllPersons() {
		return cm.getAllPersons();
	}

	@Override
	public Person getPersonById(long idPerson) {
		return cm.getPersonById(idPerson);
	}

	@Override
	public List<Activity> getAllActivities() {
		return cm.getAllActivities();
	}

	@Override
	public CV getActivitiesPerson(long idPerson) {
		return cm.getActivitiesPerson(idPerson);
	}

	@Override
	public Activity getActivityById(long idActivity) {
		return cm.getActivityById(idActivity);
	}

	public String showPerson(long idPerson) {
		p = getPersonById(idPerson);
		return "showPerson";
	}

	@Override
	public void showUser() {
		p = login.getUser();
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("showPerson.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Person getPersonne() {
		return p;
	}

	public String createPerson() {
		p = new Person();
		return "createPerson";
	}
	
	public void createPerson(Person p) {
		this.lazyModel.setRowCount(this.lazyModel.getRowCount()+1);
		this.savePerson(p);
		this.showUser();
	}

	public Activity getSelectedActivity() {
		return selectedActivity;
	}

	public void setSelectedActivity(Activity selectedActivity) {
		this.selectedActivity = selectedActivity;
	}
	
	public void onActivitySelected() {
		PrimeFaces current = PrimeFaces.current();
		current.executeScript("PF('activityDialog').show();");
	}
	
	public void onRowEdit(RowEditEvent event) {
		Activity a = ((Activity)event.getObject());
		this.saveActivity(a);
        FacesMessage msg = new FacesMessage("Activity Edited : ", ""+a.getTitle());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", ""+((Activity)event.getObject()).getTitle());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void checkMailExist(FacesContext context, UIComponent component, Object data) throws ValidatorException {
    	if(cm.mailExistant((String)data)) {
    		throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Validation Error", 
                    data + " already exist;"));
    	}
    }
	
	
}