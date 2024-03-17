package ua.deti.tqs;

import java.time.Instant;
import java.util.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

import java.time.LocalDateTime;
import java.util.Map;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import io.cucumber.java.ParameterType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
public class BookSearchSteps {

    Library library = new Library();
    List<Book> result = new ArrayList<>();

    @ParameterType("([0-9]{4})-([0-9]{2})-([0-9]{2})")
    public LocalDateTime iso8601Date(String year, String month, String day){
        return LocalDateTime.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day),0, 0);
    }

    @DataTableType
    public Book bookEntry(Map<String, String> entry) {
        LocalDateTime publicationDate = LocalDateTime.parse(entry.get("publication_date"));
        Instant instant = publicationDate.atZone(ZoneId.systemDefault()).toInstant();
        Date publicationDate_converted = Date.from(instant);
        return new Book(
                entry.get("title"),
                entry.get("author"),
                publicationDate_converted,
                entry.get("category"));
    }

    @Given("I have a library with the following books list$")
    public void haveBooksInTheStoreByList(DataTable table) {

        List<List<String>> rows = table.asLists(String.class);

        for (List<String> columns : rows) {
            store.addBook(new Book(columns.get(0), columns.get(1), columns.get(2), columns.get(3)));
        }
    }
