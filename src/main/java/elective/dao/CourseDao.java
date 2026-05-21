package elective.dao;

import elective.entity.Course;
import java.util.List;

public interface CourseDao extends GeneralDao<Course> {
    List<Course> findByTeacherId(Long teacherId);

    // Native SQL
    List<Course> findAllNativeSQL();
    List<Course> findByNameNativeSQL(String name);

    // HQL
    List<Course> findAllOrderedHQL();
    List<Course> findLongerThanHQL(int weeks);
}
