package webapi.api.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.UUID;

import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.*;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import webapi.container.config.ConfigProvider;

@Slf4j
@RequiredArgsConstructor
public class MyResourceService {
  private final ModelMapper modelMapper;
  private final DynamoDbClient dynamodbClient;
  private final String dynamodbTable;

  @Context
  @Setter
  private SecurityContext securityContext;

  @Inject
  public MyResourceService(final DynamoDbClient dynamodb, final ConfigProvider configProvider) {
    this.dynamodbTable = configProvider.getDynamoDBTableName();
    this.dynamodbClient = dynamodb;
    this.modelMapper = configureModelMapper();
  }

  private static ModelMapper configureModelMapper() {
    ModelMapper modelMapper = new ModelMapper();

    // PropertyMap<MyResourceRecord, MyResourceSummary> myresourceSummaryMap =
    //     new PropertyMap<MyResourceRecord, MyResourceSummary>() {
    //       protected void configure() {
    //         map(source.getCreatedAt()).setCreationTime(null);
    //       }
    //     };
    // modelmapper.addMappings(myresourceSummaryMap);

    return modelMapper;
  }

  @GET
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public MyResource getMyResourceDetails(@PathParam("id") final String id) {
    final MyResource output = new MyResource();
    output.setId(id);

    return output;
  }
}
