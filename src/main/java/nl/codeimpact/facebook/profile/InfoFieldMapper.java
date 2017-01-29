package nl.codeimpact.facebook.profile;


import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import nl.codeimpact.facebook.DTO.Person;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static nl.codeimpact.facebook.settings.Constants.XPATH_WORK_AND_EDUCATION_BUTTON;

@Slf4j
public class InfoFieldMapper {
    private Person person;
    private WebDriver driver;

    public InfoFieldMapper(WebDriver driver, Person person) {
        this.driver = driver;
        this.person = person;
    }

    public void mapFieldsBySegment(InfoSegment segment) {
        switch (segment) {

            case OVERVIEW:
                mapOverviewFields();
                break;
            case WORK_AND_STUDY:
                mapWorkAndStudyFields();
                break;
            case HOMETOWN:
                mapHomeTownFields();
                break;
            case CONTACT_DATA:
                mapContactDataFields();
                break;
            case FAMILY_AND_FRIENDS:
                mapFriendsAndFamilyFields();
                break;
            case DETAILS:
                mapDetailsFields();
                break;
            case LIFE_EVENTS:
                mapLifeEventsFields();
                break;
        }
    }

    private void mapOverviewFields() {
        aboutTabClick();
        WebElement overviewTab = driver.findElement(By.xpath("//a[@data-testid='nav_overview']"));
        overviewTab.click();
    }

    private void aboutTabClick() {
        WebElement aboutTab = driver.findElement(By.xpath("//a[@class='_6-6 _6-7']"));
        aboutTab.click();
    }

    private void mapWorkAndEducationFields() {
        aboutTabClick();
        WebElement workAndEducationTab = driver.findElement(By.xpath("//a[@data-testid='nav_edu_work']"));
        workAndEducationTab.click();
    }

    private void mapHomeTownFields() {
        aboutTabClick();
        WebElement homeTownTab = driver.findElement(By.xpath("//a[@data-testid='nav_places']"));
        homeTownTab.click();
    }

    private void mapContactDataFields() {
        aboutTabClick();
        WebElement contactDataTab = driver.findElement(By.xpath("//a[@data-testid='nav_contact_basic']"));
        contactDataTab.click();
    }

    private void mapFriendsAndFamilyFields() {
        aboutTabClick();
        WebElement friendsAndFamilyTab = driver.findElement(By.xpath("//a[@data-testid='nav_all_relationships']"));
        friendsAndFamilyTab.click();
    }

    private void mapDetailsFields() {
        aboutTabClick();
        WebElement detailsTab = driver.findElement(By.xpath("//a[@data-testid='nav_about']"));
        detailsTab.click();
    }

    private void mapLifeEventsFields() {
        aboutTabClick();
        WebElement lifeEventsTab = driver.findElement(By.xpath("//a[@data-testid='nav_year_overviews']"));
        lifeEventsTab.click();
    }

    @SneakyThrows
    private void mapWorkAndStudyFields() {

        WebDriverWait wait = new WebDriverWait(driver, 400);
        WebElement workAndStudyLink = wait.until(ExpectedConditions
                .elementToBeClickable(By
                        .xpath(XPATH_WORK_AND_EDUCATION_BUTTON)));
        workAndStudyLink.click();

        Thread.sleep(1000);

        List<WebElement> div = driver.findElements(By.xpath("//div[@class='_4ms4']"));
        div.forEach(link->log.debug(" education links = " + link.getText()));
        
    }

    private String getIDsFromProfileUrl(String profileUrl) {
        Pattern p = Pattern.compile("(?<=resume/)(.*)(?=query=)",Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
        Matcher m = p.matcher(profileUrl);
        if (m.find()) return (m.group(1)).replace("?","");
        return null;
    }

}
