package com.ng.bootcamp.runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)

@CucumberOptions(plugin = { "pretty" }, features = { "src/test/resources" }, glue = {
		"com.ng.bootcamp.stepdef" }, dryRun = true)

public class PizzaRunnerTest {
}
