package ua.deti.tqs;

import java.util.Date;
public class Book {
    private final String title;
    private final String author;
    private final Date published;
    private final String category;

    public Book(String title, String author, Date published, String category) {
        this.title = title;
        this.author = author;
        this.published = published;
        this.category = category;
    }
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public Date getPublished() {
        return published;
    }

    public String getCategory() { return category;}

}
