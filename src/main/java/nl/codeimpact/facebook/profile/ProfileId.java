package nl.codeimpact.facebook.profile;

/**
 * Created by Oleksandr on 24.01.2017.
 */
public class ProfileId {
    private String profileId = null;
    private String profileName = null;

    public ProfileId() {
    }

    public ProfileId(String profileIdentifier) {

        // @todo if it is a id, set the profile id, if it is a profile name, set the ProfileName
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    public String getProfileIdentintifier() {
        String profileIdentifier = null;
        if (profileName != null) {
            profileIdentifier = profileName;
        } else if (profileId != null) {
            profileIdentifier = profileId;
        }

        return profileIdentifier;
    }

    public String getProfileName() {
        return profileName;
    }

    public String getProfileId() {
        return profileId;
    }

    public static ProfileId getProfileIdFromUrl(String url) {

        // @todo strip url with regex, only profile id/profile name will stay.
        return new ProfileId("");
    }
}