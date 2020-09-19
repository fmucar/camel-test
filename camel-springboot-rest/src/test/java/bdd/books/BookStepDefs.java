package bdd.books;

import com.cooldatasoft.entity.BookEntity;
import com.cooldatasoft.repository.BookRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@Slf4j
public class BookStepDefs extends CucumberSpringContextConfiguration {


    @Autowired
    private BookRepository bookRepository;

    ResponseEntity<String> response = null;

    @Given("there are {int} books in the database")
    public void thereAreBooksInTheDatabase(int numOfBooks) {

        bookRepository.save(new BookEntity(null, "title1", "author1"));
        bookRepository.save(new BookEntity(null, "title2", "author2"));
    }

    @When("a GET request is made to {string}")
    public void aGETRequestIsMadeTo(String path) {
        response = restTemplate.getForEntity(path, String.class);
    }

    @Then("a response including {int} books should be returned")
    public void aResponseWithBooksShouldBeReturned(int numOfBooks) {
        assertThat(response, is(notNullValue()));
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
        String books = response.getBody();

        assertThat(books, Matchers.containsString("author1"));
        assertThat(books, Matchers.containsString("author2"));
        assertThat(books, Matchers.containsString("title1"));
        assertThat(books, Matchers.containsString("title2"));
    }


    @Given("the service is up and running")
    public void theServiceIsUpAndRunning() {
    }

    @When("a POST request is made to {string} with empty body")
    public void aPOSTRequestIsMadeToWithEmptyBody(String path) {

        response = restTemplate.postForEntity(path, null, String.class);
    }

    @Then("a response with status {int} should be returned")
    public void aResponseWithStatusShouldBeReturned(int status) {
        assertThat(response, is(notNullValue()));
        assertThat(response.getStatusCodeValue(), equalTo(status));
        String books = response.getBody();
    }
}
