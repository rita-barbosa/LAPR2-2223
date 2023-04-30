package pt.ipp.isep.dei.esoft.project.repository;


import pt.ipp.isep.dei.esoft.project.domain.Agency;
import pt.isep.lei.esoft.auth.AuthFacade;
import pt.isep.lei.esoft.auth.UserSession;

public class AuthenticationRepository {
    private final AuthFacade authenticationFacade = new AuthFacade();

    public boolean doLogin(String email, String pwd) {
        return authenticationFacade.doLogin(email, pwd).isLoggedIn();
    }

    public void doLogout() {
        authenticationFacade.doLogout();
    }

    public UserSession getCurrentUserSession() {
        return authenticationFacade.getCurrentUserSession();
    }

    public boolean addUserRole(String id, String description) {
        return authenticationFacade.addUserRole(id, description);
    }

    public boolean addUserWithRole(String name, String email, String pwd, String roleId) {
        if (validatePassword(pwd)) {
            return authenticationFacade.addUserWithRole(name, email, pwd, roleId);
        }
        return false;
    }

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
