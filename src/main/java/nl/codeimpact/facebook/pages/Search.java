package nl.codeimpact.facebook.pages;

import nl.codeimpact.facebook.core.Facebook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import static nl.codeimpact.Application.prop;
import static nl.codeimpact.facebook.conversion.TxtFileListTransfer.listToFile;

public class Search {

    public enum Type {
        TOP, NEWEST, PERSON, PICTURE, VIDEO, PAGE, PLACE, GROUP, APP, EVENT
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
    public ArrayList<String> execute() {
        ArrayList<String> getProfileIds = new ArrayList<>();
        WebDriver driver = facebook.getDriver();

        driver.get( prop.getProperty("searchBaseURL")+ keyword);

        try {
            getProfileIds.add("");
            while (true) {
                JavascriptExecutor jse = (JavascriptExecutor) driver;
                jse.executeScript("window.scrollBy(0,250)", "");

                List<WebElement> links = driver.findElements(By.xpath("//a[contains(@href,'ref=SEARCH&fref=nf')]"));

                ArrayList<String> finalGetProfileIds = getProfileIds;
                links.forEach(profileUrl -> finalGetProfileIds.add(profileUrl.getAttribute("href")));

                getProfileIds = uniqueSort(finalGetProfileIds);

                listToFile(getProfileIds, prop.getProperty("appliedFilePath"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getProfileIds;
    }

    private ArrayList<String> uniqueSort(ArrayList<String> finalGetProfileIds) {
        HashSet<String> linkNames = new HashSet<>(finalGetProfileIds);
        ArrayList<String> getProfileIds = new ArrayList<>(linkNames);
        Collections.sort(getProfileIds);
        return getProfileIds;
    }
}

