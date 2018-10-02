package model;

import java.util.ArrayList;

public class Classe {

    /**
     * @properties
     * String : className
     * ArrayList<Attribut> : attributs
     * ArrayList<Method> : methods
     * */
    private String className;
    private ArrayList<Attribut> attributs; // For the ATTRIBUTS
    private ArrayList<Method> methods; // For the OPERATIONS

    /**
     * Constructor of Classe
     * */
    public Classe(String className, ArrayList<Attribut> attributs, ArrayList<Method> methods) {
        this.className = className;
        this.attributs = attributs;
        this.methods = methods;
    }

    /**
     * Getter of className
     * */
    public String getClassName() {
        return className;
    }

    /**
     * Setter of className
     * */
    public void setClassName(String className) {
        this.className = className;
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
     * Getter of methods
     * */
    public ArrayList<Method> getMethods() {
        return methods;
    }

    /**
     * Setter of methods
     * */
    public void setMethods(ArrayList<Method> methods) {
        this.methods = methods;
    }


    @Override
    public String toString() {
        // TODO: toString() ...
        return super.toString();
    }
}
