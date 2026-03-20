Feature: Search by fruits

  @fruit
  Scenario: Searching for 'apple'
    Given Sergey is researching things on the internet
    When he looks up "apple"
    Then he should see information about "apple"

  @fruit
  Scenario: Searching for 'orange'
    Given Sergey is researching things on the internet
    When he looks up "orange"
    Then he should see information about "orange"


  @fruit
  Scenario: Searching for 'lemon'
    Given Sergey is researching things on the internet
    When he looks up "lemon"
    Then he should see information about "lemon"
