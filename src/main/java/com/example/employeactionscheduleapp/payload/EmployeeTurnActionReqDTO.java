package com.example.employeactionscheduleapp.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmployeeTurnActionReqDTO {
    private Boolean actionIncomeYokiOutCome;
    private Integer employeeId;
}
