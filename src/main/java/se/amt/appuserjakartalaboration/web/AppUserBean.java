package se.amt.appuserjakartalaboration.web;


import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import se.amt.appuserjakartalaboration.entity.AppUser;
import se.amt.appuserjakartalaboration.service.AppUserService;

import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class AppUserBean implements Serializable {

    private String username;
    private String password;

    @Inject
    private AppUserService appUserService;

    public List<AppUser> getUsers() {
        return appUserService.getAllUsers();
    }

    public void saveUser() {
        AppUser user = new AppUser();
        user.setUsername(username);
        user.setPassword(password);

        appUserService.saveUser(user);

        username = "";
        password = "";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
