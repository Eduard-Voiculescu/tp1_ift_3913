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

        readFile(this.filePath);
        parseFile(this.fileContent);

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

                /* Add all the words one by one, while ignoring () and , */
                line = line.replace(":", " : "); // We keep :
                line = line.replace(";", " ; "); // We keep ;
                String[] words = line.split("\\s+|,|\\(|\\)"); // Ignore |space , ( )|

                for (String word : words) {
                    if (word.length() > 0) { // We do not add spaces to file content
                        this.fileContent.add(word); // Add each word
                    }
                }
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
    private void parseFile(ArrayList<String> content) {
        for (int i = 0; i < content.size(); i++){
            if (content.get(i).equals("CLASS")){
                i = parseClass(i, content);
            } else if (content.get(i).equals("GENERALIZATION")) {
                break;
//                i = parseGeneralization(i, content);
            } else if (content.get(i).equals("RELATION")) {
                parseRelation();
            } else if (content.get(i).equals("AGGREGATION")) {
                parseAgregation();
            }
        }
    }

    /**
     * Method with 2 ArrayList (ATTRIBUTS and OPERATIONS)
     * This method will fill in attributs and
     * @param i int : position where CLASS starts in ArrayList of FileContent
     * @return i int : position where CLASS ends
     * */
    private int parseClass(int i, ArrayList<String> content){
        ArrayList<Attribut> attributs = new ArrayList<Attribut>();
        ArrayList<Method> operations = new ArrayList<Method>();
        Classe classe = new Classe("", new ArrayList<Attribut>(), new ArrayList<Method>());

        i++;
        classe.setClassName(content.get(i));
        i++;

        while (!content.get(i).equals(";")){

            if(content.get(i).equals("CLASS")){
                break;
            } else if (content.get(i).equals("ATTRIBUTES")){

                i++;
                while (!content.get(i).equals("OPERATIONS")){
                    Attribut attribut = new Attribut(content.get(i), content.get(i+2));
                    attributs.add(attribut);
                    i+=3;
                }

            } else if (content.get(i).equals("OPERATIONS")){

                i++;
                while (!content.get(i).equals(";")){
                    Method method = new Method("", "", new ArrayList<>());
                    method.setMethodName(content.get(i));
                    while (!content.get(i+1).equals(":")){ // we have a method with arguments
                        Attribut attribut = new Attribut(content.get(i+1), content.get(i+3));
                        method.addArgument(attribut);
                        i+=3;
                    }
                    // The method does not have any arguments
                    method.setMethodType(content.get(i+2));
                    operations.add(method);
                    i+=3;
                }
            }
        }
        classe.setAttributs(attributs);
        classe.setMethods(operations);
        this.classDictionnary.put(classe.getClassName(), classe);
        return i;
    }

    /**
     * Method with 1 ArrayList (SUBCLASSES)
     * */
    private int parseGeneralization(int i, ArrayList<String> content){
        ArrayList<String> subClasses = new ArrayList<String>();

        return i;
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
