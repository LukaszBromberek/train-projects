package Module3.Crypto;

import java.util.regex.Pattern;

//Encrypt and decrypt text using caesarian code
public class Crypto {

    public static void main (String[] args){
        Crypto cipher = new Crypto();

        String text = "Ala ma, kota!";
        System.out.println(text);

        text = cipher.encryptString(text,5,2);
        System.out.println(text);

        text = cipher.decryptString(text,5);
        System.out.println(text);
    }

    //Normalize given text - remove special symbols and uppercase everything
    private static String normalizeText(String text){
        String ret_val = text;


        ret_val = Crypto.removeSymbols(ret_val);
        ret_val = ret_val.toUpperCase();
        return ret_val;
    }

    //Remove symbols according to ASCII tables
    private static String removeSymbols(String text){
        String ret_val = text;

        //Remove ASCII 32-47
        for (int i=32; i<=64; i++)
        {
            ret_val = ret_val.replaceAll(Pattern.quote(String.valueOf((char)i)),"");
        }

        //Remove ASCII 91-96
        for (int i=91; i<=96; i++)
        {
            ret_val = ret_val.replaceAll(Pattern.quote(String.valueOf((char)i)),"");
        }
        //Remove ASCII 123-127
        for (int i=123; i<=127; i++)
        {
            ret_val = ret_val.replaceAll(Pattern.quote(String.valueOf((char)i)),"");
        }

        return ret_val;
    }

    //Encrypt string using given key
    private static String caesarify (String text, int key){
        String ret_val = "";

        for (int i=0; i<text.length(); i++) {
            int ascii = (int) text.charAt(i);

            if (ascii + key > 90) {
                ret_val = ret_val + String.valueOf((char) (ascii+key - 25));
            }
            else {
                ret_val = ret_val + String.valueOf((char) (ascii + key));
            }
        }
        return ret_val;
    }

    //Break text into codegroups
    private static String groupify (String text, int groupSize){
        String ret_val="";
        for(int i=0;i<text.length();i++) {
            ret_val = ret_val + String.valueOf(text.charAt(i));
            if(i%(groupSize)==(groupSize-1)){
                ret_val = ret_val + " ";
            }
        }

        if(text.length()%groupSize!=0){
            for (int i=0;i<(groupSize-text.length()%groupSize);i++) {
                ret_val = ret_val + "x";
            }
        }

        return ret_val;
    }

    //Encrypt text using caesarian code
    public String encryptString (String text, int key, int groupSize){
        String ret_val = Crypto.normalizeText(text);
        ret_val = Crypto.caesarify(ret_val, key);
        ret_val = Crypto.groupify(ret_val,groupSize);
        return ret_val;
    }

    //Remove spaces and x at the end
    private static String ungroupify (String text){
        String ret_val = text.replaceAll(" ","");
        return ret_val.replaceAll("x","");
    }

    //Try to decrypt code with given parameters
    public String decryptString (String text, int key){
        String temp = Crypto.ungroupify(text);
        String ret_val = "";

        for (int i=0; i<temp.length(); i++) {
            int ascii = (int) temp.charAt(i);

            if (ascii - key < 65) {
                ret_val = ret_val + String.valueOf((char) (ascii- key + 25));
            }
            else {
                ret_val = ret_val + String.valueOf((char) (ascii - key));
            }
        }
        return ret_val;
    }
}
