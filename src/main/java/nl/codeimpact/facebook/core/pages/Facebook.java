package nl.codeimpact.facebook.core.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static nl.codeimpact.facebook.core.conversion.TxtFileListTransfer.fileToList;
import static nl.codeimpact.facebook.core.settings.PropLoader.getProperties;

public class Facebook {

    WebDriver driver;

    public Facebook(WebDriver webdriver) {
        this.driver = webdriver;
    }

    public void login() {

        Properties prop = getProperties("fb");

        driver.get(prop.getProperty("searchURL"));

        loginToFB(prop, driver);

        driver.get(prop.getProperty("searchBaseURL")+prop.getProperty("searchKeyWord"));
    }

    protected static void loginToFB(Properties prop, WebDriver driver) {

        List<String> passwordList = new ArrayList<>();
        fileToList(passwordList, prop.getProperty("loginpassfile"));

        String loginStr = passwordList.get(0);
        String passwordStr = passwordList.get(1);

        driver.manage().window().maximize();

        driver.get(prop.getProperty("searchURL"));

        WebElement emailInput = driver.findElement(By.name("email"));
        emailInput.sendKeys(loginStr);

        WebElement passwordInput = driver.findElement(By.name("pass"));
        passwordInput.sendKeys(passwordStr);

        emailInput.submit();
    }
}
