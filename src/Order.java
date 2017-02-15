/**The code for Order class manages the order placed.
 *
 * @author Yash Jain
 * 	SBU ID: 109885836
 * 	email: yash.jain@stonybrook.edu
 * 	HW 2 CSE 214
 * 	Section 10 Daniel Scanteianu
 * 	Grading TA: Anand Aiyer
 *
 */
public class Order {
    //Data fields
    private String order;
    private String specialInstruction;
    private double price;

    //Default Constructor
    /**
     * This constructor initializes a new Order object and initalizes
     * the variables name and price.
     * @param name
     *      this is the name of the order to be added to the Order object
     * @param price
     *      this is the price of the order to be added to the Order objet
     */
    public Order(String name, String instructions, double price){
        order = name;
        specialInstruction = instructions;
        this.price = price;
    }

    //Accessors
    /**
     * Receives the price of the Order
     * @return
     *      the name of the Order
     */
    public String getOrder(){
        return order;
    }

    /**
     * Receives special instructions for the Order
     * @return
     *      special instructions for the the Order
     */
    public String getSpecialInstruction(){
        return specialInstruction;
    }

    /**
     * Receives the price of the Order
     * @return
     *      price of the Order
     */
    public double getPrice(){
        return price;
    }

    //Mutators
    /**
     * Sets the name for the Order
     * @param name
     *      name of the Order
     */
    public void setOrder(String name){
        order=name;
    }

    /**
     * Sets special instructions for the Order
     * @param instruction
     *      special instructions for the Order
     */
    public void setSpecialInstruction(String instruction){
        specialInstruction=instruction;
    }

    /**
     * Sets the price of the Order
     * @param price
     *      price of the Order
     */
    public  void setPrice(double price){
        this.price=price;
    }

    // Equals method
    /**
     * Compares a particular object with an Order object
     * @param obj
     *      the object to be compared with the current Order
     * @return
     *      true if logically equivalent, false otherwise
     */
    @Override
    public boolean equals(Object obj){
        if((obj instanceof  Order) && ((Order) obj).getOrder().equals(order) &&
                ((Order) obj).getSpecialInstruction().equals(specialInstruction) &&
                ((Order) obj).getPrice() == price){
            return true;
        }else{
            return false;
        }
    }

    //toString Method
    /**
     * String representation of the Order
     * @return
     *      the information associated with the Order
     */
    @Override
    public String toString(){
        return "Order: " + order + "  Special Instruction: " + specialInstruction + "  Price: $" + price;
    }
}
