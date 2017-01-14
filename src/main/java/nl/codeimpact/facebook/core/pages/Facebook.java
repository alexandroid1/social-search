package nl.codeimpact.facebook.core.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static nl.codeimpact.facebook.core.conversion.TxtFileListTransfer.fileToList;
import static nl.codeimpact.facebook.core.conversion.TxtFileListTransfer.listToFile;
import static nl.codeimpact.facebook.core.settings.PropLoader.getProperties;
import static nl.codeimpact.facebook.core.settings.TimeSetter.waiteOneSec;

public class Facebook {

    WebDriver driver;

    public Facebook(WebDriver webdriver) {
        this.driver = webdriver;
    }

    public void login() {

        Properties prop = getProperties("fb");

        loginToFB(prop, driver);

        driver.get(prop.getProperty("searchBaseURL")+prop.getProperty("searchKeyWord"));

        while(true){
            JavascriptExecutor jse = (JavascriptExecutor)driver;
            jse.executeScript("window.scrollBy(0,250)", "");
            waiteOneSec();

            java.util.List<WebElement> links = driver.findElements(By.xpath("//a[contains(@href,'ref=SEARCH&fref=nf')]"));

            ArrayList<String> getProfileIds = new ArrayList<String>();

            for (int i = 0; i < links.size(); i++) {
                try {
                    String profileUrl = links.get(i).getAttribute("href");
                    getProfileIds.add(profileUrl);
                } catch (Exception e) {
                    System.out.print("-");
                }
            }

            listToFile(getProfileIds, prop.getProperty("appliedFilePath"));
        }
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
