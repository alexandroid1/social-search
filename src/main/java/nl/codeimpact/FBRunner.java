package nl.codeimpact;

import lombok.extern.slf4j.Slf4j;
import nl.codeimpact.facebook.core.Facebook;
import nl.codeimpact.facebook.core.Search;
import org.apache.log4j.BasicConfigurator;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Slf4j
public class FBRunner extends nl.codeimpact.facebook.settings.FBLogin {

    public static Properties prop;
    public static List<String> passwordList;
    public static String loginStr;
    public static String passwordStr;

    public static void main(String[] args) {

        BasicConfigurator.configure();
        Facebook facebook = loginToFacebook();

        Search search = new Search(facebook);

        ArrayList<String> ids = new ArrayList<>(search
                .setKeyword(prop.getProperty("searchKeyWord"))
                .setType(Search.Type.PERSON)
                .execute());

        ids.forEach(id->log.debug(" "+id));

        ArrayList<String> linksWithIds = new ArrayList<>();
        ArrayList<String> linksWithNamesOnly = new ArrayList<>();

        getIds(ids, linksWithIds, linksWithNamesOnly);

       // linksWithIds.forEach(System.out::println);
       // linksWithNamesOnly.forEach(System.out::println);
    }

    private static void getIds(ArrayList<String> ids, ArrayList<String> linksWithIds, ArrayList<String> linksWithNamesOnly) {

        for (String id : ids) {
            if (id.contains("profile.php?id=")){
                id = id.substring(id.indexOf('=') + 1, id.lastIndexOf('&'));
                linksWithIds.add(id);
                log.debug(" "+id);
            } else {
                id = id.substring(id.lastIndexOf('/') + 1, id.lastIndexOf('?'));
                linksWithNamesOnly.add(id);
                log.debug(" "+id);
            }
        }
    }

}
