package pt.ipp.isep.dei.esoft.project.repository;


import pt.ipp.isep.dei.esoft.project.domain.Agency;
import pt.isep.lei.esoft.auth.AuthFacade;
import pt.isep.lei.esoft.auth.UserSession;

import java.io.Serializable;

/**
 * The type Authentication repository.
 */
public class AuthenticationRepository implements Serializable {
    /**
     * The Authentication facade.
     */
    private final AuthFacade authenticationFacade = new AuthFacade();

    /**
     * Do login boolean.
     *
     * @param email the email
     * @param pwd   the pwd
     * @return the boolean
     */
    public boolean doLogin(String email, String pwd) {
        return authenticationFacade.doLogin(email, pwd).isLoggedIn();
    }

    /**
     * Do logout.
     */
    public void doLogout() {
        authenticationFacade.doLogout();
    }

    /**
     * Gets current user session.
     *
     * @return the current user session
     */
    public UserSession getCurrentUserSession() {
        return authenticationFacade.getCurrentUserSession();
    }

    /**
     * Add user role boolean.
     *
     * @param id          the id
     * @param description the description
     * @return the boolean
     */
    public boolean addUserRole(String id, String description) {
        return authenticationFacade.addUserRole(id, description);
    }

    /**
     * Add user with role boolean.
     *
     * @param name   the name
     * @param email  the email
     * @param pwd    the pwd
     * @param roleId the role id
     * @return the boolean
     */
    public boolean addUserWithRole(String name, String email, String pwd, String roleId) {
        if (validatePassword(pwd)) {
            return authenticationFacade.addUserWithRole(name, email, pwd, roleId);
        }
        return false;
    }

    /**
     * Validate password boolean.
     *
     * @param pwd the pwd
     * @return the boolean
     */
    private boolean validatePassword(String pwd) {
        int passwordLength = 7;
        int digitCount = 0;
        int upperCaseLettercount = 0;

        if (pwd.length() != passwordLength) {
            return false;
        }
        for (int i = 0; i < pwd.length(); i++) {
            char letter = pwd.charAt(i);
            if (Character.isDigit(letter)) {
                digitCount++;
            } else if (Character.isUpperCase(letter)) {
                upperCaseLettercount++;
            }
        }
        return digitCount == 2 && upperCaseLettercount == 3;
    }

}
