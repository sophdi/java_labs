package elective.dao;

import elective.entity.Student;
import java.util.List;

public interface StudentDao extends GeneralDao<Student> {
    List<Student> findByCourseId(Long courseId);

    // Native SQL
    List<Student> findAllNativeSQL();
    List<Student> findByCourseYearNativeSQL(int courseYear);

    // HQL
    List<Student> findAllOrderedHQL();
    List<Student> findByCourseYearHQL(int courseYear);
}
