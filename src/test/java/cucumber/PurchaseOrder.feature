
@tag
Feature: Title of your feature
  I want to use this template for my feature file

Background:
Given I landed on ecommerce page
  
  @tag2
  Scenario Outline: Positive test for submitting the order
    Given Logged in with username  <name> and password <password>
    When add product <ProductName>  to cart
    And Checkout  <productname> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on the confirmation page

    Examples: 
      | name  								| password 			| ProductName  |
      | rahulshetty@gmail.com |  IamKing@000  | ZARA COAT 3  | 
     