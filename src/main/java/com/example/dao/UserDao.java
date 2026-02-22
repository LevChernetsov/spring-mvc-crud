package com.example.dao;

import com.example.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository  // (1) Говорим Spring: это компонент для работы с БД
public class UserDao {

    // (2) Это самое главное место! EntityManager придет из конфигурации
    @PersistenceContext
    private EntityManager entityManager;

    // (3) Сохранить нового пользователя
    @Transactional
    public void save(User user) {
        entityManager.persist(user);  // INSERT в базу
    }

    // (4) Найти пользователя по ID
    public User findById(Long id) {
        return entityManager.find(User.class, id);  // SELECT по id
    }

    // (5) Получить ВСЕХ пользователей
    public List<User> findAll() {
        // JPQL запрос (не SQL, а запрос к объектам Java)
        return entityManager.createQuery("SELECT u FROM User u", User.class)
                .getResultList();
    }

    // (6) Обновить существующего пользователя
    @Transactional
    public void update(User user) {
        entityManager.merge(user);  // UPDATE в базе
    }

    // (7) Удалить пользователя
    @Transactional
    public void delete(Long id) {
        User user = findById(id);
        if (user != null) {
            entityManager.remove(user);  // DELETE из базы
        }
    }
}
