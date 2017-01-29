package nl.codeimpact.facebook.profile;

import lombok.Data;

/**
 * Created by Oleksandr on 24.01.2017.
 */
@Data
public class ProfileId {
    private String profileId = null;
    private String profileName = null;

    public ProfileId() {
    }

    public ProfileId(String profileIdentifier) {
        if (profileIdentifier.matches("[0-9]+")) {
            setProfileId(profileIdentifier);
        } else {
            setProfileName(profileIdentifier);
        }
    }

    public String getProfileIdentifier() {
        String profileIdentifier = null;
        if (profileName != null) {
            profileIdentifier = profileName;
        } else if (profileId != null) {
            profileIdentifier = profileId;
        }

        return profileIdentifier;
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