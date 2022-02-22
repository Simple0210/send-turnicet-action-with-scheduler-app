package com.example.employeactionscheduleapp.repository;

import com.example.employeactionscheduleapp.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

    boolean existsEmployeeByFirstnameAndLastnameAndPhoneNumber(String firstname, String lastname, String phoneNumber);

}
