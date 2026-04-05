package com.example.dao;

import com.example.model.Role;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.NoResultException;

@Repository
public class RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    public Role findByName(String name) {
        try {
            return entityManager.createQuery("SELECT r FROM Role r WHERE r.name = :name", Role.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}