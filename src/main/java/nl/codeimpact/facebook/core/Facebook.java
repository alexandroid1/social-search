package nl.codeimpact.facebook.core;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static nl.codeimpact.facebook.settings.TimeSetter.waiteOneSec;

public class Facebook {

    private static WebDriver driver;
    private Properties prop;
    private String loginStr;
    private String passwordStr;

    public Facebook(WebDriver webdriver,  String loginStr, String passwordStr, Properties prop) {
        this.driver = webdriver;
        this.loginStr = loginStr;
        this.passwordStr = passwordStr;
        this.prop = prop;
    }

    public void login() {
        driver.manage().window().maximize();
        driver.get(prop.getProperty("searchURL"));

        WebElement emailInput = driver.findElement(By.name("email"));
        emailInput.sendKeys(loginStr);

        WebElement passwordInput = driver.findElement(By.name("pass"));
        passwordInput.sendKeys(passwordStr);

        emailInput.submit();

        driver.get(prop.getProperty("searchBaseURL")+prop.getProperty("searchKeyWord"));
    }

    public ArrayList<String> search() {
        ArrayList<String> getProfileIds = new ArrayList<String>();
        try {
            getProfileIds.add("");
            while (true) {
                JavascriptExecutor jse = (JavascriptExecutor) driver;
                jse.executeScript("window.scrollBy(0,250)", "");
                waiteOneSec();

                List<WebElement> links = driver.findElements(By.xpath("//a[contains(@href,'ref=SEARCH&fref=nf')]"));

                for (int i = 0; i < links.size(); i++) {
                    try {
                        String profileUrl = links.get(i).getAttribute("href");
                        getProfileIds.add(profileUrl);
                    } catch (Exception e) {
                        System.out.print("-");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getProfileIds;
    }
}
