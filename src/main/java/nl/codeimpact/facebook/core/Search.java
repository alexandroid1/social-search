package nl.codeimpact.facebook.core;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.*;
import java.util.concurrent.TimeUnit;

import static nl.codeimpact.facebook.settings.Constants.END_OF_SEARCH_RESULTS_CLASS_NAME;
import static nl.codeimpact.facebook.core.Search.Type.*;
import static nl.codeimpact.facebook.settings.Constants.FACEBOOK_BASE_URL;

public class Search {

    public enum Type {
        TOP, NEWEST, PERSON, PICTURE, VIDEO, PAGE, PLACE, GROUP, APP, EVENT
    }

    private static Map<Type, String> urlMap;
    static {
        urlMap = new HashMap<Type, String>(){
            {
                put(TOP, FACEBOOK_BASE_URL + "search/top/?init=quick&q=");
                put(NEWEST, FACEBOOK_BASE_URL + "search/latest/?q=");
                put(PERSON, FACEBOOK_BASE_URL + "search/people/?q=");
                put(PICTURE, FACEBOOK_BASE_URL + "search/photos/?q=");
                put(VIDEO, FACEBOOK_BASE_URL + "search/videos/?q=");
                put(PAGE, FACEBOOK_BASE_URL + "search/pages/?q=");
                put(PLACE, FACEBOOK_BASE_URL + "search/places/?q=");
                put(GROUP, FACEBOOK_BASE_URL + "search/groups/?q=");
                put(APP, FACEBOOK_BASE_URL + "search/apps/?q=");
                put(EVENT, FACEBOOK_BASE_URL + "search/events/?q=");
            }
        };
    }

    private static Map<Type, String> xPathMap;
    static {
        xPathMap = new HashMap<Type, String>(){
            {
                put(TOP, "//a[contains(@href,'ref=SEARCH&fref=nf')]");
                put(NEWEST, "//div[@class='_6a _5u5j _6b']//a");
                put(PERSON, "//div[@class='_gll']//a");
                put(PICTURE, "");
                put(VIDEO, "");
                put(PAGE, "");
                put(PLACE, "");
                put(GROUP, "");
                put(APP, "");
                put(EVENT, "");
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
            if (driver.findElements(
                    By.className(END_OF_SEARCH_RESULTS_CLASS_NAME)).size() > 0) {
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

