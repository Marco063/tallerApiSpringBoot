package com.edu.uptc.tallerApiSpringBoot.repositories;

import com.edu.uptc.tallerApiSpringBoot.entities.IdentityCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdentityCardRepository extends JpaRepository<IdentityCard, Integer> {
}
