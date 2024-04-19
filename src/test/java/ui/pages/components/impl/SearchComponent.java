package ui.pages.components.impl;

import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import ui.pages.components.WebComponent;

public class SearchComponent extends WebComponent {
    public SearchComponent(WebElement rootElement) {
        super(rootElement);
    }


    public void performSearch(String searchPhase) {

        sendKeys(searchPhase);
        sendKeys(Keys.ENTER);

    }

}
