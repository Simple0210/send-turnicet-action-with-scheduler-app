package com.example.employeactionscheduleapp.payload.resDto;

import com.example.employeactionscheduleapp.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmployeeTurnActionResDTo {

    private Boolean actionIncomeYokiOutCome;

    private Timestamp createdAt;

    private Employee employee;
}
