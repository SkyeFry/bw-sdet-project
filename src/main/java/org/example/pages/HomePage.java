package org.example.pages;

import com.microsoft.playwright.Page;

public class HomePage {
    private static final String HOME_PAGE_URL = "http://localhost:8080/";
    private final Page page;

    public HomePage(Page page) {
        this.page = page;
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
