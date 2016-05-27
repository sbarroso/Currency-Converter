package nl.oa.demo.cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        format = { "pretty", "html:target/cucumber" },
        glue = {"nl.oa.demo.cucumber.stepdefinitions"}
)
public class RunAppleTest {
}