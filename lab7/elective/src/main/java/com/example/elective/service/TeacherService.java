package com.example.elective.service;

import com.example.elective.entity.Teacher;
import com.example.elective.repository.TeacherRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TeacherService {

    private final TeacherRepository teacherRepository;

    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Transactional(readOnly = true)
    public List<Teacher> findAll() {
        return teacherRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Teacher> findById(Long id) {
        return teacherRepository.findById(id);
    }

    public Teacher save(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    public void delete(Long id) {
        teacherRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public boolean emailExists(String email) {
        return teacherRepository.existsByEmail(email);
    }

    @Transactional(readOnly = true)
    public boolean emailExistsForOther(String email, Long id) {
        return teacherRepository.existsByEmailAndIdNot(email, id);
    }
}