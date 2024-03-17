package ua.deti.tqs;

import java.time.Instant;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;


import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.Map;

import io.cucumber.java.DataTableType;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.time.ZoneId;

public class BookSearchSteps {

    Library library = new Library();
    List<Book> result = new ArrayList<>();

    @ParameterType("([0-9]{4})-([0-9]{2})-([0-9]{2})")
    public LocalDateTime iso8601Date(String year, String month, String day) {
        return LocalDateTime.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day), 0, 0);
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
            LocalDateTime publicationDate = LocalDateTime.parse(columns.get(2));
            Instant instant = publicationDate.atZone(ZoneId.systemDefault()).toInstant();
            Date publicationDate_converted = Date.from(instant);
            library.addBook(new Book(columns.get(0), columns.get(1), publicationDate_converted, columns.get(3)));
        }
    }

    @When("I search for a book having the title {string}")
    public void searchByTitle(String title){
        result = library.findBooksByTitle(title);
    }

    @When("I search for a book having the author {string}")
    public void searchByAuthor(String author){
        result = library.findBooksByAuthor(author);
    }

    @When("I search for books published between {int} and {int}")
    public void searchBooksBetweenYears(Date start, Date end){
        result = library.findBooksByDate(start, end);
    }

    @When("I search for a book having the category {string}")
    public void searchByCategory(String category){
        result = library.findBooksByCategory(category);
    }

    @Then("{int} books should have been found")
    public void verifyAmountOfBooksFound(final int booksFound) {
        assertEquals(result.size(), booksFound);
    }

    @And("Book {int} should have the title {string}")
    public void verifyBookAtPosition(final int position, final String title) {
        assertThat(result.get(position - 1).getTitle(), equalTo(title));
    }



}