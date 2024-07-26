package org.example.tests;

import com.microsoft.playwright.Page;
import org.example.base.BasePage;
import org.example.base.BaseTest;
import org.example.pages.AboutPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AboutPageTest extends BaseTest {
    private AboutPage aboutPage;

    @BeforeEach
    public void aboutPageSetUp() {
        aboutPage = new AboutPage(basePage.getPage());
        BasePage.navigateToUrlAndWaitForBlazor();
    }

    @Test
    public void navigateToAboutPage_OpensCorrectUrl() {
        Page newTab = aboutPage.clickAboutLink();
        assertNotNull(newTab, "The new tab should not be null");
        assertEquals(AboutPage.ABOUT_PAGE_URL, newTab.url(), "The URL does not match the expected value");
        newTab.close();
    }
}
