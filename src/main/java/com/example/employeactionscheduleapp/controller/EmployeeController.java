package com.example.employeactionscheduleapp.controller;

import com.example.employeactionscheduleapp.apiresponses.ApiResponse;
import com.example.employeactionscheduleapp.payload.EmployeeReqDTO;
import com.example.employeactionscheduleapp.service.EmployeeServiceImpl;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeServiceImpl employeeService;

    @GetMapping("/get/all")
    @ApiOperation(value = "Barcha hodimlarni olish")
    public ApiResponse getAllEmployee() {
        return employeeService.getAllEmployee();
    }

    @GetMapping("/get/{id}")
    @ApiOperation(value = "Hodimni olish id bo`yicha")
    public ApiResponse getEmployeeById(@PathVariable Integer id) {
        return employeeService.getEmployeeById(id);
    }

    @PostMapping("/add")
    @ApiOperation(value = "Yangi hodim qo`shish")
    public ApiResponse addNewEmployee(@RequestBody EmployeeReqDTO employeeReqDTO) {
        return employeeService.addNewEmployee(employeeReqDTO);
    }

    @PutMapping("/edit/{id}")
    @ApiOperation(value = "Hodim ma`lumotlarini taxrirlash")
    public ApiResponse editEmployee(@PathVariable Integer id, @RequestBody EmployeeReqDTO employeeReqDTO) {
        return employeeService.editEmployee(id, employeeReqDTO);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "Hodim ma`lumotlarini o`chirish")
    public ApiResponse deleteEmployee(@PathVariable Integer id) {
        return employeeService.deleteEmployee(id);
    }
}
