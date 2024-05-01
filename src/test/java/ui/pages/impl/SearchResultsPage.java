package ui.pages.impl;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ui.components.impl.SearchResultItemComponent;
import ui.pages.WebPage;
import ui.entities.SearchResultItem;

import java.util.List;
import java.util.stream.Collectors;

public class SearchResultsPage extends WebPage {

//    @FindBy(xpath = "//div[@class='Box-sc-g0xbh4-0 bDcVHV']//h3")
//    private List<WebElement> searchResultItems;


    private static final By SEARCH_RESULTS_ITEM_SELECTOR = By.xpath("//div[@class='Box-sc-g0xbh4-0 bDcVHV']");

    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    public List<SearchResultItem> searchResultsItems() {
        return searchResultItemComponents().stream()
                .map(SearchResultItemComponent::convertToSearchResultItem)
                .collect(Collectors.toList());
    }

    public List<SearchResultItem> searchResultsItemsWithText(String searchPhrase) {
        return searchResultsItems().stream()
                .filter(item -> item.getTitle().contains(searchPhrase) || item.getDescription().contains(searchPhrase))
                .collect(Collectors.toList());
    }

    private List<SearchResultItemComponent> searchResultItemComponents() {
        return findElements(SEARCH_RESULTS_ITEM_SELECTOR).stream()
                .map(SearchResultItemComponent::new)
                .collect(Collectors.toList());
    }



}
