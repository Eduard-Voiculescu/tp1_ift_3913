package model;

/**
 * Created by Eduard Voiculescu and Sami Steenhaut on 4 october 2018
 */

public class Attribut {

    /**
     * @properties
     * String : attributName
     * String : attributType
     * */
    private String attributName;
    private String attributType;

    /**
     * Constructor
     * @param attributName (required) : name of attribut.
     * @param attributType (required) : type of attribut.
     * */
    public Attribut(String attributName, String attributType) {
        this.attributName = attributName;
        this.attributType = attributType;
    }

    /**
     * Getter of attributName
     * @return : attribut name
     * */
    public String getAttributName() {
        return attributName;
    }

    /**
     * Setter of attributName
     * @param attributName : name of attribut.
     * */
    public void setAttributName(String attributName) {
        this.attributName = attributName;
    }

    /**
     * Getter of attributType
     * @return : attribut type
     * */
    public String getAttributType() {
        return attributType;
    }

    /**
     * Setter of attributType
     * @param attributType : type of attribut.
     * */
    public void setAttributType(String attributType) {
        this.attributType = attributType;
    }

    /**
     * Overriding toString() to better fitting in Ui
     * @return : personalized toString String to better fitting in Ui
     * */
    @Override
    public String toString() {
        return getAttributName() + " : " + getAttributType();
    }
}
