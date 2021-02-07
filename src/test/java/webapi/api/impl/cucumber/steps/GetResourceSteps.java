package webapi.api.impl.cucumber.steps;

import static org.assertj.core.api.Assertions.assertThat;

import com.google.common.base.Preconditions;
import com.google.inject.Inject;

import java.util.UUID;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GetResourceSteps {
    @When("^user gets a resource$")
    public void the_user_gets_the_application() {
        assertThat(TestEnv.getLastException()).isNull();
    }
}
