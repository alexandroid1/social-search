package nl.codeimpact;

import nl.codeimpact.facebook.core.Facebook;
import nl.codeimpact.facebook.pages.Search;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.Properties;

import static nl.codeimpact.facebook.settings.DriverInitializer.initDriver;
import static nl.codeimpact.facebook.settings.PropInirializer.initProp;

public class Application  {

    public static Properties prop;
    public static List<String> passwordList;
    public static String loginStr;
    public static String passwordStr;

    public static void main(String[] args) {

        WebDriver driver = initDriver();
        initProp();

        Facebook facebook = new Facebook(driver);
        facebook.login(loginStr, passwordStr);

        Search search = new Search(facebook);
        search
                .setKeyword(prop.getProperty("searchKeyWord"))
                .setType(Search.Type.PERSON)
                .execute();
    }

}
