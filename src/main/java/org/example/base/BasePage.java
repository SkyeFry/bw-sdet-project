package org.example.base;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.WaitUntilState;
import io.github.cdimascio.dotenv.Dotenv;

import java.util.concurrent.CompletableFuture;

public class BasePage {
    private Playwright playwright;
    private Browser browser;
    private static BrowserContext context;
    private static Page page;

    public BasePage() {
        Dotenv dotenv = Dotenv.load();
        String browserName = dotenv.get("BROWSER_NAME", "chrome");
        boolean isHeadless = Boolean.parseBoolean(dotenv.get("HEADLESS", String.valueOf(true)));

        initializeBrowser(browserName, isHeadless);
    }

    protected void initializeBrowser(String browserName, boolean isHeadless) {
        playwright = Playwright.create();

        BrowserType browserType;
        switch (browserName.toLowerCase()) {
            case "chrome":
            case "edge":
                browserType = playwright.chromium();
                break;
            case "firefox":
                browserType = playwright.firefox();
                break;
            case "webkit":
                browserType = playwright.webkit();
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browserName);
        }

        browser = browserType.launch(new BrowserType.LaunchOptions().setChannel(browserName).setHeadless(isHeadless));
        context = browser.newContext(new Browser.NewContextOptions().setIgnoreHTTPSErrors(true)
                .setViewportSize(1920, 1080));
        page = context.newPage();
    }

    public Playwright getPlaywright() {
        return playwright;
    }

    public Browser getBrowser() {
        return browser;
    }

    public BrowserContext getContext() {
        return context;
    }

    public Page getPage() {
        return page;
    }

    public void closeBrowser() {
        if (browser != null) {
            browser.close();
        }
        if (playwright != null) {
            playwright.close();
        }
    }

    public static void navigateToUrlAndWaitForBlazor(String url) {
        try {
            page.navigate(url, new Page.NavigateOptions().setWaitUntil(WaitUntilState.NETWORKIDLE));
            page.waitForFunction("() => window.Blazor !== undefined;");
        } catch (PlaywrightException e) {
            System.err.println("Navigation to URL failed: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
            throw e;
        }
    }

    public static void navigateToUrlAndWaitForBlazor() {
        navigateToUrlAndWaitForBlazor("http://localhost:8080/");
    }

    public static Page navigateToNewTab(String url) {
        CompletableFuture<Page> newPageFuture = new CompletableFuture<>();
        context.onPage(newPage -> {
            newPage.navigate(url, new Page.NavigateOptions().setWaitUntil(WaitUntilState.NETWORKIDLE));
            newPageFuture.complete(newPage);
        });
        return newPageFuture.join();
    }
}
