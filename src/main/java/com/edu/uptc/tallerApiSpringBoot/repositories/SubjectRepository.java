package com.edu.uptc.tallerApiSpringBoot.repositories;

import com.edu.uptc.tallerApiSpringBoot.entities.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {
}
