package org.example;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;


import java.time.Duration;

public class TopcashbackPage {
    private WebDriver driver;
    private WebDriverWait wait;
    @FindBy (xpath ="//a[@class='loh-sign-in']")
    private WebElement  signInbtn;
    @FindBy(css = "div.onetrust-pc-dark-filter")
    private WebElement cookieOverlay;

    @FindBy(id ="onetrust-accept-btn-handler")
    private WebElement acceptCookieBtn;

    public TopcashbackPage (WebDriver driver){
        this.driver =driver;
        PageFactory.initElements(driver,this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }
    public boolean isAcceptCookiesButtonVisible()
    {
        try{
            wait.until(ExpectedConditions.visibilityOf(acceptCookieBtn));
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    /*public void acceptAllCookies(){
        wait.until(ExpectedConditions.visibilityOf(acceptCookieBtn));
        acceptCookieBtn.click();
    }*/
    public void acceptAllCookies() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(acceptCookieBtn));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", acceptCookieBtn);
            acceptCookieBtn.click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void clickSignIn(){
        //wait.until(ExpectedConditions.invisibilityOf(cookieOverlay));
        signInbtn.click();
    }

  }
