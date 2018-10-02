package model;

public class Attribut {

    /**
     * @properties
     * String : attributName
     * String : attributType
     * */
    private String attributName;
    private String attributType;

    /**
     * Constructor of Attribut
     * */
    public Attribut(String attributName, String attributType) {
        this.attributName = attributName;
        this.attributType = attributType;
    }

    /**
     * Getter of attributName
     * */
    public String getAttributName() {
        return attributName;
    }

    /**
     * Setter of attributName
     * */
    public void setAttributName(String attributName) {
        this.attributName = attributName;
    }

    /**
     * Getter of attributType
     * */
    public String getAttributType() {
        return attributType;
    }

    /**
     * Setter of attributType
     * */
    public void setAttributType(String attributType) {
        this.attributType = attributType;
    }

    /**
     * Overriding toString() to better fitting in Ui
     * */
    @Override
    public String toString() {
        return getAttributName() + " : " + getAttributType();
    }
}
