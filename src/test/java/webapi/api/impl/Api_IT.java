package webapi.api.impl;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

/**
 * Junit entry point for cucumber tests.
 */
@RunWith(Cucumber.class)
@CucumberOptions(features = "features",
      glue = "webapi.api.impl.cucumber.steps",
      tags = {"not @NotImplemented"},// Use @NotImplemented tag for scenarios that have not been implemented
      plugin = {"pretty"})
public class Api_IT {
    
}
