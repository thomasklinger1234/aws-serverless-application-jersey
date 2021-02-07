package webapi.container.factory;

import java.time.Duration;

import org.glassfish.hk2.api.Factory;

import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider;
import software.amazon.awssdk.http.urlconnection.UrlConnectionHttpClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ssm.SsmClient;

import software.amazon.serverless.ssmcachingclient.SsmParameterCachingClient;

import webapi.container.config.ConfigProvider;
import webapi.container.config.Env;
import webapi.container.config.SsmConfigProvider;

/**
 * Factory for {@link SsmConfigProvider} for HK2 DI wiring.
 */
public class SsmConfigProviderFactory implements Factory<ConfigProvider> {
  private static final ConfigProvider configProvider;

  static {
    SsmClient ssm = SsmClient.builder()
          .region(Region.of(Env.getRegion()))
          .credentialsProvider(EnvironmentVariableCredentialsProvider.create())
          .httpClientBuilder(UrlConnectionHttpClient.builder())
          .build();
    configProvider = new SsmConfigProvider(new SsmParameterCachingClient(ssm,
          Duration.ofMinutes(5), Env.getSsmConfigKeyPrefix()));
  }

  @Override
  public ConfigProvider provide() {
    return configProvider;
  }

  @Override
  public void dispose(ConfigProvider configProvider) {

  }
}