package org.example.tests;

import org.example.base.BasePage;
import org.example.base.BaseTest;
import org.example.pages.HomePage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HomePageTest extends BaseTest {

    private final HomePage homePage = new HomePage(basePage.getPage());
    private static final String HOME_PAGE_URL = "http://localhost:8080/";
    private static final String HOME_PAGE_TITLE = "Home";
    private static final String HOME_PAGE_HEADING = "Hello, world!";

    @BeforeEach
    public void homePageSetUp() {
        BasePage.navigateToUrlAndWaitForBlazor(HOME_PAGE_URL);
    }

    @Test
    public void navigateToHomePage_OpensCorrectUrl() {
        assertEquals(HOME_PAGE_URL, homePage.getHomePageUrl());
    }

    @Test
    public void homePageTitle_IsCorrect() {
        assertEquals(HOME_PAGE_TITLE, homePage.getHomePageTitle());
    }

    @Test
    public void homePageHeading_IsCorrect() {
        assertEquals(HOME_PAGE_HEADING, homePage.getHomePageHeading());
    }
}
