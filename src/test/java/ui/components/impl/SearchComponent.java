package ui.components.impl;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import ui.components.WebComponent;

public class SearchComponent extends WebComponent {
    public SearchComponent(WebElement rootElement) {
        super(rootElement);
    }


    public void performSearch(String searchPhase) {

        sendKeys(searchPhase);
        sendKeys(Keys.ENTER);

    }

}
