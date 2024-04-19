package ui.pages.impl;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import ui.pages.WebPage;
import ui.pages.components.WebComponent;
import ui.pages.components.impl.SearchResultItemComponent;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class SearchResultsPage extends WebPage {

//    @FindBy(xpath = "//div[@class='Box-sc-g0xbh4-0 bDcVHV']//h3")
//    private List<WebElement> searchResultItems;

    private static final By SEARCH_RESULTS_ITEM_SELECTOR = By.xpath("//div[@class='Box-sc-g0xbh4-0 bDcVHV']");

    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    public List<String> searchResultsItemsText() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return searchResultItems().stream()
                .map(element -> element.getText().toLowerCase())
                .collect(Collectors.toList());
    }

    public List<String> searchResultsItemsWithText(String searchPhase) {
        return searchResultItems().stream()
                .filter(item -> item.containsSearchPhrase(searchPhase))
                .map(x->x.getText().toLowerCase())
                .collect(Collectors.toList());
    }

    private List<SearchResultItemComponent> searchResultItems() {
        return findElements(SEARCH_RESULTS_ITEM_SELECTOR).stream()
                .map(SearchResultItemComponent::new)
                .collect(Collectors.toList());
    }

    public List<String> getListOfCardNames() {
        return searchResultItems().stream()
                .map(SearchResultItemComponent::getName)
                .collect(Collectors.toList());

    }

}
