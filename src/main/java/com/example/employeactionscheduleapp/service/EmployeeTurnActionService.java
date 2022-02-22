package com.example.employeactionscheduleapp.service;

import com.example.employeactionscheduleapp.apiresponses.ApiResponse;
import com.example.employeactionscheduleapp.payload.EmployeeTurnActionReqDTO;

public interface EmployeeTurnActionService {

    ApiResponse getAllEmployeeTurnAction();

    ApiResponse getEmployeeTurnActionById(Integer id);

    ApiResponse addNewEmployeeTurnAction(EmployeeTurnActionReqDTO employeeTurnActionReqDTO);

    ApiResponse editEmployeeTurnAction(Integer id, EmployeeTurnActionReqDTO employeeTurnActionReqDTO);

    ApiResponse deleteEmployeeTurnAction(Integer id);

    ApiResponse getAllByEmployeeId(Integer employeId);


}
