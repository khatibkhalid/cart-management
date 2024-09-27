package com.carrefour.cartmanagement.integration;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "com.carrefour.cartmanagement.integration.steps"
)
public class CucumberTest {
}
