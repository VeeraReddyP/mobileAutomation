# mobileAutomation

## Below are the tools that have used for automation framework.

1)Java Programming language

2)Appium for mobile UI automation

3)Maven as build tool

4)Junit for test execution flow

5)Serenity BDD framework

6)log4j for logging purpose

7)Serenity reports for reporting

## Test Scenarios:

1)Search and add product to cart


## Run Automation :

1)command to execute the entire project :   mvn clean post-integration-test test

2)Or run Ebaytests class in src/test/java/com/cognizantTechno/test/testScripts/EbayTests.java

3)Tests will run in andorid real device or emulator. (It automatically identfied connected device , no need to configure)

4)All android Capabilities will store in capability.json file to configure easily

5)Launching Appium server also need to taken care in thee programm itself. 

6)config properties is used for fetching the test data like username and password to login.

7)Report path : target>site>serenity>index.html

## Note:

1)The provided apk file is not supportive and its been always crashing

2)So, scenario is not up-to checkout flow , search and validate the product details

3)Please install apk in emulator or real device before run the test.
