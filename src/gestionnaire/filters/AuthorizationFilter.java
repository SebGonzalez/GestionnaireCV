package gestionnaire.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import gestionnaire.controllers.GestionnaireController;

/**
 * Classe permettant de filtrer l'accès aux pages de l'application. Utilisé pour
 * la page de cooptation d'une personne (createPerson.xhtml), si l'utilisateur
 * n'est pas connecter il est redirigé vers la page de connexion
 *
 */
@WebFilter(filterName = "AuthFilter", urlPatterns = { "*.xhtml" })
public class AuthorizationFilter implements Filter {

	public AuthorizationFilter() {
	}

	/**
	 * Méthode init non utilisé
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	/**
	 * Filtre l'accès aux pages de l'application
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		try {
			GestionnaireController gc = (GestionnaireController) ((HttpServletRequest) request).getSession()
					.getAttribute("gestionnairec");
			// System.out.println("CONNECTE : " + gc.getLogin().isConnected());

			HttpServletRequest reqt = (HttpServletRequest) request;
			HttpServletResponse resp = (HttpServletResponse) response;
			HttpSession ses = reqt.getSession(false);

			String reqURI = reqt.getRequestURI();

			if (reqURI.contains("/createPerson.xhtml")) {
				if (gc.getLogin() == null || !gc.getLogin().isConnected()) {
					resp.sendRedirect("/projet/login.xhtml");
				} else {
					chain.doFilter(request, response);
				}
			} else {
				chain.doFilter(request, response);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Méthode destroy non utilisé
	 */
	@Override
	public void destroy() {

	}
}
