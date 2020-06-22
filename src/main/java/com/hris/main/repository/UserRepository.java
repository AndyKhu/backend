package com.hris.main.repository;

import com.hris.main.model.system.Tmuser;
import com.hris.main.repository.generic.systemRepository;

import java.util.Optional;

public interface UserRepository extends systemRepository<Tmuser,String> {
    Optional<Tmuser> findByUsername(String username);
    Boolean existsByUsername(String username);
}