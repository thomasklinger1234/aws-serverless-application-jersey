package webapi.container.config;

import java.time.Duration;

import lombok.RequiredArgsConstructor;
import software.amazon.awssdk.services.ssm.model.ParameterNotFoundException;
import software.amazon.serverless.ssmcachingclient.SsmParameterCachingClient;

/**
 * Provide config value from AWS SSM.
 */
@RequiredArgsConstructor
public class SsmConfigProvider implements ConfigProvider {
    private final SsmParameterCachingClient ssm;

    @Override
    public String getDynamoDBTableName() {
        return ssm.getAsString("dynamodb/table_name");
    }
}
