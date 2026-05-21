package elective.dao;

import elective.entity.Course;
import java.util.List;

public interface CourseDao extends GeneralDao<Course> {
    List<Course> findByTeacherId(Long teacherId);
}
