package ui;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.junit5.AllureJunit5;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ui.pages.TestListener;
import ui.pages.impl.HomePage;
import ui.pages.impl.SearchResultsPage;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

@ExtendWith({TestListener.class, AllureJunit5.class})
public class GitHubSearchTest {


    private static final String SEARCH_PHASE = "selenium";

    private static WebDriver driver;
    private static WebDriverWait wait;

    @BeforeAll
    public static void setUpDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @BeforeAll
    public static void setUpWait() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));

    }

    private static void switchOffImplicitWait() {
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    }


    @Test
    @Description("Verify github searching logic")
    public void checkGitHubSearch() {


        driver.get("https://github.com");

        HomePage homePage = new HomePage(driver);
        SearchResultsPage searchResultsPage = new SearchResultsPage(driver);

        homePage.searchComponent().performSearch(SEARCH_PHASE);
//        searchResultsPage.getListOfCardNames();

//        List<String> actualItems = searchResultsPage.searchResultsItemsText();
        List<String> actualItems = List.of("something");
        List<String> expectedItems = searchResultsPage.searchResultsItemsWithText(SEARCH_PHASE);
        Assertions.assertEquals(expectedItems, actualItems);


//        WebElement searchInput = driver.findElement(By.className("search-input"));
//        WebElement searchInputExtended = driver.findElement(By.id("query-builder-test"));
//
//        String searchPhrase = "selenium";
//        searchInput.click();
//        searchInputExtended.sendKeys(searchPhrase);
//        searchInputExtended.sendKeys(Keys.ENTER);
//
////        WebElement conditionElement = driver.findElement(By.xpath("//div[@class='Box-sc-g0xbh4-0 kzrAHr']"));
////        wait = new WebDriverWait(driver, Duration.ofSeconds(1));
//        wait.until(d -> driver.findElement(By.xpath("//div[@class='Box-sc-g0xbh4-0 kzrAHr']")).isDisplayed());
//        wait.withTimeout(Duration.ofSeconds(3));
//        List<String> actualItems = driver
//                .findElements(By.xpath("//div[@class='Box-sc-g0xbh4-0 bDcVHV']//h3"))
//                .stream().map(element -> element.getText().toLowerCase())
//                .collect(Collectors.toList());
//        System.out.println(actualItems);
////        Assertions.assertTrue(actualItems.stream().allMatch(item -> item.contains(searchPhrase)));
//
//        List<String> expectedItems = actualItems.stream()
//                .filter(item -> item.contains(searchPhrase))
//                .collect(Collectors.toList());
//
//        System.out.println(LocalDateTime.now());
////        switchOffImplicitWait();
////        wait.until(ExpectedConditions.visibilityOfElementLocated((By.cssSelector("[title='invalid title']"))));
////        Assertions.assertTrue(driver.findElement(By.cssSelector("[title='invalid title']")).isDisplayed());
//        Assertions.assertEquals(expectedItems, actualItems);

    }

    @AfterAll
    public static void tearDownDriver() {
        System.out.println(LocalDateTime.now());
        driver.quit();
    }

}
