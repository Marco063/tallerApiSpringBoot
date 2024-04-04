package com.edu.uptc.tallerApiSpringBoot.repositories;

import com.edu.uptc.tallerApiSpringBoot.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    void deleteById(Integer id);
}
