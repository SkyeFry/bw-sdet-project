package org.example.tests.ui;

import org.example.base.BasePage;
import org.example.base.BaseTest;
import org.example.pages.NavSidebarPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NavSidebarTest extends BaseTest {

    private final NavSidebarPage navSidebarPage = new NavSidebarPage(basePage.getPage());
    private static final String HOME_PAGE_URL = "http://localhost:8080/";
    private static final String COUNTER_PAGE_URL = "http://localhost:8080/counter";
    private static final String WEATHER_PAGE_URL = "http://localhost:8080/weather";

    @BeforeEach
    public void sidebarTabsSetUp() {
        BasePage.navigateToUrlAndWaitForBlazor(HOME_PAGE_URL);
    }

    @Test
    public void clickMenuHeading_OpensCorrectUrl() {
        navSidebarPage.clickSidebarHeading();
        assertEquals(HOME_PAGE_URL, navSidebarPage.getPageUrl());
    }

    @Test
    public void clickHomeSidebarLink_OpensCorrectUrl() {
        navSidebarPage.clickHomeSidebarLink();
        assertEquals(HOME_PAGE_URL, navSidebarPage.getPageUrl());
    }

    @Test
    public void clickCounterSidebarLink_OpensCorrectUrl() {
        navSidebarPage.clickCounterSidebarLink();
        assertEquals(COUNTER_PAGE_URL, navSidebarPage.getPageUrl());
    }

    @Test
    public void clickWeatherSidebarLink_OpensCorrectUrl() {
        navSidebarPage.clickWeatherSidebarLink();
        assertEquals(WEATHER_PAGE_URL, navSidebarPage.getPageUrl());
    }
}
