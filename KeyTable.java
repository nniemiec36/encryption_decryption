/**
 * A class meant to represent a 5 by 5 key table to a Playfair Cipher.
 * The key is implemented as a two-dimensional array of char
 * variables.
 *
 * @author
 * Nicole Niemiec
 * CSE 214 REC08
 * HOMEWORK #4
 *
 * @version 1
 */

import java.util.Arrays;

public class KeyTable {

    private char[][] key = new char[5][5];

    /**
     * Constructor to create a new KeyTable object.
     */
    public KeyTable(){
        key = new char[5][5];
    }

    /**
     * This builds a new KeyTable object from the provided string
     * and returns it.
     * @param phrase
     *      The String used for the key.
     * @return
     *      Returns the newly built KeyTable object.
     * @throws IllegalArgumentException
     *      Throws an IllegalArgumentException if the keyphrase
     *      is null.
     */
    public static KeyTable buildFromString(String phrase) throws IllegalArgumentException{

        if(phrase == null || phrase.equals(""))
            throw new IllegalArgumentException("Phrase is null.");

        KeyTable keyTable = new KeyTable();

        String alphabet = "ABCDEFGHIKLMNOPQRSTUVWXYZ";

        StringBuilder result = new StringBuilder();

        //delete duplicate letters from the string
        for(int x = 0; x < phrase.length(); x++){
            if(!result.toString().contains(String.valueOf(phrase.charAt(x)))
                    && Character.isLetter(phrase.charAt(x))) {
                result.append(phrase.charAt(x));
            }
        }

        phrase = result.toString().toUpperCase();

        for(int x = 0; x < alphabet.length(); x++){
            if(!phrase.contains(alphabet.substring(x, x+1))) {
                result.append(alphabet.charAt(x));
            }
        }

        phrase = result.toString().toUpperCase();

        int row = 0;
        int col = 0;

        //fill in the phrase
        for(int x = 0; x < phrase.length(); x++){
            if(x % 5 == 0 && x != 0){
                col = 0;
                keyTable.key[++row][col++] = phrase.charAt(x);
            }
            else
                keyTable.key[row][col++] = phrase.charAt(x);
        }

        return keyTable;
    }

    /**
     * Returns the key matrix.
     * @return
     *      Returns the key matrix contained within this KeyTable.
     */
    public char[][] getKeyTable(){

        return key;

    }

    /**
     * Returns the row in which c occurs.
     * @param c
     *      The character to locate within the key matrix.
     * @return
     *      Returns the index in which c occurs.
     * @throws IllegalArgumentException
     *      Throws an IllegalArgumentException if c
     *      is not a valid letter in the key matrix.
     */
    public int findRow(char c) throws IllegalArgumentException{

        if(!Character.isLetter(c))
            throw new IllegalArgumentException(c + " is not a valid letter.");

        for(int row = 0; row < key.length; row++){
            for(int col = 0; col < key[0].length; col++){
                if(key[row][col] == c)
                    return row;
            }
        }
        return -1;

    }

    /**
     * A method that returns the column in which c occurs.
     * @param c
     *      The character to locate within the key matrix.
     * @return
     *      Returns the column index in which the character is located
     *      in the key matrix.
     * @throws IllegalArgumentException
     *      Throws an IllegalArgumentException if c
     *      is not a valid letter in the key matrix.
     */
    public int findCol(char c) throws IllegalArgumentException{

        if(!Character.isLetter(c))
            throw new IllegalArgumentException(c + " is not a valid letter.");

        for(int row = 0; row < key.length; row++){
            for(int col = 0; col < key[0].length; col++){
                if(key[row][col] == c)
                    return col;
            }
        }
        return -1;
    }

    /**
     * Returns a String representation of the KeyTable.
     * @return
     *      A String representation of the KeyTable.
     */
    @Override
    public String toString() {
        String array = "";
        for(int row = 0; row < key.length; row++){
            for(int col = 0; col < key[0].length; col++){
                array += key[row][col] + " ";
            }

            array += "\n";
        }

        return array;
    }
}
