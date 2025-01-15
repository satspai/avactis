@registration
Feature: New user registration

  @TC1
  Scenario: User registration successful
    Given user is on store home page
    When user navigates to registration page and registers with new data
    Then user should see the registration confirmation message

  @TC2
  Scenario: User registration unsuccessful
    Given user is on store home page
    When user navigates to registration page and registers with existing data
    Then user should see the registration error message

	