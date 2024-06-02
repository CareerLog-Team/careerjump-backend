package org.careerjump.server.user.repository;


import org.careerjump.server.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

    User findByUserId(String userId);
}
