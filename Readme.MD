Overview of locator types in Playwright:
-By Role
-By Text Content
-By Labels or Placeholders
-By Test ID
-By CSS (mniej uzywane w playwright)
Example CSS: page.locator("#login-form input[name='username'"]).fill("usernameValue");
Example CSS: page.locator(".btn-primary").click();
-By XPath (mniej uzywane w playwright)
-Combining Selectors
-Advanced Selector Techniques