Feature: Reqres Delete User Feature Test
  @tugas
  Scenario Outline: Delete user with valid id
    Given Delete user with id <id>
    When Send delete user request
    Then Status code should be 204 No Content
    Examples:
      | id |
      | 2  |