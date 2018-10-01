package view;

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
