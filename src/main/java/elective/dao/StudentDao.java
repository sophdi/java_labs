package elective.dao;

import elective.entity.Student;
import java.util.List;

public interface StudentDao extends GeneralDao<Student> {
    List<Student> findByCourseId(Long courseId);
}
