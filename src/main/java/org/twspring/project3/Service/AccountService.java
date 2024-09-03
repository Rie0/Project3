package org.twspring.project3.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.twspring.project3.Api.ApiException;
import org.twspring.project3.Model.Account;
import org.twspring.project3.Model.Customer;
import org.twspring.project3.Repository.AccountRepository;
import org.twspring.project3.Repository.CustomerRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    public List<Account> getAccounts() { //Admin
        return accountRepository.findAll();
    }

    public Account viewAccountDetails(Integer customerId, Integer accountId) { //customer
        Customer customer = customerRepository.findCustomerById(customerId);
        Account account = accountRepository.findAccountById(accountId);
        if (account == null) {
            throw new ApiException("Account not found");
        }
        if (account.getId()!=account.getCustomer().getId()) {
            throw new ApiException("You don't own this account");
        }
        return account;
    }

    public List<Account> getCustomerAccounts(Integer customerId) {
        return accountRepository.findAccountByCustomerId(customerId);
    }

    public void addAccountToCustomer(Integer customerId, Account account) { //CUSTOMER //generate a random account number?
        account.setCustomer(customerRepository.findCustomerById(customerId));
        account.setBalance(0);
        account.setActive(false);
        accountRepository.save(account);
    }

    public void activateAccount(Integer accountId) { //ADMIN/EMP
        Account account = accountRepository.findAccountById(accountId);
        if (account == null) {
            throw new ApiException("Account not found");
        }
        if (account.isActive()){
            throw new ApiException("Account is already active");
        }
        account.setActive(true);
        accountRepository.save(account);
    }

    public void depositMoney(Integer customerId, Integer accountId, Double amount) {
        Account account = accountRepository.findAccountById(accountId);
        if (account == null) {
            throw new ApiException("Account not found");
        }
        if (customerId!=account.getCustomer().getId()) {
            throw new ApiException("You don't own this account");
        }
        if(!account.isActive()){
            throw new ApiException("Account is not activated yet or is blocked");
        }
        account.setBalance(account.getBalance()+amount); //hmmm...
        accountRepository.save(account);
    }
    public void withdrawMoney(Integer customerId, Integer accountId, Double amount) {
        Account account = accountRepository.findAccountById(accountId);
        if (account == null) {
            throw new ApiException("Account not found");
        }
        if (customerId!=account.getCustomer().getId()) {
            throw new ApiException("You don't own this account");
        }
        if(!account.isActive()){
            throw new ApiException("Account is not activated yet or is blocked");
        }
        if (account.getBalance()<amount){
            throw new ApiException("Insufficient funds");
        }
        account.setBalance(account.getBalance()-amount);
        accountRepository.save(account);
    }

    public void transferMoney(Integer customerId, Integer accountId, Integer receivingAccountId, Double amount) {
        Account account = accountRepository.findAccountById(accountId);
        Account receivingAccount = accountRepository.findAccountById(receivingAccountId);
        if (account == null) {
            throw new ApiException("Account not found");
        }
        if (customerId!=account.getCustomer().getId()) {
            throw new ApiException("You don't own this account");
        }
        if(!account.isActive()){
            throw new ApiException("Account is not activated yet or is blocked");
        }
        if (receivingAccount == null) {
            throw new ApiException("The account you are trying to transfer money to doesn't exist");
        }
        if(!receivingAccount.isActive()){
            throw new ApiException("The account you are trying to transfer money to is not activated yet or is blocked");
        }
        if (account.getBalance()<amount){
            throw new ApiException("Insufficient funds");
        }
        account.setBalance(account.getBalance()-amount);
        accountRepository.save(account);
        receivingAccount.setBalance(receivingAccount.getBalance()+amount);
        accountRepository.save(receivingAccount);
    }
    public void blockAccount(Integer accountId) { //ADMIN EMP
        Account account = accountRepository.findAccountById(accountId);
        if (account == null) {
            throw new ApiException("Account not found");
        }
        if(!account.isActive()){
            throw new ApiException("Account is not activated yet or is already blocked");
        }
        account.setActive(false);
        accountRepository.save(account);
    }

    public void deleteAccount(Integer customerId, Integer accountId) {
        Account account = accountRepository.findAccountById(accountId);
        if (account == null) {
            throw new ApiException("Account not found");
        }
        if (customerId!=account.getCustomer().getId()) {
            throw new ApiException("You don't own this account");
        }
        accountRepository.delete(account);
    }
}
