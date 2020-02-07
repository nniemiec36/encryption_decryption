/*** A driver class meant to take in a phrase and create an encryption key, as well as encrypt and ecrypt
 * phrases. The driver can also change the current encryption key.
 *
 * @author
 * Nicole Niemiec
 * #112039349
 * CSE 214 REC08
 * HOMEWORK #4
 *
 * @version 1
 */

import java.util.Scanner;

public class PlayFairEncryptionEngine {

    private static Scanner input = new Scanner(System.in);
    private static KeyTable key; //= new KeyTable();
    private static String keyPhrase = "";

    /**
     * Method that runs the program.
     * @param args
     *      The program as a String array.
     */
    public static void main(String[] args) {


        System.out.println("Enter key phrase: ");
        keyPhrase = input.nextLine();

        if(keyPhrase.equals("")){
            System.out.println("Please enter a phrase: ");
            keyPhrase = input.nextLine();
            try{
                key = KeyTable.buildFromString(keyPhrase);
            }catch (IllegalArgumentException ex){
                System.out.println("Phrase is null.");
            }

        }
        else {
            System.out.println("Generation success!");
        }

        main_menu();

    }

    public static void main_menu() {

        String command = "";

        do {

            System.out.println("Main Menu:");
            System.out.println("(CK) \t Change Key");
            System.out.println("(PK) \t Print Key");
            System.out.println("(EN) \t Encrypt");
            System.out.println("(DE) \t Decrypt");
            System.out.println("(Q) \t Quit");

            System.out.println("Please enter a command");
            command = input.nextLine().toUpperCase();

            switch (command){
                case "CK": main_changeKey();
                    break;
                case "PK": main_printKey();
                    break;
                case "EN": main_encrypt();
                    break;
                case "DE": main_decrypt();
                    break;
                case "Q": System.out.println("Program terminating....");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }


        }while(!command.equalsIgnoreCase("Q"));


    }

    public static void main_changeKey(){

        //String phrase = "";
        System.out.println("Enter key phrase: ");
        keyPhrase = input.nextLine();

        if(keyPhrase.equals("")){
            System.out.println("Please enter a phrase: ");
            keyPhrase = input.nextLine();
        }
        else {
            System.out.println("Generation success!");
        }


        try {
            key = KeyTable.buildFromString(keyPhrase);
            System.out.println(key.toString());
        }catch(IllegalArgumentException ex){
            System.out.println("Phrase is null.");
        }



    }

    public static void main_printKey(){


        try{
            key = KeyTable.buildFromString(keyPhrase);
            System.out.println(key.toString());
        } catch (IllegalArgumentException ex){
            System.out.println("Phrase is null");
        }



    }

    public static void main_encrypt(){

        String phrase = "";
        System.out.println("Please enter a phrase to encrypt: ");
        phrase = input.nextLine();

        try {

            Phrase phraseQueueP = Phrase.buildPhraseFromStringForEnc(phrase);

            KeyTable keyTable = KeyTable.buildFromString(keyPhrase);

            Phrase phraseQueueK = phraseQueueP.encrypt(keyTable);

            System.out.println("Encrypted text is: " + phraseQueueK.toString());
        } catch(IllegalArgumentException ex){
            System.out.println("Phrase is null");
        }

    }

    public static void main_decrypt(){

        String phrase = "";
        System.out.println("Please enter a phrase to decrypt: ");
        phrase = input.nextLine();

        while(phrase.length() % 2 != 0 || phrase.contains("J")) {
            System.out.println("Error: Phrase for decryption must valid.");
            System.out.println("Please enter a phrase to decrypt: ");
            phrase = input.nextLine();
        }


        try {
            Phrase phraseQueueD = Phrase.buildPhraseFromStringForEnc(phrase);

            KeyTable keyTable = KeyTable.buildFromString(keyPhrase);

            Phrase phraseQueueP = phraseQueueD.decrypt(keyTable);

            System.out.println("Decrypted text is: " + phraseQueueP.toString());
        } catch (IllegalArgumentException ex){
            System.out.println("Phrase is null.");
        }



    }

}
