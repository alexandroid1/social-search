package nl.codeimpact.facebook.settings;

import nl.codeimpact.Application;
import nl.codeimpact.facebook.conversion.TxtFileListTransfer;

import java.util.ArrayList;

/**
 * Created by Oleksandr on 22.01.2017.
 */
public class PropInirializer {

    public static void initProp() {
        Application.prop = PropLoader.getProperties("fb");
        Application.passwordList = new ArrayList<>();
        TxtFileListTransfer.fileToList(Application.passwordList, Application.prop.getProperty("loginpassfile"));
        Application.loginStr = Application.passwordList.get(0);
        Application.passwordStr = Application.passwordList.get(1);
    }

}
