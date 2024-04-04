package com.edu.uptc.tallerApiSpringBoot.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "identity-card")
public class IdentityCard {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(nullable = false)
    private Integer code;
    @Column(nullable = false, length = 100)
    private String career;
    @OneToOne
    @JoinColumn(name = "student-id", unique = true)
    private Student student;

    public IdentityCard() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
