package nl.codeimpact;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import nl.codeimpact.facebook.core.Facebook;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;

import static nl.codeimpact.facebook.conversion.TxtFileListTransfer.listToFile;

public class Application {

    public static void main(String[] args) {
        ChromeDriverManager.getInstance().setup();
        ChromeDriver driver = new ChromeDriver();

        Facebook facebook = new Facebook(driver);
        facebook.init();
        facebook.doLogin();

        ArrayList<String> getProfileIds = facebook.doSearch();
        listToFile(getProfileIds, facebook.getResultFilePathStr());
    }
}
