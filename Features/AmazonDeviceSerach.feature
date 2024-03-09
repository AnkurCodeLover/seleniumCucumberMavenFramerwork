Feature: Login with Valid Credentials

  @sanity
  Scenario: launch the Amazon Web Application and pass data using data table
    Given User is on the Amazon homepage
    When User select the Electronics & Computers from Department section
    Then User select Phone & Accessories from Electronics section
    And User select Mobile Phones & Smartphones
    And User selects the desired configuration as follows
      | PhoneBrand | Samsung |
      | CameraResolution | 20 MP & above  |
      | ModelYear | 2023 |
      | lowerPriceRange | 50 |
      | higherPriceRange  | 100   |
    Then user should able to verify the page after desired configuration

