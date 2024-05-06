package com.flyaway.service;

import com.flyaway.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class UserService {
    private final SessionFactory sessionFactory;

    public UserService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public boolean authenticateUser(String username, String password) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Query<User> query = session.createQuery("FROM User WHERE username = :username AND password = :password", User.class);
            query.setParameter("username", username);
            query.setParameter("password", password);
            User user = query.uniqueResult();
            transaction.commit();
            return user != null;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public int getUserIdByUsername(String username) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Query<Integer> query = session.createQuery(
                    "SELECT u.userId FROM User u WHERE u.username = :username", 
                    Integer.class);
            query.setParameter("username", username);
            Integer userId = query.uniqueResult();
            transaction.commit();
            return userId != null ? userId : -1; // Return -1 if no user is found
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }


}
