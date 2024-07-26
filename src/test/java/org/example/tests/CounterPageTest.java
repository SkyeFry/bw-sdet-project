package org.example.tests;

import org.example.base.BasePage;
import org.example.base.BaseTest;
import org.example.pages.CounterPage;
import org.example.pages.NavSidebarPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CounterPageTest extends BaseTest {

    private final CounterPage counterPage = new CounterPage(basePage.getPage());
    private static final String COUNTER_PAGE_URL = "http://localhost:8080/counter";
    private static final String COUNTER_PAGE_TITLE = "Counter";
    private static final String COUNTER_PAGE_HEADING = "Counter";

    @BeforeEach
    public void counterPageSetUp() {
        BasePage.navigateToUrlAndWaitForBlazor(COUNTER_PAGE_URL);
    }

    @Test
    public void navigateToCounterPage_OpensCorrectUrl() {
        assertEquals(COUNTER_PAGE_URL, counterPage.getCounterPageUrl());
    }

    @Test
    public void counterPageTitle_IsCorrect() {
        assertEquals(COUNTER_PAGE_TITLE, counterPage.getCounterPageTitle());
    }

    @Test
    public void counterPageHeading_IsCorrect() {
        assertEquals(COUNTER_PAGE_HEADING, counterPage.getCounterPageHeading());
    }

    @Test
    public void counterButton_Displays1_WhenClicked1Time() {
        counterPage.clickCounterButton();
        assertEquals("1", counterPage.getCounterValue());
    }

    @Test
    public void counterButton_Displays100_WhenClicked100Times() {
        IntStream.range(0, 100).forEach(i -> counterPage.clickCounterButtonWithDelay(10));
        assertEquals("100", counterPage.getCounterValue());
    }

    @Test
    public void counter_Displays0_WhenPageIsReloaded() {
        counterPage.clickCounterButton();
        assertEquals("1", counterPage.getCounterValue());
        page.reload();
        assertEquals("0", counterPage.getCounterValue());
    }

    @Test
    public void counter_Displays0_AfterNavigationAwayAndBack() {
        counterPage.clickCounterButton();
        assertEquals("1", counterPage.getCounterValue());

        NavSidebarPage navSidebarPage = new NavSidebarPage(page);
        navSidebarPage.clickHomeSidebarLink();

        navSidebarPage.clickCounterSidebarLink();
        assertEquals("0", counterPage.getCounterValue());
    }
}
