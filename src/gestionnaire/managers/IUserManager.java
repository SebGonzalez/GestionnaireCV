package gestionnaire.managers;

import gestionnaire.entities.Activity;
import gestionnaire.entities.Person;

public interface IUserManager {
	ConnectStatus connect(String user, String pwd);
	public boolean isConnected();
	void logout();
	Person getUser();
}
