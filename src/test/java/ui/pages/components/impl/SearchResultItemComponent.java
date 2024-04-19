package ui.pages.components.impl;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ui.pages.components.WebComponent;

import java.util.List;

public class SearchResultItemComponent extends WebComponent {

    private static final By TITLE_SELECTOR = By.xpath(".//h3//a");
    private static final By DESCRIPTION_SELECTOR = By.xpath("//*[@class='Box-sc-g0xbh4-0 LjnbQ']");

    public SearchResultItemComponent(WebElement rootElement) {
        super(rootElement);
    }

    public boolean containsSearchPhrase(String searchPhrase) {
        return containsTextIgnoringCase(searchPhrase, TITLE_SELECTOR)
                || containsTextIgnoringCase(searchPhrase, DESCRIPTION_SELECTOR);
    }

    private boolean containsTextIgnoringCase(String searchPhrase, By selector) {
        return findElement(selector).getText().toLowerCase().contains(searchPhrase);
    }

    public String getName() {
        return findElement(TITLE_SELECTOR).getText();
    }



}
