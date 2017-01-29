package nl.codeimpact.facebook.core;

import nl.codeimpact.facebook.DTO.Person;
import nl.codeimpact.facebook.profile.InfoFieldMapper;
import nl.codeimpact.facebook.profile.InfoSegment;
import nl.codeimpact.facebook.profile.ProfileId;
import nl.codeimpact.facebook.settings.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FacebookProfile {
    private WebDriver driver;
    private ProfileId profileId;
    private Person person = new Person();
    private boolean profileOpen = false;

    public FacebookProfile(Facebook facebook, ProfileId profileId) {
        this.driver = facebook.getDriver();
        this.profileId = profileId;
    }

    public void getTimeline() {

    }

    private void openProfile() {
        if (profileOpen == false) {
            driver.get(Constants.FACEBOOK_BASE_URL + profileId.getProfileIdentifier());
        }
    }

    public void getInfo(InfoSegment[] infoSegments) {
        openProfile();

        InfoFieldMapper infoFieldMapper = new InfoFieldMapper(driver, person);

        driver.findElement(By.xpath(Constants.XPATH_INFO_BUTTON)).click();
        for (InfoSegment infoSegment : infoSegments) {
            infoFieldMapper.mapFieldsBySegment(infoSegment);
        }
    }

    public void getFriends() {

    }

    public void getPictures() {

    }

    public Person getPersonObject() {
        return person;
    }
}
