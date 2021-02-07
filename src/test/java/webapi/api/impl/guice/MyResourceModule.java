package webapi.api.impl.guice;

import software.amazon.serverless.ssmcachingclient.SsmParameterCachingClient;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provides;
import com.google.inject.Singleton;

import java.time.Duration;

import cucumber.runtime.java.guice.ScenarioScope;
import cucumber.runtime.java.guice.impl.SequentialScenarioScope;
import software.amazon.awssdk.http.urlconnection.UrlConnectionHttpClient;
import software.amazon.awssdk.services.ssm.SsmClient;

/**
 * DI wiring for ApplicationSteps.
 */
public class MyResourceModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(ScenarioScope.class).toInstance(new SequentialScenarioScope());
    }

    @Singleton
    @Inject
    @Provides
    SsmParameterCachingClient ssmParameterCachingClient() {
      String path = String.format("/applications/webapu/%s/", System.getProperty("integtests.stage"));
      return new SsmParameterCachingClient(SsmClient.builder()
            .httpClientBuilder(UrlConnectionHttpClient.builder())
            .build(),
            Duration.ofMinutes(5), path);
    }
}
