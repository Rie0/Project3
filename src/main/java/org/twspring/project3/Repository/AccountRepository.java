package org.twspring.project3.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.twspring.project3.Model.Account;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account,Integer> {
    Account findAccountById(Integer id);
    List<Account> findAccountByCustomerId(Integer customerId);
}
