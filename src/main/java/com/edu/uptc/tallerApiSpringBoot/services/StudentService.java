package com.edu.uptc.tallerApiSpringBoot.services;

import com.edu.uptc.tallerApiSpringBoot.entities.Sectional;
import com.edu.uptc.tallerApiSpringBoot.entities.Student;
import com.edu.uptc.tallerApiSpringBoot.entities.Subject;
import com.edu.uptc.tallerApiSpringBoot.repositories.StudentRepository;
import com.edu.uptc.tallerApiSpringBoot.repositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    SubjectService subjectService;

    public List<Student> findAll(){
        return studentRepository.findAll();
    }

    public Student findById(Integer id){
        Optional<Student> optional = studentRepository.findById(id);
        return optional.isPresent() ? optional.get():null;
    }

    public Student save(Student student, Sectional sectional){
        student.setSectional(sectional);
        student.setSubjects(new ArrayList<>());
        return studentRepository.save(student);
    }

    public Student addSubject(Integer idStudent, Integer idSubject){
        Subject subject = subjectService.findById(idSubject);
        Student existStudent = studentRepository.findById(idStudent)
                .orElseThrow(() -> new RuntimeException("Student not exist"));
        List<Student> auxSubject = subject.getStudents();
        List<Subject> auxStudent = existStudent.getSubjects();
        auxStudent.add(subject);
        existStudent.setSubjects(auxStudent);
        auxSubject.add(existStudent);
        subject.setStudents(auxSubject);
        subjectService.update(subject);
        return studentRepository.save(existStudent);
    }

    public Student deleteSubject(Integer idStudent, Integer idSubject){
        Subject subject = subjectService.findById(idSubject);
        Student existStudent = studentRepository.findById(idStudent)
                .orElseThrow(() -> new RuntimeException("Student not exist"));
        List<Student> auxSubject = subject.getStudents();
        List<Subject> auxStudent = existStudent.getSubjects();
        auxStudent.removeIf(subject1 -> subject1.getId().equals(idSubject));
        existStudent.setSubjects(auxStudent);
        auxSubject.removeIf(student1 -> student1.getId().equals(idStudent));
        subject.setStudents(auxSubject);
        subjectService.update(subject);
        return studentRepository.save(existStudent);
    }

    public Student update(Student student){
        Student existingStudent = studentRepository.findById(student.getId())
                .orElseThrow(() -> new RuntimeException("Student not exist"));
        existingStudent.setBirthday(student.getBirthday());
        existingStudent.setFistName(student.getFistName());
        existingStudent.setLastName(student.getLastName());
        existingStudent.setSectional(student.getSectional());

        return studentRepository.save(existingStudent);
    }

    public Subject findSubjectOfStudent(Integer idStudent, Integer idSubject) {
        Student existStudent = studentRepository.findById(idStudent)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

        List<Subject> studentSubjects = existStudent.getSubjects();

        Optional<Subject> foundSubject = studentSubjects.stream()
                .filter(subject -> subject.getId().equals(idSubject))
                .findFirst();

        return foundSubject.isPresent() ? foundSubject.get():null;
    }


    public void delete(Student student){
        studentRepository.deleteById(student.getId());
    }

}
