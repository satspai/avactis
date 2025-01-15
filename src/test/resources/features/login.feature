@login
Feature: User login

@TC3
	Scenario: User login successful
		Given user is on store home page
		When user navigates to signin page
		And user logs in with valid credentials
		Then user should see landing page

@TC4
	Scenario: User login successful
		Given user is on store home page
		When user navigates to signin page
		And user logs in with invalid credentials
		Then user should see the login error message