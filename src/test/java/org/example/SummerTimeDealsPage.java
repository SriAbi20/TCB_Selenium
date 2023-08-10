package org.example;

import org.example.utils.CommonUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

import java.time.Duration;
import java.util.List;

public class SummerTimeDealsPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;
    private CommonUtils commonUtils;

    @FindBy (xpath="//h2[@class='hub-section-title' and contains(text(), 'Health & Beauty')]")
    private WebElement healthandbeautyHeader;

    @FindBy (xpath="//h2[contains(text(), 'Health & Beauty')]/following::div[contains(@class, 'hub-offer-card')]//a")
    private List<WebElement> healthAndBeautyElements;;

    @FindBy (linkText = "Getting Started")
    private WebElement gettingStartedLink;

    public SummerTimeDealsPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
         js = (JavascriptExecutor) driver;
         commonUtils = new CommonUtils(driver);
    }
    public void scrolltohealthandBeauty() {
        commonUtils.scrollTo(healthandbeautyHeader);
    }
    public void clickHealthAndBeautyElement(int index) {
        // Check if the index is within the bounds of the list
        if (index >= 0 && index < healthAndBeautyElements.size()) {
            WebElement element = healthAndBeautyElements.get(index);
            element.click();
        } else {
            System.out.println("Invalid index provided.");
        }
    }

    public void scrolltoGettingStarted() {
        commonUtils.scrollTo(gettingStartedLink);
        gettingStartedLink.click();
    }
}
