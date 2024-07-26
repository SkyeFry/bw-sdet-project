package org.example.tests;

import org.example.base.BasePage;
import org.example.base.BaseTest;
import org.example.util.FileUtil;
import org.example.util.FileSystemUtil;
import org.example.pages.WeatherPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class WeatherPageTest extends BaseTest {

    private final WeatherPage weatherPage = new WeatherPage(basePage.getPage());
    private static final String DOWNLOAD_PATH = System.getProperty("user.home") + "\\Downloads";
    private static final String EXPECTED_FILE_NAME = "forecast.json";
    private static final String WEATHER_PAGE_URL = "http://localhost:8080/weather";
    private static final String WEATHER_PAGE_TITLE = "Weather";
    private static final String WEATHER_PAGE_HEADING = "Weather";
    private static final String FILE_PATH = "src/test/resources/validForecastToUpload.json";
    private static final String EXPECTED_TD_VALUE = "-16";

    @BeforeEach
    public void weatherPageSetUp() {
        BasePage.navigateToUrlAndWaitForBlazor(WEATHER_PAGE_URL);
        FileSystemUtil.clearDirectory(DOWNLOAD_PATH);
    }

    @AfterEach
    void weatherPageCleanUp() {
        FileSystemUtil.clearDirectory(DOWNLOAD_PATH);
    }

    @Test
    public void navigateToWeatherPage_OpensCorrectUrl() {
        assertEquals(WEATHER_PAGE_URL, weatherPage.getWeatherPageUrl());
    }

    @Test
    public void weatherPageTitle_IsCorrect() {
        assertEquals(WEATHER_PAGE_TITLE, weatherPage.getWeatherPageTitle());
    }

    @Test
    public void weatherPageHeading_IsCorrect() {
        assertEquals(WEATHER_PAGE_HEADING, weatherPage.getWeatherPageHeading());
    }

    @Test
    public void downloadForecastDataButton_OnClick_DownloadsValidFile() {
        initiateDownload();
        verifyDownloadedFile();
    }

    @Test
    public void downloadForecastDataButton_OnClick_DownloadsValidFileContent() {
        initiateDownload();
        verifyDownloadedFile();
        validateDownloadedFileContent();
    }

    @Test
    public void chooseFileButton_OnClick_UploadsValidFileSuccessfully() {
        weatherPage.clickChooseFileButtonAndUploadFile(FILE_PATH);
        String tdValue = page.locator("xpath=//td[text()='" + EXPECTED_TD_VALUE + "']").textContent();
        assertEquals(EXPECTED_TD_VALUE, tdValue, "The <td> element does not contain the expected value '" +
                EXPECTED_TD_VALUE + "'.");
    }

    private void initiateDownload() {
        weatherPage.clickDownloadForecastDataButton(DOWNLOAD_PATH);
        assertTrue(FileSystemUtil.waitForFileDownload(DOWNLOAD_PATH, EXPECTED_FILE_NAME, 30),
                "The expected file was not downloaded within the timeout period.");
    }

    private void verifyDownloadedFile() {
        Set<String> newFiles = FileSystemUtil.getCurrentFilesInDirectory(DOWNLOAD_PATH);
        assertTrue(newFiles.contains(EXPECTED_FILE_NAME), "The expected file was not found in the new files.");
    }

    private void validateDownloadedFileContent() {
        String content = FileUtil.readFileContent(Paths.get(DOWNLOAD_PATH, EXPECTED_FILE_NAME).toString());
        assertFalse(content.isEmpty(), "The downloaded JSON file is empty.");
        assertTrue(FileUtil.validateJsonContent(content),
                "The downloaded JSON file is corrupt or not a valid JSON.");
    }
}
