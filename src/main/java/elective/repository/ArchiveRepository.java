package elective.repository;

import elective.config.Factory;
import elective.dao.ArchiveDao;
import elective.entity.Archive;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class ArchiveRepository implements ArchiveDao {

    private EntityManager getEntityManager() {
        return Factory.getInstance().getEntityManagerFactory().createEntityManager();
    }

    @Override
    public void save(Archive obj) {
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
    public void update(Archive obj) {
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
    public void delete(Archive obj) {
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
            em.createQuery("DELETE FROM Archive").executeUpdate();
            tx.commit();
        } finally {
            em.close();
        }
    }

    @Override
    public Archive findById(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Archive.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Archive> findAll() {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("FROM Archive", Archive.class).getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public Archive findByRegistrationId(Long registrationId) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("FROM Archive a WHERE a.registration.id = :regId", Archive.class)
                    .setParameter("regId", registrationId)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
}
