package metriques;

/**
 * Created by Eduard Voiculescu and Sami Steenhaut on 4 november 2018
 */

import model.Attribut;
import model.Classe;
import model.Parser;
import model.Method;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Metriques {
    /**
     * @properties
     * HashMap<String, Classe>: classes
     * HashMap<String, String>: details
     * */

    /* Dictionnary containing string -> classes */
    private HashMap<String, Classe> classes;

    /* Dictionnary containing string -> string */
    private HashMap<String, String> details;

    /* List of Strings containing the details of a metric */
    private String[] detailsMetriques = {
            "ANA(ci) : Nombre moyen d’arguments des méthodes locales pour la classe ci.",
            "NOM(ci) : Nombre de méthodes locales/héritées de la classe ci. Dans le cas où une méthode est héritée \n" +
                    "et redéfinie localement (même nom, même ordre et types des arguments et même type de retour), \n" +
                    "elle ne compte qu’une fois.",
            "NOA(ci) : Nombre d’attributs locaux/hérités de la classe ci.",
            "ITC(ci) : Nombre de fois où d’autres classes du diagramme apparaissent comme types des arguments \n" +
                    "des méthodes de ci.",
            "ETC(ci) : Nombre de fois où ci apparaît comme type des arguments dans les méthodes des autres \n" +
                    "classes du diagramme.",
            "CAC(ci) : Nombre d’associations (incluant les agrégations) locales/héritées auxquelles participe \n" +
                    "une classe ci.",
            "DIT(ci) : Taille du chemin le plus long reliant une classe ci à une classe racine dans le graphe \n" +
                    "d’héritage.",
            "CLD(ci) : Taille du chemin le plus long reliant une classe ci à une classe feuille dans le \n" +
                    "graphe d’héritage.",
            "NOC(ci) : Nombre de sous-classes directes de ci.",
            "NOD(ci) : Nombre de sous-classes directes et indirectes de ci."
    };

    /**
     * Constructor
     * @param classes (required) : name of classes to calculate metrics
     * */
    public Metriques(HashMap<String, Classe> classes) {
        this.classes = classes;
        this.details = new HashMap<String, String >();
        for (String detail : this.detailsMetriques){
            this.details.put(detail.substring(0, 3), detail); // We use substring because it modifies the string
        }
    }

    /**
     * Getter of classes
     * @return : HashMap<String, Classe>
     * */
    public HashMap<String, Classe> getClasses() {
        return classes;
    }

    /**
     * Setter of classes
     * @param classes : sets HashMap<String, Classe> of classes
     * */
    public void setClasses(HashMap<String, Classe> classes) {
        this.classes = classes;
    }

    /**
     * Getter of details
     * @return : HashMap<String, String>
     * */
    public HashMap<String, String> getDetails() {
        return details;
    }

    /**
     * Setter of details
     * @param details : sets HashMap<String, String> of details
     * */
    public void setDetails(HashMap<String, String> details) {
        this.details = details;
    }

    /**
     * Getter of detailsMetriques
     * @return : String[]
     * */
    public String[] getDetailsMetriques() {
        return detailsMetriques;
    }

    /**
     * Setter of detailsMetriques
     * @param detailsMetriques : sets String[] of detailsMetriques
     * */
    public void setDetailsMetriques(String[] detailsMetriques) {
        this.detailsMetriques = detailsMetriques;
    }

    /**
     * ANA(ci) : Nombre moyen d’arguments des méthodes locales pour la classe ci.
     * @param ci : classe à calculer la métrique ANA
     * @return : double
     * */
    public double ANA(Classe ci){
        ArrayList<Method> methods = ci.getMethods();
        if (methods.size() == 0){ // Il n'y a pas de méthodes dans la classe ci
            return 0.0;
        }
        double resultat = 0.0;
        for (Method method: methods){
            resultat += method.getAttributs().size(); // Ceci va retourner le nombre d'attributs de chaque méthode
        }

        return resultat / methods.size();
    }

    /**
     * NOM(ci) : Nombre de méthodes locales/héritées de la classe ci. Dans le cas où une méthode est héritée
     * et redéfinie localement (même nom, même ordre et types des arguments et même type de retour),
     * elle ne compte qu’une fois.
     * -----------------------------------------------------------------------------------------------------
     * @param ci : classe à calculer la métrique NOM
     * @return : int
     * */
    public int NOM(Classe ci){
        ArrayList<Method> methods = new ArrayList<>();
        return NOM(ci, methods);
    }

    /**
     * Nous avons décidé d'utiliser une autre fonction (récusive) pour calculé le NOM.
     * La raison est que nous devons considérer que chaque override ne vaut que 1.
     * Donc la méthode plus haut (NOM(Classe ci)) gardera compte des méthodes
     * -------------------------------------------------------------------------------
     * @param ci : classe à calculer la métrique NOM
     * @return : int
     * */
    private int NOM(Classe ci, ArrayList<Method> methods){
        int resultat = 0;

        /* Ici nous prenons toutes méthodes d'une classes et les ajoutons. */
        for (Method method : ci.getMethods()){
            if(!methods.contains(method)){
                methods.add(method); // la méthode n'existe pas déja, nous la rajoutons
                resultat++;
            }
        }

        /* Si une classe a un parent aller regarder ses méthodes aussi puisque nous les héritons. */
        if (ci.getParent().size() > 0){
            for (Classe parent : ci.getParent()){
                resultat += NOM(parent, methods);
            }
        }

        return resultat;
    }

    /**
     * NOA(ci) : Nombre d’attributs locaux/hérités de la classe ci.
     * ------------------------------------------------------------
     * @param ci : classe à calculer la métrique NOA
     * @return : int
     * */
    public int NOA(Classe ci){

        int resultat = ci.getAttributs().size();

        if (ci.getParent().size() > 0){
            /* Puisque une classe peut avoir un parent et le parent d'une classe
             * peut avoir un parent et ainsi de suite, nous passons récusivement sur
              * tout les parents. */
            for (Classe parent : ci.getParent()){
                resultat += NOA(parent);
            }
        }

        return resultat;
    }

    /**
     * ITC(ci) : Nombre de fois où d’autres classes du diagramme apparaissent comme types des arguments des méthodes de ci.
     * --------------------------------------------------------------------------------------------------------------------
     * @param ci : classe à calculer la métrique ITC
     * @return : int
     * */
    public int ITC(Classe ci){
        ArrayList<Method> methods = ci.getMethods();
        int resultat = 0;

        for (Method method : methods){
            for (Attribut attribut : method.getAttributs()){
                if (classes.containsKey(attribut.getAttributType())){
                    resultat++;
                }
            }
        }

        return resultat;
    }

    /**
     * ETC(ci) : Nombre de fois où ci apparaît comme type des arguments dans les méthodes des autres classes du diagramme.
     * -------------------------------------------------------------------------------------------------------------------
     * @param ci : classe à calculer la métrique ETC
     * @return : int
     * */
    public int ETC(Classe ci){
        String className = ci.getClassName();
        int resultat = 0;

        for (Map.Entry<String, Classe> classesEntry : classes.entrySet()){
            if (!classesEntry.getKey().equals(className)){ // Nous regardons les classes autres que soi-même
                for (Method method : classesEntry.getValue().getMethods()){
                    for (Attribut attribut : method.getAttributs()){
                        if (attribut.getAttributType().equals(className)){
                            resultat++;
                        }
                    }
                }
            }
        }

        return resultat;
    }

    /**
     * CAC(ci) : Nombre d’associations (incluant les agrégations) locales/héritées auxquelles participe une classe ci.
     * ---------------------------------------------------------------------------------------------------------------
     * @param ci : classe à calculer la métrique CAC
     * @return : int
     * */
    public int CAC(Classe ci){
        int resultat = ci.getRelations().size();
        /* Puisque une classe peut avoir un parent et le parent d'une classe
         * peut avoir un parent et ainsi de suite, nous passons récusivement sur
         * tout les parents. */
        if (ci.getParent().size() > 0){
            for (Classe parent : ci.getParent()){
                resultat += CAC(parent);
            }
        }
        return resultat;
    }

    /**
     * DIT(ci) : Taille du chemin le plus long reliant une classe ci à une classe racine dans le graphe d’héritage.
     * ------------------------------------------------------------------------------------------------------------
     * @param ci : classe à calculer la métrique DIT
     * @return : int
     * */
    public int DIT(Classe ci) {
        /* Puisque une classe peut avoir un parent et le parent d'une classe
         * peut avoir un parent et ainsi de suite, nous passons récusivement sur
         * tout les parents. */
        if (ci.getParent().size() > 0){
            int resultat = 0;
            for (Classe parent : ci.getParent()){
                int resultatTemporaire = DIT(parent);
                if (resultatTemporaire > resultat){
                    resultat = resultatTemporaire;
                }
            }
            return resultat + 1;
        } else { // La classe ci n'a pas de parent
            return 0;
        }
    }

    /**
     * CLD(ci) : Taille du chemin le plus long reliant une classe ci à une classe feuille dans le graphe d’héritage.
     * ------------------------------------------------------------------------------------------------------------
     * @param ci : classe à calculer la métrique CLD
     * @return : int
     * */
    public int CLD(Classe ci){
        /* Puisque une classe peut avoir un enfant et l'enfant d'une classe
         * peut avoir un enfant et ainsi de suite, nous passons récusivement sur
         * tout les enfants. */
        if (ci.getSubClasses().size() > 0){
            int resultat = 0;
            for (Classe enfant : ci.getSubClasses()){
                int resultatTemporaire = CLD(enfant);
                if (resultatTemporaire > resultat){
                    resultat = resultatTemporaire;
                }
            }
            return 1 + resultat;
        } else {
            return 0;
        }
    }

    /**
     * NOC(ci) : Nombre de sous-classes directes de ci.
     * ------------------------------------------------
     * @param ci : classe à calculer la métrique NOC
     * @return : int
     * */
    public int NOC(Classe ci){
        return ci.getSubClasses().size();
    }

    /**
     * NOD(ci) : Nombre de sous-classes directes et indirectes de ci.
     * --------------------------------------------------------------
     * @param ci : classe à calculer la métrique NOC
     * @return : int
     * */
    public int NOD(Classe ci){
        /* Puisque une classe peut avoir un enfant et l'enfant d'une classe
         * peut avoir un enfant et ainsi de suite, nous passons récusivement sur
         * tout les enfants. */
        int resultat = 0;
        if (ci.getSubClasses().size() > 0) {
            for (Classe sousClasses : ci.getSubClasses()){
                resultat += NOD(sousClasses) + 1;
            }
            return resultat;
        } else { // Pas de sous-classes
            return 0;
        }
    }

}
