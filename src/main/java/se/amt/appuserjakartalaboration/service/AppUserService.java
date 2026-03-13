package se.amt.appuserjakartalaboration.service;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import se.amt.appuserjakartalaboration.entity.AppUser;
import se.amt.appuserjakartalaboration.repository.AppUserRepository;

import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
public class AppUserService {

    private AppUserRepository appUserRepository;

    private static final Logger logger = Logger.getLogger(AppUserService.class.getName());

    @Inject
    public AppUserService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    public AppUserService() {
    }

    @Transactional
    public AppUser saveUser(AppUser user) {
        logger.info("Saving user: " + user.getUsername());
        AppUser savedUser = appUserRepository.save(user);
        logger.info("Saved user with id: " + savedUser.getId());
        return savedUser;
    }

    public List<AppUser> getAllUsers() {
        logger.info("Retrieving all users");
        return appUserRepository.findAll();
    }

    public AppUser login(String username, String password) {
        logger.warning("testing logger");
        logger.info("Login attempt for: " + username);
        AppUser user = appUserRepository.findByUsernamePassword(username, password);

        if (user != null) {
            logger.info("Logged in as: " + username);
        } else {
            logger.warning("Login failed for user: " + username);
        }

        return user;
    }
}
