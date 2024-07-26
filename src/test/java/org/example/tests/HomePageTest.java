package org.example.tests;

import org.example.base.BasePage;
import org.example.base.BaseTest;
import org.example.pages.HomePage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HomePageTest extends BaseTest {

    private final HomePage homePage = new HomePage(basePage.getPage());

    @BeforeEach
    public void homePageSetUp() {
        BasePage.navigateToUrlAndWaitForBlazor(HomePage.HOME_PAGE_URL);
    }

    @Test
    public void navigateToHomePage_OpensCorrectUrl() {
        assertEquals(HomePage.HOME_PAGE_URL, homePage.getHomePageUrl());
    }

    @Test
    public void homePageTitle_IsCorrect() {
        assertEquals(HomePage.HOME_PAGE_TITLE_TEXT, homePage.getHomePageTitle());
    }

    @Test
    public void homePageHeading_IsCorrect() {
        assertEquals(HomePage.HOME_PAGE_HEADING_TEXT, homePage.getHomePageHeading());
    }
}
