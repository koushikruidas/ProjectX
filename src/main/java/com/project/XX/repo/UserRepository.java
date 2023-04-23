package com.project.XX.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.XX.entity.User;
import com.project.XX.util.Role;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	@Query("SELECT u FROM User u WHERE u.id = :id AND u.role = :role")
	Optional<User> findByIdAndRole(@Param("id") Long id, @Param("role") Role role);
}
