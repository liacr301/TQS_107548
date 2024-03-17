package ua.deti.tqs;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Library {
    private final List<Book> library = new ArrayList<>();
    public void addBook(final Book book) {
        library.add(book);
    }

    public List<Book> findBooksByDate(final Date from, final Date to) {
        Calendar end = Calendar.getInstance();
        end.setTime(to);
        end.roll(Calendar.YEAR, 1);

        return library.stream().filter(book -> {
            return from.before(book.getPublished()) && end.getTime().after(book.getPublished());
        }).sorted(Comparator.comparing(Book::getPublished).reversed()).collect(Collectors.toList());
    }

    public List<Book> findBooksByTitle(final String title) {
        return library.stream().filter(book -> {
            return title.equals(book.getTitle());
        }).sorted(Comparator.comparing(Book::getTitle).reversed()).collect(Collectors.toList());
    }

    public List<Book> findBooksByAuthor(final String author) {
        return library.stream().filter(book -> {
            return author.equals(book.getAuthor());
        }).sorted(Comparator.comparing(Book::getAuthor).reversed()).collect(Collectors.toList());
    }

    public List<Book> findBooksByCategory(final String category) {
        return library.stream().filter(book -> {
            return category.equals(book.getCategory());
        }).sorted(Comparator.comparing(Book::getCategory).reversed()).collect(Collectors.toList());
    }
}
