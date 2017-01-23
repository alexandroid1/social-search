package nl.codeimpact.facebook.pages;

import nl.codeimpact.facebook.core.Facebook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.*;

import static nl.codeimpact.Application.prop;
import static nl.codeimpact.facebook.conversion.TxtFileListTransfer.listToFile;
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
                put(PERSON, "//button[@aria-label='Add Friend']");
            }
        };
    }

    private String keyword;
    private Type searchType;
    private Facebook facebook = null;

    public Search(Facebook facebook) {
        this.facebook = facebook;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public void setType(Type searchType) {
        this.searchType = searchType;
    }

    /**
     * Returns all profile id's
     * @return
     */
    public ArrayList<String> execute(Type type) {
        ArrayList<String> profileIds = new ArrayList<>();
        WebDriver driver = facebook.getDriver();

        driver.get( urlMap.get(type) + keyword);

        try {
            for (int i = 1; i < 150 ; i++) {
                JavascriptExecutor jse = (JavascriptExecutor) driver;
                jse.executeScript("window.scrollBy(0,250)", "");

               // List<WebElement> links = driver.findElements(By.xpath(xPathMap.get(PERSON)));

                List<WebElement> links = driver.findElements(By.xpath(xPathMap.get(type)));

                ArrayList<String> profileIdsInner = profileIds;
                links.forEach(profileUrl -> profileIdsInner.add(profileUrl.getAttribute("href")));
                links.forEach(profileUrl -> System.out.println((profileUrl.getAttribute("href"))));

                profileIds = profileIdsInner;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        profileIds = uniqueSort(profileIds);
        listToFile(profileIds, prop.getProperty("appliedFilePath"));

        return profileIds;
    }

    private ArrayList<String> uniqueSort(ArrayList<String> finalGetProfileIds) {
        HashSet<String> linkNames = new HashSet<>(finalGetProfileIds);
        ArrayList<String> getProfileIds = new ArrayList<>(linkNames);
        Collections.sort(getProfileIds);
        return getProfileIds;
    }

}

