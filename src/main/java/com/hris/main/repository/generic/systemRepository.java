package com.hris.main.repository.generic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface systemRepository <T, ID extends Serializable>
        extends JpaRepository<T, ID> {
    T insert(T t, String user);

    T update(T t, String user);

    void delete(T t, String user);

    T deletesoft(T t, String user);
}
