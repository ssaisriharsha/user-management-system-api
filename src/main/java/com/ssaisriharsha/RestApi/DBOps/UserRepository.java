package com.ssaisriharsha.RestApi.DBOps;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class UserRepository implements UserDAO{
    private final EntityManager em;

    @Autowired
    public UserRepository(EntityManager em) {
        this.em = em;
    }
    @Override
    public User save(User user) {
        @SuppressWarnings("")
        User u = em.merge(user);
        return u;
    }

    @Override
    public User findById(int id) {
        return em.find(User.class, id);
    }

    @Override
    public List<User> findByLastName(String lastName) {
        String queryString = "SELECT u FROM User u WHERE u.lastName=:lastName";
        Query query = em.createQuery(queryString, User.class);
        query.setParameter("lastName", lastName);
        @SuppressWarnings("unchecked")
        List<User> resultList = query.getResultList();
        return resultList;
    }

    @Override
    public List<User> findByFirstName(String firstName) {
        String queryString = "SELECT u FROM User u WHERE u.firstName=:firstName";
        Query query = em.createQuery(queryString, User.class);
        query.setParameter("firstName", firstName);
        @SuppressWarnings("unchecked")
        List<User> resultList = query.getResultList();
        return resultList;
    }


    @Override
    public List<User> findByActivityStatus(Boolean activityStatus) {
        String queryString = "SELECT u FROM User u WHERE u.active=:active";
        Query query = em.createQuery(queryString, User.class);
        query.setParameter("active", activityStatus);
        @SuppressWarnings("unchecked")
        List<User> resultList = query.getResultList();
        return resultList;
    }

    @Override
    public List<User> findAll() {
        String queryString = "SELECT u FROM User u";
        Query query = em.createQuery(queryString, User.class);
        @SuppressWarnings("unchecked")
        List<User> resultList = query.getResultList();
        return resultList;
    }

/*
    @Override
    public int updateUserById(User user) {
        String queryString = "UPDATE User u SET u.firstName=:firstname, u.lastName=:lastname, u.email=:email, " +
                "u.active=:active WHERE u.id=:id";
        Query query = em.createQuery(queryString);
        query.setParameter("firstname", user.getFirstName());
        query.setParameter("lastname", user.getLastName());
        query.setParameter("email", user.getEmail());
        query.setParameter("active", user.getActive());
        query.setParameter("id", user.getId());
        @SuppressWarnings("")
        int rowsAffected = query.executeUpdate();
        return rowsAffected;
    }
*/

    @Override
    public int deleteUserById(int id) {
        String queryString = "DELETE User u WHERE u.id=:id";
        Query query = em.createQuery(queryString);
        query.setParameter("id", id);
        @SuppressWarnings("")
        int rowsAffected = query.executeUpdate();
        return rowsAffected;
    }

    @Override
    public User findByEmail(String mailId) {
        String queryString = "SELECT u FROM User u WHERE u.email=:mail";
        Query query = em.createQuery(queryString, User.class);
        query.setParameter("mail", mailId);
        @SuppressWarnings("")
        User user = (User) query.getSingleResult();
        return user;
    }
}
