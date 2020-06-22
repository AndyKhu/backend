package com.hris.main.controller.sys;

import com.hris.main.model.system.Tmmenu;
import com.hris.main.repository.menuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/sys/menu")
public class menuAPI {
    @Autowired
    menuRepository menuRepository;

    @PostMapping("/saveMenu")
    public ResponseEntity<?> addMenu(
            Authentication auth,
            @RequestBody Tmmenu menu) {
        try {
            menuRepository.insert(menu,auth.getName());
            return ResponseEntity.ok(menu);
        } catch (Exception e){
            return new ResponseEntity<String>("Fail -> Bad Request!",
                    HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/removeMenu")
    public ResponseEntity<?> removeMenu(
            Authentication auth,
            @RequestBody Tmmenu menu) {
        try {
            if(menu.getId() != null && !menu.getId().isEmpty()){
                Optional<Tmmenu> a = menuRepository.findById(menu.getId());
                if(a.isPresent() && a.get().getStatus()) {
                    a.get().setStatus(false);
                    menuRepository.deletesoft(a.get(), auth.getName());
                    return new ResponseEntity<>("Delete Success",
                            HttpStatus.OK);
                }else{
                    return new ResponseEntity<>("Fail -> Item not found - Bad Request!",
                            HttpStatus.BAD_REQUEST);
                }
            }else{
                return new ResponseEntity<>("Fail -> No Id on Request - Bad Request!",
                        HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e){
            return new ResponseEntity<>("Fail -> Bad Request!",
                    HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/removeMenuForce")
    public ResponseEntity<?> removeMenuForce(
            Authentication auth,
            @RequestBody Tmmenu menu) {
        try {
            menuRepository.delete(menu,auth.getName());
            return new ResponseEntity<>("Delete Force Success",
                    HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>("Fail -> Bad Request!",
                    HttpStatus.BAD_REQUEST);
        }
    }
}
