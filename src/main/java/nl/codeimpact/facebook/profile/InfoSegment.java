package nl.codeimpact.facebook.profile;

public enum InfoSegment {
    OVERVIEW("ELEMENTID"),
    WORK_AND_STUDY("ELEMENTID"),
    HOMETOWN("ELEMENTID"),
    CONTACT_DATA("ELEMENTID"),
    FAMILY_AND_FRIENDS("ELEMENTID"),
    DETAILS("ELEMENTID"),
    LIFE_EVENTS("ELEMENTID");

    private String elementId;

    InfoSegment(String elementId) {
        this.elementId = elementId;
    }

    public String getElementId() {
        return elementId;
    }
}
