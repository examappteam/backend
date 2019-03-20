package org.examapp.authenticationservice.repository;

import org.examapp.authenticationservice.model.ROLE_NAME;
import org.examapp.authenticationservice.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ROLE_NAME roleName);
}
