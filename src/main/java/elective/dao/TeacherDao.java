package elective.dao;

import elective.entity.Teacher;
import java.util.List;

public interface TeacherDao extends GeneralDao<Teacher> {
    List<Teacher> findByLastName(String lastName);
}
