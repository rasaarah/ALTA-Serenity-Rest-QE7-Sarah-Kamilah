Feature: Reqres Create User Feature Test
  @tugas
  Scenario: Post create new user with valid json
    Given Post create user with valid json
    When Send post user request
    Then Status code should be 201 Created
    And Response body should contain name "sarah" and job "QA Engineer"
    And Validate create user json schema