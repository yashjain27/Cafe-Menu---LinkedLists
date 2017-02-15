/**An Exception class which indicates that the user attempted
 * to access a OrderListNode that does not exist
 * (either before the head node or after the tail node).
 * This exception can also be thrown when an operation is performed
 * on an empty list (i.e. head, tail, and cursor are all equal to null).
 *
 * @author Yash Jain
 * 	SBU ID: 109885836
 * 	email: yash.jain@stonybrook.edu
 * 	HW 2 CSE 214
 * 	Section 10 Daniel Scanteianu
 * 	Grading TA: Anand Aiyer
 *
 */
public class EndOfListException extends Exception{
    public EndOfListException(String message){
        super(message);
    }
}
