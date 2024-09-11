package com.gmdb.service;

import com.gmdb.dao.MovieDAO;
import com.gmdb.entity.Movie;
import com.gmdb.entity.User;
import java.util.List;

public class MovieService {
    private MovieDAO movieDAO;

    public MovieService() {
        movieDAO = new MovieDAO();
    }

    public void addMovie(Movie movie) {
        movieDAO.addMovie(movie);
    }

    public List<Movie> getMoviesByUser(User user) {
        return movieDAO.getMoviesByUser(user);
    }
    
    public Movie getMovieByName(String name) {
        return movieDAO.getMovieByName(name);
    }
}
