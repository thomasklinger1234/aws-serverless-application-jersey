package webapi.container;

import com.amazonaws.serverless.proxy.internal.LambdaContainerHandler;
import com.amazonaws.serverless.proxy.internal.testutils.Timer;
import com.amazonaws.serverless.proxy.jersey.JerseyLambdaContainerHandler;
import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;

import javax.inject.Singleton;

import org.glassfish.hk2.api.TypeLiteral;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import webapi.container.CorsHeadersResponseFilter;
import webapi.container.config.ConfigProvider;
import webapi.container.factory.SsmConfigProviderFactory;
import webapi.api.exception.ApiExceptionMapper;
import webapi.api.impl.MyResourceService;

public class StreamLambdaHandler implements RequestStreamHandler {
    private static final ResourceConfig jerseyApplication = new ResourceConfig()
        .registerClasses(CorsHeadersResponseFilter.class, ApiExceptionMapper.class, MyResourceService.class)
        .register(JacksonFeature.class)
        .register(new AbstractBinder() {
          @Override
          protected void configure() {
            bindFactory(SsmConfigProviderFactory.class)
                .to(ConfigProvider.class)
                .in(Singleton.class);
          }
        });

    private static final JerseyLambdaContainerHandler<AwsProxyRequest, AwsProxyResponse> handler
            = JerseyLambdaContainerHandler.getAwsProxyHandler(jerseyApplication);

    @Override
    public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context)
            throws IOException {
        handler.proxyStream(inputStream, outputStream, context);
    }
}