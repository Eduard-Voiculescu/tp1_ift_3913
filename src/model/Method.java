package model;

import java.util.ArrayList;

public class Method {

    /**
     * @properties
     * String : methodName
     * String : methodType
     * */
    private String methodName;
    private String methodType;
    private ArrayList<Attribut> attributs;

    /**
     * Constructor of Method
     * */
    public Method(String methodName, String methodType, ArrayList<Attribut> attributs) {
        this.methodName = methodName;
        this.methodType = methodType;
        this.attributs = attributs;
    }

    /**
     * Getter of methodName
     * */
    public String getMethodName() {
        return methodName;
    }

    /**
     * Setter of methodName
     * */
    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    /**
     * Getter of methodType
     * */
    public String getMethodType() {
        return methodType;
    }

    /**
     * Setter of methodType
     * */
    public void setMethodType(String methodType) {
        this.methodType = methodType;
    }

    /**
     * Getter of attributs
     * */
    public ArrayList<Attribut> getAttributs() {
        return attributs;
    }

    /**
     * Setter of attributs
     * */
    public void setAttributs(ArrayList<Attribut> attributs) {
        this.attributs = attributs;
    }

    /**
     * Add attributs to ArrayList
     */
    public void addArgument(Attribut attributs){
        getAttributs().add(attributs);
    }

    /**
     * Overriding toString() to better fitting in Ui
     * */
    @Override
    public String toString() {
        String value = getMethodName() + " (";

        for (Attribut attribut : this.attributs) {
            value += attribut.toString();
            value += ", ";
        }

        if (value.charAt(value.length() - 1) != '(') {
            value = value.substring(0, value.length() - 2);
        }

        value += ") : ";
        value += getMethodType();

        return value;
    }

}
