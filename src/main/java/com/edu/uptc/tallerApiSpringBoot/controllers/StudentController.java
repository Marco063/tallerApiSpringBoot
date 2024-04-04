package com.edu.uptc.tallerApiSpringBoot.controllers;

import com.edu.uptc.tallerApiSpringBoot.entities.Sectional;
import com.edu.uptc.tallerApiSpringBoot.entities.Student;
import com.edu.uptc.tallerApiSpringBoot.entities.Subject;
import com.edu.uptc.tallerApiSpringBoot.responses.ResponseHandler;
import com.edu.uptc.tallerApiSpringBoot.services.SectionalService;
import com.edu.uptc.tallerApiSpringBoot.services.StudentService;
import com.edu.uptc.tallerApiSpringBoot.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    SectionalService sectionalService;
    @Autowired
    SubjectService subjectService;

    @GetMapping
    public ResponseEntity<Object> findAll(){
        try{
            List<Student> result = studentService.findAll();
            return ResponseHandler.generateResponse("Success", HttpStatus.OK, result);
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null );
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable Integer id){
        try{
            Student result = studentService.findById(id);
            return ResponseHandler.generateResponse("Success", HttpStatus.OK, result);
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null );
        }
    }

    @PostMapping("{idSectional}")
    public ResponseEntity<Object> save(@RequestBody Student student, @PathVariable Integer idSectional){
        try{
            Sectional sectional = sectionalService.findById(idSectional);
            if(sectional != null) {
                Student result = studentService.save(student, sectional);
                return ResponseHandler.generateResponse("Success", HttpStatus.OK, result);
            }else{
                return ResponseHandler.generateResponse("Sectional not found", HttpStatus.NOT_FOUND, null);
            }
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null );
        }
    }

    @PutMapping("/addSubject/{idStudent}/{idSubject}")
    public ResponseEntity<Object> addSubject(@PathVariable Integer idStudent, @PathVariable Integer idSubject){
        try{
            Student student = studentService.findById(idStudent);
            Subject subject = subjectService.findById(idSubject);
            if(student != null && subject != null){
                Student result = studentService.addSubject(idStudent,idSubject);
                return ResponseHandler.generateResponse("Success", HttpStatus.OK, result);
            }else{
                return ResponseHandler.generateResponse("Not found elements", HttpStatus.NOT_FOUND, null );
            }
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null );
        }
    }
    @PutMapping("/removeSubject/{id_Student}/{id_Subject}")
    public ResponseEntity<Object> removeSubject(@PathVariable Integer id_Student, @PathVariable Integer id_Subject){
        try{
            Student student = studentService.findById(id_Student);
            Subject subject = subjectService.findById(id_Subject);
            if(student != null && subject != null){
                if(studentService.findSubjectOfStudent(id_Student,id_Subject) != null) {
                    Student result = studentService.deleteSubject(id_Student, id_Subject);
                    return ResponseHandler.generateResponse("Success", HttpStatus.OK, result);
                }else{
                    return ResponseHandler.generateResponse("Subject not found for the student", HttpStatus.NOT_FOUND, null);
                }
            }else{
                return ResponseHandler.generateResponse("Not found elements", HttpStatus.NOT_FOUND, null );
            }
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null );
        }
    }

    @PutMapping("/update/{idSectional}")
    public ResponseEntity<Object> update(@RequestBody Student student, @PathVariable Integer idSectional){
        try{
            Sectional sectional = sectionalService.findById(idSectional);
            if(sectional != null) {
                student.setSectional(sectional);
                Student result = studentService.update(student);
                return ResponseHandler.generateResponse("Success", HttpStatus.OK, result);
            }else{
                return ResponseHandler.generateResponse("Sectional not found", HttpStatus.NOT_FOUND, null);
            }
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null );
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id){
        try{
            Student student = studentService.findById(id);
            if(student != null){
                for (Subject subject : student.getSubjects()) {
                    if(subject !=null) {
                        studentService.deleteSubject(id, subject.getId());
                    }
                }
                studentService.delete(student);
                return ResponseHandler.generateResponse("Success", HttpStatus.OK, student);
            }else{
                return ResponseHandler.generateResponse("Student not found", HttpStatus.NOT_FOUND, null);
            }
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null );
        }
    }


}
