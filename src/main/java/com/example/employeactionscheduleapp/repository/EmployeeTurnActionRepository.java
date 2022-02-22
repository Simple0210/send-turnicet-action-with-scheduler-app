package com.example.employeactionscheduleapp.repository;

import com.example.employeactionscheduleapp.entity.EmployeeTurnAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeTurnActionRepository extends JpaRepository<EmployeeTurnAction, Integer> {

    /*
    EMPLOYEE ID BO`YICHA YIG`IB BARCHA ACTIONLARNI BERADI
     */
    List<EmployeeTurnAction> findAllByEmployeeId(Integer employee_id);

    /*
    BOSHQA API GA SENDINGI FALSE BO`LGANLARNI JO`NATISH UCHUN YIG`IB BERADI
     */
    List<EmployeeTurnAction> findAllBySending(boolean sending);


}
