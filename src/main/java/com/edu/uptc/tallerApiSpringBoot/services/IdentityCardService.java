package com.edu.uptc.tallerApiSpringBoot.services;

import com.edu.uptc.tallerApiSpringBoot.entities.IdentityCard;
import com.edu.uptc.tallerApiSpringBoot.entities.Student;
import com.edu.uptc.tallerApiSpringBoot.repositories.IdentityCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IdentityCardService {
    @Autowired
    private IdentityCardRepository identityCardRepository;

    public List<IdentityCard> findAll(){
        return identityCardRepository.findAll();
    }

    public IdentityCard findById(Integer id){
        Optional<IdentityCard> optional = identityCardRepository.findById(id);
        return optional.isPresent() ? optional.get():null;
    }

    public IdentityCard save (IdentityCard identityCard, Student student){
        identityCard.setStudent(student);
        return identityCardRepository.save(identityCard);
    }

    public void delete(IdentityCard identityCard){
        Student student = identityCard.getStudent();
        if(student.getIdentityCard() != null){
            student.setIdentityCard(null);
        }
        identityCardRepository.delete(identityCard);
    }

    public IdentityCard update (IdentityCard identityCard){
        IdentityCard existingIdentityCard = identityCardRepository.findById(identityCard.getId())
                .orElseThrow(() -> new RuntimeException("Identity card not found"));
        existingIdentityCard.setCareer(identityCard.getCareer());
        existingIdentityCard.setCode(identityCard.getCode());

        return identityCardRepository.save(existingIdentityCard);
    }
}
