package se.amt.appuserjakartalaboration.web;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import se.amt.appuserjakartalaboration.entity.AppUser;
import se.amt.appuserjakartalaboration.service.AppUserService;

import java.io.Serializable;

@Named
@SessionScoped
public class LoginBean implements Serializable {

    private String username;
    private String password;
    private AppUser loggedInUser;

    @Inject
    private AppUserService appUserService;

    public String login() {
        AppUser user = appUserService.login(username, password);

        if (user != null) {
            loggedInUser = user;
            return "/chat.xhtml?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Login error", "Wrong username or password"));
            return null;
        }
    }

    public String logout() {
        loggedInUser = null;
        username = null;
        password = null;

        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/login.xhtml?faces-redirect=true";
    }

    public boolean isLoggedIn() {
        return loggedInUser != null;
    }

    public String getDisplayName() {
        return loggedInUser != null ? loggedInUser.getUsername() : "";
    }

    public AppUser getLoggedInUser() {
        return loggedInUser;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
