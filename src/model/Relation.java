package model;

/**
 * Created by Eduard Voiculescu on 4 october 2018
 */

public class Relation {

    /**
     * @properties
     * String : relationName
     * String : relationType
     * */
    private String relationName;
    private String relationDetails;

    /**
     * Constructor
     * @param relationName (required) : name of relation
     * Also sets relationsDetails to "" to print out on Ui
     * */
    public Relation(String relationName) {
        this.relationName = relationName;
        this.relationDetails = "";
    }

    /**
     * Getter of relationName
     * @return : relation name
     * */
    public String getRelationName() {
        return relationName;
    }

    /**
     * Setter of relationName
     * @param relationName : sets relation name
     * */
    public void setRelationName(String relationName) {
        this.relationName = relationName;
    }

    /**
     * Getter of relationDetails
     * @return : relation details
     * */
    public String getRelationDetails() {
        return relationDetails;
    }

    /**
     * Setter of relationDetails
     * @param relationDetails : sets relation details
     * */
    public void setRelationDetails(String relationDetails) {
        this.relationDetails = relationDetails;
    }

    /**
     * @return : personalized string toString() to better fitting in Ui
     * */
    @Override
    public String toString() {
        return this.relationName;
    }
}
