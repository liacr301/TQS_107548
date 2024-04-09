Feature: Reservation Feature
  In order to reserve a bus trip
  As a user
  I want to create a reservation

  Scenario: User makes a reservation
    Given the user user navigates to trips page by opening Firefox
    When the user selects a trip from "Aveiro" to "Viseu" and the date "2024-04-12"
    And selects the option with the time "11:00:00" and price "35.00"
    And the user fills in the reservation details with "Lia", "Lima", "hvjbdk@gmail.com"
    Then the reservation is successful
    And the user recieves the token "1234abcdef"

  Scenario: User searches for a reservation using a token
    Given the user has a reservation token "1234abcdef"
    When the user navigates to the reservation search page
    And the user enters the reservation token "1234abcdef"
    Then the user sees the reservation details "Aveiro", "Viseu", "2024-04-12", "11:00:00", "Lia", "Lima" and "hvjbdk@gmail.com"
