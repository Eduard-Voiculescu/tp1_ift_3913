package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Parser {

    /* Dictionary containing all classes */
    private Map<String, Classe> classDictionnary;

    /* FilePath to .ucd file */
    private String filePath;

    /* ArrayList containing all the lines in the file read */
    private ArrayList<String> fileContent;

    /**
     * Constructeur du Parseur
     * @param filePath String : path to file to read
     * */
    public Parser(String filePath) {

        this.classDictionnary = new HashMap<String, Classe>();
        this.filePath = filePath;
        this.fileContent = new ArrayList<String>();

        Classe monday = new Classe("monday");
        Classe tuesday = new Classe("tuesday");
        Classe wednesday = new Classe("wednesday");
        Classe thursday = new Classe("thursday");
        Classe friday = new Classe("friday");
        Classe saturday = new Classe("saturday");
        Classe sunday = new Classe("sunday");
        classDictionnary.put(monday .getClassName(), monday);
        classDictionnary.put(tuesday .getClassName(), tuesday);
        classDictionnary.put(wednesday .getClassName(), wednesday);
        classDictionnary.put(thursday .getClassName(), thursday);
        classDictionnary.put(friday .getClassName(), friday);
        classDictionnary.put(saturday .getClassName(), saturday);
        classDictionnary.put(sunday .getClassName(), sunday);

        readFile(this.filePath);
        translateFile(this.fileContent);

    }

    /**
     * Getter du dictionnaire de classe
     * */
    public Map<String, Classe> getClassDictionnary() {
        return classDictionnary;
    }

    /**
     * Method to read file content and put in ArrayList FileContent
     * */
    private void readFile(String filePath) {
        try {

            FileReader fr = new FileReader(filePath);
            BufferedReader bf = new BufferedReader(fr);
            String line; // Line read
            int i = 0; // Length of ArrayList of fileContent

            /* Inspiration https://www.geeksforgeeks.org/different-ways-reading-text-file-java/ */
            while ((line = bf.readLine())!= null){
                if (i == 0 && !line.contains("MODEL")) { // Verify that it is indeed a MODEL
                    System.out.println("UML Diagram does not start with MODEL - Error, improper UML Class Diagram");
                    return;
                }

                this.fileContent.add(line);
                i++;
            }

            /* Close FileReader and BufferedReader */
            fr.close();
            bf.close();

            for (String l: this.fileContent) {
                System.out.println(l);
            }

        } catch (Exception e) {
            System.out.println("Error while reading file");
            System.out.println(e.getMessage());
        }
    }

    /**
     * Translate File in different places amongst information.
     * CLASS
     * GENERALIZATION
     * RELATION
     * AGGREGATION
     * */
    private void translateFile(ArrayList<String> content) {
        for (int i = 0; i < content.size(); i++){
            if (content.get(i).equals("CLASS")){
                parseClass();
            } else if (content.get(i).equals("GENERALIZATION")) {
                parseGeneralization();
            } else if (content.get(i).equals("RELATION")) {
                parseRelation();
            } else if (content.get(i).equals("AGGREGATION")) {
                parseAgregation();
            }
        }
    }

    /**
     * Method with 2 ArrayList (ATTRIBUTS and OPERATIONS)
     * */
    private void parseClass(){
        ArrayList<String> attributs = new ArrayList<String>();
        ArrayList<String> operations = new ArrayList<String>();
    }

    /**
     * Method with 1 ArrayList (SUBCLASSES)
     * */
    private void parseGeneralization(){
        ArrayList<String> subClasses = new ArrayList<String>();
    }

    /**
     * Method with 1 ArrayList (ROLES)
     * */
    private void parseRelation(){
        ArrayList<String> roles = new ArrayList<String>();
    }

    /**
     * Method with 2 ArrayList (CONTAINER and PARTS)
     * */
    private void parseAgregation(){
        ArrayList<String> container = new ArrayList<String>();
        ArrayList<String> parts = new ArrayList<String>();
    }

    public static void main(String[] args) {
        Parser parser = new Parser("C:\\Users\\Eddy\\Desktop\\Ligue.ucd");
    }
}
