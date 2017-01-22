package nl.codeimpact;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import nl.codeimpact.facebook.core.Facebook;
import nl.codeimpact.facebook.pages.Search;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.Properties;

import static nl.codeimpact.facebook.settings.PropInirializer.initProp;

public class Application  {

    public static Properties prop;
    public static List<String> passwordList;
    public static String loginStr;
    public static String passwordStr;

    public static void main(String[] args) {
        ChromeDriverManager.getInstance().setup();
        ChromeDriver driver = new ChromeDriver();

        initProp();

        Facebook facebook = new Facebook(driver);
        facebook.login(loginStr, passwordStr);

        Search search = new Search(facebook);
        search.setKeyword(prop.getProperty("searchKeyWord"));
        search.setType(Search.Type.PERSON);
        search.execute();
    }
}
