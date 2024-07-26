package org.example.pages;

import com.microsoft.playwright.Download;
import com.microsoft.playwright.Page;

import java.nio.file.Paths;

public class WeatherPage {
    private final Page page;
    public static final String WEATHER_PAGE_URL = "http://localhost:8080/weather";
    public static final String WEATHER_PAGE_TITLE_TEXT = "Weather";
    public static final String WEATHER_PAGE_HEADING_TEXT = "Weather";
    public static final String WEATHER_PAGE_HEADING_SELECTOR = "h1";
    public static final String DOWNLOAD_FORECAST_DATA_BUTTON_SELECTOR = "text='Download Forecast Data'";
    public static final String CHOOSE_FILE_BUTTON_SELECTOR = "xpath=/html/body/div[1]/main/article/input";

    public WeatherPage(Page page) {
        this.page = page;
    }

    public String getWeatherPageUrl() {
        return page.url();
    }

    public String getWeatherPageTitle() {
        return page.title();
    }

    public String getWeatherPageHeading() {
        return page.textContent(WEATHER_PAGE_HEADING_SELECTOR);
    }

    public void clickDownloadForecastDataButton(String savePath) {
        Download download = page.waitForDownload(() -> {
            page.click(DOWNLOAD_FORECAST_DATA_BUTTON_SELECTOR);
        });
        download.saveAs(Paths.get(savePath, download.suggestedFilename()));
    }

    public void clickChooseFileButtonAndUploadFile(String filePath) {
        // Clicking the button leaves the file dialog open if not running in headless mode - does not impact tests
        page.locator(CHOOSE_FILE_BUTTON_SELECTOR).click();
        page.setInputFiles(CHOOSE_FILE_BUTTON_SELECTOR, Paths.get(filePath));
    }
}
