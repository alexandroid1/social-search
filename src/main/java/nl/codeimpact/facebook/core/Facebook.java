package nl.codeimpact.facebook.core;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static nl.codeimpact.facebook.conversion.TxtFileListTransfer.fileToList;
import static nl.codeimpact.facebook.conversion.TxtFileListTransfer.listToFile;
import static nl.codeimpact.facebook.settings.PropLoader.getProperties;
import static nl.codeimpact.facebook.settings.TimeSetter.waiteOneSec;

public class Facebook {

    private WebDriver driver;
    private Properties prop;
    private List<String> passwordList;
    private String loginStr;
    private String passwordStr;
    private String searchUrlStr;
    private String resultFilePathStr;

    public Facebook(WebDriver webdriver) {
        this.driver = webdriver;
    }

    public void init() {
        prop = getProperties("fb");
        passwordList = new ArrayList<>();
        fileToList(passwordList, prop.getProperty("loginpassfile"));
        loginStr = passwordList.get(0);
        passwordStr = passwordList.get(1);
        searchUrlStr = prop.getProperty("searchURL");
        resultFilePathStr = prop.getProperty("appliedFilePath");
    }

    public void doLogin() {
        driver.manage().window().maximize();
        driver.get(searchUrlStr);

        WebElement emailInput = driver.findElement(By.name("email"));
        emailInput.sendKeys(loginStr);

        WebElement passwordInput = driver.findElement(By.name("pass"));
        passwordInput.sendKeys(passwordStr);

        emailInput.submit();

        driver.get(prop.getProperty("searchBaseURL")+prop.getProperty("searchKeyWord"));
    }

    public void doSearch() {
        while(true){
            JavascriptExecutor jse = (JavascriptExecutor)driver;
            jse.executeScript("window.scrollBy(0,250)", "");
            waiteOneSec();

            List<WebElement> links = driver.findElements(By.xpath("//a[contains(@href,'ref=SEARCH&fref=nf')]"));

            ArrayList<String> getProfileIds = new ArrayList<>();

            for (int i = 0; i < links.size(); i++) {
                try {
                    String profileUrl = links.get(i).getAttribute("href");
                    getProfileIds.add(profileUrl);
                } catch (Exception e) {
                    System.out.print("-");
                }
            }
            listToFile(getProfileIds, resultFilePathStr);
        }
    }
}
