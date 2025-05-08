package com.playwright;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import com.playwright.fixtures.PlaywrightTestCase;
import com.playwright.fixtures.TakesFinalScreenshot;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;

import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class AddingItemsToTheCartTest extends PlaywrightTestCase implements TakesFinalScreenshot {

    @DisplayName("Search for pliers")
    @Test
    void searchForPliers(){
        page.navigate("https://practicesoftwaretesting.com");
        page.getByPlaceholder("Search").fill("Pliers");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Search")).click();

        assertThat(page.locator(".card")).hasCount(4);

        List<String> productNames = page.getByTestId("product-name").allTextContents();
        Assertions.assertThat(productNames).allMatch(name -> name.contains("Pliers"));

    //    Locator outOfStockItem = page.locator(".card")
   //             .filter(new Locator.FilterOptions().setHasText("Out of stock"))
          //      .getByTestId("productName");

       // assertThat(outOfStockItem).hasCount(1);
        //assertThat(outOfStockItem).hasText("Long Nose Pliers");


    }
}
