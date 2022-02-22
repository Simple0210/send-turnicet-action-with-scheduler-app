package com.example.employeactionscheduleapp.controller;

import com.example.employeactionscheduleapp.apiresponses.ApiResponse;
import com.example.employeactionscheduleapp.payload.EmployeeTurnActionReqDTO;
import com.example.employeactionscheduleapp.service.EmployeeTurnActionServiceImpl;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employeTurnAction")
@RequiredArgsConstructor
public class EmployeeTurnActionController {

    private final EmployeeTurnActionServiceImpl employeeTurnActionService;

    @Value(value = "${centeral.api.url}")
    private String baseUrl;

    @GetMapping("/get/all")
    @ApiOperation(value = "Barcha kirdi-chiqdilarni olish")
    public ApiResponse getAllEmployeeTurnAction() {
        return employeeTurnActionService.getAllEmployeeTurnAction();
    }

    @GetMapping("/get/{id}")
    @ApiOperation(value = "Kirdi-chiqdini olish id bo`yicha")
    public ApiResponse getEmployeeTurnActionById(@PathVariable Integer id) {
        return employeeTurnActionService.getEmployeeTurnActionById(id);
    }

    @PostMapping("/add")
    @ApiOperation(value = "Yangi kirdi-chiqdi qo`shish.")
    public ApiResponse addNewEmployeeTurnAction(@RequestBody EmployeeTurnActionReqDTO employeeTurnActionReqDTO) {
        return employeeTurnActionService.addNewEmployeeTurnAction(employeeTurnActionReqDTO);
    }

    @PutMapping("/edit/{id}")
    @ApiOperation(value = "Kirdi-chiqdini taxrirlash")
    public ApiResponse editEmployeeTurnAction(@PathVariable Integer id, @RequestBody EmployeeTurnActionReqDTO employeeTurnActionReqDTO) {
        return employeeTurnActionService.editEmployeeTurnAction(id, employeeTurnActionReqDTO);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "Kirdi-chiqdini o`chirish")
    public ApiResponse deleteEmployeeTurnAction(@PathVariable Integer id) {
        return employeeTurnActionService.deleteEmployeeTurnAction(id);
    }

    @GetMapping("/get/all/{employeeId}")
    @ApiOperation(value = "Bitta hodimning id isi bo`yicha shu hodimning kirdi-chiqdilarini olish")
    public ApiResponse getAllByEmployeeId(@PathVariable Integer employeeId) {
        return employeeTurnActionService.getAllByEmployeeId(employeeId);
    }

}
