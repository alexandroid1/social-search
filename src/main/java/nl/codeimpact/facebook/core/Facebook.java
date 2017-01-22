package nl.codeimpact.facebook.core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static nl.codeimpact.Application.prop;

public class Facebook {

    private static WebDriver driver;

    public Facebook(WebDriver webdriver) {
        this.driver = webdriver;
    }

    public void login(String loginStr, String passwordStr) {
        driver.manage().window().maximize();
        driver.get(prop.getProperty("searchURL"));

        WebElement emailInput = driver.findElement(By.name("email"));
        emailInput.sendKeys(loginStr);

        WebElement passwordInput = driver.findElement(By.name("pass"));
        passwordInput.sendKeys(passwordStr);

        emailInput.submit();
    }

    public WebDriver getDriver() {
        return this.driver;
    }
}
