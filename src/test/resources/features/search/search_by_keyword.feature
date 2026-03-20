Feature: Search by colors

  @color
  Scenario: Searching for 'green'
    Given Sergey is researching things on the internet
    When he looks up "green"
    Then he should see information about "green"

  @color
  Scenario: Searching for 'red'
    Given Sergey is researching things on the internet
    When he looks up "red"
    Then he should see information about "red"

  @color
  Scenario: Searching for 'blue'
    Given Sergey is researching things on the internet
    When he looks up "blue"
    Then he should see information about "blue"
