/**The code for OrderListNode manages the Order nodes.
 *
 * @author Yash Jain
 * 	SBU ID: 109885836
 * 	email: yash.jain@stonybrook.edu
 * 	HW 2 CSE 214
 * 	Section 10 Daniel Scanteianu
 * 	Grading TA: Anand Aiyer
 *
 */
public class OrderListNode {
    //Data fields
    private Order data;
    private OrderListNode next;
    private OrderListNode prev;

    //Constructor
    /**
     * Creates a new Node gives the following parameter.
     * @param initData
     *       the Order to be added to OrderListNode
     * @throws IllegalArgumentException
     *        if an empty Order is thrown
     */
    public OrderListNode(Order initData) throws IllegalArgumentException {
        //Check if the object equals null
        if(initData == null){
            throw new IllegalArgumentException("Empty object thrown.");
        }
        data = initData;
        next = null;
        prev = null;
    }

    //Accessors

    /**
     * Receives the Order
     * @return
     *      the Order
     */
    public Order getData(){
        return data;
    }

    /**
     * Receives the next Order
     * @return
     *      the next Order
     */
    public OrderListNode getNext(){
        return next;
    }

    /**
     * Receives the previous Order
     * @return
     *      the previous Order
     */
    public OrderListNode getPrev(){
        return prev;
    }

    //Mutators

    /**
     * Sets the Order for the node
     * @param newOrder
     *      the Order placed
     */
    public void setData(Order newOrder){
        data = newOrder;
    }

    /**
     * Sets the next Order of the node
     * @param newNext
     *      next Order placed
     */
    public void setNext(OrderListNode newNext){
        next = newNext;
    }

    /**
     * Sets the previous Order of the node
     * @param newPrev
     *      previous Order placed
     */
    public void setPrev(OrderListNode newPrev){
        prev = newPrev;
    }

}
