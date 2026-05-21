package elective.config;

import elective.repository.*;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Factory {

    private static final Factory instance = new Factory();
    private final EntityManagerFactory emf;

    private Factory() {
        emf = Persistence.createEntityManagerFactory("electivedb");
    }

    public static Factory getInstance() {
        return instance;
    }

    public EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }

    public TeacherRepository getTeacherRepository() { return new TeacherRepository(); }
    public CourseRepository getCourseRepository() { return new CourseRepository(); }
    public StudentRepository getStudentRepository() { return new StudentRepository(); }
    public RegistrationRepository getRegistrationRepository() { return new RegistrationRepository(); }
    public ArchiveRepository getArchiveRepository() { return new ArchiveRepository(); }
}
