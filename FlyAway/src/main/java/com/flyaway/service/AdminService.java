package com.flyaway.service;

import com.flyaway.model.Admin;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


public class AdminService {
    private final SessionFactory sessionFactory;

    public AdminService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public boolean authenticateAdmin(String username, String password) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Admin admin = session.createQuery("FROM Admin WHERE username = :username AND password = :password", Admin.class)
                    .setParameter("username", username)
                    .setParameter("password", password)
                    .uniqueResult();
            transaction.commit();
            return admin != null;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean changePassword(int adminId, String newPassword) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Admin admin = session.get(Admin.class, adminId);
            if (admin != null) {
                admin.setPassword(newPassword);
                session.update(admin);
                transaction.commit();
                return true;
            } else {
                return false; // Admin with given ID not found
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public int getAdminIdByUsername(String username) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Query<Integer> query = session.createQuery("SELECT admin.id FROM Admin admin WHERE username = :username", Integer.class);
            query.setParameter("username", username);
            Integer adminId = query.uniqueResult();
            transaction.commit();
            return adminId != null ? adminId : -1; // Return admin ID or -1 if not found
        } catch (Exception e) {
            e.printStackTrace();
            return -1; // Return -1 in case of any error
        }
    }
}


