package ui;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class SeleniumRunner {

    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://github.com");


        WebElement searchInput = driver.findElement(By.className("search-input"));
        WebElement searchInputExtended = driver.findElement(By.id("query-builder-test"));

        String searchPhrase = "selenium";
        searchInput.click();
        searchInputExtended.sendKeys(searchPhrase);
        searchInputExtended.sendKeys(Keys.ENTER);

//        WebElement conditionElement = driver.findElement(By.xpath("//div[@class='Box-sc-g0xbh4-0 kzrAHr']"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
        wait.until(d -> driver.findElement(By.xpath("//div[@class='Box-sc-g0xbh4-0 kzrAHr']")).isDisplayed());
        wait.withTimeout(Duration.ofSeconds(3));
//        Thread.sleep(5000);
        List<String> actualItems = driver
                .findElements(By.xpath("//div[@class='Box-sc-g0xbh4-0 bDcVHV']//h3"))
                .stream().map(element -> element.getText().toLowerCase())
                .collect(Collectors.toList());
        System.out.println(actualItems);
//        Assertions.assertTrue(actualItems.stream().allMatch(item -> item.contains(searchPhrase)));

        List<String> expectedItems = actualItems.stream()
                        .filter(item -> item.contains(searchPhrase))
                                .collect(Collectors.toList());
        Assertions.assertEquals(expectedItems, actualItems);



        driver.quit();

    }
}
