package org.example.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitUntilState;

public class HomePage {
    private static final String HOME_PAGE_URL = "http://localhost:8080/";
    private final Page page;

    public HomePage(Page page) {
        this.page = page;
    }

    public void navigateToHomePage() {
        page.navigate(HOME_PAGE_URL, new Page.NavigateOptions().setWaitUntil(WaitUntilState.NETWORKIDLE));
    }

    public String getHomePageUrl() {
        return HOME_PAGE_URL;
    }

    public String getHomePageTitle() {
        return page.title();
    }

    public String getHomePageHeading() {
        return page.textContent("h1");
    }
}
