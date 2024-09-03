package org.twspring.project3.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.twspring.project3.DTO.CustomerDTO;
import org.twspring.project3.DTO.EmployeeDTO;
import org.twspring.project3.Model.Customer;
import org.twspring.project3.Model.Employee;
import org.twspring.project3.Model.User;
import org.twspring.project3.Repository.AuthRepository;
import org.twspring.project3.Repository.CustomerRepository;
import org.twspring.project3.Repository.EmployeeRepository;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthRepository authRepository;
    private final EmployeeRepository employeeRepository;
    private final CustomerRepository customerRepository;

    public void registerAsEmployee(EmployeeDTO employeeDTO) {

        String hash= new BCryptPasswordEncoder().encode(employeeDTO.getPassword());//Hash password

        User user = new User();
        user.setUsername(employeeDTO.getUsername());
        user.setPassword(hash);
        user.setRole("EMPLOYEE");
        user.setEmail(employeeDTO.getEmail());
        user.setName(employeeDTO.getName());
        authRepository.save(user);

        Employee employee = new Employee();
        employee.setPosition(employeeDTO.getPosition());
        employee.setSalary(employeeDTO.getSalary());
        employee.setUser(user);
        employeeRepository.save(employee);
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

    public void registerAdmin(User user) {
        String hash= new BCryptPasswordEncoder().encode(user.getPassword());
        user.setRole("ADMIN");
        user.setPassword(hash);
        authRepository.save(user);
    }

    //logout/login
}
