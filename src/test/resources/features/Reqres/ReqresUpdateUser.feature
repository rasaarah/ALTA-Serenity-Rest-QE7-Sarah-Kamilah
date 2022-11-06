Feature: Reqres Update User Feature Test
  @tugas
  Scenario Outline: Put update user with valid json test
    Given Put update user with valid json with id <id>
    When Send put update user request
    Then Status code should be 200 OK
    And Response body should contain name "Sarah Update" and job "QA Update"
    And Validate put update json schema
    Examples:
      | id |
      | 1  |
      | 2  |