package com.example.employeactionscheduleapp.service;

import com.example.employeactionscheduleapp.apiresponses.ApiResponse;
import com.example.employeactionscheduleapp.entity.Employee;
import com.example.employeactionscheduleapp.payload.EmployeeReqDTO;
import com.example.employeactionscheduleapp.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public ApiResponse getAllEmployee() {
        List<Employee> employees = employeeRepository.findAll();
        return new ApiResponse(employees, true);
    }

    @Override
    public ApiResponse getEmployeeById(Integer id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        return optionalEmployee.map(employee -> new ApiResponse(employee, true)).orElseGet(() -> new ApiResponse("Bunday hodim topilmadi", false));
    }

    @Override
    public ApiResponse addNewEmployee(EmployeeReqDTO employeeReqDTO) {
        boolean hasEmployee = employeeRepository.existsEmployeeByFirstnameAndLastnameAndPhoneNumber(employeeReqDTO.getFirstname(), employeeReqDTO.getLastname(), employeeReqDTO.getPhoneNumber());
        if (hasEmployee) {
            return new ApiResponse("Bunday hodim tizimda mavjud", false);
        }
        Employee employee = parseEmployeeFromDTO(employeeReqDTO, null);
        employeeRepository.save(employee);
        return new ApiResponse("Hodim ma`lumotlari muvaffaqiyatli saqlandi", true);
    }

    @Override
    public ApiResponse editEmployee(Integer id, EmployeeReqDTO employeeReqDTO) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()) {
            Employee employee = parseEmployeeFromDTO(employeeReqDTO, optionalEmployee.get());
            boolean hasEmployee = employeeRepository.existsEmployeeByFirstnameAndLastnameAndPhoneNumber(employee.getFirstname(), employee.getLastname(), employee.getPhoneNumber());
            if (hasEmployee) {
                return new ApiResponse("Bunday hodim tizimda mavjud", false);
            } else {
                employeeRepository.save(employee);
                return new ApiResponse("Hodimning ma`lumotlari muvaffaqiyatli taxrirlandi", true);
            }
        }
        return new ApiResponse("Bunday hodim topilmadi", false);
    }

    @Override
    public ApiResponse deleteEmployee(Integer id) {
        try {
            employeeRepository.deleteById(id);
            return new ApiResponse("Hodim ma`lumotlari o`chirildi", true);
        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResponse("Hodim ma`lumotlarini o`chirishda xatolik yuz berdi", false);
        }
    }

    /*
    EMPLOYEEREQDTO DAN EMPLOYEE YASAB QAYTARUVCHI METOD, AGAR DTO BILAN BIRGA EMPLOYEE BERILSA EDIT QILAYOTGAN BO`LADI
     */
    @Override
    public Employee parseEmployeeFromDTO(EmployeeReqDTO employeeReqDTO, Employee employee) {
        if (employee == null) {
            return new Employee(
                    employeeReqDTO.getFirstname(),
                    employeeReqDTO.getLastname(),
                    employeeReqDTO.getPhoneNumber()
            );
        } else {
            employee.setFirstname(employeeReqDTO.getFirstname());
            employee.setLastname(employeeReqDTO.getLastname());
            employee.setPhoneNumber(employeeReqDTO.getPhoneNumber());
            return employee;
        }
    }
}
