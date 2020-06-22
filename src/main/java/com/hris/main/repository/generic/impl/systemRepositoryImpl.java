package com.hris.main.repository.generic.impl;

import com.hris.main.helper.staticdata.DataAuditTrailType;
import com.hris.main.model.generic.DataTrail;
import com.hris.main.model.system.TbDataAuditTrail;
import com.hris.main.repository.generic.systemRepository;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.io.Serializable;

public class systemRepositoryImpl<T, ID extends Serializable>
        extends SimpleJpaRepository<T, ID> implements systemRepository<T, ID> {

    private EntityManager entityManager;

    public systemRepositoryImpl(JpaEntityInformation<T, ?>
                                          entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public T insert(T t, String user) {
        if (t instanceof DataTrail) {
            T tt = save(t);
            if (updateTrail(DataAuditTrailType.CREATE, tt, user)) {
              return tt;
            }
        } else {
            return save(t);
        }
        return null;
    }

    @Override
    @Transactional
    public T update(T t, String user) {
        if (t instanceof DataTrail) {
            if (updateTrail(DataAuditTrailType.UPDATE, t, user)) {
                return entityManager.merge(t);
            }
        } else {
            return entityManager.merge(t);
        }
        return null;
    }

    @Override
    @Transactional
    public void delete(T t, String user) {
        if (t instanceof DataTrail) {
            if (updateTrail(DataAuditTrailType.DELETE_FORCE, t, user)) {
                super.delete(t);
            }
        } else {
            super.delete(t);
        }
    }

    @Override
    @Transactional
    public T deletesoft(T t, String user) {
        if (t instanceof DataTrail) {
            if (updateTrail(DataAuditTrailType.DELETE, t, user)) {
                return entityManager.merge(t);
            }
        } else {
            return save(t);
        }
        return null;
    }

    private boolean updateTrail(DataAuditTrailType type, T t, String user) {
        DataTrail s = (DataTrail) t;
        entityManager.persist(new TbDataAuditTrail(s.getId().toString(), type, user));
        return true;
    }
}
