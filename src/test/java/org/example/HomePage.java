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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;

import java.time.Duration;

public class HomePage {
    private WebDriver driver ;
    private WebDriverWait wait;

    private Actions actions;
    private JavascriptExecutor jsExecutor;

    /*
    @FindBy (xpath="//input[@placeholder='Search Merchant / Store Name']")
    private WebElement inputSearch ;
    @FindBy (css = ".gecko-search-input.search-text-box ")
    private WebElement inputSearch ;
    @FindBy (id = "ctl00_ctl22_Search_SiteSearchText")
    private WebElement inputSearch ;
      @FindBy(id = "ctl00_GeckoTwoColPrimary_SearchResultsContent")
    private WebElement searchResultItems;
     @FindBy (className = "logout-header")
    private WebElement logoutMessage;
      @FindBy(xpath = "//li[@class='nav-link']//a//span")
    private WebElement summertimeDealsLink;
     */

    @FindBy(className = "gecko-search-input")
    private WebElement inputSearch;
    @FindBy(className = "search-panel")
    private WebElement searchResultItems;
    @FindBy (xpath="//input[@title='Search']")
    private WebElement searchIcon;
    @FindBy (id ="accountIcon")
    private WebElement accountIcon;

   @FindBy(xpath = "//h1[@class='logout-header']//span")
   private WebElement logoutMessage;

    @FindBy(linkText = "Sign Out")
    private WebElement signOutLink;

    @FindBy(linkText = "Summertime Deals Hub")
    private WebElement summertimeDealsLink;

    public HomePage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        // Create an instance of the Actions class
        actions = new Actions(driver);
        jsExecutor = (JavascriptExecutor) driver;
    }
    public void setInputSearch(String product) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // 10 seconds maximum wait time
            WebElement inputElement = wait.until(ExpectedConditions.visibilityOf(inputSearch));

            inputElement.clear();
            inputElement.sendKeys(product);
            inputElement.sendKeys(Keys.ENTER);
        } catch (org.openqa.selenium.StaleElementReferenceException e) {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // 10 seconds maximum wait time
            WebElement inputElement = wait.until(ExpectedConditions.visibilityOf(inputSearch));

            inputElement.clear();
            inputElement.sendKeys(product);
            inputElement.sendKeys(Keys.ENTER);
        }
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
    public void clickAccountIcon(){
        actions.moveToElement(accountIcon).perform();
    }
    public void clickSignOut(){
        signOutLink.click();
    }
    public String getLogoutMessageText(){
      return logoutMessage.getText();
    }
    public void clickSummertimeDealsLink() {
        try {
            WebElement refreshedElement = wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(summertimeDealsLink)));

            boolean isClickable = (boolean) jsExecutor.executeScript(
                    "return arguments[0].hasAttribute('href') && !arguments[0].hasAttribute('disabled');",
                    refreshedElement);

            if (isClickable) {
                jsExecutor.executeScript("arguments[0].click();", refreshedElement);
            } else {
                System.out.println("Summertime Deals link is not clickable.");
                // Handle the case where the link is not clickable.
            }
        } catch (Exception e) {
            System.out.println("Exception while clicking Summertime Deals link: " + e.getMessage());
            // Handle the exception if needed.
        }
    }


    /*public void clickSummertimeLink() {
        try {
            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
            jsExecutor.executeScript("arguments[0].click();", summertimeDealsLink);
        } catch (StaleElementReferenceException e) {
            //
        }
    }*/
}
