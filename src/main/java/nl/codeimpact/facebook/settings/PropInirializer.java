package nl.codeimpact.facebook.settings;

import nl.codeimpact.Application;

import java.util.ArrayList;

import static nl.codeimpact.facebook.conversion.TxtFileListTransfer.fileToList;
import static nl.codeimpact.facebook.settings.PropLoader.getProperties;

/**
 * Created by Oleksandr on 22.01.2017.
 */
public class PropInirializer {

    public static void initProp() {
        Application.prop = getProperties("fb");
        Application.passwordList = new ArrayList<>();
        fileToList(Application.passwordList, Application.prop.getProperty("loginpassfile"));
        Application.loginStr = Application.passwordList.get(0);
        Application.passwordStr = Application.passwordList.get(1);
    }

}
