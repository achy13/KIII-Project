package com.form.securelogin.model;

public class Movie {
    private String title;
    private String imageUrl;
    private String rating;
    private String movieLink;

    public Movie(String title, String imageUrl, String rating, String movieLink) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.rating = rating;
        this.movieLink = movieLink;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getMovieLink() {
        return movieLink;
    }

    public void setMovieLink(String movieLink) {
        this.movieLink = movieLink;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", rating='" + rating + '\'' +
                ", movieLink='" + movieLink + '\'' +
                '}';
    }
}

