package com.form.securelogin.controller;

import com.form.securelogin.model.Book;
import com.form.securelogin.model.Movie;
import com.form.securelogin.service.impl.BookScrapingService;
import com.form.securelogin.service.impl.MovieScrapingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.List;

@Controller
public class ContentController {

    private final BookScrapingService bookScrapingService;
    private final MovieScrapingService movieScrapingService;

    @Autowired
    public ContentController(BookScrapingService bookScrapingService, MovieScrapingService movieScrapingService) {
        this.bookScrapingService = bookScrapingService;
        this.movieScrapingService = movieScrapingService;
    }

    // Endpoint to display books
    @GetMapping("/books")
    public String getBooks(Model model) {
        try {
            String url = "https://www.goodreads.com/list/show/1.Best_Books_Ever";
            List<Book> books = bookScrapingService.scrapeBooks(url);

            model.addAttribute("title", "Top Books");
            model.addAttribute("books", books);

            return "books";  // View name (books.html)
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("error", "Failed to retrieve books.");
            return "error";  // Error page
        }
    }

    @GetMapping("/movies")
    public String getMovies(Model model) {
        try {
            String url = "https://www.imdb.com/chart/top/";
            List<Movie> movies = movieScrapingService.scrapeMovies(url);
            System.out.println(movies.size());

            model.addAttribute("title", "Top Movies");
            model.addAttribute("movies", movies);

            return "movies";  // View name (books.html)
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("error", "Failed to retrieve books.");
            return "error";  // Error page
        }
    }

    @GetMapping("/series")
    public String getSeries(Model model) {
        try {
            String url = "https://www.imdb.com/chart/toptv/";
            List<Movie> series = movieScrapingService.scrapeMovies(url);

            model.addAttribute("title", "Top Series");
            model.addAttribute("series", series);

            return "series";  // View name (books.html)
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("error", "Failed to retrieve books.");
            return "error";  // Error page
        }
    }
}
