package org.example.tests;

import org.example.base.BaseTest;
import org.example.pages.HomePage;
import org.example.pages.NavSidebarPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NavSidebarTest extends BaseTest {

    private NavSidebarPage navSidebarPage;
    private static final String HOME_PAGE_URL = "http://localhost:8080/";
    private static final String COUNTER_PAGE_URL = "http://localhost:8080/counter";
    private static final String WEATHER_PAGE_URL = "http://localhost:8080/weather";

    @BeforeEach
    public void sidebarTabsSetUp() {
        navSidebarPage = new NavSidebarPage(basePage.getPage());
        HomePage homePage = new HomePage(basePage.getPage());
        homePage.navigateToHomePage();
        page.waitForFunction("() => window.Blazor !== undefined;");
    }

    @Test
    public void clickMenuHeading_IsSuccessful() {
        navSidebarPage.clickSidebarHeading();
        assertEquals(HOME_PAGE_URL, navSidebarPage.getPageUrl());
    }

    @Test
    public void clickHomeSidebarLink_IsSuccessful() {
        navSidebarPage.clickHomeSidebarLink();
        assertEquals(HOME_PAGE_URL, navSidebarPage.getPageUrl());
    }

    @Test
    public void clickCounterSidebarLink_IsSuccessful() {
        navSidebarPage.clickCounterSidebarLink();
        assertEquals(COUNTER_PAGE_URL, navSidebarPage.getPageUrl());
    }

    @Test
    public void clickWeatherSidebarLink_IsSuccessful() {
        navSidebarPage.clickWeatherSidebarLink();
        assertEquals(WEATHER_PAGE_URL, navSidebarPage.getPageUrl());
    }
}
