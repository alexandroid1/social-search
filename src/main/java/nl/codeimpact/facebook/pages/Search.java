package nl.codeimpact.facebook.pages;

import nl.codeimpact.facebook.core.Facebook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static nl.codeimpact.Application.prop;

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

                for (int i = 0; i < links.size(); i++) {
                    try {
                        String profileUrl = links.get(i).getAttribute("href");
                        getProfileIds.add(profileUrl);
                        System.out.println(profileUrl);
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

