# GUI Test Automation using Selenium

## Features supported
1. Dependency management and project management by using MAVEN 
2. Functional UI Automation by using Selenium
3. TestNG for testing workflow
4. GIT as distributed version-control system
5. Modular Approach via Page Object model
6. Browser supported - Chrome, IE and Firefox
7. HTML Report by including Extent Reports
8. Logging via Log4j
9. Property Reader to read Test data from properties files.
10. Archived Last execution results by utilizing `java.util.zip`. 
11. Custom exception is created to avoid reading false files, Object Not Found, Zip Creation Exception.
12. TestNG Listeners to tackle Skipped testcase in extent report.
13. Custom assertion to print custom message when assertion fails.
14. Capture screen shots for failed testcases.
15. Implicit and Explicit waits are considered for Windows to Load and Webelement to load.
16. Maven is configured in such a way that will run different testNG.xml provided at run time.


## Pre-requisite
1. Java above 1.8.
2. Maven version above 3.0.
3. TestNG 6.14.3

## How to install & Run using command prompt
1. Please extract the project at your desired path.
2. Go to `src/test/resources/config/config.properties` file and update configurations. 
3. Open the command prompt and go to the project path.
4. Run the command "mvn clean install -DsuiteXmlFile=testng.xml"
5. All the automated test cases in the testNG.xml will be executed.

## Installing

Refer to the documents attached in `referenceDocument` Folder

		
## To view Report 
1. Go to the root directory under `SeleniumProject/Current_test_results/<yyyy-mm-dd hh-mm-ss>/testReport.html`
2. All past reports are saved under `SeleniumProject/Arhived_test_results/<yyyy-mm-dd hh-mm-ss>.zip/testReport.html` 


Note: Test cases are available in `SeleniumProject\src\test\resources\Testcases.xlsx` for your reference.

## Git Repository for the source.
`https://github.com/sachinahuja90/NAGPSeleniumAssignment`

