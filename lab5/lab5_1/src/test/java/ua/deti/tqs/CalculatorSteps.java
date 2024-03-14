package ua.deti.tqs;

import static java.lang.invoke.MethodHandles.lookup;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.slf4j.LoggerFactory.getLogger;

import static org.junit.jupiter.api.Assertions.assertNotNull;


import org.slf4j.Logger;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CalculatorSteps {
    static final Logger log = getLogger(lookup().lookupClass());

    private Calculator calc;
    private Exception Error;

    @Given("a calculator I just turned on$")
    public void setup() {
        calc = new Calculator();
    }

    @When("I add {int} and {int}")
    public void add(int arg1, int arg2) {
        log.debug("Adding {} and {}", arg1, arg2);
        calc.push(arg1);
        calc.push(arg2);
        calc.push("+");
    }

    @When("I substract {int} to {int}")
    public void substract(int arg1, int arg2) {
        log.debug("Substracting {} and {}", arg1, arg2);
        calc.push(arg1);
        calc.push(arg2);
        calc.push("-");
    }

    @When("I multiply {int} to {int}")
    public void multiply(int arg1, int arg2) {
        log.debug("Multiplying {} and {}", arg1, arg2);
        calc.push(arg1);
        calc.push(arg2);
        calc.push("*");
    }

    @When("I divide {int} by 0")
    public void divideByZero(int arg1) {
        log.debug("Dividing {} by 0", arg1);
        try {
            calc.push(arg1);
            calc.push(0); // Pushing 0 to simulate division by zero
            calc.push("/");
        } catch (Exception e) {
            Error = e; // Catching and storing the exception
        }
    }

    @Then("I expect an error")
    public void expectError() {
        assertNotNull(Error, "Expected an error, but none was thrown.");
    }

    @Then("the result is {double}")
    public void the_result_is(double expected) {
        Number value = calc.value();
        log.debug("Result: {} (expected {})", value, expected);
        assertEquals(expected, value);
    }
}
