@registration
Feature: New user registration

  @registerpositive
  Scenario: User registration successful
    Given user is on store home page
    When user navigates to registration page and registers with new data
    Then user should see the registration confirmation message

	@registernegative
  Scenario: User registration unsuccessful
    Given user is on store home page
    When user navigates to registration page and registers with new data
    Then user should see the registration error message

