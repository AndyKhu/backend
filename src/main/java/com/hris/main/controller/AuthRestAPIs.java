package com.hris.main.controller;

import com.hris.main.message.request.LoginForm;
import com.hris.main.message.request.SignUpForm;
import com.hris.main.message.response.JwtResponse;
import com.hris.main.model.dto.userDetail;
import com.hris.main.model.system.*;
import com.hris.main.model.system.view.vwUserMenuAccess;
import com.hris.main.repository.UserRepository;
import com.hris.main.repository.vwUserMenuRepository;
import com.hris.main.security.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthRestAPIs {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    vwUserMenuRepository vwUserMenuRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);
        String username = jwtProvider.getUserNameFromJwtToken(jwt);
        List<vwUserMenuAccess> listmenu = vwUserMenuRepository.findByUsername(username);
        Integer maxlevel = vwUserMenuRepository.maxlevel(username);
        userDetail tt = new userDetail(new JwtResponse(jwt),listmenu,maxlevel);
        return ResponseEntity.ok(tt);
    }

    @GetMapping("/checkToken")
    public ResponseEntity<?> checkToken(@Valid @RequestHeader("Authorization") String auth) {
        String jwt = auth.replace("Bearer ","");
        if (jwt!="" && jwt != null && jwtProvider.validateJwtToken(jwt)) {
            String username = jwtProvider.getUserNameFromJwtToken(jwt);
            List<vwUserMenuAccess> listmenu = vwUserMenuRepository.findByUsername(username);
            Integer maxlevel = vwUserMenuRepository.maxlevel(username);
            userDetail tt = new userDetail(new JwtResponse(jwt),listmenu,maxlevel);
            return ResponseEntity.ok(tt);
        }else {
            return new ResponseEntity<String>("Fail -> Jwt Not Falid!",
                    HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {
        if(userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity<String>("Fail -> Username is already taken!",
                    HttpStatus.BAD_REQUEST);
        }
//         Creating user's account
        Tmuser user = new Tmuser(signUpRequest.getUsername(),encoder.encode(signUpRequest.getPassword()),signUpRequest.getRole());
        userRepository.insert(user,"SYSTEM");

        return ResponseEntity.ok().body("User registered successfully!");
    }
}