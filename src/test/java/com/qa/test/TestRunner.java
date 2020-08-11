package com.qa.test;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)

// this is test runner class


@CucumberOptions(
        plugin = {"json:build/cucumber.json","html:target/cucumber-reports" },
        features = {"src/test/features"},
        glue = {"com.qa.automation"},
        monochrome = true
)

public class TestRunner {

}
