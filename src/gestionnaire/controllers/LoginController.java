package gestionnaire.controllers;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import gestionnaire.entities.Person;
import gestionnaire.managers.ConnectStatus;
import gestionnaire.managers.IUserManager;

@ManagedBean(name = "login")
@SessionScoped
public class LoginController {

	IGestionnaireController gestionnaire;
	
	private boolean edition = false;
	
	@EJB
	IUserManager um;

	private String pwd;
	private String msg;
	private String mail;

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getMail() {
		return mail;
	}
	
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public Person getUser() {
		return um.getUser();
	}

	public String connect() {
		ConnectStatus result = um.connect(mail, pwd);
		if (result == ConnectStatus.UNKNOWN) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Mail inconnu", "Merci d'entrer un mail existant"));
			return "login";
		} else if(result == ConnectStatus.WRONGPWD){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Mot de passe incorrect", "Merci d'entrer le bon mot de passe"));
			return "login";
		}
		
		gestionnaire.showUser();
		return "";
	}
	
	public boolean isConnected() {
		return um.isConnected();
	}

	public String logout() {
		System.out.println("Logout");
		um.logout();
		this.edition = false;
		return "login";
	}

	public IGestionnaireController getGestionnaire() {
		return gestionnaire;
	}

	public void setGestionnaire(IGestionnaireController gestionnaire) {
		this.gestionnaire = gestionnaire;
	}
	
	public void changeEdition() {
		this.edition = !edition;
	}
	
	public boolean getEdition() {
		return this.edition;
	}
	
	
}
