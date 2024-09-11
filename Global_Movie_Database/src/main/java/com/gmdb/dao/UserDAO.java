package com.gmdb.dao;

import com.gmdb.entity.User;
import com.gmdb.utility.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class UserDAO {
    private SessionFactory sessionFactory;

    public UserDAO() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public void registerUser(User user) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(user);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public User login(String email, String password) {
        Session session = sessionFactory.openSession();
        User user = null;
        try {
            String hql = "FROM User U WHERE U.email = :email AND U.password = :password";
            Query<User> query = session.createQuery(hql, User.class);
            query.setParameter("email", email);
            query.setParameter("password", password);
            user = query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return user;
    }
}
