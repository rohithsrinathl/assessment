# assessment
Set Up:

1. Install Eclipse for Automation Testers.
2. Intall cucumber plugin in Eclipse
3. Install TestNG plugin in Eclipse
4. Create Maven Project
5. Add Selenium and related dependencies in pom.xml for UI.
6. Add Rest Assured and related dependencies in pom.xml for API.
7. Add open csv and related dependencies in pom.xml for CSV data processing.

Running the Test Suite:

1. Run the CucumberRunner class as TestNG Test or run the Test suite as Maven Test
2. The Feature files can also be separately run as individual cucumber features.
3. Test cases are written in Behaviour driven approach using Gherkin language and available in the folder src/test/resources/features.

Reports:

1. When run as Cucumber Features,the report is generated in test-output/Default suite/default test.html
2. When run as TestNG test, the report is generated in test-output/index.html and also as xml file in test-output/testng-results.xml
