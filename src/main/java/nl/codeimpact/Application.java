package nl.codeimpact;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import nl.codeimpact.facebook.core.pages.Facebook;
import nl.codeimpact.facebook.core.pages.Search;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Properties;

import static nl.codeimpact.facebook.core.settings.PropLoader.getProperties;

public class Application {

    public static void main(String[] args) {
        ChromeDriverManager.getInstance().setup();
        ChromeDriver driver = new ChromeDriver();

        Properties prop = getProperties("fb");

        Facebook facebook = new Facebook(driver);
        facebook.login("username", "password");

        Search search = new Search();
        search.getAllProfilesSearchString("test");
    }
}
