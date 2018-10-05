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
     * Constructor
     * @param className (required) : name of classe
     * @param attributs (required) : ArrayList of attributs
     * @param methods (required) : ArrayList of methods
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
     * @return : name of classe
     * */
    public String getClassName() {
        return className;
    }

    /**
     * Setter of className
     * @param className : name of classe
     * */
    public void setClassName(String className) {
        this.className = className;
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
     * Getter of methods
     * @return : ArrayList of methods
     * */
    public ArrayList<Method> getMethods() {
        return methods;
    }

    /**
     * Setter of methods
     * @param methods : sets ArrayList of methods
     * */
    public void setMethods(ArrayList<Method> methods) {
        this.methods = methods;
    }

    /**
     * Getter of subClasses
     * @return : ArrayList of sub classes
     * */
    public ArrayList<String> getSubClasses() {
        return subClasses;
    }

    /**
     * Setter of subClasses
     * @param subClasses : sets ArayList of sub classes
     * */
    public void setSubClasses(ArrayList<String> subClasses) {
        this.subClasses = subClasses;
    }

    /**
     * Getter of relations
     * @return : ArrayList of relations
     * */
    public ArrayList<Relation> getRelations() {
        return relations;
    }

    public void setRelations(ArrayList<Relation> relations) {
        this.relations = relations;
    }

    /**
     * Function to add subClass to given subClass array
     * @param subC : adds subClass to ArrayList of subClasses
     * */
    public void addSubClasse(String subC){
        this.subClasses.add(subC);
    }

    /**
     * Function to add rel to given relations array
     * @param rel : adds rel to ArrayList of relations
     * */
    public void addRelation(Relation rel){
        this.relations.add(rel);
    }

}
