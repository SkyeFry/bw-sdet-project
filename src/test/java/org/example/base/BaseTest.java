package org.example.base;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitUntilState;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {
    protected static BasePage basePage;
    protected static Page page;

    @BeforeAll
    public static void setUp() {
        basePage = new BasePage();
        page = basePage.getPage();
    }

    @AfterAll
    public static void tearDown() {
        basePage.closeBrowser();
    }
}
