package model;

import java.lang.reflect.Array;
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
    private ArrayList<String> subClasses; // For the SUBCLASSES
    private ArrayList<Relation> relations; // For the RELATIONS

    /**
     * Constructor of Classe
     * */
    public Classe(String className, ArrayList<Attribut> attributs, ArrayList<Method> methods) {
        this.className = className;
        this.attributs = attributs;
        this.methods = methods;
        this.subClasses = new ArrayList<String>();
        this.relations = new ArrayList<Relation>();
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

    /**
     * Getter of subClasses
     * */
    public ArrayList<String> getSubClasses() {
        return subClasses;
    }

    /**
     * Setter of subClasses
     * */
    public void setSubClasses(ArrayList<String> subClasses) {
        this.subClasses = subClasses;
    }

    public ArrayList<Relation> getRelations() {
        return relations;
    }

    public void setRelations(ArrayList<Relation> relations) {
        this.relations = relations;
    }

    /**
     * Function to add subClass to given subClass array
     * */
    public void addSubClasse(String subC){
        this.subClasses.add(subC);
    }

    public void addRelation(Relation rel){
        this.relations.add(rel);
    }

    @Override
    public String toString() {
        // TODO: toString() ...
        return super.toString();
    }
}
