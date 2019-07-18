Feature: This feature automates Pizza functionality


Scenario Outline: This scenario validate pizza page title
Given User goes to home page "http://bootcamp.ngc.training/"
When User clicks on <Link>
Then User is on <Validate> page
Examples:
|Link|Validate|
|Pizza|Order Pizza|

Scenario: This scenario is to order a pizza
Given User is on "Order Pizza" page - number is 1 and cost is 9.5
And Customer "John" "Doe"
And Called from number "510-586-7000"
And Orders "Medium" pizza using "Original Hand Tossed Crust" crust and following toppings
|Pineapple|
|Peppers|
And Wants pizza "Delivery" to address
|1 North Pole|ANCHORAGE|AK|99530|
And With special instructions"Please hurry!!! I am hungry"
And Orders the pizza
Then Order is verified
# But Call customer service

# Scenario: Incomplete pizza order 
# Given User is on "Order Pizza" page
# And Orders "Large" pizza using "Cheese Stuffed Crust" crust and following toppings
# |Pepperoni|
# |Mushrooms|
# And Customer will "Pickup"And Orders the pizza
# When Order is verified
# Then verify the following error messsage is displayed
# |First Name is required.|
# |Last Name is required.|
# |Enter a valid phone number|