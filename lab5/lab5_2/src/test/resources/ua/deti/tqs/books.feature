@books_sample

Feature: Searching Books

     Background: A Library
        Given the library has the following books:
                | title                                | author      | publication_date | category
                | The Devil in the White City          | Erik Larson | 2003             | History
                | The Lion, the Witch and the Wardrobe | C.S. Lewis  | 1950-10-16       | Fantasy
                | In the Garden of Beasts              | Erik Larson | 2011-05-10       | History

     Scenario: Search By Name
        When I search for a book having the title "In the Garden of Beasts"
        Then I should find book with the title "In the Garden of Beasts"

     Scenario: Search By Author
        When I search for a book having th author "Erik Larson"
        Then I should find 2 books

     Scenario: Search By Year
        When I search for a book from 2011
        Then I should find 1 book
        Then I should find the book with the title "In the Garden of Beasts"

     Scenario: Search By Category
        When I search for a book with the category "History"
        Then I should find 2 books
        Then I should find the books with the titles "The Devil in the White City" and "In the Garden of Beasts"