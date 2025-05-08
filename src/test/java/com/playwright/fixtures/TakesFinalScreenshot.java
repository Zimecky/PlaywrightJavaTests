package com.playwright.fixtures;

import com.microsoft.playwright.Page;

public interface TakesFinalScreenshot {

    default void takeScreenshot(Page page){
        ScreenshotManager.takeScreenshot(page, "Final screenshot");
    }
}
