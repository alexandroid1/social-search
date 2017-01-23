package nl.codeimpact;

import nl.codeimpact.facebook.core.Facebook;
import nl.codeimpact.facebook.pages.Search;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
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

        ArrayList<String> ids = new ArrayList<>(search
                .setKeyword(prop.getProperty("searchKeyWord"))
                .setType(Search.Type.PERSON)
                .execute());

        ids.forEach(System.out::println);

        ArrayList<String> linksWithIds = new ArrayList<>();
        ArrayList<String> linksWithNamesOnly = new ArrayList<>();

        getIds(ids, linksWithIds, linksWithNamesOnly);

        linksWithIds.forEach(System.out::println);
        linksWithNamesOnly.forEach(System.out::println);
    }

    private static void getIds(ArrayList<String> ids, ArrayList<String> linksWithIds, ArrayList<String> linksWithNamesOnly) {
        String beginStrWithIds = "https://www.facebook.com/profile.php?id=";
        String endStrWithIds = "&ref=br_rs";

        String beginStrWithNamesOnly = "https://www.facebook.com/";
        String endStrWithNamesOnly = "?ref=br_rs";

        for (String item : ids) {
            if (item.contains(beginStrWithIds)){
                item = item.replace(beginStrWithIds,"");
                item = item.replace(endStrWithIds,"");
                linksWithIds.add(item);
            } else {
                item = item.replace(beginStrWithNamesOnly,"");
                item = item.replace(endStrWithNamesOnly,"");
                linksWithNamesOnly.add(item);
            }
        }

    }

}
