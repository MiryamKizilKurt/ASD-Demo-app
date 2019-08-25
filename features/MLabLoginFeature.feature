# Login Feature
# language: en
@MLabLoginProfile
Feature: MLabLogin Profile
  As a student of ASD subject
  I want to login my ASD mLab profile using my admin credentials
  In order to connect to mLab cloud database

  Background: User navigates to ASD-Demo-app home Given
    Given I am on the "ASD Demo Home" page on URL "https://asd-demo-app.herokuapp.com/mLab.jsp"
     
  Scenario: Successful login      
    When I fill in "Username" with "Georges034302"
    And I fill in "Password" with "darkside666"
    Then I should see "mLab Connection" green
 
  Scenario Outline: Failed login using wrong credentials
    When I fill in "Username" with "<username>"
    And I fill in "Password" with "<password>"
    Then I should see "mLab Connection" red
    Examples:
      | username            | password      | warning                           |
      | Test                | !23           | Incorrect credentials. Try again! |
      | Test                |               | Please enter password.            |
      |                     | 123           | Please enter username.            |
      |                     |               | Please enter your credentials.    |