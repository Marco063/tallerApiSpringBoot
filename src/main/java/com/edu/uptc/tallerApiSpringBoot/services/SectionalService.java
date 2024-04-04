package com.edu.uptc.tallerApiSpringBoot.services;

import com.edu.uptc.tallerApiSpringBoot.entities.Sectional;
import com.edu.uptc.tallerApiSpringBoot.repositories.SectionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SectionalService {
    @Autowired
    private SectionalRepository sectionalRepository;

    public List<Sectional> findAll(){
        return sectionalRepository.findAll();
    }
    public Sectional findById(Integer id){
        Optional<Sectional> optional = sectionalRepository.findById(id);
        return optional.isPresent() ? optional.get() : null;
    }

    public Sectional save (Sectional sectional){
        return sectionalRepository.save(sectional);
    }

    public Sectional update (Sectional sectional){
        Sectional existingSectional = sectionalRepository.findById(sectional.getId())
                .orElseThrow(() -> new RuntimeException("Sectional not found"));

        existingSectional.setAddress(sectional.getAddress());
        existingSectional.setName(sectional.getName());
        existingSectional.setSize(sectional.getSize());

        return sectionalRepository.save(existingSectional);
    }
    public void delete (Sectional sectional){
        sectionalRepository.delete(sectional);
    }
}
