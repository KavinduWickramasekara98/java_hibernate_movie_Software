package com.gmdb;

import com.gmdb.entity.Movie;
import com.gmdb.entity.Review;
import com.gmdb.entity.User;
import com.gmdb.service.MovieService;
import com.gmdb.service.ReviewService;
import com.gmdb.service.UserService;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static UserService userService = new UserService();
    private static MovieService movieService = new MovieService();
    private static ReviewService reviewService = new ReviewService();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Register (Enter No 1)");
            System.out.println("2. Login (Enter No 2)");
            System.out.println("3. Add Movie (Enter No 3)");
            System.out.println("4. View Movies Purchased by User (Enter No 4)");
            System.out.println("5. Add Review (Enter No 5)");
            System.out.println("6. View Average Rating of a Movie (Enter No 6)");
            System.out.println("7. Exit (Enter No 7)");

            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            String email;
            String password;
            switch (option) {
                case 1: // Register
                    System.out.println("Enter your name:");
                    String name = scanner.nextLine();
                    System.out.println("Enter your email:");
                    email = scanner.nextLine();
                    System.out.println("Enter your password:");
                    password = scanner.nextLine();
                    
                    // Create a User object
                    User newUser = new User(name, email, password);
                    
                    // Register the user
                    userService.registerUser(newUser);
                    System.out.println("User registered successfully.");
                    break;

                case 2: // Login
                    System.out.println("Enter your email:");
                    email = scanner.nextLine();
                    System.out.println("Enter your password:");
                    password = scanner.nextLine();
                    
                    // Attempt login
                    User user = userService.login(email, password);
                    if (user != null) {
                        System.out.println("Login successful!");
                        // Proceed to other operations if needed
                    } else {
                        System.out.println("Invalid credentials.");
                    }
                    break;

                case 3: // Add Movie
                    System.out.println("Enter movie name:");
                    String movieName = scanner.nextLine();
                    System.out.println("Enter director name:");
                    String director = scanner.nextLine();
                    System.out.println("Enter movie runtime (in minutes):");
                    int runtime = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.println("Enter movie genres (comma separated):");
                    String genres = scanner.nextLine();
                    System.out.println("Enter movie actors (comma separated):");
                    String actors = scanner.nextLine();
                    // Create a Movie object
                    Movie movie = new Movie();
                    movie.setName(movieName);
                    movie.setDirector(director);
                    movie.setRuntime(runtime);
                    movie.setActors(actors);
                    
                    // Add the movie
                    movieService.addMovie(movie);
                    System.out.println("Movie added successfully.");
                    break;

                case 4: // View Movies Purchased by User
                    System.out.println("Enter your email to fetch purchased movies:");
                    email = scanner.nextLine();
                    System.out.println("Enter your password:");
                    password = scanner.nextLine();
                    user = userService.login(email, password);
                    if (user != null) {
                        List<Movie> movies = movieService.getMoviesByUser(user);
                        for (Movie m : movies) {
                            System.out.println("Movie: " + m.getName());
                        }
                    } else {
                        System.out.println("Invalid user.");
                    }
                    break;
case 5: // Add Review
                    System.out.println("Enter movie name:");
                    String movieNameForReview = scanner.nextLine();
                    Movie movieForReview = movieService.getMovieByName(movieNameForReview); // Assume this method exists
                    if (movieForReview != null) {
                        System.out.println("Enter your email:");
                        email = scanner.nextLine();
                        System.out.println("Enter your password:");
                        password = scanner.nextLine();
                        System.out.println("Enter your rating (1-5):");
                        int rating = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        System.out.println("Enter your review:");
                        String reviewDescription = scanner.nextLine();

                        User reviewUser = userService.login(email, password);
                        if (reviewUser != null) {
                            Review review = new Review();
                            review.setUser(reviewUser);
                            review.setMovie(movieForReview);
                            review.setRating(rating);
                            review.setReviewDescription(reviewDescription);
                            reviewService.addReview(review);
                            System.out.println("Review added successfully.");
                        } else {
                            System.out.println("Invalid user.");
                        }
                    } else {
                        System.out.println("Movie not found.");
                    }
                    break;

               case 6: // View Average Rating of a Movie
                System.out.println("Enter movie name to get average rating:");
                movieNameForReview = scanner.nextLine();
    
                // Fetch the movie by name
                 movieForReview = movieService.getMovieByName(movieNameForReview);
                if (movieForReview != null) {
                    double averageRating = reviewService.getAverageRating(movieForReview);
                    System.out.println("Average rating for movie '" + movieForReview.getName() + "' is " + averageRating);
                } else {
                    System.out.println("Movie not found.");
                }
                    break;


                case 7: // Exit
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option.");
                    break;
            }
        }
    }
}
