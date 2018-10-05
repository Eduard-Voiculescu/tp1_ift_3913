package Test;

import model.Parser;

/**
 * PLEASE NOTE: WE ADDED COLOR TO OUR PRINT STATEMENTS. TESTS THAT FAIL WILL PRINT OUT RED AND TESTS
 * THAT PASS WILL PRINT OUT GREEN. IT IS POSSIBLE THAT ON WINDOWS COMMAND PROMPT THE COLORS WILL NOT SHOW.
 * ON CYGWIN AND LINUX BASH, COLORS DO SHOW. FOR MAC, IT DEPENDS ON THAT MAC VERSION, SOME WORK SOME DON'T.
 */

public class Test {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";

    private final static String userDir = System.getProperty("user.dir") + "\\src\\Test\\";

    private static String pathToLigueNoModel = "Ligue_no_model.ucd";
    private static String pathToLigueDifferentAssignment = "Ligue_different_assignment.ucd";
    private static String pathToLigueAllClassesAreDoubled = "Ligue_all_classes_are_doubled.ucd";
    private static String pathToLigueClassesDoubledDifferentAttributs = "Ligue_classes_doubled_different_attributs.ucd";
    private static String pathToLigueClassesDoubledDifferentOperations =  "Ligue_classes_doubled_different_operations.ucd";
    private static String pathToLigueClassesWithSameAttributNameButDifferentType = "Ligue_classe_with_same_attribut_name_but_different_type.ucd";
    private static String pathToLigueClassesWithSameOperationsSameAttributsType = "Ligue_classes_with_same_operations_same_attributs_type.ucd";
    private static String pathToLigueClasseWithSameNameMethodButDifferentType = "Ligue_classe_with_same_name_method_but_different_type.ucd";

    /**
     * This function should print out :
     * - UML Diagram does not start with MODEL - Error, improper UML Class Diagram-
     * @param pathToLigueNoModel : path to ligue with no model
     */
    private static void testLigueNoModel (String pathToLigueNoModel){

        Parser parser = new Parser(userDir + pathToLigueNoModel);
        if (parser.valide) { // I want valide to be false
            System.out.println(ANSI_RED + "testLigueNoModel -- Failed" + ANSI_RESET);
            throw new AssertionError();
        }
        System.out.println(ANSI_GREEN + "testLigueNoModel -- Passed" + ANSI_RESET);
    }

    /**
     * This function is used to check if Ligue with different assignment will still work
     * @param pathToLigueDifferentAssignment : paht to ligue with different assignment
     */
    private static void testLigueDifferentAssignment (String pathToLigueDifferentAssignment){

        Parser parser = new Parser(userDir + pathToLigueDifferentAssignment);
        if (!parser.valide){ // I want valide to be true
            System.out.println(ANSI_RED + "testLigueDifferentAssignment -- Failed" + ANSI_RESET);
            throw new AssertionError();
        }
        System.out.println(ANSI_GREEN + "testLigueDifferentAssignment -- Passed" + ANSI_RESET);
    }

    /**
     * This function is used to check if Ligue with same classes will show the same classes
     * @param pathToLigueAllClassesAreDoubled : path to ligue where all classes are doubled
     */
    private static void testLigueAllClassesAreDoubled (String pathToLigueAllClassesAreDoubled){

        Parser parser = new Parser(userDir + pathToLigueAllClassesAreDoubled);
        if (parser.valide) { // I want valide to be false
            System.out.println(ANSI_RED + "testLigueAllClassesAreDoubled -- Failed" + ANSI_RESET);
            throw new AssertionError();
        }
        System.out.println(ANSI_GREEN + "testLigueAllClassesAreDoubled -- Passed" + ANSI_RESET);

    }

    /**
     * This function is used to check if Ligue with different classes with different attributs
     * will show up.
     * We programed it that only the first instance of a given class with show. So if additional
     * classes but different attributs will be omitted.
     * @param pathToLigueClassesDoubledDifferentAttributs : path to ligue where classes are doubled with different attributs
     */
    private static void testLigueClassesDoubledDifferentAttributs(String pathToLigueClassesDoubledDifferentAttributs){

        Parser parser = new Parser(userDir + pathToLigueClassesDoubledDifferentAttributs);
        if (parser.valide) { // I want valide to be false
            System.out.println(ANSI_RED + "pathToLigueClassesDoubledDifferentAttributs -- Failed" + ANSI_RESET);
            throw new AssertionError();
        }
        System.out.println(ANSI_GREEN + "pathToLigueClassesDoubledDifferentAttributs -- Passed" + ANSI_RESET);

    }

    /**
     * This function is used to check if Ligue with different classes with different operations
     * will show up.
     * We programed it that only the first instance of a given class with show. So if additional
     * classes but different operations will be omitted.
     * @param pathToLigueClassesDoubledDifferentOperations : path to ligue where classes are double with different operations
     */
    private static void testLigueClassesDoubledDifferentOperations(String pathToLigueClassesDoubledDifferentOperations){

        Parser parser = new Parser(userDir + pathToLigueClassesDoubledDifferentOperations);
        if (parser.valide) { // I want valide to be false
            System.out.println(ANSI_RED + "pathToLigueClassesDoubledDifferentOperations -- Failed" + ANSI_RESET);
            throw new AssertionError();
        }
        System.out.println(ANSI_GREEN + "pathToLigueClassesDoubledDifferentOperations -- Passed" + ANSI_RESET);

    }

    /**
     * This function is used to check if Ligue with different classes with same attribut name but different type
     * will show up.
     * We programed it that only the first instance of a given class with show. So if additional
     * classes but different attrubut type with same name, they will be omitted.
     * @param pathToLigueClassesWithSameAttributNameButDifferentType : path to ligue where slasses have same attributs but different types
     */
    private static void testLigueClassesWithSameAttributNameButDifferentType(String pathToLigueClassesWithSameAttributNameButDifferentType){

        Parser parser = new Parser(userDir + pathToLigueClassesWithSameAttributNameButDifferentType);
        if (parser.valide) { // I want valide to be false
            System.out.println(ANSI_RED + "pathToLigueClassesWithSameAttributNameButDifferentType -- Failed" + ANSI_RESET);
            throw new AssertionError();
        }
        System.out.println(ANSI_GREEN + "pathToLigueClassesWithSameAttributNameButDifferentType -- Passed" + ANSI_RESET);

    }

    /**
     * This function is used to check if Ligue with different classes with same operations name and same attributs type
     * will show up.
     * We programed it that operations iwth samme name and same attributs type (all of them) they will not show up.
     * @param LigueClassesWithSameOperationsSameAttributsType : path to ligue where classes have same operations with same attributs type
     */
    private static void testLigueClassesWithSameOperationsSameAttributsType(String LigueClassesWithSameOperationsSameAttributsType){

        Parser parser = new Parser(userDir + LigueClassesWithSameOperationsSameAttributsType);
        if (parser.valide) { // I want valide to be false
            System.out.println(ANSI_RED + "LigueClassesWithSameOperationsSameAttributsType -- Failed" + ANSI_RESET);
            throw new AssertionError();
        }
        System.out.println(ANSI_GREEN + "LigueClassesWithSameOperationsSameAttributsType -- Passed" + ANSI_RESET);

    }

    /**
     * This function is used to check if Ligue with different classes with same operations name but different operations type
     * will show up.
     * We programed it that operations with same name but different type will show up.
     * @param LigueClasseWithSameNameMethodButDifferentType : path to ligue where classes have same name methode but different type
     */
    private static void testLigueClasseWithSameNameMethodButDifferentType(String LigueClasseWithSameNameMethodButDifferentType){

        Parser parser = new Parser(userDir + LigueClasseWithSameNameMethodButDifferentType);
        if (!parser.valide) { // I want valide to be false
            System.out.println(ANSI_RED + "LigueClasseWithSameNameMethodButDifferentType -- Failed" + ANSI_RESET);
            throw new AssertionError();
        }
        System.out.println(ANSI_GREEN + "LigueClasseWithSameNameMethodButDifferentType -- Passed" + ANSI_RESET);

    }


    public static void main(String[] args) {
        testLigueNoModel(pathToLigueNoModel);
        testLigueDifferentAssignment(pathToLigueDifferentAssignment);
        testLigueAllClassesAreDoubled(pathToLigueAllClassesAreDoubled);
        testLigueClassesDoubledDifferentAttributs(pathToLigueClassesDoubledDifferentAttributs);
        testLigueClassesDoubledDifferentOperations(pathToLigueClassesDoubledDifferentOperations);
        testLigueClassesWithSameAttributNameButDifferentType(pathToLigueClassesWithSameAttributNameButDifferentType);
        testLigueClassesWithSameOperationsSameAttributsType(pathToLigueClassesWithSameOperationsSameAttributsType);
        testLigueClasseWithSameNameMethodButDifferentType(pathToLigueClasseWithSameNameMethodButDifferentType);
    }

}
