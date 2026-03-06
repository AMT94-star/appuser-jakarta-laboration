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
    public List<AppUser> findAll() {
        return em.createQuery("SELECT u FROM AppUser u", AppUser.class).getResultList();
    }
}
