Feature: Reqres Get List User  Feature test
  @tugas
  Scenario Outline: Get list user with valid parameter page
    Given Get list user with parameter page <page>
    When Send get list user request
    Then Status code should be 200 OK
    And Response body page should be <page>
    And Validate get list user json schema
  Examples:
    | page |
    | 1    |
    | 2    |















