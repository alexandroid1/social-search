package nl.codeimpact;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import nl.codeimpact.facebook.core.Facebook;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static nl.codeimpact.facebook.conversion.TxtFileListTransfer.fileToList;
import static nl.codeimpact.facebook.conversion.TxtFileListTransfer.listToFile;
import static nl.codeimpact.facebook.settings.PropLoader.getProperties;

public class Application {
    private static Properties prop;
    private static List<String> passwordList;
    private static String loginStr;
    private static String passwordStr;


    public static void main(String[] args) {
        ChromeDriverManager.getInstance().setup();
        ChromeDriver driver = new ChromeDriver();

        init();
        Facebook facebook = new Facebook(driver, loginStr, passwordStr, prop);
        facebook.login();

        ArrayList<String> getProfileIds = facebook.search();
        listToFile(getProfileIds, prop.getProperty("appliedFilePath"));
    }

    public static void init() {
        prop = getProperties("fb");
        passwordList = new ArrayList<>();
        fileToList(passwordList, prop.getProperty("loginpassfile"));
        loginStr = passwordList.get(0);
        passwordStr = passwordList.get(1);
    }
}
