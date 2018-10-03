package model;

public class Relation {

    /**
     * @properties
     * String : relationName
     * String : relationType
     * */
    private String relationName;
    private String relationDetails;

    public Relation(String relationName) {
        this.relationName = relationName;
        this.relationDetails = "";
    }

    public String getRelationName() {
        return relationName;
    }

    public void setRelationName(String relationName) {
        this.relationName = relationName;
    }

    public String getRelationDetails() {
        return relationDetails;
    }

    public void setRelationDetails(String relationDetails) {
        this.relationDetails = relationDetails;
    }

    @Override
    public String toString() {
        return this.relationName;
    }
}
