package com.hris.main.repository;

import com.hris.main.model.system.view.vwUserMenuAccess;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface vwUserMenuRepository extends CrudRepository<vwUserMenuAccess, String> {
    List<vwUserMenuAccess> findByUsername(String username);

    @Query("Select max(level) from vwUserMenuAccess where username = :username")
    Integer maxlevel(String username);
}