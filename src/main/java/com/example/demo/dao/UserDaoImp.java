package com.example.demo.dao;

import com.example.demo.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @PersistenceContext
   private EntityManager em;

   @Override
   public List<User> getUsersList() {
      Query query = em.createQuery("SELECT u FROM User u");
      List<User> entities = query.getResultList();
      return entities;
   }

   @Override
   public void addUser(User user) {
      em.persist(user);
   }

   @Override
   public void removeUser(Long id) {
      Query query = em.createQuery("delete from User u where u.id=:id")
              .setParameter("id", id);
      query.executeUpdate();
   }

   @Override
   public User findUserById(Long id) {
      return em.find(User.class, id);
   }

   @Override
   public void updateUser(User user) {
      em.merge(user);
   }
}
