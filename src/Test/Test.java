package Test;

/**
 * Created by Eduard Voiculescu and Sami Steenhaut on 4 october 2018
 */

import model.Classe;
import model.Parser;
import metriques.Metriques;

/**
 * PLEASE NOTE: WE ADDED COLOR TO OUR PRINT STATEMENTS. TESTS THAT FAIL WILL PRINT OUT RED AND TESTS
 * THAT PASS WILL PRINT OUT GREEN. IT IS POSSIBLE THAT ON WINDOWS COMMAND PROMPT THE COLORS WILL NOT SHOW.
 * ON CYGWIN AND LINUX BASH, COLORS DO SHOW. FOR MAC, IT DEPENDS ON THAT MAC VERSION, SOME WORK SOME DON'T.
 */

public class Test {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_WHITE = "\u001B[37m";

    private final static String userDir = System.getProperty("user.dir") + "/src/Test/";

    /* Test Suite for parser */
    private static String pathToLigueNoModel = "Ligue_no_model.ucd";
    private static String pathToLigueDifferentAssignment = "Ligue_different_assignment.ucd";
    private static String pathToLigueAllClassesAreDoubled = "Ligue_all_classes_are_doubled.ucd";
    private static String pathToLigueClassesDoubledDifferentAttributs = "Ligue_classes_doubled_different_attributs.ucd";
    private static String pathToLigueClassesDoubledDifferentOperations =  "Ligue_classes_doubled_different_operations.ucd";
    private static String pathToLigueClassesWithSameAttributNameButDifferentType = "Ligue_classe_with_same_attribut_name_but_different_type.ucd";
    private static String pathToLigueClassesWithSameOperationsSameAttributsType = "Ligue_classes_with_same_operations_same_attributs_type.ucd";
    private static String pathToLigueClasseWithSameNameMethodButDifferentType = "Ligue_classe_with_same_name_method_but_different_type.ucd";

    /* Test Suite for Metrics */
    private static String pathToLigue = "Ligue.ucd";
    private static String pathToLiGueMultipleParentsWithEquipeSameMethodJoueur = "Ligue_multiple_parents_with_equipe_same_method_joueur.ucd";
    private static String pathToLiGueMultipleParentSameLevel = "Ligue_multiple_parent_same_level.ucd";
    private static String pathToModelANATestMetric = "Model_ANA_Test_Metric.ucd";
    private static String pathToModelNOMTestMetric = "Model_NOM_Test_Metric.ucd";
    private static String pathToModelNOATestMetric = "Model_NOA_Test_Metric.ucd";
    private static String pathToModelITCTestMetric = "Model_ITC_Test_Metric.ucd";
    private static String pathToModelETCTestMetric = "Model_ETC_Test_Metric.ucd";

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

    /**
     * This function tests all the metrics of all the classes in Ligue.ucd (manual entry of good answers)
     * Here even if all classes are doubled, the parser will ignore them and the metrics will be the same.
     * @param Ligue : Ligue.ucd file to check metrics
     * */
    private static void testLigueMetric(String Ligue){
        Parser parser = new Parser(userDir + Ligue);
        Classe entraineur = parser.getClassDictionnary().get("Entraineur");
        Classe participant = parser.getClassDictionnary().get("Participant");
        Classe stade = parser.getClassDictionnary().get("Stade");
        Classe equipe = parser.getClassDictionnary().get("Equipe");
        Classe joueur = parser.getClassDictionnary().get("Joueur");
        if (metricTesting(Ligue, entraineur, 0.0, 0, 2, 0, 0, 1, 1, 0, 0, 0)
                && metricTesting(Ligue, participant, 0.0, 0, 1, 0, 0, 0, 0, 1, 2, 2)
                && metricTesting(Ligue, stade, 2.0, 1, 2, 1, 0, 1, 0, 0, 0, 0)
                && metricTesting(Ligue, equipe, 0.3333333333333333, 3, 1, 1, 1, 3, 0, 0, 0, 0)
                && metricTesting(Ligue, joueur, 0.5, 2, 3, 0, 1, 1, 1, 0, 0, 0)){
            System.out.println(ANSI_GREEN +  Ligue + " metric -- Passed" + ANSI_RESET);
        }
        else {
            System.out.println(ANSI_RED + Ligue +" metric -- Failed" + ANSI_RESET);
        }
    }

    /**
     * This function tests all the metrics of all the classes in Ligue_multiple_parents.ucd (manual entry of good answers)
     * @param Ligue : Ligue_multiple_parents.ucd file to check metrics
     * */
    private static void testLigueMultipleParentsMetric(String Ligue){
        Parser parser = new Parser(userDir + Ligue);
        Classe entraineur = parser.getClassDictionnary().get("Entraineur");
        Classe participant = parser.getClassDictionnary().get("Participant");
        Classe stade = parser.getClassDictionnary().get("Stade");
        Classe equipe = parser.getClassDictionnary().get("Equipe");
        Classe joueur = parser.getClassDictionnary().get("Joueur");
        if (metricTesting(Ligue, entraineur, 0.0, 4, 5, 0, 0, 5, 3, 0, 0, 0)
                && metricTesting(Ligue, participant, 0.0, 4, 4, 0, 0, 4, 2, 1, 2, 2)
                && metricTesting(Ligue, stade, 2.0, 4, 3, 1, 0, 4, 1, 2, 1, 3)
                && metricTesting(Ligue, equipe, 0.3333333333333333, 3, 1, 1, 1, 3, 0, 3, 1, 4)
                && metricTesting(Ligue, joueur, 0.3333333333333333, 6, 6, 0, 1, 5, 3, 0, 0, 0)){
            System.out.println(ANSI_GREEN +  Ligue + " metric -- Passed" + ANSI_RESET);
        }
        else {
            System.out.println(ANSI_RED + Ligue +" metric -- Failed" + ANSI_RESET);
        }
    }


    /**
     * This function tests all the metrics of all the classes in Ligue_multiple_parents.ucd (manual entry of good answers)
     * @param Ligue : Ligue_multiple_parents.ucd file to check metrics
     * */
    private static void testLiGueMultipleParentSameLevelMetric(String Ligue){
        Parser parser = new Parser(userDir + Ligue);
        Classe entraineur = parser.getClassDictionnary().get("Entraineur");
        Classe participant = parser.getClassDictionnary().get("Participant");
        Classe parent = parser.getClassDictionnary().get("Parent");
        Classe stade = parser.getClassDictionnary().get("Stade");
        Classe equipe = parser.getClassDictionnary().get("Equipe");
        Classe joueur = parser.getClassDictionnary().get("Joueur");
        if (metricTesting(Ligue, entraineur, 0.0, 4, 5, 0, 0, 5, 3, 0, 0, 0)
                && metricTesting(Ligue, participant, 0.0, 4, 4, 0, 0, 4, 2, 1, 2, 2)
                && metricTesting(Ligue, parent, 0.0, 4, 4, 0, 0, 4, 2, 0, 0, 0)
                && metricTesting(Ligue, stade, 2.0, 4, 3, 1, 0, 4, 1, 2, 2, 4)
                && metricTesting(Ligue, equipe, 0.3333333333333333, 3, 1, 1, 1, 3, 0, 3, 1, 5)
                && metricTesting(Ligue, joueur, 0.5, 6, 6, 0, 1, 5, 3, 0, 0, 0)){
            System.out.println(ANSI_GREEN +  Ligue + " metric -- Passed" + ANSI_RESET);
        }
        else {
            System.out.println(ANSI_RED + Ligue +" metric -- Failed" + ANSI_RESET);
        }
    }

    /**
     * This function is used to test the general metrics (instead of rewriting the same function
     * over and over again, we wrot a general metric testing.
     * @param ligueUCDToTest : file to test metrics
     * @param ci : classe to test metrics on
     * @param ana : ANA metric
     * @param nom : NOM metric
     * @param noa : NOA metric
     * @param itc : ITC metric
     * @param etc : ETC metric
     * @param cac : CAC metric
     * @param dit : DIT metric
     * @param cld : CLD metric
     * @param noc : NOC metric
     * @param nod : NOD metric
     * */
    private static boolean metricTesting(String ligueUCDToTest,
                                         Classe ci,
                                         Double ana,
                                         Integer nom,
                                         Integer noa,
                                         Integer itc,
                                         Integer etc,
                                         Integer cac,
                                         Integer dit,
                                         Integer cld,
                                         Integer noc,
                                         Integer nod){
        Parser parser = new Parser(userDir + ligueUCDToTest);
        Metriques metriques = new Metriques(parser.getClassDictionnary());
        return metriques.ANA(ci) == ana && metriques.NOM(ci) == nom && metriques.NOA(ci) == noa && metriques.ITC(ci) == itc
                && metriques.ETC(ci) == etc && metriques.CAC(ci) == cac && metriques.DIT(ci) == dit
                && metriques.CLD(ci) == cld && metriques.NOC(ci) == noc && metriques.NOD(ci) == nod;
    }

    /**
     * This function will test if a given Classe ci has the good ANA metric value.
     * @param pathToModelANATestMetric : Metric file to check ANA metric
     * @param ci : String name of class to calculate metric
     * @param ana : Double
     * */
    private static void anaMetricTesting(String pathToModelANATestMetric, String ci, Double ana){
        Parser parser = new Parser(userDir + pathToModelANATestMetric);
        Metriques metriques = new Metriques(parser.getClassDictionnary());
        if (metriques.ANA(parser.getClassDictionnary().get(ci)) == ana) {
            System.out.println(ANSI_GREEN +  pathToModelANATestMetric + " metric -- Passed -- Expected ANA Metric value " +
                    "to be " + ana + " and we calculated " + metriques.ANA(parser.getClassDictionnary().get(ci)) + ANSI_RESET);
        } else {
            System.out.println(ANSI_RED +  pathToModelANATestMetric + " metric -- Failed -- Expected ANA Metric value " +
                    "to be " + ana + " and we calculated " + metriques.ANA(parser.getClassDictionnary().get(ci)) + ANSI_RESET);
        }
    }

    /**
     * This function will test if a given Classe ci has the good NOM metric value.
     * @param pathToModelNOMTestMetric : Metric file to check NOM metric
     * @param ci : String name of class to calculate metric
     * @param nom : Integer
     * */
    private static void nomMetricTesting(String pathToModelNOMTestMetric, String ci,Integer nom){
        Parser parser = new Parser(userDir + pathToModelNOMTestMetric);
        Metriques metriques = new Metriques(parser.getClassDictionnary());
        if (metriques.NOM(parser.getClassDictionnary().get(ci)) == nom) {
            System.out.println(ANSI_GREEN +  pathToModelANATestMetric + " metric -- Passed -- Expected NOM Metric value " +
                    "to be " + nom + " and we calculated " + metriques.NOM(parser.getClassDictionnary().get(ci)) + ANSI_RESET);
        } else {
            System.out.println(ANSI_RED +  pathToModelANATestMetric + " metric -- Passed -- Expected NOM Metric value " +
                    "to be " + nom + " and we calculated " + metriques.NOM(parser.getClassDictionnary().get(ci)) + ANSI_RESET);
        }
    }

    /**
     * This function will test if a given Classe ci has the good NOA metric value.
     * @param pathToModelNOATestMetric : Metric file to check NOA metric
     * @param ci : String name of class to calculate metric
     * @param noa : Integer
     * */
    private static void noaMetricTesting(String pathToModelNOATestMetric, String ci, Integer noa){
        Parser parser = new Parser(userDir + pathToModelNOATestMetric);
        Metriques metriques = new Metriques(parser.getClassDictionnary());
        if (metriques.NOA(parser.getClassDictionnary().get(ci)) == noa) {
            System.out.println(ANSI_GREEN +  pathToModelNOATestMetric + " metric -- Passed -- Expected NOA Metric value " +
                    "to be " + noa + " and we calculated " + metriques.NOA(parser.getClassDictionnary().get(ci)) + ANSI_RESET);
        } else {
            System.out.println(ANSI_RED+  pathToModelNOATestMetric + " metric -- Passed -- Expected NOA Metric value " +
                    "to be " + noa + " and we calculated " + metriques.NOA(parser.getClassDictionnary().get(ci)) + ANSI_RESET);
        }
    }

    /**
     * This function will test if a given Classe ci has the good ITC metric value.
     * @param pathToModelITCTestMetric : Metric file to check ITC metric
     * @param ci : String name of class to calculate metric
     * @param itc : Integer
     * */
    private static void itcMetricTesting(String pathToModelITCTestMetric, String ci, Integer itc){
        Parser parser = new Parser(userDir + pathToModelITCTestMetric);
        Metriques metriques = new Metriques(parser.getClassDictionnary());
        if (metriques.ITC(parser.getClassDictionnary().get(ci)) == itc) {
            System.out.println(ANSI_GREEN +  pathToModelITCTestMetric + " metric -- Passed -- Expected ITC Metric value " +
                    "to be " + itc + " and we calculated " + metriques.ITC(parser.getClassDictionnary().get(ci)) + ANSI_RESET);
        } else {
            System.out.println(ANSI_RED+  pathToModelITCTestMetric + " metric -- Passed -- Expected ITC Metric value " +
                    "to be " + itc + " and we calculated " + metriques.ITC(parser.getClassDictionnary().get(ci)) + ANSI_RESET);
        }
    }

    /**
     * This function will test if a given Classe ci has the good ETC metric value.
     * @param pathToModelETCTestMetric : Metric file to check ETC metric
     * @param ci : String name of class to calculate metric
     * @param etc : Integer
     * */
    private static void etcMetricTesting(String pathToModelETCTestMetric, String ci, Integer etc){
        Parser parser = new Parser(userDir + pathToModelETCTestMetric);
        Metriques metriques = new Metriques(parser.getClassDictionnary());
        if (metriques.ETC(parser.getClassDictionnary().get(ci)) == etc) {
            System.out.println(ANSI_GREEN +  pathToModelETCTestMetric + " metric -- Passed -- Expected ETC Metric value " +
                    "to be " + etc + " and we calculated " + metriques.ETC(parser.getClassDictionnary().get(ci)) + ANSI_RESET);
        } else {
            System.out.println(ANSI_RED+  pathToModelETCTestMetric + " metric -- Passed -- Expected ETC Metric value " +
                    "to be " + etc + " and we calculated " + metriques.ETC(parser.getClassDictionnary().get(ci)) + ANSI_RESET);
        }
    }

    /**
     * This function will test if a given Classe ci has the good CAC metric value.
     * @param ligueMetric : Metric file to check CAC metric
     * @param cac : Integer
     * */
    private static void cacMetricTesting(String ligueMetric, Classe ci, Integer cac){

    }

    /**
     * This function will test if a given Classe ci has the good DIT metric value.
     * @param ligueMetric : Metric file to check DIT metric
     * @param dit : Integer
     * */
    private static void ditMetricTesting(String ligueMetric, Classe ci, Integer dit){

    }

    /**
     * This function will test if a given Classe ci has the good CLD metric value.
     * @param ligueMetric : Metric file to check CLD metric
     * @param cld : Integer
     * */
    private static void cldMetricTesting(String ligueMetric, Classe ci, Integer cld){

    }

    /**
     * This function will test if a given Classe ci has the good NOC metric value.
     * @param ligueMetric : Metric file to check NOC metric
     * @param noc : Integer
     * */
    private static void nocMetricTesting(String ligueMetric, Classe ci, Integer noc){

    }

    /**
     * This function will test if a given Classe ci has the good NOD metric value.
     * @param ligueMetric : Metric file to check NOD metric
     * @param nod : Integer
     * */
    private static void nodMetricTesting(String ligueMetric, Classe ci, Integer nod){

    }

    public static void main(String[] args) {

        System.out.println(ANSI_WHITE + "------------------------------------------- Parser Test Suite | BEGIN -------------------------------------------" + ANSI_RESET);
        testLigueNoModel(pathToLigueNoModel);
        testLigueDifferentAssignment(pathToLigueDifferentAssignment);
        testLigueAllClassesAreDoubled(pathToLigueAllClassesAreDoubled);
        testLigueClassesDoubledDifferentAttributs(pathToLigueClassesDoubledDifferentAttributs);
        testLigueClassesDoubledDifferentOperations(pathToLigueClassesDoubledDifferentOperations);
        testLigueClassesWithSameAttributNameButDifferentType(pathToLigueClassesWithSameAttributNameButDifferentType);
        testLigueClassesWithSameOperationsSameAttributsType(pathToLigueClassesWithSameOperationsSameAttributsType);
        testLigueClasseWithSameNameMethodButDifferentType(pathToLigueClasseWithSameNameMethodButDifferentType);
        System.out.println(ANSI_WHITE + "-------------------------------------------- Parser Test Suite | END --------------------------------------------" + ANSI_RESET);

        System.out.println();

        System.out.println(ANSI_WHITE + "------------------------------------------- Metric Test Suite | BEGIN -------------------------------------------" + ANSI_RESET);
        testLigueMetric(pathToLigue);
        testLigueMultipleParentsMetric(pathToLiGueMultipleParentsWithEquipeSameMethodJoueur);
        testLiGueMultipleParentSameLevelMetric(pathToLiGueMultipleParentSameLevel);

        /* Here all classes are doubled the parser will ignore all the doubled classes and the metrics will
        * be the same as those in Ligue.ucd */
        testLigueMetric(pathToLigueAllClassesAreDoubled);

        /* Here classes have the same attribut names but with different type. As this is not allowed, the parser
         * will ignore those respective attributs and the metrics will be the same as those in Ligue.ucd */
        testLigueMetric(pathToLigueClassesWithSameAttributNameButDifferentType);

        /* Here we will do one test for every metric */
        anaMetricTesting(pathToModelANATestMetric, "ANATesting", 2.5);
        nomMetricTesting(pathToModelNOMTestMetric, "NOMTesting2", 2);
        noaMetricTesting(pathToModelNOATestMetric, "NOATesting1", 3);
        noaMetricTesting(pathToModelNOATestMetric, "NOATesting2", 6);
        itcMetricTesting(pathToModelITCTestMetric, "ITCTesting1", 2);
        itcMetricTesting(pathToModelITCTestMetric, "ITCTesting2", 0);
        etcMetricTesting(pathToModelETCTestMetric, "ETCTesting1", 0);
        etcMetricTesting(pathToModelETCTestMetric, "ETCTesting2", 2);

        System.out.println(ANSI_WHITE + "-------------------------------------------- Metric Test Suite | END --------------------------------------------" + ANSI_RESET);
    }

}
