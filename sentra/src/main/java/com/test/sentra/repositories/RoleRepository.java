package com.test.sentra.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.sentra.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	Optional<Role> findByName(String name); 
}
