package org.twspring.project3.Controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.twspring.project3.Api.ApiResponse;
import org.twspring.project3.DTO.EmployeeDTO;
import org.twspring.project3.Model.User;
import org.twspring.project3.Service.EmployeeService;

@RestController
@RequestMapping("/api/v1/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping("/get-all") //ADMIN
    public ResponseEntity getAllEmployees() {
        return ResponseEntity.status(200).body(employeeService.getAllEmployees());
    }
    @PostMapping("/register-employee")
    public ResponseEntity registerEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {
        employeeService.registerAsEmployee(employeeDTO);
        return ResponseEntity.status(200).body("Employee registered successfully");
    }
    @PutMapping("/update-my-info")
    public ResponseEntity updateMyInfo(@AuthenticationPrincipal User employeeAuthInfo,
                                       @RequestBody@Valid EmployeeDTO employeeInfo) {
        employeeService.updateEmployee(employeeAuthInfo.getId(), employeeInfo);
        return ResponseEntity.status(200).body(new ApiResponse("Employee updated successfully"));
    }
    @DeleteMapping("/delete-my-account")
    public ResponseEntity deleteMyAccount(@AuthenticationPrincipal User employeeAuthInfo) {
        employeeService.deleteEmployee(employeeAuthInfo.getId());
        return ResponseEntity.status(200).body(new ApiResponse("Employee deleted successfully"));
    }
}
