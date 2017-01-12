package nl.codeimpact.facebook.core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Facebook {

    WebDriver driver;

    public Facebook(WebDriver webdriver) {
        this.driver = webdriver;
    }

    public void login(String email, String password) {
        driver.get("https://www.facebook.com");

        WebElement emailInput = driver.findElement(By.name("email"));
        emailInput.sendKeys(email);

        WebElement passwordInput = driver.findElement(By.name("pass"));
        passwordInput.sendKeys(password);

        emailInput.submit();
    }
}
