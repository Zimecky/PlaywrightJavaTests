package com.playwright;

import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.junit.Options;
import com.microsoft.playwright.junit.OptionsFactory;
import com.microsoft.playwright.junit.UsePlaywright;
import org.junit.jupiter.api.*;

import java.util.Arrays;

@UsePlaywright
public class SimplePlaywrightTest {

    public static class MyOptions implements OptionsFactory{
        @Override
        public Options getOptions(){
            return new Options()
                    .setHeadless(false)
                    .setLaunchOptions(new BrowserType.LaunchOptions().setArgs(Arrays.asList("--no-sandbox", "disable-gpu")));
        }

    }

    @Test
    void shouldShowThePageTitle(Page page){
        page.navigate("https://practicesoftwaretesting.com");
        String title = page.title();
        Assertions.assertTrue(title.contains("Practice Software Testing"));
    }

    @Test
    void shouldSearchByKeyword(Page page){
        page.navigate("https://practicesoftwaretesting.com");
        page.locator("[placeholder=Search]").fill("Pliers");
        page.locator("button:has-text('Search')").click();

        int matchingProductCount = page.locator(".card-title").count();
        Assertions.assertTrue(matchingProductCount> 0);
    }
}
