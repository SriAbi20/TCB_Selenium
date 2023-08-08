package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ItemPage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(css = "h2.merchant-offers-table span.gecko-merchant-heading")
    private WebElement merchantHeading;

    public ItemPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }
    public String getMerchantHeading(){
        System.out.print(merchantHeading.getText());
        return merchantHeading.getText();
    }
}
