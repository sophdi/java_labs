package elective.repository;

import elective.config.Factory;
import elective.dao.CourseDao;
import elective.entity.Course;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class CourseRepository implements CourseDao {

    private EntityManager getEntityManager() {
        return Factory.getInstance().getEntityManagerFactory().createEntityManager();
    }

    @Override
    public void save(Course obj) {
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
    public void update(Course obj) {
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
    public void delete(Course obj) {
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
            em.createQuery("DELETE FROM Course").executeUpdate();
            tx.commit();
        } finally {
            em.close();
        }
    }

    @Override
    public Course findById(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Course.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Course> findAll() {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("FROM Course", Course.class).getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Course> findByTeacherId(Long teacherId) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("FROM Course c WHERE c.teacher.id = :teacherId", Course.class)
                    .setParameter("teacherId", teacherId)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Course> findAllNativeSQL() {
        EntityManager em = getEntityManager();
        try {
            return em.createNativeQuery("SELECT * FROM courses", Course.class).getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Course> findByNameNativeSQL(String name) {
        EntityManager em = getEntityManager();
        try {
            return em.createNativeQuery("SELECT * FROM courses WHERE name = ?", Course.class)
                    .setParameter(1, name)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Course> findAllOrderedHQL() {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("FROM Course c ORDER BY c.name", Course.class).getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Course> findLongerThanHQL(int weeks) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("FROM Course c WHERE c.durationWeeks > :weeks", Course.class)
                    .setParameter("weeks", weeks)
                    .getResultList();
        } finally {
            em.close();
        }
    }
}
