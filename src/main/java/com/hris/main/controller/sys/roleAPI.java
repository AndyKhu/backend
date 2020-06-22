package com.hris.main.controller.sys;

import com.hris.main.model.system.Tmauth;
import com.hris.main.model.system.Tmmenu;
import com.hris.main.repository.authRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/sys/role")
public class roleAPI {
    @Autowired
    authRepository authRepository;

    @PostMapping("/saveAuth")
    public ResponseEntity<?> addMenu(
            Authentication auth,
            @RequestBody Tmauth tmauth) {
        try {
            authRepository.insert(tmauth,auth.getName());
            return ResponseEntity.ok(auth);
        } catch (Exception e){
            return new ResponseEntity<String>("Fail -> Bad Request!",
                    HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/removeAuth")
    public ResponseEntity<?> removeMenuForce(
            Authentication auth,
            @RequestBody Tmauth tmauth) {
        try {
            authRepository.delete(tmauth,auth.getName());
            return new ResponseEntity<>("Delete Force Success",
                    HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>("Fail -> Bad Request!",
                    HttpStatus.BAD_REQUEST);
        }
    }
}
