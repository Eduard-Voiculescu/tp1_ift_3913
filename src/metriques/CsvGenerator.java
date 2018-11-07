package metriques;

import model.Classe;

import com.opencsv.CSVWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Eduard Voiculescu and Sami Steenhaut on 4 november 2018
 */

public class CsvGenerator {

    /* Final variable to get home directory of user */
    public static final String HOME_DIRECTORY = System.getProperty("user.home");

    /**
     * @properties
     * List<String> : csv
     * String[] : headers
     * HashMap<String, Classe> : classDictionary
     * FileWriter: outputFile
     * String : filePath
     * File : csvFile
     * Metriques : metriques
     * */
    private List<String[]> csv; // For the CSV string to put on CSV file

    /* The headers of the CSV file */
    private String[] headers = {"Class Name", "ANA", "NOM", "NOA", "ITC", "ETC", "CAC", "DIT", "CLD", "NOC", "NOD"};

    /* Dictionnary containing all the classes of a .ucd file */
    private HashMap<String, Classe> classDictionary;

    /* FileWriter object */
    private FileWriter outputFile;

    /* File path of csv file */
    private String filePath;

    /* CSV File */
    private File csvFile;

    /* CSVWriter to write csv file */
    private CSVWriter csvWriter;

    /* Metriques */
    private Metriques metriques;

    /**
     * Constructor
     * @param classDictionary (required) : dictionary of all classes to compute csv file
     * */

    public CsvGenerator(HashMap<String, Classe> classDictionary) throws IOException{
        this.csv = new ArrayList<String[]>();
        this.classDictionary = classDictionary;
        this.filePath = HOME_DIRECTORY + "/Desktop/CSV_Class_Diagram.csv";
        this.csvFile = new File(this.filePath);
        this.outputFile = new FileWriter(this.csvFile);
        this.csvWriter = new CSVWriter(this.outputFile);
        this.metriques = new Metriques(classDictionary);
    }

    /**
     * This function will compute what has to be put to csv file (in an ArrayList<String>)
     * @param classDictionary : dictionary to generate csv from
     * */
    public void generateCSV(HashMap<String, Classe> classDictionary) {
        this.csv.add(headers);
        this.csv = calculateMetrique(classDictionary);
    }

    /**
     * This function calculates all the metrics coming from a classDictionary
     * @param classDictionary : dictionary of class objects
     * @return a list of csv
     * */
    private List<String[]> calculateMetrique(HashMap<String, Classe> classDictionary){

        /* Here we have to calculate all the metrics of all the classes from the class dictionary */
        for (Classe ci : classDictionary.values()){
            this.csv.add(
                    new String[]{
                            ci.getClassName(),
                            Double.toString(this.metriques.ANA(ci)),
                            Integer.toString(this.metriques.NOM(ci)),
                            Integer.toString(this.metriques.NOA(ci)),
                            Integer.toString(this.metriques.ITC(ci)),
                            Integer.toString(this.metriques.ETC(ci)),
                            Integer.toString(this.metriques.CAC(ci)),
                            Integer.toString(this.metriques.DIT(ci)),
                            Integer.toString(this.metriques.CLD(ci)),
                            Integer.toString(this.metriques.NOC(ci)),
                            Integer.toString(this.metriques.NOD(ci)),
                    });
        }
        return this.csv;
    }

    /**
     * This function writes all data to a csv file
     * */
    public void writeAllDataToCSV() throws IOException{
        this.csvWriter.writeAll(this.csv);
        this.csvWriter.close();
        System.out.println("CSV has successfully been written on the user's Desktop.");
    }

}
