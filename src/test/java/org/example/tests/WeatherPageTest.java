package org.example.tests;

import org.example.base.BasePage;
import org.example.base.BaseTest;
import org.example.util.FileUtil;
import org.example.util.FileSystemUtil;
import org.example.pages.WeatherPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class WeatherPageTest extends BaseTest {

    private final WeatherPage weatherPage = new WeatherPage(basePage.getPage());
    private static final String DOWNLOAD_DIR = System.getProperty("user.home") + "\\Downloads\\WeatherTest";
    private static final String EXPECTED_FILE_NAME = "forecast.json";
    private static final String FILE_PATH_OF_FILE_TO_UPLOAD = "src/test/resources/validForecastToUpload.json";
    private static final String TD_ELEMENT_SELECTOR = "xpath=//td[text()='";
    private static final String EXPECTED_TD_VALUE = "-16";

    @BeforeEach
    public void weatherPageSetUp() {
        try {
            Files.createDirectories(Paths.get(DOWNLOAD_DIR));
        } catch (Exception e) {
            fail("Failed to create download directory: " + e.getMessage());
        }
        BasePage.navigateToUrlAndWaitForBlazor(WeatherPage.WEATHER_PAGE_URL);
    }

    @AfterEach
    void weatherPageCleanUp() {
        FileSystemUtil.clearDirectory(DOWNLOAD_DIR);
        try {
            Files.deleteIfExists(Paths.get(DOWNLOAD_DIR));
        } catch (Exception e) {
            fail("Failed to delete download directory: " + e.getMessage());
        }
    }

    @Test
    public void navigateToWeatherPage_OpensCorrectUrl() {
        assertEquals(WeatherPage.WEATHER_PAGE_URL, weatherPage.getWeatherPageUrl());
    }

    @Test
    public void weatherPageTitle_IsCorrect() {
        assertEquals(WeatherPage.WEATHER_PAGE_TITLE_TEXT, weatherPage.getWeatherPageTitle());
    }

    @Test
    public void weatherPageHeading_IsCorrect() {
        assertEquals(WeatherPage.WEATHER_PAGE_HEADING_TEXT, weatherPage.getWeatherPageHeading());
    }

    @Test
    public void clickDownloadForecastDataButton_DownloadsValidFile() {
        initiateDownload();
        verifyDownloadedFile();
    }

    @Test
    public void clickDownloadForecastDataButton_DownloadsValidFileAndContent() {
        initiateDownload();
        verifyDownloadedFile();
        validateDownloadedFileContent();
    }

    @Test
    public void clickChooseFileButton_UploadsValidFileSuccessfully() {
        weatherPage.clickChooseFileButtonAndUploadFile(FILE_PATH_OF_FILE_TO_UPLOAD);
        String tdValue = page.locator(TD_ELEMENT_SELECTOR + EXPECTED_TD_VALUE + "']").textContent();
        assertEquals(EXPECTED_TD_VALUE, tdValue, "The <td> element does not contain the expected value '" +
                EXPECTED_TD_VALUE + "'.");
    }

    private void initiateDownload() {
        weatherPage.clickDownloadForecastDataButton(DOWNLOAD_DIR);
        assertTrue(FileSystemUtil.waitForFileDownload(DOWNLOAD_DIR, EXPECTED_FILE_NAME, 30),
                "The expected file was not downloaded within the timeout period.");
    }

    private void verifyDownloadedFile() {
        Set<String> newFiles = FileSystemUtil.getCurrentFilesInDirectory(DOWNLOAD_DIR);
        assertTrue(newFiles.contains(EXPECTED_FILE_NAME), "The expected file was not found in the new files.");
    }

    private void validateDownloadedFileContent() {
        String content = FileUtil.readFileContent(Paths.get(DOWNLOAD_DIR, EXPECTED_FILE_NAME).toString());
        assertFalse(content.isEmpty(), "The downloaded JSON file is empty.");
        assertTrue(FileUtil.validateJsonContent(content),
                "The downloaded JSON file is corrupt or not a valid JSON.");
    }
}
