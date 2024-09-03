package org.twspring.project3.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.twspring.project3.Model.User;

@Repository
public interface AuthRepository extends JpaRepository<User,Integer> {
    User findUserById(Integer id);
    User findByUsername(String username);
}
