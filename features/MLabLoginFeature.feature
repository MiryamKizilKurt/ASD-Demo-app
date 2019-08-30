# MLabLogin Feature
# language: en
@MLabLoginProfile
Feature: MLabLoginFeature
  As a student of ASD subject
  I want to login my ASD mLab account using my admin credentials
  In order to connect to mLab cloud database

Background: User navigates to mLab login page 
   Given I am on mLab login page 

  Scenario: Successful login to mLab      
    When I fill in "Username" with "<mLab username>"
    And I fill in "Password" with "<mLab Password>"
    Then I should see "mLab Connection" green
 
  Scenario Outline: Failed login to mLab using wrong credentials
    When I fill in "Username" with "<username>"
    And I fill in "Password" with "<password>"
    Then I should see "mLab Connection" red
    Examples:
      | username           | password     | warning                                       |
      | Test                    | !23               | Incorrect credentials. Try again! |
      | Test                    |                     | Please enter password.               |
      |                            | 123              | Please enter username.              |
      |                            |                     | Please enter your credentials.    |