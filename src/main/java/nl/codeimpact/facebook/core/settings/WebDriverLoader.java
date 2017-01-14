package nl.codeimpact.facebook.core.settings;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Properties;

/**
 * Created by Oleksandr on 11.01.2017.
 */
public class WebDriverLoader {

    protected static WebDriver getChromeWebDriverAndLogin(Properties prop) {
        ChromeDriverManager.getInstance().setup();
        ChromeDriver driver = new ChromeDriver();
        return driver;
    }
}
