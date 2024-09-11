package com.gmdb.dao;

import com.gmdb.entity.Movie;
import com.gmdb.entity.Review;
import com.gmdb.utility.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class ReviewDAO {
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public void addReview(Review review) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(review);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public double getAverageRating(Movie movie) {
        Session session = sessionFactory.openSession();
        double averageRating = 0.0;
        try {
            String hql = "SELECT AVG(r.rating) FROM Review r WHERE r.movie.id = :movieId";
            Query<Double> query = session.createQuery(hql, Double.class);
            query.setParameter("movieId", movie.getId());
            // Ensure the result is not null
            Double result = query.uniqueResult();
            if (result != null) {
                averageRating = result;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return averageRating;
    }
}
