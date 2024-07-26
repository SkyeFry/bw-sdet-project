package org.example.pages;

import com.microsoft.playwright.Page;

public class HomePage {
    private final Page page;
    public static final String HOME_PAGE_URL = "http://localhost:8080/";
    public static final String HOME_PAGE_TITLE_TEXT = "Home";
    public static final String HOME_PAGE_HEADING_TEXT = "Hello, world!";
    private static final String HOME_PAGE_HEADING_SELECTOR = "h1";


    public HomePage(Page page) {
        this.page = page;
    }

    public String getHomePageUrl() {
        return page.url();
    }

    public String getHomePageTitle() {
        return page.title();
    }

    public String getHomePageHeading() {
        return page.textContent(HOME_PAGE_HEADING_SELECTOR);
    }
}
