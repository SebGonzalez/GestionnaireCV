package gestionnaire.managers;

import java.io.Serializable;

/**
 * Enumération définissant les réponses possibles lors de la connexion d'un
 * utilisateur OK : la connexion c'est bien déroule UNKNOWN : le mail n'est pas
 * renseigné dans la base de donnée WRONGPWF : le mail existe mais le mot de
 * passe n'est pas bon
 *
 */
public enum ConnectStatus implements Serializable {
	OK, UNKNOWN, WRONGPWD;
}
