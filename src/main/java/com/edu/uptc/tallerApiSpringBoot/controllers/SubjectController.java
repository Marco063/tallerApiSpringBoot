package com.edu.uptc.tallerApiSpringBoot.controllers;

import com.edu.uptc.tallerApiSpringBoot.entities.Student;
import com.edu.uptc.tallerApiSpringBoot.entities.Subject;
import com.edu.uptc.tallerApiSpringBoot.responses.ResponseHandler;
import com.edu.uptc.tallerApiSpringBoot.services.StudentService;
import com.edu.uptc.tallerApiSpringBoot.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subject")
public class SubjectController {
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private StudentService studentService;

    @GetMapping
    public ResponseEntity<Object> findAll(){
        try{
            List<Subject> result = subjectService.findAll();
            return ResponseHandler.generateResponse("Success", HttpStatus.OK, result);
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null );
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable Integer id){
        try{
            Subject result = subjectService.findById(id);
            return ResponseHandler.generateResponse("Success", HttpStatus.OK, result);
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null );
        }
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody Subject subject){
        try{
            Subject result = subjectService.save(subject);
            return  ResponseHandler.generateResponse("Success", HttpStatus.OK, result);
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null );
        }
    }

    @PutMapping
    public ResponseEntity<Object> update(@RequestBody Subject subject){
        try{
            Subject result = subjectService.update(subject);
            return ResponseHandler.generateResponse("Success", HttpStatus.OK, result);
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null );
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id){
        try{
            Subject subject = subjectService.findById(id);
            if(subject != null){
                for(Student student : subject.getStudents()) {
                    student.getSubjects().remove(subject);
                }
                subjectService.delete(subject);
                return ResponseHandler.generateResponse("Success", HttpStatus.OK, subject);
            } else {
                return ResponseHandler.generateResponse("Subject not exist", HttpStatus.NOT_FOUND, null);
            }
        } catch (Exception e){
                return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

}
