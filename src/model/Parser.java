package model;

/**
 * Created by Eduard Voiculescu on 4 october 2018
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * PLEASE NOTE: IF A .ucd FILE AND PROVIDED AND ONE ERROR OCCURS, WE WILL SIMPLY PRINT OUT THE ENTIRE
 * STACK TRACE. THE UI WILL NOT CLOSE. THE USER CAN THEN SELECT A VALID UML CLASS DIAGRAM.
 *
 * PLEASE NOTE 2: WE ADDED COLOR TO OUR PRINT STATEMENTS. TESTS THAT FAIL WILL PRINT OUT RED AND TESTS
 * THAT PASS WILL PRINT OUT GREEN. IT IS POSSIBLE THAT ON WINDOWS COMMAND PROMPT THE COLORS WILL NOT SHOW.
 * ON CYGWIN AND LINUX BASH, COLORS DO SHOW. FOR MAC, IT DEPENDS ON THAT MAC VERSION, SOME WORK SOME DON'T.
 * */

public class Parser {

    /**
     * @properties
     * Map<String, Classe> : classDictionnary
     * String : filePath
     * String filePathArrayList<String> : fileContent
     * ArrayList<String> : fileContent
     * boolean : valide;
     * */

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";


    /* Dictionary containing all classes */
    private HashMap<String, Classe> classDictionnary;

    /* FilePath to .ucd file */
    private String filePath;

    /* ArrayList containing all the lines in the file read */
    private ArrayList<String> fileContent;

    /* Variable used for testing purposes */
    public boolean valide;

    /**
     * Constructor
     * @param filePath String (required) : path to file to read
     * Also sets filepath, fileContent and valide variables
     * */
    public Parser(String filePath) {

        this.classDictionnary = new HashMap<String, Classe>();
        this.filePath = filePath;
        this.fileContent = new ArrayList<String>();
        this.valide = true; // we assume file is ok at the beginning

        readFile(this.filePath);
        parseFile(this.fileContent);

    }

    /**
     * Getter du dictionnaire de classe
     * @return : HashMap<String, Classe> of classDictionnary
     * */
    public HashMap<String, Classe> getClassDictionnary() {
        return classDictionnary;
    }

    /**
     * Method to read file content and put in ArrayList FileContent. Reads entire file till the end.
     * @param filePath : filePath to where file is read
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
                    System.out.println(ANSI_RED + "--- ERROR UML Diagram does not start with MODEL - Error, improper UML Class Diagram ---" + ANSI_RESET);
                    this.valide = false;
                    return;
                }

                /* Add all the words one by one, while ignoring () and , */
                // If we see any of the :, ::, :=, =, ::=, replace with :
                line = line.replace("::=", " : ")
                        .replace("::", " : ")
                        .replace(":=", " : ")
                        .replace("=", " : ")
                        .replace(":", " : ");
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

        } catch (Exception e) {
            System.out.println("Error while reading file");
            System.out.println(e.getMessage());
        }
    }

    /**
     * @param content : fileContent
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
                i = parseGeneralization(i, content);
            } else if (content.get(i).equals("RELATION")) {
                i = parseRelation(i, content);
            } else if (content.get(i).equals("AGGREGATION")) {
                i = parseAggregation(i, content);
            }
        }
    }

    /**
     * Method with 2 ArrayList (ATTRIBUTS and OPERATIONS)
     * This method will fill in attributs and
     * @param i : index of where parseClass starts at
     * @param content : fileContent
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
                    if (attributs.size() == 0){ // There is nothing in the attribut list
                        attributs.add(attribut);
                    } else {
                        boolean canAdd = true; // boolean value to see if we can add attribute
                        for(int j = 0; j < attributs.size(); j++) {
                            if(attributs.get(j).getAttributName().equals(attribut.getAttributName())){
                                System.out.println(ANSI_RED + "--- ERROR " + attribut.getAttributName() +  " already exists in " + classe.getClassName() + " ---" + ANSI_RESET);
                                valide = false;
                                canAdd = false;
                                break;
                            }
                        }
                        if (canAdd)
                            attributs.add(attribut);
                    }
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
                    if (operations.size() == 0){ // There is nothing in the operations list
                        operations.add(method);
                    } else {
                        boolean canAdd = true; // boolean value to see if we can add method
                        for(int j = 0; j < operations.size(); j++){
                            if(operations.get(j).getMethodName().equals(method.getMethodName())){
                                // both methods have no arguments and both methods are of same type
                                if(operations.get(j).getAttributs().size() == 0 && method.getAttributs().size() == 0 && operations.get(j).getMethodType().equals(method.getMethodType())){
                                    System.out.println(ANSI_RED + "--- ERROR method " + method.getMethodName() + " already exists in " + classe.getClassName() + " ---" + ANSI_RESET);
                                    valide = false;
                                    canAdd = false;
                                    break;
                                } else { // both methods have arguments and both methods are of same type
                                    int numberOfEqualArguments = 0;
                                    for (int k = 0; k < operations.get(j).getAttributs().size(); k++){
                                        // attribut type of arguments of operations and method are the same
                                        if(operations.get(j).getAttributs().size() > 0 && method.getAttributs().size() > 0){
                                            if(operations.get(j).getAttributs().get(k).getAttributType().equals(method.getAttributs().get(k).getAttributType())){
                                                numberOfEqualArguments++;
                                            }
                                        }
                                    }
                                    // both operation and method have same number of argument type
                                    if (numberOfEqualArguments == method.getAttributs().size()){
                                        // operations and method have same number of arguments AND are of same type
                                        if (operations.get(j).getMethodType().equals(method.getMethodType())){
                                            System.out.println(ANSI_RED + "--- ERROR method " + method.getMethodName() + " already exists in " + classe.getClassName() + " ---" + ANSI_RESET);
                                            valide = false;
                                            canAdd = false;
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                        if (canAdd)
                            operations.add(method);
                    }
                    i+=3;
                }
            }
        }
        classe.setAttributs(attributs);
        classe.setMethods(operations);

        /* We have to verify that if a given classe already exists in the dictionary to simply not put it it
         * and print out a message
         */
        if(this.classDictionnary.size() == 0){
            this.classDictionnary.put(classe.getClassName(), classe);
        } else {
            if(!this.classDictionnary.containsKey(classe.getClassName())){
                this.classDictionnary.put(classe.getClassName(), classe);

            } else {
                System.out.println(ANSI_RED + "--- ERROR : Class " + classe.getClassName() + " already exists in the UML Diagram. We will simply ignore the doubled class ---" + ANSI_RESET);
                this.valide = false;
            }
        }
        return i;
    }

    /**
     * @param i : index of where parseGeneralization starts at
     * @param content : fileContent
     * @return i int : position where GENERALIZATION ends
     * */
    private int parseGeneralization(int i, ArrayList<String> content){
        i++;
        String motherClass = content.get(i);
        i+=2; // No multiple motherClasses !

        ArrayList<String> subClasses = new ArrayList<String>();

        while (!content.get(i).equals(";")){
            subClasses.add(content.get(i));
            i++;
        }

        for (String subClass : subClasses) {
            this.getClassDictionnary().get(motherClass).addSubClasse(this.getClassDictionnary().get(subClass));
            this.getClassDictionnary().get(subClass).addParent(this.getClassDictionnary().get(motherClass));
        }
        return i;
    }

    /**
     * @param i : index of where parseRelation starts at
     * @param content : fileContent
     * @return i int : position where RELATION ends
     * */
    private int parseRelation(int i, ArrayList<String> content){
        String relation = content.get(i+1);
        String relationDetails = content.get(i) + " " + relation + "\n";
        i+=2;

        relationDetails += "\t" + content.get(i) + "\n";
        i++;

        Relation rel = new Relation(relation);

        while (!content.get(i).equals(";")){
            if (content.get(i).equals("CLASS")){
                rel.setRelationName("(R) " + relation);
                this.getClassDictionnary().get(content.get(i+1)).addRelation(rel); // Ajouter la relation au Dictionnaire
                relationDetails += "\t" + content.get(i);
                relationDetails += " " + content.get(i+1) + " " + content.get(i+2);
                i+=3;
            }
            relationDetails += "\n";
        }
        rel.setRelationDetails(relationDetails);

        return i;
    }

    /**
     * @param i : index of where parseAggregation starts at
     * @param content : fileContent
     * @return i int : position where AGGREGATION ends
     * */
    private int parseAggregation(int i, ArrayList<String> content){

        String aggregation = "Aggregation"; // We are at and aggregation now
        String aggregationDetails = content.get(i);
        i++;

        Relation rel = new Relation(aggregation);

        while (!content.get(i).equals(";")){
            if (content.get(i).equals("CONTAINER") || content.get(i).equals("PARTS")){
                aggregationDetails += "\n" + content.get(i) + "\n";
            } else if (content.get(i).equals("CLASS")){
                rel.setRelationName("(A) " + content.get(i+1));
                aggregationDetails += "\t" + content.get(i) + " ";
                this.getClassDictionnary().get(content.get(i+1)).addRelation(rel);
            } else {
                aggregationDetails += content.get(i) + " ";
            }
            i++;
        }

        rel.setRelationDetails(aggregationDetails);

        return i;
    }

}
