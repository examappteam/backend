package org.examapp.authenticationservice.repository;

import org.examapp.service.authentication.model.ROLE_NAME;
import org.examapp.service.authentication.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ROLE_NAME roleName);
}
