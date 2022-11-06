Feature: Reqres Get Unknown Data Feature test
  @tugas
  Scenario Outline: Get unknown user list
    Given Get unknown list page <page>
    When Send get unkown list request
    Then Status code should be 200 OK
    And Response body page should be page <page>
    And Validate get unknown list user json schema
    Examples:
      | page |
      | 1    |
      | 2    |

  @tugas
  Scenario Outline: Get unknown single user
    Given Get unknown page <id>
    When Send get unknown single list
    Then Status code should be 200 OK
    And Response body page should be id <id> and name <name>
    Examples:
      | id | name             |
      | 1  | "cerulean"       |
      | 2  | "fuchsia rose"   |
      | 3  | "true red"       |
      | 4  | "aqua sky"       |
      | 5  | "tigerlily"      |
      | 6  | "blue turquoise" |
