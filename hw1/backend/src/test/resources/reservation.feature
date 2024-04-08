Feature: Reservation Feature
  In order to reserve a bus trip
  As a user
  I want to create a reservation

  Scenario: User makes a reservation
    Given the user navigates to the homepage
    When the user selects a trip from "Aveiro" to "Viseu"
    And the user fills in the reservation details with "Lia", "Lima", "hvjbdk@gmail.com"
    Then the reservation is successful

  Scenario: User searches for a reservation using a token
    Given the user has a reservation token "123456abcdef"
    When the user navigates to the reservation search page
    And the user enters the reservation token
    Then the user sees the reservation details
