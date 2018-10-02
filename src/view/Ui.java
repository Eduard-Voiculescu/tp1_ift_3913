package view;
/* Imports */
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.*;
import model.*;

public class Ui {

    private static final int FRAME_HEIGHT = 800;
    private static final int FRAME_WIDTH = 800;

    /* JFileChoser */
    private JFileChooser jFileChooser;

    /* JList */
    private JList<String> listClasses;
    private JList<String> listAttributs;
    private JList<String> listMethods;
    private JList<String> listSubClasses;
    private JList<String> listAssociations_Agregations;
    private JList<String> listDetails;

    /* DefaultListModel */
    private DefaultListModel<String> classes;
    private DefaultListModel<String> attributs;
    private DefaultListModel<String> methods;
    private DefaultListModel<String> subClasses;
    private DefaultListModel<String> associations_Agregations;
    private DefaultListModel<String> details;

    /* Parser */
    private Parser parser;

    /**
     * Ui constructor
     * */
    public Ui (){

        /*Initialiser tout les DefaultListModel */
        this.classes = new DefaultListModel<>();
        this.attributs = new DefaultListModel<>();
        this.methods = new DefaultListModel<>();
        this.subClasses = new DefaultListModel<>();
        this.associations_Agregations = new DefaultListModel<>();
        this.details = new DefaultListModel<>();

        /* Initialiser tout les JList */
        this.listClasses = new JList<String>(this.classes);
        this.listAttributs = new JList<String>(this.attributs);
        this.listMethods = new JList<String>(this.methods);
        this.listSubClasses = new JList<String>(this.subClasses);
        this.listAssociations_Agregations = new JList<String>(this. associations_Agregations);
        this.listDetails = new JList<String>(this.details);

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
        setJListSize(this.listAttributs, 7, 20, 300);
        setJListSize(this.listMethods, 7, 20, 300);
        setJListSize(this.listSubClasses, 7, 20, 300);
        setJListSize(this.listAssociations_Agregations, 7, 20, 300);
        setJListSize(this.listDetails, 5, 20, 400);

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
        JPanel jPanelClass = new JPanel();
        JScrollPane jScrollPaneClass = new JScrollPane(this.listClasses);
        jPanelClass.add(jScrollPaneClass);
        jPanelClass.setBorder(new TitledBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(EtchedBorder.LOWERED)),"Classes"));
        jPanel.add(jPanelClass, BorderLayout.WEST);
        listClasses.addMouseListener(new MouseAdapter() { // Add MouseClicked Event
            @Override
            public void mouseClicked(MouseEvent e) {
                String selectedClass = listClasses.getSelectedValue();
                printClassesInformation(selectedClass);
            }
        });

        /* This is the parent of GridLayout and Details */
        JPanel jPanelGridParent = new JPanel();
        jPanelGridParent.setLayout(new BorderLayout());

        /* JPanel de GridLayout pour Attributs, Methods, SubClass, Association et Aggregations. */
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
        JScrollPane jScrollPaneMethods = new JScrollPane(this.listMethods);
        jScrollPaneMethods.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
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
        JScrollPane jScrollPaneAssociationsAndAggregations = new JScrollPane(this.listAssociations_Agregations);
        jPanelAssociationsAndAggregations.add(jScrollPaneAssociationsAndAggregations);
        jPanelAssociationsAndAggregations.setBorder(new TitledBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(EtchedBorder.LOWERED)),"Associations et Aggregations"));
        jPanelGrid.add(jPanelAssociationsAndAggregations);

        /* JPanelDetails */
        JPanel jPanelDetails = new JPanel();
        JScrollPane jScrollPaneDetails = new JScrollPane(this.listDetails);
        jPanelDetails.add(jScrollPaneDetails);
        jPanelDetails.setBorder(new TitledBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(EtchedBorder.LOWERED)),"Détails"));
        jPanelGridParent.add(jPanelDetails, BorderLayout.SOUTH);

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

                // TODO: Add parser here ...

                this.parser = new Parser(filePath);

                printClasses();
            }
        });

        jPanelChooseFile.add(jButtonLoad, BorderLayout.WEST);
        jPanelChooseFile.add(jTextFieldPath, BorderLayout.CENTER);
        jPanel.add(jPanelChooseFile, BorderLayout.NORTH);
        jPanelGridParent.add(jPanelGrid, BorderLayout.CENTER);
        jPanel.add(jPanelGridParent, BorderLayout.CENTER);

        // Add the JPanel to the JFrame
        jFrame.getContentPane().add(jPanel);
        jFrame.setVisible(true);

    }

    /**
     * Method to print all classes in a .ucd file
     * */
    private void printClasses() {
        for (String key : this.parser.getClassDictionnary().keySet()) {
            this.classes.addElement(key);
        }
    }

    /**
     * Method to print all class information in a .ucd file
     * */
    private void printClassesInformation(String selectedClass){

        /* Remove all elements so on next clicked class, respective information to that class will appear. */
        this.attributs.removeAllElements();
        this.methods.removeAllElements();
        this.subClasses.removeAllElements();
        this.associations_Agregations.removeAllElements();
        this.details.removeAllElements();

        for(int i = 0; i < this.parser.getClassDictionnary().get(selectedClass).getAttributs().size(); i++){
            this.attributs.addElement(this.parser.getClassDictionnary().get(selectedClass).getAttributs().get(i).toString());
        }

        for(int i = 0; i < this.parser.getClassDictionnary().get(selectedClass).getMethods().size(); i++){
            this.methods.addElement(this.parser.getClassDictionnary().get(selectedClass).getMethods().get(i).toString());
        }

    }

    /**
     * Method to set JListSize for all area in the Ui interface
     * */
    private void setJListSize(JList jList, int rowCount, int cellHeight, int cellWidth) {
        jList.setVisibleRowCount(rowCount);
        jList.setFixedCellHeight(cellHeight);
        jList.setFixedCellWidth(cellWidth);
    }

}
