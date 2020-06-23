package Model;

import java.util.LinkedList;

public class GeneradorCodi {

    private String codi;
    private LinkedList<String> codisGenerats;

    public GeneradorCodi(){
        codisGenerats = new LinkedList<>();
    }

    public String RandomString() {
        do {
            // chose a Character random from this String
            String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                    + "0123456789"
                    + "abcdefghijklmnopqrstuvxyz";

            // create StringBuffer size of AlphaNumericString
            StringBuilder sb = new StringBuilder(9);

            for (int i = 0; i < 9; i++) {

                // generate a random number between
                // 0 to AlphaNumericString variable length
                int index
                        = (int) (AlphaNumericString.length()
                        * Math.random());

                // add Character one by one in end of sb
                sb.append(AlphaNumericString
                        .charAt(index));
            }
            codi =  sb.toString();
        } while(codisGenerats.contains(codi));
        codisGenerats.add(codi);
        return codi;
    }
}
