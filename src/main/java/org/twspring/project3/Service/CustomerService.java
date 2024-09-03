package org.twspring.project3.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.twspring.project3.DTO.CustomerDTO;
import org.twspring.project3.Model.Customer;
import org.twspring.project3.Model.User;
import org.twspring.project3.Repository.AuthRepository;
import org.twspring.project3.Repository.CustomerRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final AuthRepository authRepository;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public void registerAsCustomer(CustomerDTO customerDTO) {
        String hash= new BCryptPasswordEncoder().encode(customerDTO.getPassword());//Hash password

        User user = new User();
        user.setUsername(customerDTO.getUsername());
        user.setPassword(hash);
        user.setRole("CUSTOMER");
        user.setEmail(customerDTO.getEmail());
        user.setName(customerDTO.getName());
        authRepository.save(user);

        Customer customer = new Customer();
        customer.setPhoneNumber(customerDTO.getPhoneNumber());
        customer.setUser(user);
        customerRepository.save(customer);
    }

    //add view my profile?

    public void updateCustomer(Integer customerId,CustomerDTO customerDTO) {
       Customer oldCustomer = customerRepository.findCustomerById(customerId);
       User oldUser = oldCustomer.getUser();

        String hash= new BCryptPasswordEncoder().encode(customerDTO.getPassword());//Hash password


        //oldUser.setUsername(customerDTO.getUsername()); More logical to NOT allow the change of usernames
       oldUser.setEmail(customerDTO.getEmail());
       oldUser.setPassword(hash);
       oldUser.setName(customerDTO.getName());
       oldCustomer.setPhoneNumber(customerDTO.getPhoneNumber());
       customerRepository.save(oldCustomer);
       authRepository.save(oldUser);
    }

    public void deleteCustomer(Integer customerId) { //could be the customer's id or an admin's
        authRepository.deleteById(customerId);
    }
}
