package com.effective.tms.security.repository;

import com.effective.tms.security.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
    boolean existsByUsername(String username);
    Optional<UserAccount> findByUsername(String username);
}
