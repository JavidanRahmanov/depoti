package com.cavidanrahmanov.depoti.security.service;

import com.cavidanrahmanov.depoti.security.dto.LoginRequestDTO;
import com.cavidanrahmanov.depoti.security.dto.UserRequestDTO;
import com.cavidanrahmanov.depoti.security.model.Users;
import com.cavidanrahmanov.depoti.security.repository.UserRepository;
import com.cavidanrahmanov.depoti.service.FavoritesService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepo;
    private final ModelMapper modelMapper;
    private final FavoritesService favoriteService;
   final AuthenticationManager authManager;

    private final JWTService jwtService;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public Users register(UserRequestDTO userDTO) {

        Users user = modelMapper.map(userDTO, Users.class);
        user.setPassword(encoder.encode(userDTO.getPassword()));
        return userRepo.save(user);
    }

    public String verify(LoginRequestDTO request) {
        Authentication authentication =
                authManager.authenticate(
                        new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        if (authentication.isAuthenticated()) {
            // 1. İstifadəçini tap
            Users user = userRepo.findByUsername(request.getUsername())
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));

            // 2. Temporary favoritləri merge edirik
            if (request.getTemporaryUserId() != null) {
                favoriteService.mergeFavoritesAfterLogin(request.getTemporaryUserId(), user.getId());
            }

            // 3. Token qaytarırıq
            return jwtService.generateToken(user.getUsername());
        }

        return "Fail";
    }
}
