package view;

/**
 * Created by Eduard Voiculescu on 4 october 2018
 */

/* Imports */
import metriques.CsvGenerator;
import metriques.Metriques;
import model.Classe;
import model.Parser;
import model.Relation;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.MessageFormat;
import java.util.ArrayList;

public class Ui {

    private static final int FRAME_HEIGHT = 800;
    private static final int FRAME_WIDTH = 1000;

    /* JFileChoser */
    private JFileChooser jFileChooser;

    /* JList */
    private JList<String> listClasses;
    private JList<String> listAttributs;
    private JList<String> listMethods;
    private JList<String> listSubClasses;
    private JList<String> listAssociations_Aggregations;
    private JList<String> listDetails;
    private JList<String> listMetriques;

    /* DefaultListModel */
    private DefaultListModel<String> classes;
    private DefaultListModel<String> attributs;
    private DefaultListModel<String> methods;
    private DefaultListModel<String> subClasses;
    private DefaultListModel<String> associations_Aggregations;
    private DefaultListModel<String> details;
    private DefaultListModel<String> metriques;

    /* Parser */
    private Parser parser;

    /* Current class */
    private String selectedClass = "";

    /**
     * Constructor
     * */
    public Ui (){

        /*Initialiser tout les DefaultListModel */
        this.classes = new DefaultListModel<>();
        this.attributs = new DefaultListModel<>();
        this.methods = new DefaultListModel<>();
        this.subClasses = new DefaultListModel<>();
        this.associations_Aggregations = new DefaultListModel<>();
        this.details = new DefaultListModel<>();
        this.metriques = new DefaultListModel<>();

        /* Initialiser tout les JList */
        this.listClasses = new JList<String>(this.classes);
        this.listAttributs = new JList<String>(this.attributs);
        this.listMethods = new JList<String>(this.methods);
        this.listSubClasses = new JList<String>(this.subClasses);
        this.listAssociations_Aggregations = new JList<String>(this.associations_Aggregations);
        this.listDetails = new JList<String>(this.details);
        this.listMetriques = new JList<String>(this.metriques);

        /* La selection des nodes dans les listes. Pas toutes les listes on peut selectionner. setEnabled = false
        * so there is no user input possible. */
        this.listAttributs.setEnabled(false);
        this.listMethods.setEnabled(false);
        this.listSubClasses.setEnabled(false);
        this.listDetails.setEnabled(false);

        /* Launch Ui Initialization */
        initializeUi();

    }

    /**
     * Method to initialize all the Ui components
     * */
    private void initializeUi() {

        setJListSize(this.listClasses, 20, 20, 100);
        setJListSize(this.listAttributs, 7, 20, 350);
        setJListSize(this.listMethods, 7, 20, 350);
        setJListSize(this.listSubClasses, 7, 20, 350);
        setJListSize(this.listAssociations_Aggregations, 7, 20, 350);
        setJListSize(this.listDetails, 5, 20, 600);
        setJListSize(this.listMetriques, 20, 20, 100);

        /* Frame Initialization */
        JFrame jFrame = new JFrame("UML Parser");
        jFrame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setResizable(true);

        /* Panel Initialization - This is the container of all the JFrame */
        JPanel jPanel = new JPanel();
        jFrame.add(jPanel);
        jPanel.setLayout(new BorderLayout());
        jPanel.setSize(FRAME_WIDTH, FRAME_HEIGHT);

        /* JPanelClass */
        JPanel jPanelClassParent = new JPanel();
        JPanel jPanelClass = new JPanel();
        JScrollPane jScrollPaneClass = new JScrollPane(this.listClasses);
        jPanelClass.add(jScrollPaneClass);
        jPanelClass.setBorder(new TitledBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(EtchedBorder.LOWERED)),"Classes"));
        jPanelClassParent.add(jPanelClass);
        jPanel.add(jPanelClassParent, BorderLayout.WEST);
        this.listClasses.addMouseListener(new MouseAdapter() { // Add MouseClicked Event
            @Override
            public void mouseClicked(MouseEvent e) {
                selectedClass = listClasses.getSelectedValue();
                printClassesInformation(selectedClass);
            }
        });

        /* This is the parent of GridLayout and Details */
        JPanel jPanelGridParent = new JPanel();
        jPanelGridParent.setLayout(new BorderLayout());

        /* JPanel de GridLayout pour Attributs, Methods, SubClasses, Association et Aggregations. */
        JPanel jPanelGrid = new JPanel();
        jPanelGrid.setLayout(new GridLayout(2,2));

        /* JPanelAttributs */
        JPanel jPanelAttributs = new JPanel();
        JScrollPane jScrollPaneAttributs = new JScrollPane(this.listAttributs);
        jPanelAttributs.add(jScrollPaneAttributs);
        jPanelAttributs.setBorder(new TitledBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(EtchedBorder.LOWERED)),"Attributs"));
        jPanelGrid.add(jPanelAttributs);

        /* JPanelMethods */
        JPanel jPanelMethods = new JPanel();
        JScrollPane jScrollPaneMethods = new JScrollPane(this.listMethods, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jPanelMethods.add(jScrollPaneMethods);
        jPanelMethods.setBorder(new TitledBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(EtchedBorder.LOWERED)),"Methods"));
        jPanelGrid.add(jPanelMethods);

        /* JPanelSubClass */
        JPanel jPanelSubClass = new JPanel();
        JScrollPane jScrollPaneSubClass = new JScrollPane(this.listSubClasses);
        jPanelSubClass.add(jScrollPaneSubClass);
        jPanelSubClass.setBorder(new TitledBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(EtchedBorder.LOWERED)),"Sous-Classes"));
        jPanelGrid.add(jPanelSubClass);

        /* JPanelAssociationsAndAggregations */
        JPanel jPanelAssociationsAndAggregations = new JPanel();
        JScrollPane jScrollPaneAssociationsAndAggregations = new JScrollPane(this.listAssociations_Aggregations);
        jPanelAssociationsAndAggregations.add(jScrollPaneAssociationsAndAggregations);
        jPanelAssociationsAndAggregations.setBorder(new TitledBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(EtchedBorder.LOWERED)),"Associations et Aggregations"));
        jPanelGrid.add(jPanelAssociationsAndAggregations);
        this.listAssociations_Aggregations.addMouseListener(new MouseAdapter() { // Add MouseClicked Event
            @Override
            public void mouseClicked(MouseEvent e) {
                selectedClass = listClasses.getSelectedValue();
                String selectedRAA = listAssociations_Aggregations.getSelectedValue();
                printClassDetails(selectedClass, selectedRAA);
            }
        });

        /* JPanelDetails */
        JPanel jPanelDetails = new JPanel();
        JScrollPane jScrollPaneDetails = new JScrollPane(this.listDetails, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jPanelDetails.add(jScrollPaneDetails);
        jPanelDetails.setBorder(new TitledBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(EtchedBorder.LOWERED)),"Détails"));
        jPanelGridParent.add(jPanelDetails, BorderLayout.SOUTH);

        /* JPanelMetriques */
        JPanel jPanelMetriques = new JPanel(new BorderLayout());
        JScrollPane jScrollPaneMetriques = new JScrollPane(this.listMetriques);
        jPanelMetriques.add(jScrollPaneMetriques, BorderLayout.NORTH);
        jPanelMetriques.setBorder(new TitledBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(EtchedBorder.LOWERED)),"Métriques"));

        this.listMetriques.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    printMetriquesDetails(listMetriques.getSelectedValue().substring(0, 3));
                } catch (NullPointerException nullPointerException){
                    // Do nothing ...
                }
            }
        });

        /* JButton CSV generator */
        JButton jButtonCsv = new JButton("CSV File");
        jButtonCsv.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    System.out.println("CSV File will be created on user's Desktop. ");
                    CsvGenerator csvGenerator = new CsvGenerator(parser.getClassDictionnary());
                    csvGenerator.generateCSV(parser.getClassDictionnary());
                    csvGenerator.writeAllDataToCSV();
                } catch (Exception exc){
                    exc.printStackTrace();
                    System.out.println(exc.getMessage());
                }
            }
        });
        jPanelMetriques.add(jButtonCsv, BorderLayout.SOUTH);
        jPanel.add(jPanelMetriques, BorderLayout.EAST);

        /* JPanelChooseFile */
        JPanel jPanelChooseFile = new JPanel();
        jPanelChooseFile.setLayout(new BorderLayout());
        jPanelChooseFile.setVisible(true);

        /* JTextField Initialization - for the FilePath to choose*/
        JTextField jTextFieldPath = new JTextField(10);
        jTextFieldPath.setText("");

        /* JButton Load */
        JButton jButtonLoad = new JButton("Load File");
        jButtonLoad.addActionListener(e -> {
            this.jFileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            this.jFileChooser.setDialogTitle("Choose UML diagram");
            this.jFileChooser.setAcceptAllFileFilterUsed(false);

            /* On accepte seulement les fichiers avec l'extension .ucd */
            FileNameExtensionFilter filter = new FileNameExtensionFilter("UML Diagram", "ucd");
            this.jFileChooser.addChoosableFileFilter(filter);

            int result = jFileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                String filePath = this.jFileChooser.getSelectedFile().getAbsolutePath();
                jTextFieldPath.setText(filePath);

                this.parser = new Parser(filePath);

                printClasses();
            }
        });

        /* JButton Calculate Metrics */
        JButton jButtonCalculateMetrics = new JButton("Calculate Metrics");
        jButtonCalculateMetrics.addActionListener(e -> {
            try {
                printMetriques(selectedClass);
            } catch (Exception ex){ // Mostly if NullPointerException occurs ...
                ex.printStackTrace();
                System.out.println(ex.getMessage());
            }
        });

        jPanelChooseFile.add(jButtonLoad, BorderLayout.WEST);
        jPanelChooseFile.add(jTextFieldPath, BorderLayout.CENTER);
        jPanelChooseFile.add(jButtonCalculateMetrics, BorderLayout.EAST);
        jPanel.add(jPanelChooseFile, BorderLayout.NORTH);
        jPanelGridParent.add(jPanelGrid, BorderLayout.CENTER);
        jPanel.add(jPanelGridParent, BorderLayout.CENTER);

        // Add the JPanel to the JFrame
        jFrame.getContentPane().add(jPanel);
        jFrame.setVisible(true);

    }

    /**
     * Method to print all metrics in a .ucd file
     * */
    private void printMetriques(String ci) {
        this.metriques.removeAllElements();

        Classe classe = this.parser.getClassDictionnary().get(ci);
        Metriques metriques = new Metriques(this.parser.getClassDictionnary());
        try {
            this.metriques.addElement(addElementMetric("ANA", metriques.ANA(classe)));
            this.metriques.addElement(addElementMetric("NOM", metriques.NOM(classe)));
            this.metriques.addElement(addElementMetric("NOA", metriques.NOA(classe)));
            this.metriques.addElement(addElementMetric("ITC", metriques.ITC(classe)));
            this.metriques.addElement(addElementMetric("ETC", metriques.ETC(classe)));
            this.metriques.addElement(addElementMetric("CAC", metriques.CAC(classe)));
            this.metriques.addElement(addElementMetric("DIT", metriques.DIT(classe)));
            this.metriques.addElement(addElementMetric("CLD", metriques.CLD(classe)));
            this.metriques.addElement(addElementMetric("NOC", metriques.NOC(classe)));
            this.metriques.addElement(addElementMetric("NOD", metriques.NOD(classe)));
        } catch (NullPointerException e) {
            System.out.println("--- ERROR Une des métriques ne peut pas être calculé. ---");
        }

    }

    /**
     * Function to add metric element to metrique DefaultList
     * @param metric : metric name
     * @param metricValue : metric value
     * */
    private String addElementMetric(String metric, Number metricValue){
        return MessageFormat.format("{0} = {1}", metric, metricValue);
    }

    /**
     * Method to print details once a metric is clicked on.
     * @param metriqueName : Name or metric
     * */
    private void printMetriquesDetails (String metriqueName){
        /* Clean the list so if user presses on another metric respective info is displayed */
        this.details.removeAllElements();
        Metriques metriques = new Metriques(this.parser.getClassDictionnary());
        String metricDetail = metriques.getDetails().get(metriqueName);
        this.details.addElement(metricDetail);
    }

    /**
     * Method to print all classes in a .ucd file
     * */
    private void printClasses() {
        this.classes.removeAllElements();
        this.attributs.removeAllElements();
        this.methods.removeAllElements();
        this.subClasses.removeAllElements();
        this.associations_Aggregations.removeAllElements();
        this.details.removeAllElements();
        for (String key : this.parser.getClassDictionnary().keySet()) {
            // Ajouter un vérificateur si une classe existante est déja dans la liste
            // Simplement ne pas l'ajouter et imprimer un message explicitant l'erreur
            this.classes.addElement(key);
        }
    }

    /**
     * Method to print details once selected Relation or Association and Agregation occurs.
     * @param selectedClass : selectedClass String
     * @param selectedRAA : selectedRAA (relations or associations and aggregations) String
     * */
    private void printClassDetails(String selectedClass, String selectedRAA){

        /* Clean the list so if user presses on another Relation or Association and Aggregation respective info is displayed */
        this.details.removeAllElements();

        /* Get relations associated to selectedClass */
        ArrayList<Relation> relations = this.parser.getClassDictionnary().get(selectedClass).getRelations();

        for(Relation rel : relations){
            if (selectedRAA.contains("(R)") && rel.getRelationName().equals(selectedRAA)){

                /* Create String Array of element containing \n */
                String toPrint[];
                toPrint = rel.getRelationDetails().split("\n");
                for (int i = 0; i< toPrint.length; i++) {
                    if (i==0)
                        this.details.addElement(toPrint[i]);
                    else
                        this.details.addElement("    " + toPrint[i]);
                    }

            } else if (selectedRAA.contains("(A)") && rel.getRelationName().equals(selectedRAA)) {
                /* Create String Array of element containing \n */
                String toPrint[];
                toPrint = rel.getRelationDetails().split("\n");
                for (int i = 0; i < toPrint.length; i++) {
                    if (toPrint[i].contains("\t")){
                        this.details.addElement("    " + toPrint[i]);
                    } else {
                        this.details.addElement(toPrint[i]);
                    }
                }
            }
        }

    }

    /**
     * Method to print all class information in a .ucd file
     * @param selectedClass : selectedClass String
     * */
    private void printClassesInformation(String selectedClass){

        /* Remove all elements so on next clicked class, respective information to that class will appear. */
        this.attributs.removeAllElements();
        this.methods.removeAllElements();
        this.subClasses.removeAllElements();
        this.associations_Aggregations.removeAllElements();
        this.details.removeAllElements();
        this.metriques.removeAllElements();

        try{
            /* Print attributs of respective clicked class in appropriate field. */
            for(int i = 0; i < this.parser.getClassDictionnary().get(selectedClass).getAttributs().size(); i++){
                this.attributs.addElement(this.parser.getClassDictionnary().get(selectedClass).getAttributs().get(i).toString());
            }
        } catch (NullPointerException e) {
            System.out.println("There are no attributs to put in the UI");
        }
        try {
            /* Print methods of respective clicked class in appropriate field. */
            for(int i = 0; i < this.parser.getClassDictionnary().get(selectedClass).getMethods().size(); i++){
                this.methods.addElement(this.parser.getClassDictionnary().get(selectedClass).getMethods().get(i).toString());
            }
        } catch (NullPointerException e) {
            System.out.println("There are no methods to put in the UI");
        }

        try {
            /* Print subClasses of respective clicked class in appropriate field.  */
            for(int i = 0; i < this.parser.getClassDictionnary().get(selectedClass).getSubClasses().size(); i++){
                this.subClasses.addElement(this.parser.getClassDictionnary().get(selectedClass).getSubClasses().get(i).toString());
            }
        } catch (NullPointerException e) {
            System.out.println("There are no subClasses to put in the UI");
        }

        try {
            /* Print relations of respective clicked class */
            for(int i = 0; i < this.parser.getClassDictionnary().get(selectedClass).getRelations().size(); i++){
                this.associations_Aggregations.addElement(this.parser.getClassDictionnary().get(selectedClass).getRelations().get(i).toString());
            }
        } catch (NullPointerException e) {
            System.out.println("There are no associations and/or Aggregations to put in the UI");
        }

    }

    /**
     * Method to set JListSize for all area in the Ui interface
     * @param jList : sets jList
     * @param rowCount : sets j:ist RowCount to rowCount (int)
     * @param cellHeight : sets jList CellHeight to cellHeight (int)
     * @param cellWidth : sets jList CellWidth to cellWidth (int)
     * */
    private void setJListSize(JList jList, int rowCount, int cellHeight, int cellWidth) {
        jList.setVisibleRowCount(rowCount);
        jList.setFixedCellHeight(cellHeight);
        jList.setFixedCellWidth(cellWidth);
    }

}
