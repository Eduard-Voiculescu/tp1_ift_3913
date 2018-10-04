package Test;

import model.Parser;

public class Test {

    private final static String userDir = System.getProperty("user.dir") + "\\src\\Test\\";
    private static String pathToLigueNoModel = "Ligue_no_model.ucd";
    private static String pathToLigueDifferentAssignment = "Ligue_different_assignment.ucd";
    private static String pathToLigueAllClassesAreDoubled = "Ligue_all_classes_are_doubled.ucd";


    /**
     * This function should print out :
     * - UML Diagram does not start with MODEL - Error, improper UML Class Diagram-
     */
    private static void testLigueNoModel (String pathToLigueNoModel){

        Parser parser = new Parser(userDir + pathToLigueNoModel);
        if (parser.fileIsOk) {
            System.out.println((char)27 + "[31m" + "testLigueNoModel -- Failed");
            throw new AssertionError();
        }
        System.out.println("testLigueAllClassesAreDoubled -- Passed");
    }

    /**
     * This function is used to check if Ligue with different assignment will still work
     */
    private static void testLigueDifferentAssignment (String pathToLigueDifferentAssignment){

        Parser parser = new Parser(userDir + pathToLigueDifferentAssignment);
        if (!parser.fileIsOk){
            System.out.println((char)27 + "[31m" + "testLigueDifferentAssignment -- Failed");
            throw new AssertionError();
        }
        System.out.println("testLigueAllClassesAreDoubled -- Passed");
    }

    /**
     * This function is used to check if Ligue with different assignment will still work
     */
    private static void testLigueAllClassesAreDoubled (String pathToLigueAllClassesAreDoubled){

        Parser parser = new Parser(userDir + pathToLigueAllClassesAreDoubled);
        if (parser.fileIsOk) {
            System.out.println((char)27 + "[31m" + "testLigueAllClassesAreDoubled -- Failed");
            throw new AssertionError();
        }
        System.out.println((char)27 + "[31m" + "testLigueAllClassesAreDoubled -- Passed");

    }

    public static void main(String[] args) {
        testLigueNoModel(pathToLigueNoModel);
        testLigueDifferentAssignment(pathToLigueDifferentAssignment);
        testLigueAllClassesAreDoubled(pathToLigueAllClassesAreDoubled);
    }

}
