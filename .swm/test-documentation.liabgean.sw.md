---
title: Test Documentation
---
<SwmSnippet path="/src/main/java/org/example/base/BasePage.java" line="23">

---

This is initializing the different types of browsers along with other nifty things

```java
    protected void initializeBrowser(String browserName, boolean isHeadless) {
        playwright = Playwright.create();

        BrowserType browserType;
        switch (browserName.toLowerCase()) {
            case "chrome":
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
```

---

</SwmSnippet>

&nbsp;

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBYnctc2RldC1wcm9qZWN0JTNBJTNBU2t5ZUZyeQ==" repo-name="bw-sdet-project"><sup>Powered by [Swimm](https://app.swimm.io/)</sup></SwmMeta>
