package org.example.pages;

import com.microsoft.playwright.Page;

public class CounterPage {
    private final Page page;
    public static final String COUNTER_PAGE_URL = "http://localhost:8080/counter";
    public static final String COUNTER_PAGE_TITLE_TEXT = "Counter";
    public static final String COUNTER_PAGE_HEADING_TEXT = "Counter";
    public static final String COUNTER_PAGE_HEADING_SELECTOR = "h1";
    private static final String CLICK_ME_BUTTON_SELECTOR = ".btn.btn-primary";
    private static final String COUNTER_VALUE_SELECTOR = "p[role='status']";

    public CounterPage(Page page) {
        this.page = page;
    }

    public String getCounterPageUrl() {
        return page.url();
    }

    public String getCounterPageTitle() {
        return page.title();
    }

    public String getCounterPageHeading() {
        return page.textContent(COUNTER_PAGE_HEADING_SELECTOR);
    }

    public void clickCounterButton() {
        page.click(CLICK_ME_BUTTON_SELECTOR);
    }

    public void clickCounterButton(int delayInMillis) {
        clickCounterButton();
        try {
            Thread.sleep(delayInMillis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public String getCounterValue() {
        String statusText = page.textContent(COUNTER_VALUE_SELECTOR);
        return statusText.split(": ")[1].trim();
    }
}
