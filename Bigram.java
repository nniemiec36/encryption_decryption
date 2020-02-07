/**
 * A class meant to represent a 5 by 5 key table to a Playfair Cipher.
 * The key is implemented as a two-dimensional array of char
 * variables.
 *
 * @author
 * Nicole Niemiec
 * #112039349
 * CSE 214 REC08
 * HOMEWORK #4
 *
 * @version 1
 */

public class Bigram {

    private char first;
    private char second;

    /**
     * A constructor method to build a new Bigram object that takes
     * in two char parameters.
     *
     * @param first
     *      First char to be set to the 'first' variable.
     * @param second
     *      Second char to be set to the 'second' variable.
     */
    public Bigram(char first, char second) {
        this.first = first;
        this.second = second;
    }

    /**
     * A constructor method to build a new Bigram object.
     */
    public Bigram(){
        first = '\u0000';
        second = '\u0000';
    }

    /**
     * An accessor method that returns the first char in the Bigram object.
     * @return
     *      The first char variable in the Bigram object.
     */
    public char getFirst() {
        return first;
    }

    /**
     * A mutator method that sets the first char in the Bigram object.
     * @param first
     *      The char to be set to the first char variable in the
     *      Bigram object.
     */
    public void setFirst(char first) {
        this.first = first;
    }

    /**
     * An accessor method that returns the second char in the Bigram object.
     * @return
     *      Returns the second char in the Bigram object.
     */
    public char getSecond() {
        return second;
    }

    /**
     * A mutator method that sets the second char in the Bigram object.
     * @param second
     *      The char to be set to the first char variable in the
     *      Bigram object.
     */
    public void setSecond(char second) {
        this.second = second;
    }

    /**
     * An accessor method that returns both the char variables in a concatenated String.
     * @return
     *      Returns both of the chars in one String.
     */
    public String getSet(){
        String set= "";
        set += first;
        set += second;

        return set;

    }

    /**
     * Returns the two characters in a concatenated String.
     * @return
     *      Returns the String of the two characters.
     */
    @Override
    public String toString() {
        return "(" + first + ", " + second + ") ";
    }
}
