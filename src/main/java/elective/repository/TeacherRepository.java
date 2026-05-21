package elective.repository;

import elective.config.Factory;
import elective.dao.TeacherDao;
import elective.entity.Teacher;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class TeacherRepository implements TeacherDao {

    private EntityManager getEntityManager() {
        return Factory.getInstance().getEntityManagerFactory().createEntityManager();
    }

    @Override
    public void save(Teacher obj) {
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
    public void update(Teacher obj) {
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
    public void delete(Teacher obj) {
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
            em.createQuery("DELETE FROM Teacher").executeUpdate();
            tx.commit();
        } finally {
            em.close();
        }
    }

    @Override
    public Teacher findById(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Teacher.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Teacher> findAll() {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("FROM Teacher", Teacher.class).getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Teacher> findByLastName(String lastName) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("FROM Teacher t WHERE t.lastName = :lastName", Teacher.class)
                    .setParameter("lastName", lastName)
                    .getResultList();
        } finally {
            em.close();
        }
    }
}
