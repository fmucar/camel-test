package bdd;

import bdd.books.CucumberSpringContextConfiguration;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

/**
 * To run cucumber test.
 */
@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/resources/features"},
        plugin = {"pretty", "html:target/cucumber/books.html"},
        glue = "bdd.books")
public class CucumberTestRunner extends CucumberSpringContextConfiguration {

}