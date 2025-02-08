package com.form.securelogin.service.impl;

import com.form.securelogin.model.Book;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookScrapingService {
    public List<Book> scrapeBooks(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();

        Elements bookTitleElements = doc.select("a.bookTitle");
        Elements authorElements = doc.select("a.authorName");
        Elements imageElements = doc.select("img.bookCover");

        List<Book> books = new ArrayList<>();

        for (int i = 0; i < 19; i++) {
            String title = bookTitleElements.get(i).text();
            String bookLink = "https://www.goodreads.com" + bookTitleElements.get(i).attr("href");
            String author = authorElements.get(i).text();
            String authorLink = "https://www.goodreads.com" + authorElements.get(i).attr("href");
            String imageUrl = imageElements.get(i).attr("src");

            books.add(new Book(title, bookLink, author, authorLink, imageUrl));
        }

        return books;
    }
}
