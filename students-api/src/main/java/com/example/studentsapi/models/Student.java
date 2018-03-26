package com.example.studentsapi.models;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor @NoArgsConstructor @Getter @Setter
@Entity @Table(name = "STUDENTS")

public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "FIRST_NAME")
    private String first_name;

    @Column(name = "LAST_NAME")
    private String last_name;

    @Column(name = "CLASSES")
    private String classes;

    public Student(String first_name, String last_name, String classes) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.classes = classes;
    }
}
