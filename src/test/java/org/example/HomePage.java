package org.example;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import org.openqa.selenium.By;


import java.time.Duration;

public class HomePage {
    private WebDriver driver ;
    private WebDriverWait wait;

    /*
    @FindBy (xpath="//input[@placeholder='Search Merchant / Store Name']")
    private WebElement inputSearch ;
    @FindBy (css = ".gecko-search-input.search-text-box ")
    private WebElement inputSearch ;
     */
    @FindBy (id = "ctl00_ctl22_Search_SiteSearchText")
    private WebElement inputSearch ;
    @FindBy(id = "ctl00_GeckoTwoColPrimary_SearchResultsContent")
    private WebElement searchResultItems;
    @FindBy (xpath="//input[@title='Search']")
    private WebElement searchIcon;
    public HomePage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }
    public void setInputSearch(String product){
        wait.until(ExpectedConditions.visibilityOf(inputSearch));
        inputSearch.clear();
        inputSearch.sendKeys(product);
        inputSearch.sendKeys(Keys.ENTER);
    }
    // Get search result items
    public List<WebElement> getSearchResultItems() {
        return driver.findElements(By.cssSelector("div#ctl00_GeckoTwoColPrimary_SearchResultsContentPanel a.search-panel"));

    }
    public void clickSearchResultItem(int index) {
        List<WebElement> resultItems = getSearchResultItems();

        for(WebElement resultItem : resultItems) {
            WebElement merchantNameElement = resultItem.findElement(By.className("search-merchant-name"));
            String merchantName = merchantNameElement.getText();
            System.out.println(merchantName);
        }
        if (index >= 0 && index < resultItems.size()) {
            resultItems.get(index).click();
        } else {
            throw new IllegalArgumentException("Invalid search result item index");
        }
    }

    public void pressSearch(){
        //wait.until(ExpectedConditions.elementToBeClickable(searchIcon));
        searchIcon.submit();
    }
}
