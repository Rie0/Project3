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

    //FOR TESTS ONLY


//    public void registerAdmin(User user) {
//        String hash = new BCryptPasswordEncoder().encode(user.getPassword());
//        user.setRole("ADMIN");
//        user.setPassword(hash);
//        authRepository.save(user);
//    }
}
