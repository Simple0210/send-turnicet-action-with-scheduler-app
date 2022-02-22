package com.example.employeactionscheduleapp.entity;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String firstname;
    @Column
    private String lastname;
    @Column
    private String phoneNumber;

    public Employee(String firstname, String lastname, String phoneNumber) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.phoneNumber = phoneNumber;
    }
}
