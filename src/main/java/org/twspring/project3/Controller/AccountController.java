package org.twspring.project3.Controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.twspring.project3.Model.Account;
import org.twspring.project3.Model.User;
import org.twspring.project3.Service.AccountService;

@RestController
@RequestMapping("/api/v1/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @GetMapping("/get-all") //ADMIN
    public ResponseEntity getAllAccounts() {
        return ResponseEntity.status(200).body(accountService.getAccounts());
    }

    @GetMapping("/get-account-details/{accountId}")
    public ResponseEntity getAccountDetails(@AuthenticationPrincipal User customer,
                                            @PathVariable Integer accountId) {
        return ResponseEntity.status(200).body(accountService.viewAccountDetails(customer.getId(), accountId));
    }

    @GetMapping("/get-my-accounts")
    public ResponseEntity getMyAccounts(@AuthenticationPrincipal User customer) {
        return ResponseEntity.status(200).body(accountService.getCustomerAccounts(customer.getId()));
    }

    @PostMapping("/add-new-account")
    public ResponseEntity addNewAccount(@AuthenticationPrincipal User customer,
                                        @RequestBody@Valid Account account) {
        accountService.addAccountToCustomer(customer.getId(), account);
        return ResponseEntity.status(200).body("Account created successfully");
    }

    @PutMapping("activate-account/{accountId}")//EMPLOYEE/ADMIN
    public ResponseEntity activateAccount(@AuthenticationPrincipal User employee,
                                          @PathVariable Integer accountId){
        accountService.activateAccount(accountId);
        return ResponseEntity.status(200).body("Account activated successfully");
    }

    @PutMapping("/deposit-money/{accountId}/amount/{amount}")
    public ResponseEntity depositMoney(@AuthenticationPrincipal User customer,
                                       @PathVariable Integer accountId,
                                       @PathVariable double amount){
        accountService.depositMoney( customer.getId(), accountId, amount);
        return ResponseEntity.status(200).body("Amount deposited successfully");
    }

    @PutMapping("/withdraw-money/{accountId}/amount/{amount}")
    public ResponseEntity withdrawMoney(@AuthenticationPrincipal User customer,
                                        @PathVariable Integer accountId,
                                        @PathVariable double amount){
        accountService.withdrawMoney( customer.getId(), accountId, amount);
        return ResponseEntity.status(200).body("Amount withdrawn successfully");
    }

    @PutMapping("/transfer-money-from/{accountId}/to/{receivingAccountId}/amount/{amount}")
    public ResponseEntity transferMoney(@AuthenticationPrincipal User customer,
                                        @PathVariable Integer accountId,
                                        @PathVariable Integer receivingAccountId,
                                        @PathVariable double amount){
        accountService.transferMoney( customer.getId(), accountId, receivingAccountId, amount);
        return ResponseEntity.status(200).body("Amount transferred successfully");
    }

    @PutMapping("/block/{accountId}") //EMPLOYEE/ADMIN
    public ResponseEntity blockAccount(@AuthenticationPrincipal User employee,
                                       @PathVariable Integer accountId){
        accountService.blockAccount(accountId);
        return ResponseEntity.status(200).body("Account blocked successfully");
    }

    @DeleteMapping("/delete-my-account/{accountId}")
    public ResponseEntity deleteAccount(@AuthenticationPrincipal User customer,
                                        @PathVariable Integer accountId){
        accountService.deleteAccount(customer.getId(), accountId);
        return ResponseEntity.status(200).body("Account deleted successfully");
    }

}
