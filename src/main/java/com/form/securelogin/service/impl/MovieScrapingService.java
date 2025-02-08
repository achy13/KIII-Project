package com.form.securelogin.service.impl;

import com.form.securelogin.model.Book;
import com.form.securelogin.model.Movie;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class MovieScrapingService {

        public List<Movie> scrapeMovies(String url) throws IOException {
            Document doc = Jsoup.connect(url).get();

            Elements movieTitleElements = doc.select("h3.ipc-title__text");
            Elements movieLinkElements = doc.select("a.ipc-title-link-wrapper");
            Elements ratingElements = doc.select("span.ipc-rating-star--rating");
            Elements imageElements = doc.select("div.ipc-media img");

            List<Movie> movies = new ArrayList<>();

            for (int i = 1; i < 21; i++) {
                String title = movieTitleElements.get(i).text();
                String rating = ratingElements.get(i).text();
                String link = "https://www.imdb.com/" + movieLinkElements.get(i).attr("href");
                String imageUrl = imageElements.get(i).attr("src");

                movies.add(new Movie(title, imageUrl, rating, link));
            }

            return movies;
        }
}
