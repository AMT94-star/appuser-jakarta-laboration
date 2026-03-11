package se.amt.appuserjakartalaboration.repository;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import se.amt.appuserjakartalaboration.entity.AppUser;

import java.util.List;

@ApplicationScoped
public class AppUserJpaRepository implements AppUserRepository {

    @PersistenceContext(unitName = "appuser_database")
    private EntityManager em;

    @Override
    public AppUser save(AppUser appUser) {
        em.persist(appUser);
        return appUser;
    }

    @Override
    public AppUser findByUsernamePassword(String username, String password) {
        List<AppUser> result = em.createQuery(
                        "SELECT u FROM AppUser u WHERE u.username = :username AND u.password = :password",
                        AppUser.class).setParameter("username", username)
                .setParameter("password", password).getResultList();

        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public List<AppUser> findAll() {
        return em.createQuery("SELECT u FROM AppUser u", AppUser.class).getResultList();
    }


}
