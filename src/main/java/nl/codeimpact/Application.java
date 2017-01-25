package nl.codeimpact;

import nl.codeimpact.facebook.core.Facebook;
import nl.codeimpact.facebook.pages.Search;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static nl.codeimpact.facebook.Constants.FACEBOOK_BASE_URL;
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

       // linksWithIds.forEach(System.out::println);
       // linksWithNamesOnly.forEach(System.out::println);
    }

    private static void getIds(ArrayList<String> ids, ArrayList<String> linksWithIds, ArrayList<String> linksWithNamesOnly) {

        for (String id : ids) {
            if (id.contains("profile.php?id=")){

                linksWithIds.add(matchWithIds(id));

                System.out.print(testWithIds(id));
                System.out.println(" "+matchWithIds(id));

            } else {
                id = id.replace(FACEBOOK_BASE_URL,"").replace("?ref=br_rs","");
                linksWithNamesOnly.add(id);

                System.out.print(testWithIds(id));
                System.out.println(" "+id);
            }
        }
    }

    public static boolean testWithIds(String testIdString){
        Pattern p = Pattern.compile(".*?(\\d+)",Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
        Matcher m = p.matcher(testIdString);
        return m.matches();
    }

    public static String matchWithIds(String testIdString){
        Pattern p = Pattern.compile(".*?(\\d+)",Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
        Matcher m = p.matcher(testIdString);
        return (m.group(1)).toString();
    }





}
