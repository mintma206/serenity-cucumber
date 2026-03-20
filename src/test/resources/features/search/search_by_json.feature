Feature: Search by json

  @json:jsons/test.json
  Scenario: Searching single case by json
    Given Sergey is attempts to open search page
    When he looks up by "cust.name" in json
    Then he should see information "cust.expect" in json

  @json:jsons/test.json
  Scenario: Searching list case by json
    Given Tom is attempts to open search page
    When he prepares to search using "items" in json
    Then he should verify the search results for each "items" in json

