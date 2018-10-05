package model;

/**
 * Created by Eduard Voiculescu and Sami Steenhaut on 4 october 2018
 */

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
     * Constructor
     * @param methodName (required) : name of method
     * @param methodType (required) : type of method
     * @param attributs (required) : ArrayList of attributs related to method
     * */
    public Method(String methodName, String methodType, ArrayList<Attribut> attributs) {
        this.methodName = methodName;
        this.methodType = methodType;
        this.attributs = attributs;
    }

    /**
     * Getter of methodName
     * @return : method name
     * */
    public String getMethodName() {
        return methodName;
    }

    /**
     * Setter of methodName
     * @param methodName : sets method name
     * */
    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    /**
     * Getter of methodType
     * @return : type of method
     * */
    public String getMethodType() {
        return methodType;
    }

    /**
     * Setter of methodType
     * @param methodType : sets method type
     * */
    public void setMethodType(String methodType) {
        this.methodType = methodType;
    }

    /**
     * Getter of attributs
     * @return : ArrayList of attributs
     * */
    public ArrayList<Attribut> getAttributs() {
        return attributs;
    }

    /**
     * Setter of attributs
     * @param attributs : sets ArrayList of attributs
     * */
    public void setAttributs(ArrayList<Attribut> attributs) {
        this.attributs = attributs;
    }

    /**
     * Add attributs to ArrayList
     * @param attributs : adds attribut to ArrayList of attributs
     */
    public void addArgument(Attribut attributs){
        this.attributs.add(attributs);
    }

    /**
     * Overriding toString() to better fitting in Ui
     * @return : personalized string toString() to better fitting in Ui
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
