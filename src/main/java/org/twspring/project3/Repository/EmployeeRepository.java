package org.twspring.project3.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.twspring.project3.Model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
    Employee findEmployeeById(Integer employeeId);
}
