package com.example.employeactionscheduleapp.service;

import com.example.employeactionscheduleapp.apiresponses.ApiResponse;
import com.example.employeactionscheduleapp.entity.Employee;
import com.example.employeactionscheduleapp.payload.EmployeeReqDTO;

public interface EmployeeService {

    ApiResponse getAllEmployee();

    ApiResponse getEmployeeById(Integer id);

    ApiResponse addNewEmployee(EmployeeReqDTO employeeReqDTO);

    ApiResponse editEmployee(Integer id, EmployeeReqDTO employeeReqDTO);

    ApiResponse deleteEmployee(Integer id);

    Employee parseEmployeeFromDTO(EmployeeReqDTO employeeReqDTO, Employee employee);

}
