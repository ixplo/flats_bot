package rs.beograd.service;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;

public class ChromeWebDriverService {

    public static final String CHROME_DRIVER_PATH = "C:/Drivers/chromedriver/chromedriver.exe";

    public WebDriver create() {
        WebDriverManager.chromedriver().setup();
        org.openqa.selenium.chrome.ChromeDriverService service = new org.openqa.selenium.chrome.ChromeDriverService.Builder()
                .withBuildCheckDisabled(true)
                .usingDriverExecutable(new File(CHROME_DRIVER_PATH))
                .build();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors");
        return new ChromeDriver(service, options);
    }
}
