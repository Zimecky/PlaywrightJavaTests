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

    @DisplayName("With Page Object")
    @Test
    void withPageObject() {
        page.navigate("https://practicesoftwaretesting.com");

        SearchComponent searchComponent = new SearchComponent(page);
        ProductList productList = new ProductList(page);

        searchComponent.searchBy("tape");

        var matchingProducts = productList.getProductNames();

        Assertions.assertThat(matchingProducts)
                .contains("Tape Measure 7.5m", "Measuring Tape", "Tape Measure 5m");
    }

    @DisplayName("With Page Object second")
    @Test
    void withPageObjectSecond() {
        page.navigate("https://practicesoftwaretesting.com");

        //can be created above method if used in other methods
        SearchComponent searchComponent = new SearchComponent(page);
        ProductList productList = new ProductList(page);
        ProductDetails productDetails = new ProductDetails(page);
        NavBar navBar = new NavBar(page);

        searchComponent.searchBy("pliers");
        //productList.viewProductDetails("Combination Pliers");
        productList.viewProductDetails("Combination Pliers"); // change to other Pliers which are not out of stock

        productDetails.increaseQuantityBy(2);
        productDetails.addToCart();

        navBar.openCart();

    }

    class SearchComponent {
        private final Page page;

        SearchComponent(Page page) {
            this.page = page;
        }

        public void searchBy(String keyword) {
            page.waitForResponse("**/products/search?q=" + keyword, () -> {
                page.getByPlaceholder("Search").fill(keyword);
                page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Search")).click();
            });
        }
    }

    class ProductList {
        private final Page page;

        ProductList(Page page) {
            this.page = page;
        }

        public List<String> getProductNames() {
            return page.getByTestId("product-name").allInnerTexts();
        }

        public void viewProductDetails(String productName){
            page.locator(".card").getByText(productName).click();
        }
    }

    class ProductDetails {
        private final Page page;

        ProductDetails(Page page) {
            this.page = page;
        }

        public void increaseQuantityBy(int increment) {
            for (int i = 1; i < increment; i++) {
                page.getByTestId("increase-quantity").click();
            }
        }

        public void addToCart() {
            page.waitForResponse
                    (response -> response.url().contains("/carts") && response.request().method().equals("POST"),
                            () -> page.getByText("Add to cart").click()
                    );

        }
    }

    class NavBar {
        private final Page page;

        NavBar(Page page) {
            this.page = page;
        }

        public void openCart() {
            page.getByTestId("nav-cart").click();
        }
    }

}
