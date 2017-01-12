package nl.codeimpact;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import nl.codeimpact.facebook.core.Facebook;
import nl.codeimpact.facebook.core.Search;
import org.openqa.selenium.chrome.ChromeDriver;

public class Application {

    public static void main(String[] args) {
        ChromeDriverManager.getInstance().setup();
        ChromeDriver driver = new ChromeDriver();

        Facebook facebook = new Facebook(driver);
        facebook.login("username", "password");

        Search search = new Search();
        search.getAllProfilesSearchString("test");
    }
}
