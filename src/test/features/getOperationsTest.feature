Feature: Verify the Get Operations

  Scenario Outline: Verify the status code of the given endpoint
    Given I perform GET operation for "<Endpoint>"
    Then I should get the StatusCode "200"
    Examples:
      |Endpoint  |
      |/posts    |
      |/comments |
      |/users    |

  Scenario Outline: Verify the response headers for the given endpoint
    Given I perform GET operation for "<Endpoint>"
    Then I should get the "<HeaderType>" as "<Header>"
    Examples:
      |Endpoint  |HeaderType       |Header          |
      |/posts    |Content-Type     |application/json|
      |/posts    |Server           |cloudflare      |
      |/posts    |Content-Encoding |gzip            |
      |/comments |Content-Type     |application/json|
      |/comments |Server           |cloudflare      |
      |/comments |Content-Encoding |gzip            |
      |/users    |Content-Type     |application/json|
      |/users    |Server           |cloudflare      |
      |/users    |Content-Encoding |gzip            |

   Scenario Outline: Verify the response body by passing the query parameter
     Given I perform GET operation for "<Endpoint>" with "<Parameter>" = "<Value>"
     Then I should get the ID values as "<Value>" in the response body
     Examples:
       |Endpoint     |Parameter  |Value|
       |/posts       |id         |1    |
       |/posts       |id         |2    |
       |/posts       |id         |100  |
       |/comments    |id         |1    |
       |/comments    |id         |2    |
       |/comments    |id         |100  |
       |/users       |id         |1    |
       |/users       |id         |2    |
       |/users       |id         |10   |

   Scenario Outline: Verify the response with the invalid Endpoint given
     Given I perform GET operation for "<Endpoint>"
     Then I should get the StatusCode "404"
     Examples:
       |Endpoint |
       |/post  |
       |/comment |
       |/user    |