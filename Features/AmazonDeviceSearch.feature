Feature: Login with Valid Credentials

  @sanity
  Scenario: launch the Amazon Web Application and pass data using data table
    Given User is on the Amazon homepage
    When User select the Electronics & Computers from Department section
    Then User select Phone & Accessories from Electronics section
    And User select Mobile Phones & Smartphones
    And User selects the desired configuration as follows
      | PhoneBrand       | Samsung       |
      | CameraResolution | 20 MP & above |
      | ModelYear        | 2023          |
      | lowerPriceRange  | 50            |
      | higherPriceRange | 100           |
    Then user should able to verify the page after desired configuration

  @sanity
  Scenario Outline: launch the Amazon Web Application and pass data using example keyword
    Given User is on the Amazon homepage
    When User select the Electronics & Computers from Department section
    Then User select Phone & Accessories from Electronics section
    And User select Mobile Phones & Smartphones
    Then user select the "<PhoneBrand>","<CameraResolution>","<ModelYear>","<lowerPriceRange>" and "<higherPriceRange>" as desired configuration
    Then user should able to verify the page after desired configuration
    Examples:
      | PhoneBrand | CameraResolution | ModelYear | lowerPriceRange | higherPriceRange |
      | Samsung    | 20 MP & above    | 2023      | 50              | 100              |

  @sanity
  Scenario: launch the Amazon Web Application and reading data through excel
    Given User is on the Amazon homepage
    When User select the Electronics & Computers from Department section
    Then User select Phone & Accessories from Electronics section
    And User select Mobile Phones & Smartphones
    Then User selects the desired configuration
    Then user should able to verify the page after desired configuration

