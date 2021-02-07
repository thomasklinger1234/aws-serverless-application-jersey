Feature: GetResourceDetails
  As a user, I would like to get the details of my resource.

  Scenario: user gets a resource
    Given a user has a resource
    When the user gets the resource
    Then the resource should be returned
