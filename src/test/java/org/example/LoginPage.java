package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy (id= "txtEmail")
    private WebElement inputEmail ;

    @FindBy (xpath ="//input[@type='password']")
    private WebElement inputPassword ;

    @FindBy (xpath ="//button[@type='submit']")
    private WebElement loginBtn ;

    public LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }
    public void setInputEmail (String email){
        wait.until(ExpectedConditions.visibilityOf(inputEmail)) ;
        inputEmail.sendKeys(email);
    }
    public void setInputPassword (String password) {
        wait.until(ExpectedConditions.visibilityOf(inputPassword));
        inputPassword.sendKeys(password);
    }
    public void clickLogin(){
        wait.until(ExpectedConditions.elementToBeClickable(loginBtn));
        loginBtn.click();
    }

}
