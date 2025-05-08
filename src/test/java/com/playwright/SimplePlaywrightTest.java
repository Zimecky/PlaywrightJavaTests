package com.playwright;

import com.playwright.fixtures.PlaywrightTestCase;
import com.playwright.fixtures.TakesFinalScreenshot;
import org.junit.jupiter.api.*;


public class SimplePlaywrightTest extends PlaywrightTestCase implements TakesFinalScreenshot {

    @Test
    @DisplayName("Show page title")
    void shouldShowThePageTitle(){
        page.navigate("https://practicesoftwaretesting.com");
        String title = page.title();
        Assertions.assertTrue(title.contains("Practice Software Testing"));
    }

    @Test
    @DisplayName("Search By Keyword")
    void shouldSearchByKeyword(){
        page.navigate("https://practicesoftwaretesting.com");
        page.locator("[placeholder=Search]").fill("Pliers");
        page.locator("button:has-text('Search')").click();

        int matchingProductCount = page.locator(".card-title").count();
        Assertions.assertTrue(matchingProductCount> 0);
    }
}
