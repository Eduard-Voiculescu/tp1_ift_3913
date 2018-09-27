
/* Imports */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.*;
import java.io.File;

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

    /* JTextArea */
    private JTextArea jTextDetails;

    public Ui (){

        String week[]= { "Monday","Tuesday","Wednesday",
                "Thursday","Friday","Saturday","Sunday"};

        listClasses = new JList<String>(week);
        listAttributs = new JList<String>(week);
        listMethods = new JList<String>(week);
        listSubClasses = new JList<String>(week);
        listAssociations_Agregations = new JList<String>(week);
        listDetails = new JList<String>(week);

        setJListSize(listClasses, 20, 20, 100);
        setJListSize(listAttributs, 5, 20, 200);
        setJListSize(listMethods, 5, 20, 200);
        setJListSize(listSubClasses, 5, 20, 200);
        setJListSize(listAssociations_Agregations, 5, 20, 200);
        setJListSize(listDetails, 5, 20, 400);

        /* La selection des nodes dans les listes. Pas toutes les listes on peut selectionner. setEnabled = false
        * so there is no user input possible. */
        listAttributs.setEnabled(false);
        listMethods.setEnabled(false);
        listSubClasses.setEnabled(false);
        listDetails.setEnabled(false);

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
        JScrollPane jScrollPaneClass = new JScrollPane(listClasses);
        jPanelClass.add(jScrollPaneClass);
        jPanelClass.setBorder(new TitledBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(EtchedBorder.LOWERED)),"Classes"));
        jPanel.add(jPanelClass, BorderLayout.WEST);

        /* This is the parent of GridLayout and Details */
        JPanel jPanelGridParent = new JPanel();
        jPanelGridParent.setLayout(new BorderLayout());

        /* JPanel de GridLayout pour Attributs, Methods, SubClass, Association et Aggregations. */
        JPanel jPanelGrid = new JPanel();
        jPanelGrid.setLayout(new GridLayout(2,2));

        /* JPanelAttributs */
        JPanel jPanelAttributs = new JPanel();
        JScrollPane jScrollPaneAttributs = new JScrollPane(listAttributs);
        jPanelAttributs.add(jScrollPaneAttributs);
        jPanelAttributs.setBorder(new TitledBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(EtchedBorder.LOWERED)),"Attributs"));
        jPanelGrid.add(jPanelAttributs);

        /* JPanelMethods */
        JPanel jPanelMethods = new JPanel();
        JScrollPane jScrollPaneMethods = new JScrollPane(listMethods);
        jPanelMethods.add(jScrollPaneMethods);
        jPanelMethods.setBorder(new TitledBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(EtchedBorder.LOWERED)),"Methods"));
        jPanelGrid.add(jPanelMethods);

        /* JPanelSubClass */
        JPanel jPanelSubClass = new JPanel();
        JScrollPane jScrollPaneSubClass = new JScrollPane(listSubClasses);
        jPanelSubClass.add(jScrollPaneSubClass);
        jPanelSubClass.setBorder(new TitledBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(EtchedBorder.LOWERED)),"Sous-Classes"));
        jPanelGrid.add(jPanelSubClass);

        /* JPanelAssociationsAndAggregations */
        JPanel jPanelAssociationsAndAggregations = new JPanel();
        JScrollPane jScrollPaneAssociationsAndAggregations = new JScrollPane(listAssociations_Agregations);
        jPanelAssociationsAndAggregations.add(jScrollPaneAssociationsAndAggregations);
        jPanelAssociationsAndAggregations.setBorder(new TitledBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(EtchedBorder.LOWERED)),"Associations et Aggregations"));
        jPanelGrid.add(jPanelAssociationsAndAggregations);

        /* JPanelDetails */
        JPanel jPanelDetails = new JPanel();
        JScrollPane jScrollPaneDetails = new JScrollPane(listDetails);
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
        jTextFieldPath.setText("Ligue.ucd");

        /* JButton Load */
        JButton jButtonLoad = new JButton("Load File");
        jButtonLoad.addActionListener(e -> {
            jFileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            int result = jFileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                jTextFieldPath.setText(jFileChooser.getSelectedFile().toString());
                // TODO: Add parser here ...
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

    private JLabel createJLabel(String labelName, JPanel jPanel, int width, int height, int x, int y) {
        JLabel jLabel = new JLabel(labelName);
        ;
        jPanel.add(jLabel);
        jLabel.setSize(width, height);
        jLabel.setLocation(x, y);
        return jLabel;
    }

    private JTextArea createTextArea(JPanel jPanel, int width, int height, int x, int y) {
        JTextArea jTextArea = new JTextArea("");
        jTextArea.setBounds(x, y, width, height);
        jTextArea.setEditable(false);
        jPanel.add(jTextArea);
        return jTextArea;
    }

    private JScrollPane createScrollPane(JPanel jPanel, JTextArea jTextArea, int width, int height, int x, int y) {
        JScrollPane jScrollPane = new JScrollPane(jTextArea);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane.setSize(width, height);
        jScrollPane.setLocation(x, y);
        jPanel.add(jScrollPane);
        jScrollPane.setVisible(true);
        return jScrollPane;
    }

    private void setJListSize(JList jList, int rowCount, int cellHeight, int cellWidth) {
        jList.setVisibleRowCount(rowCount);
        jList.setFixedCellHeight(cellHeight);
        jList.setFixedCellWidth(cellWidth);
    }

    public static void main(String[] args) {
        Ui ui = new Ui();
    }

}
