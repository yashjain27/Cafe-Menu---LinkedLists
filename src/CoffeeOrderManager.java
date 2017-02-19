import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The code is the driver class.
 *
 * @author Yash Jain
 *         SBU ID: 109885836
 *         email: yash.jain@stonybrook.edu
 *         HW 2 CSE 214
 *         Section 10 Daniel Scanteianu
 *         Grading TA: Anand Aiyer
 */
public class CoffeeOrderManager {
    public static void main(String[] args)  {
        //Declaration of variables
        Scanner input = new Scanner(System.in);
        String choice, drink, specialInstruction;
        double price;
        int server=1;

        //OrderList objects
        OrderList barista1 = new OrderList();
        OrderList barista2 = new OrderList();
        OrderList[] barista = {barista1, barista2};

        //Order object
        Order clipboard = null;


        do {
            //Menu
            System.out.println("Menu:");
            System.out.println("\tO) Order");
            System.out.println("\tP) Print Order Lists");
            System.out.println("\tE) Extra Credit Functions");
            System.out.println("\tC) Cursor Options");
            System.out.println("\tQ) Quit");
            System.out.println();
            System.out.print("\nPlease select an option: ");
            choice = input.nextLine();

            switch (choice.toUpperCase()){
                case "O":
                    //Input-output for the order
                    System.out.print("Please enter drink name: ");
                    drink = input.nextLine();
                    System.out.print("Please enter special requests: ");
                    specialInstruction = input.nextLine();
                    price = getInputDouble("Please enter the price: ", 0);
                    server = getInputInt("\nPlease select Barista (1 or 2): ",1,2);
                    System.out.println("Where should the order be added? Options: F - Front of List," +
                            " B - Back of List, A - After Cursor, S - After Similar Order (default: end of list)");

                    //New Order object
                    Order order = new Order(drink, specialInstruction, price);

                    //Placement of the order
                    System.out.print("Please select an option: ");
                    choice = input.nextLine();

                    switch(choice.toUpperCase()){
                        case "F":
                            try {
                                barista[server - 1].addNewHead(order);
                            }catch (IllegalArgumentException e){
                                System.out.println(e);
                            }
                            break;
                        case "B":
                            try {
                                barista[server-1].appendToTail(order);
                            } catch (IllegalArgumentException e) {
                                System.out.println(e);
                            }
                            break;
                        case "A":
                            try {
                                barista[server - 1].insertAfterCursor(order);
                            }catch (IllegalArgumentException e) {
                                System.out.println(e);
                            }
                            break;
                        case "S":
                            try {
                                barista[server-1].listSearch(order);
                            } catch (IllegalArgumentException e) {
                                System.out.println(e);
                            } catch ( EndOfListException e){
                                System.out.println(e + ", order placed at the bottom");
                                barista[server-1].appendToTail(order);
                            }
                            break;
                        default:
                            try {
                                barista[server-1].appendToTail(order);
                            } catch (IllegalArgumentException e) {
                                System.out.println(e);
                            }
                    }
                    break;

                case "P":
                    System.out.println(barista[0].toString(1));
                    System.out.println(barista[1].toString(2));
                    break;

                case "E":
                    System.out.print("\nOptions: Reverse - R or Merge - M: ");
                    choice=input.nextLine();
                    server=getInputInt("\nPlease select a list (1 or 2): ",1,2);

                    switch (choice.toUpperCase()) {
                        case "R":
                            try {
                                barista[server - 1] = barista[server - 1].reverseList();
                            } catch (NullPointerException e) {
                                System.out.println(e);
                            }
                            break;
                        /*
                        case "M":
                            //Merge method
                            try {
                                System.out.println("hey");
                               barista[server - 1] =  barista[server - 1].mergeList(barista[1]);
                               //barista1 = barista1.mergeList(barista2);
                            } catch (EndOfListException e) {
                                System.out.println(e);
                            }
                            break;*/
                        default:
                            System.out.println("Invalid input, please try again later.");
                    }
                    break;

                case "C":
                    server = getInputInt("\nPlease select a cursor (1 or 2): ",1,2);
                    System.out.println("Cursor options: F - Forward, B - Backward, H-To Head, T - To Tail," +
                            " R - Remove, C - Cut, P - Paste.");
                    System.out.print("\nPlease select an option: ");
                    choice = input.nextLine();

                    do {
                        switch (choice.toUpperCase()) {
                            case "F":
                                try {
                                    barista[server-1].cursorForward(); //Move cursor forward
                                } catch (EndOfListException e) {
                                    System.out.println(e);
                                }
                                break;
                            case "B":
                                try {
                                    barista[server-1].cursorBackward(); //Move cursor backwards.
                                } catch (EndOfListException e) {
                                    System.out.println(e);
                                }
                                break;
                            case "H":
                                barista[server-1].resetCursorToHead(); //Cursor to head
                                break;
                            case "T":
                                barista[server-1].cursorToTail(); //Cursor to tail
                                break;
                            case "R":
                                try {
                                    barista[server-1].removeCursor(); //Node pointed by cursor is removed
                                } catch (EndOfListException e) {
                                    e.printStackTrace();
                                    System.out.println(e);
                                }
                                break;
                            case "C":
                                try {
                                    clipboard = barista[server - 1].removeCursor();
                                    if(clipboard != null)
                                        System.out.println("\n" + clipboard.getOrder() + " is in clipboard.");
                                } catch (EndOfListException e) {
                                    System.out.println(e);
                                }
                                break;
                            case "P":
                                if(clipboard != null) {
                                    try {
                                        barista[server - 1].insertAfterCursor(clipboard);
                                    } catch (IllegalArgumentException e) {
                                        System.out.println(e);
                                    }
                                    clipboard = null;
                                }else System.out.println("\nNothing to paste");
                                break;
                            default:
                                System.out.println("Invalid choice, please try again.");
                                choice = "x";
                        }
                    }while(choice.equals("x"));

                    break;
                case "Q":
                    break;
                default:
                    System.out.println("Invalid input please try again");
            }

        } while (!choice.toUpperCase().equals("Q"));

        System.out.println("Thanks for using this program!");
    }

    /**
     * Get an input value for a Double
     * @param message
     *      message that is passed in the method
     * @param min
     *      mininum value for double
     * @return
     *      Double value
     */
    public static double getInputDouble(String message, double min){
        return getInputDouble(message, min, -1);
    }

    /**
     * Get an input value for an double
     * @param message
     *       message that is passed in the method
     * @param min
     *       minimum value for double
     * @param max
     *      maximum value for double
     * @return
     *      Double value
     */
    public static double getInputDouble(String message, double min, double max){
        double value;
        Scanner in = new Scanner(System.in);

        while(true) {
            try {
                System.out.print(message);
                value = in.nextDouble();
                in.nextLine();
                if (value < min || (value > max && !(max == -1 ))){
                    System.out.println("Error: outside range [" + min + "," + max + "]");
                }else{
                    break;
                }
            } catch (InputMismatchException ex) {
                System.out.println("Invalid double. Please try again");
                in.nextLine();
            }
        }
        return value;
    }

    /**
     * Get an input value for an int
     * @param message
     *       message that is passed in the method
     * @param min
     *       minimum value for int
     * @param max
     *      maximum value for int
     * @return
     *      Int value
     */
    public static int getInputInt(String message, int min, int max){
        int value;
        Scanner in = new Scanner(System.in);

        while(true) {
            try {
                System.out.print(message);
                value = in.nextInt();
                in.nextLine();
                if (value < min || (value > max && !(max == -1 ))){
                    System.out.println("Error: outside range [" + min + "," + max + "]");
                }else{
                    break;
                }
            } catch (InputMismatchException ex) {
                System.out.println("Invalid int. Please try again");
                in.nextLine();
            }
        }
        return value;
    }
}
