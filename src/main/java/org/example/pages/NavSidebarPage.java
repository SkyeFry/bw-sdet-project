package org.example.pages;

import com.microsoft.playwright.Page;

public class NavSidebarPage {
    private final Page page;
    private static final String HOME_SIDEBAR_LINK_SELECTOR = "text=Home";
    private static final String COUNTER_SIDEBAR_LINK_SELECTOR = "text=Counter";
    private static final String WEATHER_SIDEBAR_LINK_SELECTOR = "text=Weather";
    private static final String SIDEBAR_HEADING_SELECTOR = "text=SDET_Assessment";

    public NavSidebarPage(Page page) {
        this.page = page;
    }

    public void clickSidebarHeading() {
        page.click(SIDEBAR_HEADING_SELECTOR);
    }

    public void clickHomeSidebarLink() {
        page.click(HOME_SIDEBAR_LINK_SELECTOR);
    }

    public void clickCounterSidebarLink() {
        page.click(COUNTER_SIDEBAR_LINK_SELECTOR);
    }

    public void clickWeatherSidebarLink() {
        page.click(WEATHER_SIDEBAR_LINK_SELECTOR);
    }

    public String getPageUrl() {
        return page.url();
    }
}
