package elective.dao;

import java.util.List;

public interface GeneralDao<T> {
    void save(T obj);
    void update(T obj);
    void delete(T obj);
    void deleteAll();
    T findById(Long id);
    List<T> findAll();
}
