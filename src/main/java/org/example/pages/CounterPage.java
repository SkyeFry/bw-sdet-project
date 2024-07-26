package org.example.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitUntilState;

public class CounterPage {
    private static final String COUNTER_PAGE_URL = "http://localhost:8080/counter";
    private final Page page;
    private final String clickMeButtonSelector = ".btn.btn-primary";

    public CounterPage(Page page) {
        this.page = page;
    }

    public void navigateToCounterPage() {
        page.navigate(COUNTER_PAGE_URL, new Page.NavigateOptions().setWaitUntil(WaitUntilState.NETWORKIDLE));
    }

    public String getCounterPageUrl() {
        return COUNTER_PAGE_URL;
    }

    public String getCounterPageTitle() {
        return page.title();
    }

    public String getCounterPageHeading() {
        return page.textContent("h1");
    }

    public void clickCounterButton() {
        page.click(clickMeButtonSelector);
    }

    public void clickCounterButtonWithDelay(int delayInMillis) {
        clickCounterButton();
        try {
            Thread.sleep(delayInMillis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public String getCounterValue() {
        String statusText = page.textContent("p[role='status']");
        return statusText.split(": ")[1].trim();
    }
}
