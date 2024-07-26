package org.example.tests.ui;

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


    @BeforeEach
    public void counterPageSetUp() {
        BasePage.navigateToUrlAndWaitForBlazor(CounterPage.COUNTER_PAGE_URL);
    }

    @Test
    public void navigateToCounterPage_OpensCorrectUrl() {
        assertEquals(CounterPage.COUNTER_PAGE_URL, counterPage.getCounterPageUrl());
    }

    @Test
    public void counterPageTitle_IsCorrect() {
        assertEquals(CounterPage.COUNTER_PAGE_TITLE_TEXT, counterPage.getCounterPageTitle());
    }

    @Test
    public void counterPageHeading_IsCorrect() {
        assertEquals(CounterPage.COUNTER_PAGE_HEADING_TEXT, counterPage.getCounterPageHeading());
    }

    @Test
    public void clickCounterButton_CounterDisplays1_WhenClicked1Time() {
        counterPage.clickCounterButton();
        assertEquals("1", counterPage.getCounterValue());
    }

    @Test
    public void clickCounterButton_CounterDisplays100_WhenClicked100Times() {
        IntStream.range(0, 100).forEach(i -> counterPage.clickCounterButton(10));
        assertEquals("100", counterPage.getCounterValue());
    }

    @Test
    public void counter_CounterDisplays0_WhenPageIsReloaded() {
        counterPage.clickCounterButton();
        assertEquals("1", counterPage.getCounterValue());
        page.reload();
        assertEquals("0", counterPage.getCounterValue());
    }

    @Test
    public void counter_CounterDisplays0_AfterNavAwayAndBack() {
        counterPage.clickCounterButton();
        assertEquals("1", counterPage.getCounterValue());

        NavSidebarPage navSidebarPage = new NavSidebarPage(page);
        navSidebarPage.clickHomeSidebarLink();

        navSidebarPage.clickCounterSidebarLink();
        assertEquals("0", counterPage.getCounterValue());
    }
}
