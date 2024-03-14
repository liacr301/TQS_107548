@books_sample

Feature: Searching Books

     Background: A Library
        Given the library has the following books:
                | title                                | author      |
                | The Devil in the White City          | Erik Larson |
                | The Lion, the Witch and the Wardrobe | C.S. Lewis  |
                | In the Garden of Beasts              | Erik Larson |

     Scenario: Search By Name
        When I search for a book having the title "In the Garden of Beasts"
        Then I should find book with the title "In the Garden of Beasts"

     Scenario: Search By Author
        When I search for a book having th author "Erik Larson"
        Then I should find 2 books

     Scenario: Search By Year
        When I search
        Then