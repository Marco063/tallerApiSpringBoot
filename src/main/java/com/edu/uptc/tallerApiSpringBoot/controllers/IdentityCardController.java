package com.edu.uptc.tallerApiSpringBoot.controllers;

import com.edu.uptc.tallerApiSpringBoot.entities.IdentityCard;
import com.edu.uptc.tallerApiSpringBoot.entities.Student;
import com.edu.uptc.tallerApiSpringBoot.responses.ResponseHandler;
import com.edu.uptc.tallerApiSpringBoot.services.IdentityCardService;
import com.edu.uptc.tallerApiSpringBoot.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/card")
public class IdentityCardController {
    @Autowired
    IdentityCardService identityCardService;
    @Autowired
    StudentService studentService;

    @GetMapping
    public ResponseEntity<Object> findAll(){
        try{
            List<IdentityCard> result = identityCardService.findAll();
            return ResponseHandler.generateResponse("Success", HttpStatus.OK, result);
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null );
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable Integer id){
        try{
            IdentityCard result = identityCardService.findById(id);
            return ResponseHandler.generateResponse("Success", HttpStatus.OK, result);
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null );
        }
    }

    @PostMapping("/{idStudent}")
    public ResponseEntity<Object> save(@RequestBody IdentityCard identityCard, @PathVariable Integer idStudent){
        try{
            Student student = studentService.findById(idStudent);
            if(student != null){
                IdentityCard result = identityCardService.save(identityCard, student);
                return ResponseHandler.generateResponse("Success", HttpStatus.OK, result);
            }else{
                return ResponseHandler.generateResponse("Student not found", HttpStatus.NOT_FOUND, null );
            }
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null );
        }
    }

    @PutMapping
    public ResponseEntity<Object> update(@RequestBody IdentityCard identityCard){
        try{
            IdentityCard result = identityCardService.update(identityCard);
            return ResponseHandler.generateResponse("Success", HttpStatus.OK, result);
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null );
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id){
        try{
            IdentityCard identityCard = identityCardService.findById(id);
            if(identityCard != null) {
                identityCardService.delete(identityCard);
                return ResponseHandler.generateResponse("Success", HttpStatus.OK, identityCard);
            }else{
                return ResponseHandler.generateResponse("Identity card not found", HttpStatus.NOT_FOUND, null);
            }
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null );
        }
    }
}
