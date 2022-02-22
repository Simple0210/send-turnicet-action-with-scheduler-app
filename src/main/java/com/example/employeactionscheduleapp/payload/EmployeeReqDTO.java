package com.example.employeactionscheduleapp.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmployeeReqDTO {

    private String firstname;
    private String lastname;
    private String phoneNumber;
}
