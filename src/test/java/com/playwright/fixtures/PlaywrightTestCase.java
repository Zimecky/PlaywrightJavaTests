package com.playwright.fixtures;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.util.Arrays;


public abstract class PlaywrightTestCase {

    protected static ThreadLocal<Playwright> playwright =
            ThreadLocal.withInitial(() -> {
                        Playwright playwright = Playwright.create();
                        playwright.selectors().setTestIdAttribute("data-test");
                        return playwright;
                    }
            );

    protected static ThreadLocal<Browser> browser =
            ThreadLocal.withInitial(() ->
                     playwright.get().chromium().launch(
                             new BrowserType.LaunchOptions()
                                     .setHeadless(false)
                                     .setArgs(Arrays.asList("--no-sandbox", "--disable-extensions", "--disable-gpu"))
                    )
            );

    protected BrowserContext browserContext;
    protected Page page;

    @BeforeEach
    void setupBrowserContext() {
        browserContext = browser.get().newContext();
        page = browserContext.newPage();
    }

    @AfterEach
    void takeFinalScreenshot() {
        ScreenshotManager.takeScreenshot(page, "End of test");
    }

    @AfterAll
    static void tearDown() {
      browser.get().close();
      browser.remove();

      playwright.get().close();
      playwright.remove();
    }
}
