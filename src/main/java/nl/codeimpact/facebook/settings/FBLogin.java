package nl.codeimpact.facebook.settings;

import nl.codeimpact.FBRunner;
import nl.codeimpact.facebook.core.Facebook;
import org.openqa.selenium.WebDriver;

import static nl.codeimpact.facebook.settings.DriverInitializer.initDriver;
import static nl.codeimpact.facebook.settings.PropInirializer.initProp;

/**
 * Created by Oleksandr on 29.01.2017.
 */
public class FBLogin {
    protected static Facebook loginToFacebook() {
        WebDriver driver = initDriver();
        initProp();
        Facebook facebook = new Facebook(driver);
        facebook.login(FBRunner.loginStr, FBRunner.passwordStr);
        return facebook;
    }
}
