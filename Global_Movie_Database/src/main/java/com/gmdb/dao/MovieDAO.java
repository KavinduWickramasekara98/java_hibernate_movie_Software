package com.gmdb.dao;

import com.gmdb.entity.Movie;
import com.gmdb.entity.User;
import com.gmdb.utility.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class MovieDAO {
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public void addMovie(Movie movie) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(movie);
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

    public List<Movie> getMoviesByUser(User user) {
        Session session = sessionFactory.openSession();
        List<Movie> movies = null;
        try {
            String hql = "SELECT m FROM Movie m JOIN m.purchasedMovies p WHERE p.user.id = :userId";
            Query<Movie> query = session.createQuery(hql, Movie.class);
            query.setParameter("userId", user.getId());
            movies = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return movies;
    }
    public Movie getMovieByName(String name) {
        Session session = sessionFactory.openSession();
        String hql = "FROM Movie M WHERE M.name = :movieName";
        Query<Movie> query = session.createQuery(hql);
        query.setParameter("movieName", name);
        Movie movie = query.uniqueResult();
        session.close();
        return movie;
    }
}
