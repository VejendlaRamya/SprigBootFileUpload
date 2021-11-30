package com.portal.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.portal.app.model.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	User findByUsername(String username);
	@Query(value="select * from user where id in (select user_id from user_roles where role_id=(select id from role where name=:userRole))",nativeQuery=true)
List<User>	getAllUserByUserRole(@Param(value = "userRole") String userRole);
}
