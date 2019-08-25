# Login Feature
# language: en
@LoginProfile
Feature: Login Profile
  As a student of ASD subject
  I want to login my ASD demo profile using my credentials
  In order to collaborate with my team

  Background: User navigates to ASD-Demo-app home Given
    Given I am on the "ASD Demo Home" page on URL "https://asd-demo-app.herokuapp.com/"
    When I fill in "Username" with "Georges034302"
    And I fill in "Password" with "darkside666"
    Then I should see "mLab Connection" green
 
  Scenario: Successful login
    When I fill in "Username" with "john.smith@uts.com"
    And I fill in "Password" with "helloworld123"
    And I click on the "Login" button
    Then I am on the "Main Page" page on URL "https://asd-demo-app.herokuapp.com/main.jsp"
    And I should see "My Account" Button
    And I should see the "Logout" button
 
  Scenario Outline: Failed login using wrong credentials
    When I fill in "Username" with "<username>"
    And I fill in "Password" with "<password>"
   Then I am on the "Main Page" page on URL "https://asd-demo-app.herokuapp.com/main.jsp"
    And I should see "User Does not exit" message
    And I should see the "Logout" button
    Examples:
      | username            | password      | warning                           |
      | Test                | !23           | Incorrect credentials. Try again! |
      | Tset                | 123           | Incorrect credentials. Try again! |
      | Tset                | !23           | Incorrect credentials. Try again! |
      | Test                |               | Please enter password.            |
      |                     | 123           | Please enter username.            |
      |                     |               | Please enter your credentials.    |