package org.twspring.project3.Service;


import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.twspring.project3.DTO.EmployeeDTO;
import org.twspring.project3.Model.Employee;
import org.twspring.project3.Model.User;
import org.twspring.project3.Repository.AuthRepository;
import org.twspring.project3.Repository.EmployeeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final AuthRepository authRepository;
    private final EmployeeRepository employeeRepository;


    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
    //add view my profile?

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
    public void updateEmployee(Integer employeeId, EmployeeDTO employeeDTO) {
        Employee oldEmployee=employeeRepository.findEmployeeById(employeeId);
        User oldUser=oldEmployee.getUser();

        String hash= new BCryptPasswordEncoder().encode(employeeDTO.getPassword());//Hash password


        //oldUser.setUsername(employeeDTO.getUsername()); More logical to NOT allow the change of usernames
        oldUser.setPassword(hash);
        oldUser.setEmail(employeeDTO.getEmail());
        oldUser.setName(employeeDTO.getName());
        oldEmployee.setPosition(employeeDTO.getPosition());
        oldEmployee.setSalary(employeeDTO.getSalary());
        employeeRepository.save(oldEmployee);
        authRepository.save(oldUser);
    }

    public void deleteEmployee(Integer employeeId) {
        authRepository.deleteById(employeeId);
    }
}
