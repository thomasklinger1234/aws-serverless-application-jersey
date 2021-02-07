package webapi.container; 

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

import lombok.extern.slf4j.Slf4j;

/**
 * Request filter to support CORS.
 */
@Provider
@Slf4j
public class CorsHeadersResponseFilter implements ContainerResponseFilter {
  @Override
  public void filter(ContainerRequestContext requestContext,
                     ContainerResponseContext responseContext) {
    String origin = "*";
    responseContext.getHeaders().add("Access-Control-Allow-Origin", origin);
  }
}