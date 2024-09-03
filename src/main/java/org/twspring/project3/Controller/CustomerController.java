package org.twspring.project3.Controller;



import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.twspring.project3.Api.ApiResponse;
import org.twspring.project3.DTO.CustomerDTO;
import org.twspring.project3.Model.Customer;
import org.twspring.project3.Model.User;
import org.twspring.project3.Service.CustomerService;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping("/get-all") //ADMIN
    public ResponseEntity getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @PutMapping("/update-my-info")
    public ResponseEntity updateMyInfo(@AuthenticationPrincipal User customerAuthInfo,
                                       @RequestBody@Valid CustomerDTO customerInfo) {
        customerService.updateCustomer(customerAuthInfo.getId(),customerInfo);
        return ResponseEntity.status(200).body(new ApiResponse("Customer updated successfully"));
    }
    @DeleteMapping("/delete-my-account")
    public ResponseEntity deleteMyAccount(@AuthenticationPrincipal User customerAuthInfo) {
        customerService.deleteCustomer(customerAuthInfo.getId());
        return ResponseEntity.status(200).body(new ApiResponse("Customer deleted successfully"));
    }
}
