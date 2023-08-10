package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.example.utils.CommonUtils;



import java.time.Duration;


public class TCBTest {
    private Properties testData = new Properties();
    private WebDriver driver;
    private TopcashbackPage topcashbackPage;
    private LoginPage loginPage ;
    private HomePage homePage;
    private ItemPage itemPage;
    private CommonUtils commonUtils;

    private SummerTimeDealsPage summerDealPage;

    @BeforeEach
    public void setUp(){

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        topcashbackPage = new TopcashbackPage(driver);
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        itemPage = new ItemPage(driver);
        summerDealPage = new SummerTimeDealsPage(driver);
        commonUtils = new CommonUtils(driver);

        driver.get("https://www.topcashback.co.uk/");
        if(topcashbackPage.isAcceptCookiesButtonVisible()) {
            topcashbackPage.acceptAllCookies();
        }
        // Load test data from properties file using FileReader
        try (FileReader fileReader = new FileReader("C:\\Users\\sriab\\Learning Projects\\TCBSelenium\\src\\test\\testdata.properties")) {
            testData.load(fileReader);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @Test
    public void testsuccessfulSearchTCB(){
        String username = testData.getProperty("username");
        String password = testData.getProperty("password");
        String searchText = testData.getProperty("searchText");
        String logoutText = testData.getProperty("logoutmessage");

        topcashbackPage.clickSignIn();
        loginPage.setInputEmail(username);
        loginPage.setInputPassword(password);
        loginPage.clickLogin();
        homePage.setInputSearch(searchText);
        homePage.pressSearch();
        homePage.clickSearchResultItem(0);
        Assertions.assertEquals(searchText,itemPage.getMerchantHeading(),"Texts are equal");
        navigateBack(2);
        homePage.clickAccountIcon();
        homePage.clickSignOut();
        commonUtils.customWait(5);
        Assertions.assertEquals(logoutText,homePage.getLogoutMessageText(),"Texts are equal");
        commonUtils.customWait(10);
    }
    @Test
    public void invalidLogin(){
        String username = testData.getProperty("invalid_username");
        String password = testData.getProperty("invalid_password");
        String errorMessage = testData.getProperty("invalid_login_errText");

        topcashbackPage.clickSignIn();
        loginPage.setInputEmail(username);
        loginPage.setInputPassword(password);
        loginPage.clickLogin();
        Assertions.assertEquals(errorMessage,loginPage.getErrorMessageText(),"Texts are equal");
    }

    @Test
    public void testSummerTimeDeals(){
        String username = testData.getProperty("username");
        String password = testData.getProperty("password");
        String expectedUrl="https://www.topcashback.co.uk/hubs/summertime-hub/";
        String currentUrl;

        topcashbackPage.clickSignIn();
        loginPage.setInputEmail(username);
        loginPage.setInputPassword(password);
        loginPage.clickLogin();
        homePage.clickSummertimeDealsLink();
        currentUrl=driver.getCurrentUrl();
        Assertions.assertEquals(expectedUrl,currentUrl,"Urls are equal");
        summerDealPage.scrolltohealthandBeauty();
        summerDealPage.clickHealthAndBeautyElement(0);
        navigateBack(1);
        summerDealPage.scrolltoGettingStarted();
        commonUtils.customWait(5);

    }
    @AfterEach
    public void tearDown(){
        driver.quit();
    }

    private void navigateBack (int times){
        for(int i = times;i>0;i--){
            driver.navigate().back();
        }
    }
}
