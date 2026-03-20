Feature: Search by animal

  @animal
  Scenario: Searching for 'fish'
    Given Sergey is researching things on the internet
    When he looks up "fish"
    Then he should see information about "fish"

  @animal
  Scenario: Searching for 'dog'
    Given Sergey is researching things on the internet
    When he looks up "dog"
    Then he should see information about "dog"

  @animal
  Scenario: Searching for 'cat'
    Given Sergey is researching things on the internet
    When he looks up "cat"
    Then he should see information about "cat"
