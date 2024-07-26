package org.example.pages;

import com.microsoft.playwright.Download;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitUntilState;

import java.nio.file.Paths;

public class WeatherPage {
    private static final String WEATHER_PAGE_URL = "http://localhost:8080/weather";
    private final Page page;

    public WeatherPage(Page page) {
        this.page = page;
    }

    public void navigateToWeatherPage() {
        page.navigate(WEATHER_PAGE_URL, new Page.NavigateOptions().setWaitUntil(WaitUntilState.NETWORKIDLE));
    }

    public String getWeatherPageUrl() {
        return WEATHER_PAGE_URL;
    }

    public String getWeatherPageTitle() {
        return page.title();
    }

    public String getWeatherPageHeading() {
        return page.textContent("h1");
    }

    public void clickDownloadForecastDataButton(String savePath) {
        Download download = page.waitForDownload(() -> {
            page.click("text='Download Forecast Data'");
        });
        download.saveAs(Paths.get(savePath, download.suggestedFilename()));
    }

    public void clickChooseFileButtonAndUploadFile(String filePath) {
        page.locator("xpath=/html/body/div[1]/main/article/input").click();

        page.setInputFiles("xpath=/html/body/div[1]/main/article/input", Paths.get(filePath));
    }
}
