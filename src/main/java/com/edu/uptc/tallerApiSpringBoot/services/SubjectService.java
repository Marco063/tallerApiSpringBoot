package com.edu.uptc.tallerApiSpringBoot.services;

import com.edu.uptc.tallerApiSpringBoot.entities.Subject;
import com.edu.uptc.tallerApiSpringBoot.repositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {
    @Autowired
    SubjectRepository subjectRepository;

    public List<Subject> findAll(){
        return subjectRepository.findAll();
    }

    public Subject findById(Integer id){
        Optional<Subject> optional = subjectRepository.findById(id);
        return optional.isPresent() ? optional.get():null;
    }

    public Subject save (Subject subject){
        return subjectRepository.save(subject);
    }

    public Subject update (Subject subject){
        Subject existingSubject = subjectRepository.findById(subject.getId())
                .orElseThrow(() -> new RuntimeException("Subject not found"));

        existingSubject.setCredits(subject.getCredits());
        existingSubject.setDescription(subject.getDescription());
        existingSubject.setName(subject.getName());

        return subjectRepository.save(existingSubject);
    }

    public void delete (Subject subject){
        subjectRepository.delete(subject);
    }
}
