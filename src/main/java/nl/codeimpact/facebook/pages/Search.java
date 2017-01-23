package nl.codeimpact.facebook.pages;

import nl.codeimpact.facebook.core.Facebook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.*;
import java.util.concurrent.TimeUnit;

import static nl.codeimpact.facebook.pages.Search.Type.PERSON;
import static nl.codeimpact.facebook.pages.Search.Type.TOP;

public class Search {

    public enum Type {
        TOP, NEWEST, PERSON, PICTURE, VIDEO, PAGE, PLACE, GROUP, APP, EVENT
    }

    private static Map<Type, String> urlMap;
    static {
        urlMap = new HashMap<Type, String>(){
            {
                put(TOP, "https://www.facebook.com/search/top/?init=quick&q=");
                put(PERSON, "https://www.facebook.com/search/people/?q=");
            }
        };
    }

    private static Map<Type, String> xPathMap;
    static {
        xPathMap = new HashMap<Type, String>(){
            {
                put(TOP, "//a[contains(@href,'ref=SEARCH&fref=nf')]");
                put(PERSON, "//div[@class='_gll']//a");
            }
        };
    }

    private String keyword = "";
    private Type searchType = PERSON;
    private Facebook facebook = null;

    public Search(Facebook facebook) {
        this.facebook = facebook;
    }

    public Search setKeyword(String keyword) {
        this.keyword = keyword;
        return this;
    }

    public Search setType(Type searchType) {
        this.searchType = searchType;
        return this;
    }

    public ArrayList<String> execute() {
        ArrayList<String> ids = new ArrayList<>();
        WebDriver driver = facebook.getDriver();
        driver.get( urlMap.get(this.searchType) + keyword);
        while(true) {
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            jse.executeScript("window.scrollBy(0,250)", "");
            if (driver.findElements(By.className("_24j")).size() > 0) {
                break;
            }
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.MILLISECONDS);
        }

        List<WebElement> links = driver.findElements(By.xpath(xPathMap.get(this.searchType)));
        links.forEach(profileUrl -> ids.add(profileUrl.getAttribute("href")));




        Collections.sort(ids);
        return ids;
    }
}

