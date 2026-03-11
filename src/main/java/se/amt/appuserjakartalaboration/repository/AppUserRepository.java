package se.amt.appuserjakartalaboration.repository;

import se.amt.appuserjakartalaboration.entity.AppUser;

import java.util.List;

public interface AppUserRepository {

    AppUser save(AppUser appUser);

    AppUser findByUsernamePassword(String username, String password);

    List<AppUser> findAll();
}
