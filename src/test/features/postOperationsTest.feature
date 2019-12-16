Feature: Verify the post operations for the given Endpoints

  Scenario Outline: Verify the status code of the given endpoint for the post request
    Given I perform POST operation for "<Endpoint>" with payload at "<FilePath>"
    Then I should get the status code as "201"
    Examples:
      |Endpoint  |FilePath  |
      |/posts    |/src/main/resources/posts.json |
      |/comments |/src/main/resources/comments.json |
      |/users    |/src/main/resources/users.json |

  Scenario Outline: Verify the response data of the Post request for the given endpoint
    Given I perform POST operation for "<Endpoint>" with payload at "<FilePath>"
    Then I should get the response "<data>" as "<value>"
    Examples:
      |Endpoint  |FilePath                        |data  | value |
      |/posts    |/src/main/resources/posts.json | id     | 101  |
      |/comments |/src/main/resources/comments.json |name  |Arpitha Maruthi |
      |/users    |/src/main/resources/users.json |  username |Arpitha       |

  Scenario Outline: Verify the status code of the given endpoint for the post request
    Given I perform POST operation for "<Endpoint>" with payload at "<FilePath>"
    Then I should get the status code as "500"
    Examples:
      |Endpoint  |FilePath  |
      |/posts    |/src/main/resources/posts_invalid.json |
      |/comments |/src/main/resources/comments_invalid.json |
      |/users    |/src/main/resources/users_invalid.json |
