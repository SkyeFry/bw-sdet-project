package org.example.base;

import com.microsoft.playwright.*;
import io.github.cdimascio.dotenv.Dotenv;

public class BasePage {
    private Playwright playwright;
    private Browser browser;
    private BrowserContext context;
    private Page page;

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
}
