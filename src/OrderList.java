/**
 * The code for OrderListNode manages the Order nodes.
 *
 * @author Yash Jain
 *         SBU ID: 109885836
 *         email: yash.jain@stonybrook.edu
 *         HW 2 CSE 214
 *         Section 10 Daniel Scanteianu
 *         Grading TA: Anand Aiyer
 */
public class OrderList {
    //Data fields
    private OrderListNode head;
    private OrderListNode tail;
    private OrderListNode cursor;
    private int numOfOrders;

    //Default constructor

    /**
     * Creates a new OrderList object and initializes the data fields to
     * null.
     * <dt>Postcondtions:</dt>
     *      <dd>This OrderList has been initialized with head, tail,
     *      and cursor all set to null.</dd>
     */
    public OrderList() {
        head = null;
        tail = null;
        cursor = null;
        numOfOrders = 0;
    }

    //Methods

    /**
     * Receives the number of Orders placed
     *
     * @return the number of Orders
     */
    public int numOrders() {
        return numOfOrders;
    }

    /**
     * Receives the Order pointed by the cursor
     *
     * @return the Order pointed by cursor
     */
    public Order getCursorOrder() {
        return cursor.getData();
    }

    /**
     * Resets the cursor to the start of the list
     * <dt>Postconditions: </dt>
     *      <dd>If head is not null, the cursor now references
     *      the first OrderListNode in this list.</dd>
     *      <dd>If head is null, the cursor is set to null as
     *      well (there are no Orders in this list).</dd>
     */
    public void resetCursorToHead() {
        if(cursor == head){
            System.out.println("Cursor is already at head of list");
        }else if (head != null) {
            cursor = head;
            System.out.println("Cursor at the head");
        }
        else cursor = null;
    }

    /**
     * Cursor is now at the tail
     * <dt>Postconditions: </dt>
     *      <dd>If the tail is not null, the cursor now
     *      references the last OrderListNode in the list</dd>
     *      <dd>If head is null, the cursor is null as well</dd>
     */
    public void cursorToTail(){
        if(cursor == tail)
            System.out.println("Cursor is already at tail of list");
        else if(tail != null) {
            cursor = tail;
        }
        else cursor = null;
    }

    /**
     * Moves the cursor to select the next OrderListNode in the list
     * @throws EndOfListException
     *        Indicates that the node next to the cursor is empty
     */
    public void cursorForward() throws EndOfListException{
        if(cursor == null)
            throw new EndOfListException("Empty list.");
        if(cursor != tail) {
            cursor = cursor.getNext();
            System.out.println("\nCursor has moved forward.");
        }
        else
            throw new EndOfListException("\nCursor is already at tail of list.");
    }

    /**
     * Moves the cursor to select the previous OrderListNode in the list.
     * @throws EndOfListException
     *        Indicates that the node previous to the cursor is empty
     */
    public void cursorBackward() throws EndOfListException{
        if(cursor == null)
            throw new EndOfListException("Empty list.");
        if(cursor != head) {
            cursor = cursor.getPrev();
            System.out.println("\nCursor has moved backwards.");
        }
        else
            throw new EndOfListException("\nCursor is already at head of list.");
    }

    /**
     * Inserts the indicated Order after the cursor.
     * <dt>Precondtion:</dt>
     *      <dd>newOrder is not null</dd>
     * @param newOrder
     *        Order object
     * @throws IllegalArgumentException
     *      Indicates the Order is empty
     * <dt>Postcondition:</dt>
     *      <dd>newOrder has been wrapped in a new OrderListNode object</dd>
     *      <dd>If cursor was previously not null, the newly created OrderListNode
     *      has been inserted into the list before the cursor.</dd>
     *      <dd>If cursor was previously null, the newly created OrderListNode has
     *      been set as the new head of the list (as well as the tail).</dd>
     *      <dd>The cursor now references the newly created OrderListNode.</dd>
     */
    public void insertAfterCursor(Order newOrder) throws IllegalArgumentException {
        if(newOrder == null)
            throw new IllegalArgumentException("Empty order!");

        OrderListNode newOrderList = new OrderListNode(newOrder); //New node

        if(cursor == null){
            head = newOrderList;
            tail = newOrderList;
            cursor = newOrderList;
            numOfOrders++;
        }
        else{
            newOrderList.setNext(cursor.getNext());
            if(cursor.getNext() != null)
                cursor.getNext().setPrev(newOrderList);
            cursor.setNext(newOrderList);
            newOrderList.setPrev(cursor);
            cursor = newOrderList;

            //Set the tail to the cursor if thew newOrderList is the last node
            if(cursor.getNext() == null)
                tail = cursor;

            numOfOrders++;
        }
    }

    /**
     * Inserts the indicated Order after the tail of the list.
     * <dt>Preconditions:</dt>
     *     <dd>newOrder is not null.</dd>
     * @param newOrder
     *      Order object
     * @throws IllegalArgumentException
     *       Indicates that the Order is empty
     * <dt>Postconditions:</dt>
     *     <dd>newOrder has been wrapped in a new OrderListNode object</dd>
     *     <dd>If tail was previously not null, the newly created OrderListNode
     *     has been inserted into the list after the tail</dd>
     *     <dd>If tail was previously null, the newly created OrderListNode has
     *     been set as the new head of the list(as well as the tail and the cursor)
     *     </dd>
     *     <dd>The tail now references the newly created OrderListNode</dd>
     */
    public void appendToTail(Order newOrder) throws IllegalArgumentException{
        if(newOrder == null)
            throw new IllegalArgumentException("Order is empty");

        OrderListNode newOrderList = new OrderListNode(newOrder);

        if(tail == null){
            head = newOrderList;
            tail = newOrderList;
            cursor = newOrderList;
            numOfOrders++;
        }
        else{
            newOrderList.setNext(tail.getNext());
            newOrderList.setPrev(tail);
            tail.setNext(newOrderList);
            tail = newOrderList;
            numOfOrders++;
        }
    }

    /**
     * Removes the OrderListNode referenced by cursor and returns the Order
     * inside.
     * <dt>Preconditions:</dt>
     *      <dd>Cursor is not null</dd>
     * @return
     *      Returns the Order inside the cursor
     * @throws EndOfListException
     *        Indicates that the cursor is null
     * <dt>Postconditions:</dt>
     *      <dd>The OrderListNode referencedby cursor has been removed from
     *      the list.</dd>
     *      <dd>All other OrderListNodes in the list exist in the same order
     *      as before.</dd>
     *      <dd>The cursor now references the previous OrderListNode
     *      (or the head, if the cursor previously referenced the head
     *      of the list).</dd>
     */
    public Order removeCursor()throws EndOfListException{
        if(cursor == null)
            throw new EndOfListException("Empty cursor");

        Order order = null;

        if(cursor.getNext() == null && cursor.getPrev() == null) {
            order = cursor.getData();
            tail=null;
            cursor=null;
            head = null;
        }else {
            if (cursor.getPrev() != null)
                cursor.getPrev().setNext(cursor.getNext());
            if (cursor.getNext() != null)
                cursor.getNext().setPrev(cursor.getPrev());

            order = cursor.getData();
            cursor = cursor.getPrev();
        }
        if(cursor != null && cursor.getNext() == null)
            tail=cursor;

        numOfOrders--;
        System.out.println(order.getOrder() + " removed.");
        return order;
    }

    /**
     * Adds a new order to the front of the list
     * <dt>Preconditions: </dt>
     * <dd>Order is not null</dd>
     * @param order
     *       Order object
     * @throws IllegalArgumentException
     *       Indicates that the order is empty.
     * <dt>Postconditions: </dt>
     * <dd>new Order is the now the head</dd>
     */
    public void addNewHead(Order order) throws IllegalArgumentException{
        if(order == null)
            throw new IllegalArgumentException("Empty order");

        OrderListNode newNode = new OrderListNode(order);

        if(head == null){
            head = newNode;
            tail = newNode;
            cursor = newNode;
            numOfOrders++;
        }else {
            newNode.setNext(head);
            head.setPrev(newNode); // here
            head = newNode;
            numOfOrders++;
        }
    }

    /**
     * Looks for a similar order and places the order next to it.
     * <dt>Preconditions: </dt>
     * <dd>Order is not empty</dd>
     * @param order
     *        Order object
     * @return
     *        boolean value, true if similar order found, false otherwise
     * @throws IllegalArgumentException
     *        Indicates that the order was empty
     * @throws EndOfListException
     *        Indicates that no similar order exists
     * <dt>Postconditions: </dt>
     * <dd>Order has been placed next to a similar order</dd>
     */
    public boolean listSearch(Order order) throws IllegalArgumentException, EndOfListException{
        if(order == null)
            throw new IllegalArgumentException("Empty order");

        OrderListNode newOrder = new OrderListNode(order);
        OrderListNode nodePtr = head;

        while(nodePtr!=null){
            if(order.getOrder().equals(nodePtr.getData().getOrder())){
                newOrder.setNext(nodePtr.getNext());
                newOrder.setPrev(nodePtr);
                if(nodePtr.getNext()!= null)
                    nodePtr.getNext().setPrev(newOrder);
                nodePtr.setNext(newOrder);
                nodePtr = newOrder;
                numOfOrders++;

                if(nodePtr.getNext() == null){
                    tail = newOrder;
                }
                return true;
            }
            nodePtr=nodePtr.getNext();
        }
        throw new EndOfListException("No similar order matched");
    }

    /**
     * To string method that prints out the bariasta's orders
     * @param server
     *      which barista is chosen (1 or 2)
     * @return
     *      String that represents all the order's placed for the barista
     */
    public String toString(int server){
        //New OrderListNode, which is a node pointer
        OrderListNode nodePtr = head;

        //Print the order
        String orders = new String();
        orders += ("Barista: " + server + "\n");
        orders += String.format("%-20s%-40s%-5s\n","Order Name", "Special Instructions", "Price");
        orders += "-----------------------------------------------------------------\n";

        while(nodePtr != null){
            if(nodePtr == cursor)
                orders += String.format("->%-18s%-40s%-5s\n", nodePtr.getData().getOrder(),
                        nodePtr.getData().getSpecialInstruction(), nodePtr.getData().getPrice());
            else
            orders += String.format("%-20s%-40s%-5s\n", nodePtr.getData().getOrder(),
                    nodePtr.getData().getSpecialInstruction(), nodePtr.getData().getPrice());
            nodePtr=nodePtr.getNext();
        }

        return orders;
    }

    /**
     * This method reverses the orderList.
     * @return
     *      Reversed linked list
     */
    public OrderList reverseList() throws NullPointerException{
        //Check if the list is empty
        if(head == null)
            throw new NullPointerException("Empty list. Nothing to reverse.");

        //new reversed List
        OrderList reversedList = new OrderList();

        //Recursive  call
        reversedList = reverseList(reversedList, head);

        //Add the head of the original list to the tail of the new one
        reversedList.appendToTail(head.getData());
        return reversedList; //return the reversed list
    }

    public OrderList reverseList(OrderList reversedList, OrderListNode head){
        if(head.getNext() != null) {
            head = head.getNext();
            reverseList(reversedList, head); //recursive call

            reversedList.appendToTail(head.getData()); //Add reversed node to new List
        }

        return reversedList;
    }
}














