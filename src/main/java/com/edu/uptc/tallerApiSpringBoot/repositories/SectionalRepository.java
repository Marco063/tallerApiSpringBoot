package com.edu.uptc.tallerApiSpringBoot.repositories;

import com.edu.uptc.tallerApiSpringBoot.entities.Sectional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SectionalRepository extends JpaRepository<Sectional, Integer> {
}
