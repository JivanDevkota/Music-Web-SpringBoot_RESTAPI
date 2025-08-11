package com.nsu.mymusic.service.userServices;

import com.nsu.mymusic.dtos.AuthenticationRequest;
import com.nsu.mymusic.entity.Role;
import com.nsu.mymusic.entity.Users;
import com.nsu.mymusic.repository.RoleRepository;
import com.nsu.mymusic.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AuthenticationManager authenticationManager;


    @PostConstruct
    public void addUser() {
        //check if role exist or not if not create
        Role role = roleRepository.findByRoleName("ROLE_USER")
                .orElseGet(() -> {
                    Role admin = new Role();
                    admin.setRoleName("ROLE_USER");
                    return roleRepository.save(admin);
                });

        //check if user of this role exist
        Optional<Users> user = userRepository.findByRoleName("ROLE_USER");
        if (!user.isPresent()) {
            Users newUser = new Users();
            newUser.setUsername("Ram");
            newUser.setEmail("Ram@gmail.com");
            newUser.setPassword(passwordEncoder.encode("1234"));
            Set<Role> roles = new HashSet<>();
            roles.add(role);
            newUser.setRoles(roles);
            userRepository.save(newUser);
        }
    }

    public String login(AuthenticationRequest request) {
        try {
            Authentication authenticate = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(
                            request.getUsername(), request.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authenticate);
            return "Login Done by User" + authenticate.getName();
        } catch (Exception e) {
            return "Invalid username or password";
        }
    }
}
