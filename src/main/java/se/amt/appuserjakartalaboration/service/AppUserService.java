package se.amt.appuserjakartalaboration.service;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import se.amt.appuserjakartalaboration.entity.AppUser;
import se.amt.appuserjakartalaboration.repository.AppUserRepository;

import java.util.List;

@ApplicationScoped
public class AppUserService {

    private AppUserRepository appUserRepository;

    @Inject
    public AppUserService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    public AppUserService() {
    }

    @Transactional
    public AppUser saveUser(AppUser user) {
        return appUserRepository.save(user);
    }

    public List<AppUser> getAllUsers() {
        return appUserRepository.findAll();
    }

    public AppUser login(String username, String password) {
        return appUserRepository.findByUsernamePassword(username, password);
    }
}
