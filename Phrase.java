/**
 * A wrapper class for a queue. This queue will be utilized to retain the order of
 * the phrase that is provided. The Phrase class utilizes an ArrayList of Bigrams
 * to implement the Queue properly.
 *
 * @author
 * Nicole Niemiec
 * #112039349
 * CSE 214 REC08
 * HOMEWORK #4
 *
 * @version 1
 */

import java.util.ArrayList;

public class Phrase extends ArrayList<Bigram> implements Queue {

    /**
     * Builds and returns a new Phrase object, which is a queue containing bigrams
     * representing the String inserted as the parameter.
     * @param s
     *      The String to represent as a Bigram queue.
     * @return
     *      Returns the new Phrase object which contains a queue of Bigram objects
     *      representing s.
     */
    public static Phrase buildPhraseFromStringForEnc(String s){

        Phrase phraseQueue = new Phrase();

        s = s.toUpperCase();

        String newString = "";

        for(int x = 0; x < s.length(); x++){
            if(Character.isLetter(s.charAt(x)))
                newString += s.charAt(x);
        }

        newString = newString.replaceAll("J", "I");

        int x = 0;

        int size = newString.length();

        while(x < size) {

            if (x + 1 < size) {
                if (newString.charAt(x) == newString.charAt(x + 1)) {
                    Bigram b = new Bigram(newString.charAt(x), 'X');
                    phraseQueue.enqueue(b);
                    x++;
                } else if (newString.charAt(x) != newString.charAt(x + 1)) {
                    Bigram b = new Bigram(newString.charAt(x), newString.charAt(x + 1));
                    phraseQueue.enqueue(b);
                    x += 2;
                }
            }
            else if(x + 1 >= size && x >= size - 1){
                Bigram b = new Bigram(newString.charAt(x), 'X');
                phraseQueue.enqueue(b);
                x++;
            }

        }

        return phraseQueue;

    }

    //build into helper methods //also need to dequeue the original

    /**
     * Encrypts this Phrase, storing the encrypted bigrams in a new Phrase queue object
     * and then returns this object.
     * @param key
     *      The KeyTable needed to encrypt this Phrase.
     * @return
     *      Returns the new Phrase object which contains a queue of Bigram objects
     *      representing the encrypted version of this Phrase.
     * @throws IllegalArgumentException
     *      Throws an IllegalArgumentException if the key is null.
     */
    public Phrase encrypt(KeyTable key) throws IllegalArgumentException{

        if(key == null)
            throw new IllegalArgumentException("Key is null.");

        Phrase phraseEncrypt = new Phrase();

        int index = 0;
        int row1 = 0;
        int col1 = 0;
        int col2 = 0;
        int row2 = 0;

        int x = 0;
        int size = size();

       while(x < size){

            Bigram d = dequeue();

           try{
               col1 = key.findCol(d.getFirst());
           } catch(IllegalArgumentException ex){
               System.out.println("Could not find letter.");
           }

           try{
               row1 = key.findRow(d.getFirst());
           } catch(IllegalArgumentException ex){
               System.out.println("Could not find letter.");
           }

           try{
               col2 = key.findCol(d.getSecond());
           } catch(IllegalArgumentException ex){
               System.out.println("Could not find letter.");
           }

           try{
               row2 = key.findRow(d.getSecond());
           } catch(IllegalArgumentException ex){
               System.out.println("Could not find letter.");
           }

            if(key.findCol(d.getFirst()) == key.findCol(d.getSecond())){

                if(row1 == 4){
                    Bigram b = new Bigram(key.getKeyTable()[0][col1], key.getKeyTable()[row2 + 1][col2]);
                    phraseEncrypt.enqueue(b);
                }
                else if(row2 == 4) {
                    Bigram b = new Bigram(key.getKeyTable()[row1 + 1][col1], key.getKeyTable()[0][col2]);
                    phraseEncrypt.enqueue(b);
                }
                else {
                    Bigram b = new Bigram(key.getKeyTable()[row1 + 1][col1], key.getKeyTable()[row2 + 1][col2]);
                    phraseEncrypt.enqueue(b);
                }

            }
            else if(key.findRow(d.getFirst()) == key.findRow(d.getSecond())){

                if(col1 == 4){
                    Bigram b = new Bigram(key.getKeyTable()[row1][0], key.getKeyTable()[row2][col2 + 1]);
                    phraseEncrypt.enqueue(b);
                }
                else if(col2 == 4) {
                    Bigram b = new Bigram(key.getKeyTable()[row1][col1 + 1], key.getKeyTable()[row2][0]);
                    phraseEncrypt.enqueue(b);
                }
                else {
                    Bigram b = new Bigram(key.getKeyTable()[row1][col1 + 1], key.getKeyTable()[row2][col2 + 1]);
                    phraseEncrypt.enqueue(b);
                }

            }
            else{

                int newCol1 = col2;
                int newCol2 = col1;

                Bigram b = new Bigram(key.getKeyTable()[row1][newCol1], key.getKeyTable()[row2][newCol2]);
                phraseEncrypt.enqueue(b);
            }

            x++;
        }

        return phraseEncrypt;
    }

    /**
     * Decrypts this Phrase, storing the decrypted bigrams in a new Phrase queue object
     * and returning it.
     * @param key
     *       The KeyTable needed to decrypt this Phrase.
     * @return
     *      Returns the new Phrase object which contains a queue of Bigram objects
     *      representing the String.
     * @throws IllegalArgumentException
     *      Throws an IllegalArgumentException object if the key is null.
     */
    public Phrase decrypt(KeyTable key) throws IllegalArgumentException{

        if(key == null)
            throw new IllegalArgumentException("Key is null.");

        Phrase phraseDecrypt = new Phrase();

        int index = 0;
        int row1 = 0;
        int col1 = 0;
        int col2 = 0;
        int row2 = 0;

        int x = 0;
        int size = size();

        while (x < size) {

            Bigram d = dequeue();
            if (key.findCol(d.getFirst()) == key.findCol(d.getSecond())) {

                col1 = key.findCol(d.getFirst());
                row1 = key.findRow(d.getFirst());
                col2 = key.findCol(d.getSecond());
                row2 = key.findRow(d.getSecond());

                if (row1 == 0) {
                    Bigram b = new Bigram(key.getKeyTable()[4][col1], key.getKeyTable()[row2 - 1][col2]);
                    phraseDecrypt.enqueue(b);
                } else if (row2 == 0) {
                    Bigram b = new Bigram(key.getKeyTable()[row1 - 1][col1], key.getKeyTable()[4][col2]);
                    phraseDecrypt.enqueue(b);
                } else {
                    Bigram b = new Bigram(key.getKeyTable()[row1 - 1][col1], key.getKeyTable()[row2 - 1][col2]);
                    phraseDecrypt.enqueue(b);
                }

            } else if (key.findRow(d.getFirst()) == key.findRow(d.getSecond())) {

                col1 = key.findCol(d.getFirst());
                row1 = key.findRow(d.getFirst());
                col2 = key.findCol(d.getSecond());
                row2 = key.findRow(d.getSecond());

                if (col1 == 0) {
                    Bigram b = new Bigram(key.getKeyTable()[row1][4], key.getKeyTable()[row2][col2 - 1]);
                    phraseDecrypt.enqueue(b);;
                } else if (col2 == 0) {
                    Bigram b = new Bigram(key.getKeyTable()[row1][col1 - 1], key.getKeyTable()[row2][4]);
                    phraseDecrypt.enqueue(b);
                } else {
                    Bigram b = new Bigram(key.getKeyTable()[row1][col1 - 1], key.getKeyTable()[row2][col2 - 1]);
                    phraseDecrypt.enqueue(b);
                }

            } else {

                //rectangle case
                col1 = key.findCol(d.getFirst());
                row1 = key.findRow(d.getFirst());
                col2 = key.findCol(d.getSecond());
                row2 = key.findRow(d.getSecond());
                int newCol1 = col2;
                int newCol2 = col1;

                Bigram b = new Bigram(key.getKeyTable()[row1][newCol1], key.getKeyTable()[row2][newCol2]);
                phraseDecrypt.enqueue(b);
            }
            x++;
        }
        return phraseDecrypt;
    }

    /**
     * Adds a new Bigram to the end of the Phrase.
     * @param b
     *      Bigram to be added.
     */
    public void enqueue(Bigram b){

        add(b);

    }

    /**
     * Removes the first Bigram in the Phrase and returns it.
     * @return
     *      Returns the first Bigram in the Phrase.
     */
    public Bigram dequeue(){

        Bigram delete = get(0);

        remove(0);

        return delete;

    }

    /**
     * Returns (without removing) the first Bigram in the phrase.
     * @return
     *      Returns the first Bigram in the Phrase.
     */
    public Bigram peek(){

        return get(0);


    }

    /**
     * Returns a String representation of the Phrase formed by the collection
     * of Bigrams.
     * @return
     *      Returns the collection of Bigrams in that Phrase as a String.
     */
    @Override
    public String toString() {
        String phraseString = "";

        int x = 0;
        while(x < size()){
           phraseString += get(x).getSet();
           x++;
        }

        return phraseString;
    }
}
