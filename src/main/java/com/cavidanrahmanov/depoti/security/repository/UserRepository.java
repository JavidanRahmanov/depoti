package com.cavidanrahmanov.depoti.security.repository;

import com.cavidanrahmanov.depoti.security.dto.UserRequestDTO;
import com.cavidanrahmanov.depoti.security.dto.UserResponseDTO;
import com.cavidanrahmanov.depoti.security.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    Users findByUsername(String username);
}
