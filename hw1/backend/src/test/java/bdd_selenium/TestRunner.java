package bdd_selenium;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources",
    glue = {"ua.deti.tqs.backend.bdd_selenium"},
    plugin = {"pretty", "html:target/cucumber-reports"},
    strict = true
)
public class TestRunner {
}
