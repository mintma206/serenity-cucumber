Feature: Search by example

  @example
  Scenario Outline: Searching for <element>
    Given Sergey is researching things on the internet
    When he looks up "<element>"
    Then he should see information about "<element>"

    Examples:
      | element |
      | iPhone  |
      | iPad    |
      | iMac    |
      | MacBook |
      | MacBook Pro |

