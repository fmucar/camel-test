Feature: Book REST API


  Scenario: Retrieve list of books from rest endpoint
    Given there are 2 books in the database
    When a GET request is made to "/api/books"
    Then a response including 2 books should be returned


  Scenario: Create book fails if the request body is empty
    Given the service is up and running
    When a POST request is made to "/api/books" with empty body
    Then a response with status 500 should be returned