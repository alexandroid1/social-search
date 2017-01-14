package nl.codeimpact.facebook.core.settings;

/**
 * Created by Oleksandr on 06.01.2017.
 */
public class TimeSetter {

    protected static int getWaitSeconds() {
        double randNumber = Math.random();
        return (int) randNumber * 1000 + 500;
    }

    protected void waiteOneSec() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
