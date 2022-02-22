package com.example.employeactionscheduleapp.service;

import com.example.employeactionscheduleapp.apiresponses.ApiResponse;
import com.example.employeactionscheduleapp.entity.Employee;
import com.example.employeactionscheduleapp.entity.EmployeeTurnAction;
import com.example.employeactionscheduleapp.payload.EmployeeTurnActionReqDTO;
import com.example.employeactionscheduleapp.payload.resDto.EmployeeTurnActionResDTo;
import com.example.employeactionscheduleapp.repository.EmployeeRepository;
import com.example.employeactionscheduleapp.repository.EmployeeTurnActionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeTurnActionServiceImpl implements EmployeeTurnActionService {

    private final EmployeeTurnActionRepository employeeTurnActionRepository;
    private final EmployeeRepository employeeRepository;

    @Value(value = "${centeral.api.url}")
    private String baseUrl;

    @Override
    public ApiResponse getAllEmployeeTurnAction() {
        List<EmployeeTurnAction> employeeTurnActions = employeeTurnActionRepository.findAll();
        return new ApiResponse(employeeTurnActions, true);
    }

    @Override
    public ApiResponse getEmployeeTurnActionById(Integer id) {
        Optional<EmployeeTurnAction> optionalEmployeeTurnAction = employeeTurnActionRepository.findById(id);
        return optionalEmployeeTurnAction.map(employeeTurnAction -> new ApiResponse(employeeTurnAction, true))
                .orElseGet(() -> new ApiResponse("Bunday Employee Turniket Action topilmadi", false));
    }

    @Override
    public ApiResponse addNewEmployeeTurnAction(EmployeeTurnActionReqDTO employeeTurnActionReqDTO) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeTurnActionReqDTO.getEmployeeId());
        if (optionalEmployee.isPresent()) {
            EmployeeTurnAction employeeTurnAction = new EmployeeTurnAction(
                    employeeTurnActionReqDTO.getActionIncomeYokiOutCome(),
                    optionalEmployee.get()
            );
            employeeTurnActionRepository.save(employeeTurnAction);
            return new ApiResponse("Employee Turniket Action muvaffaqiyatli saqlandi", true);
        }
        return new ApiResponse("Employee Turniket Actionni saqlash uchun Hodim topilmadi", false);
    }

    @Override
    public ApiResponse editEmployeeTurnAction(Integer id, EmployeeTurnActionReqDTO employeeTurnActionReqDTO) {
        Optional<EmployeeTurnAction> actionOptional = employeeTurnActionRepository.findById(id);
        if (actionOptional.isPresent()) {
            Optional<Employee> optionalEmployee = employeeRepository.findById(employeeTurnActionReqDTO.getEmployeeId());
            if (optionalEmployee.isPresent()) {
                EmployeeTurnAction employeeTurnAction = actionOptional.get();
                employeeTurnAction.setActionIncomeYokiOutCome(employeeTurnActionReqDTO.getActionIncomeYokiOutCome());
                employeeTurnAction.setEmployee(optionalEmployee.get());
                employeeTurnActionRepository.save(employeeTurnAction);
                return new ApiResponse("Employee Turniket Action muvaffaqiyatli taxrirlandi", true);
            } else {
                return new ApiResponse("Employe Turniket Actionni taxrirlash uchun Hodim topilmadi", false);
            }
        } else {
            return new ApiResponse("Bunday Employee Turniket Action topilmadi", false);
        }
    }

    @Override
    public ApiResponse deleteEmployeeTurnAction(Integer id) {
        try {
            employeeTurnActionRepository.deleteById(id);
            return new ApiResponse("Employee Turniket Action ma`lumotlari muvaffaqiyatli o`chirildi", true);
        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResponse("Employee Turniket Actionni o`chirishda xatolik yuz berdi", false);
        }
    }

    @Override
    public ApiResponse getAllByEmployeeId(Integer employeeId) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
        if (optionalEmployee.isPresent()) {
            List<EmployeeTurnAction> allByEmployeeId = employeeTurnActionRepository.findAllByEmployeeId(employeeId);
            return new ApiResponse(allByEmployeeId, true);
        }
        return new ApiResponse("Tizimda bunday hodim mavjud emas", false);
    }

    @Scheduled(cron = "0 * * * * *")
    public void schedulerTask() {

        List<EmployeeTurnAction> turnActions = employeeTurnActionRepository.findAllBySending(false);
        for (EmployeeTurnAction turnAction : turnActions) {

            EmployeeTurnActionResDTo turnActionResDTo = parseFromEmpTurnActionToResDTO(turnAction);

            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            HttpEntity<EmployeeTurnActionResDTo> entity = new HttpEntity<>(turnActionResDTo, headers);
            ApiResponse apiResponse = restTemplate.exchange(baseUrl, HttpMethod.POST, entity, ApiResponse.class).getBody();
            System.out.println(apiResponse);

            turnAction.setSending(true);
            turnAction.setIdFromApi(Integer.parseInt(apiResponse.getObject().toString()));
            turnAction.setMessageFromApi(apiResponse.getMessage());
            turnAction.setSuccessFromApi(apiResponse.isSuccess());
            employeeTurnActionRepository.save(turnAction);
        }


//        List<EmployeeTurnActionResDTo> actionResDTos = employeeTurnActionRepository.findAllBySending(false)
//                .stream().map(this::parseFromEmpTurnActionToResDTO).collect(Collectors.toList());
//
//        if (!actionResDTos.isEmpty()) {
//            RestTemplate restTemplate = new RestTemplate();
//            HttpHeaders headers = new HttpHeaders();
//            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//            HttpEntity<List<EmployeeTurnActionResDTo>> entity = new HttpEntity<>(actionResDTos, headers);
//            ApiResponse body = restTemplate.exchange(baseUrl, HttpMethod.POST, entity, ApiResponse.class).getBody();
//            System.out.println(body);
//        }
    }

    /*
    BOSHQA API GA REQUEST JO`NATISH UCHUN EMPTURNACTIONNI RESDTOGA O`GIRIB QAYTARUVCHI METOD
     */
    public EmployeeTurnActionResDTo parseFromEmpTurnActionToResDTO(EmployeeTurnAction employeeTurnAction) {
//        Optional<EmployeeTurnAction> optionalEmployeeTurnAction = employeeTurnActionRepository.findById(employeeTurnAction.getId());
//        if (optionalEmployeeTurnAction.isPresent()) {
//            EmployeeTurnAction employeeTurnAction1 = optionalEmployeeTurnAction.get();
//            employeeTurnAction1.setSending(true);
//            employeeTurnActionRepository.save(employeeTurnAction1);
//        }
        return new EmployeeTurnActionResDTo(
                employeeTurnAction.getActionIncomeYokiOutCome(),
                employeeTurnAction.getCreatedAt(),
                employeeTurnAction.getEmployee()
        );
    }
}
