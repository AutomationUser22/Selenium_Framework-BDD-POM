## What is this Framework
This is a Test Automation framework mainly developed with help of Java/Selenium And Maven

## Tech Stack Used for this Framework
1. JAVA (Coding Language)
2. Selenium (Automation Tool for Testing Web apps)
3. GitHub (To host the code on repo)
4. AWS code build (To build and test the application/ No build required as testing website directly but can be done)
5. AWS Device Farm (To record all test execution related on cloud)
6. AWS SNS notification as per build status.
7. AWS S3 to store code artifacts in this case test result report.
8. AWS Event bridge to initialize the email when build status is changed
9. AWS Lambda function in python to create the email and AWS SES to send the email with test artifact/report to user.

## Below is the complete Infrastructure used for this framework

<div style="width: 100%;">
  <img src="Selenium_Vinayak_CI_CD.svg" style="width: 100%;" alt="Click to see the source">
</div>

