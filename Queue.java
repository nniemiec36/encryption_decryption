/**
 * An interface of the Queue class that will be implemented by the Phrase class.
 *
 * @author
 * Nicole Niemiec
 * CSE 214 REC08
 * HOMEWORK #4
 *
 * @version 1
 */
public interface Queue {

public void enqueue(Bigram b);
public Bigram dequeue();
public Bigram peek();
public int size();
public boolean isEmpty();

}
