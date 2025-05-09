package com.playwright;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.playwright.fixtures.PlaywrightTestCase;
import com.playwright.fixtures.TakesFinalScreenshot;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class SearchTest extends PlaywrightTestCase implements TakesFinalScreenshot {

    @DisplayName("Without page objects")
    @Test
    public void withoutPageObject() {
        page.navigate("https://practicesoftwaretesting.com");
        waitForSearchResponse();
        List<String> matchingProducts = page.getByTestId("product-name").allInnerTexts();
        Assertions.assertThat(matchingProducts)
                .contains("Tape Measure 7.5m", "Measuring Tape", "Tape Measure 5m");
    }


    private void waitForSearchResponse() {
        page.waitForResponse("**/products/search?q=tape", () -> {
            page.getByPlaceholder("Search").fill("tape");
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Search")).click();
        });
    }


}
