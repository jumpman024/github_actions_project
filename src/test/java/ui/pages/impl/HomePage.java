package ui.pages.impl;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ui.components.impl.SearchComponent;
import ui.pages.WebPage;

public class HomePage extends WebPage {

    @FindBy(className = "search-input")
    private WebElement searchInput;
//
//    @FindBy(id = "query-builder-test")
//    private WebElement searchInputExtended;


    private static final By SEARCH_COMPONENT_SELECTOR = By.id("query-builder-test");

//    private static final By SEARCH_COMPONENT_SELECTOR = By.cssSelector("[name='q']");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public SearchComponent searchComponent() {
                driver.findElement(By.className("search-input")).click();
        return new SearchComponent(findElement(SEARCH_COMPONENT_SELECTOR));
    }

//    public void performSearch(String searchPhase) {
//        searchInput.click();
//        searchInputExtended.sendKeys(searchPhase);
//        searchInputExtended.sendKeys(Keys.ENTER);
//    }

}
