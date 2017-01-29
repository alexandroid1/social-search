package nl.codeimpact.facebook.settings;

import nl.codeimpact.FBRunner;
import nl.codeimpact.facebook.convert.ListTransfer;

import java.util.ArrayList;

/**
 * Created by Oleksandr on 22.01.2017.
 */
public class PropInirializer {

    public static void initProp() {
        FBRunner.prop = PropLoader.getProperties("fb");
        FBRunner.passwordList = new ArrayList<>();
        ListTransfer.fileToList(FBRunner.passwordList, FBRunner.prop.getProperty("loginpassfile"));
        FBRunner.loginStr = FBRunner.passwordList.get(0);
        FBRunner.passwordStr = FBRunner.passwordList.get(1);
    }

}
