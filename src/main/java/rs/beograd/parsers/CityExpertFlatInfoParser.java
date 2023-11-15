package rs.beograd.parsers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import rs.beograd.dto.FlatInfo;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class CityExpertFlatInfoParser implements FlatInfoParser {
    public static final String BASE_URL = "https://cityexpert.rs/en";
    public static final String URL = BASE_URL + "/properties-for-rent/belgrade?ptId=1,5&minPrice=500&maxPrice=850&polygonsArray=Novi%20Beograd,Blok%2067%20(Belville),Blok%2065,West%2065,Delta%20City,Beograd%20na%20vodi,Op%C5%A1tina%20Novi%20Beograd,Blok%2067a%20(A%20Blok)";

    private final WebDriver driver;

    public CityExpertFlatInfoParser() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors");
        driver = new ChromeDriver(options);
    }

    public Set<FlatInfo> getFlats() {
        try {
            return getFlatsInternalJs();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return Collections.emptySet();
    }

    private Set<FlatInfo> getFlatsInternalJs() throws IOException,InterruptedException {
        Set<FlatInfo> flats = new HashSet<>();

        driver.get(URL);
        Thread.sleep(10000);

        driver.findElements(By.tagName("app-property-card")).forEach(propertyCard -> {
            String id = propertyCard.findElement(By.className("property-card__header-info")).getText();
            String url = propertyCard.findElement(By.tagName("a")).getAttribute("href");
            String price = propertyCard.findElement(By.className("property-card__price-value")).getText();
            String publishDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            flats.add(new FlatInfo(id, url, price, publishDate));
        });

        return flats;
    }
}
