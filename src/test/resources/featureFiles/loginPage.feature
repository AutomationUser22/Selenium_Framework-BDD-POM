Feature: Validate user is able to login
  Scenario: User is login with correct credentials
    Given Login Page is open
    When User enter correct UserName
    And User enter correct Password
    And User clicks on Login button
    Then Product list Page should open