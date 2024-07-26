package org.example.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class AboutPage {
    private final Page page;
    private static final String ABOUT_LINK_TEXT = "About";
    public static final String ABOUT_PAGE_URL = "https://learn.microsoft.com/en-us/aspnet/core/?view=aspnetcore-8.0";

    public AboutPage(Page page) {
        this.page = page;
    }

    public Page clickAboutLink() {
        try (Page newTab = page.waitForPopup(() -> {
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(ABOUT_LINK_TEXT)).click();
        })) {
            return newTab;
        }
    }
}
