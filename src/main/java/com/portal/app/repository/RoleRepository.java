package com.portal.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.portal.app.model.Role;
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findByName(String name);

}
