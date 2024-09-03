package org.twspring.project3.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.twspring.project3.Model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {

    Customer findCustomerById(Integer id);
}
