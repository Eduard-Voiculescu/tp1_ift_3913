package view;

/**
 * Created by Eduard Voiculescu and Sami Steenhaut on 4 october 2018
 */

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException{

        try {
            Ui ui = new Ui();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

    }

}
