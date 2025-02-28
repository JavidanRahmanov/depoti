package com.cavidanrahmanov.depoti.security.service;

import com.cavidanrahmanov.depoti.security.dto.UserRequestDTO;
import com.cavidanrahmanov.depoti.security.model.Users;
import com.cavidanrahmanov.depoti.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepo;
    private final ModelMapper modelMapper;
   final AuthenticationManager authManager;

    private final JWTService jwtService;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public Users register(UserRequestDTO userDTO) {

        Users user = modelMapper.map(userDTO, Users.class);
        user.setPassword(encoder.encode(userDTO.getPassword()));
        return userRepo.save(user);
    }

    public String verify(Users user) {
        Authentication authentication =
                authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));

        if(authentication.isAuthenticated())
            return jwtService.generateToken(user.getUsername());

        return "Fail";
    }
}
