package elective.repository;

import elective.config.Factory;
import elective.dao.StudentDao;
import elective.entity.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class StudentRepository implements StudentDao {

    private EntityManager getEntityManager() {
        return Factory.getInstance().getEntityManagerFactory().createEntityManager();
    }

    @Override
    public void save(Student obj) {
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
    public void update(Student obj) {
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
    public void delete(Student obj) {
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
            em.createQuery("DELETE FROM Student").executeUpdate();
            tx.commit();
        } finally {
            em.close();
        }
    }

    @Override
    public Student findById(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Student.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Student> findAll() {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("FROM Student", Student.class).getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Student> findByCourseId(Long courseId) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery(
                    "SELECT s FROM Student s JOIN s.courses c WHERE c.id = :courseId", Student.class)
                    .setParameter("courseId", courseId)
                    .getResultList();
        } finally {
            em.close();
        }
    }
}
