package nl.oa.demo.cucumber;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = "nl.oa.demo.cucumber.service")
public class CucumberConfiguration {
}