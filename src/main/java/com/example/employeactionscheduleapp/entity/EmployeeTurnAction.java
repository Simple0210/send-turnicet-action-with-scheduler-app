package com.example.employeactionscheduleapp.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class EmployeeTurnAction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private Boolean actionIncomeYokiOutCome;
    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdAt;
    @ManyToOne
    private Employee employee;
    @Column
    private boolean sending=false;
    @Column
    private Integer idFromApi;
    @Column
    private String messageFromApi;
    @Column
    private Boolean successFromApi=null;


    public EmployeeTurnAction(Boolean actionIncomeYokiOutCome, Employee employee) {
        this.actionIncomeYokiOutCome = actionIncomeYokiOutCome;
        this.employee = employee;
    }

}
