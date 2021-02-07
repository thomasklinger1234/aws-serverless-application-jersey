package webapi.api.impl.cucumber.steps;

import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import lombok.extern.slf4j.Slf4j;

public class TestSetupAndTeardown {
    @Before
    public void beforeScenario(final Scenario s) {
      TestEnv.reset();
    }
}
