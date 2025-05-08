## Overview of locator types in Playwright:
* By Role
* By Text Content
* By Labels or Placeholders
* By Test ID
* By CSS (mniej uzywane w playwright)
  * Example CSS: <code>page.locator("#login-form input[name='username'"]).fill("usernameValue");</code>
  * Example CSS: <code>page.locator(".btn-primary").click(); </code>
* By XPath (mniej uzywane w playwright)
* Combining Selectors
* Advanced Selector Techniques
## Technologies Used:
* Playwright (https://playwright.dev/)
* Java
* Maven
* JUnit 5
* Allure Framework (https://allurereport.org/docs/)
## Automated tests - Local Run 
Running automated tests through maven command locally:
* <code>mvn clean verify</code>
## Test Report will be generated in directory:
<code>/target/site/allure-maven-plugin/index.html</code>