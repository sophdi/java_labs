package elective.repository;

import elective.config.Factory;
import elective.dao.RegistrationDao;
import elective.entity.Registration;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class RegistrationRepository implements RegistrationDao {

    private EntityManager getEntityManager() {
        return Factory.getInstance().getEntityManagerFactory().createEntityManager();
    }

    @Override
    public void save(Registration obj) {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(obj);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public void update(Registration obj) {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(obj);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public void delete(Registration obj) {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.remove(em.contains(obj) ? obj : em.merge(obj));
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public void deleteAll() {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.createQuery("DELETE FROM Registration").executeUpdate();
            tx.commit();
        } finally {
            em.close();
        }
    }

    @Override
    public Registration findById(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Registration.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Registration> findAll() {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("FROM Registration", Registration.class).getResultList();
        } finally {
            em.close();
        }
    }
}
