package nl.codeimpact;

import lombok.extern.slf4j.Slf4j;
import nl.codeimpact.facebook.DTO.Person;
import nl.codeimpact.facebook.core.Facebook;
import nl.codeimpact.facebook.core.FacebookProfile;
import nl.codeimpact.facebook.profile.InfoSegment;
import nl.codeimpact.facebook.profile.ProfileId;
import org.apache.log4j.BasicConfigurator;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static nl.codeimpact.facebook.settings.FBLogin.loginToFacebook;

@Slf4j
public class FBRunner {

    public static Properties prop;
    public static List<String> passwordList;
    public static String loginStr;
    public static String passwordStr;

    public static void main(String[] args) {

        BasicConfigurator.configure();
        Facebook facebook = loginToFacebook();



/*        Search search = new Search(facebook);

        ArrayList<String> ids = new ArrayList<>(search
                .setKeyword(prop.getProperty("searchKeyWord"))
                .setType(Search.Type.PERSON)
                .execute());*/



        /*---------ProfileId single profile----------*/


        ProfileId profileId = new ProfileId();
        String id = prop.getProperty("profilename");
        profileId.setProfileName(id);

        FacebookProfile profile = new FacebookProfile(facebook, profileId);

        InfoSegment[] infoSegments = new InfoSegment[] {InfoSegment.WORK_AND_STUDY, InfoSegment.HOMETOWN};

        profile.getInfo(infoSegments);

        Person person = profile.getPersonObject();

        person.getStudy().forEach(study->log.debug(study.toString()));
        person.getWork().forEach(work->log.debug(work.toString()));

        /*-----------------------------------------------*/

/*        ids.forEach(id->log.debug(" "+id));

        ArrayList<String> linksWithIds = new ArrayList<>();
        ArrayList<String> linksWithNames = new ArrayList<>();

        getIds(ids, linksWithIds, linksWithNames);

        linksWithIds.forEach(id->log.debug(" "+id));
        linksWithNames.forEach(id->log.debug(" "+id));*/
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

    public static ProfileId getProfileIdFromUrl(String url) {
        String identifier;
        if (url.contains("profile.php?id=")) {
            identifier = url.substring(url.indexOf('=') + 1, url.lastIndexOf('&'));
        } else {
            identifier = url.substring(url.lastIndexOf('/') + 1, url.lastIndexOf('?'));
        }
        return new ProfileId(identifier);
    }




}
