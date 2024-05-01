package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class WebPage {

    protected WebDriver driver;

    protected WebPage(WebDriver driver) {
        // we changed logic from page factory to driver initialization
//        PageFactory.initElements(driver, this);

        this.driver = driver;
    }

    protected WebElement findElement(By bySelector) {
        return driver.findElement(bySelector);
    }

    protected List<WebElement> findElements(By bySelector) {
        return driver.findElements(bySelector);
    }

}
