import java.util.*;

// Cake class to store cake details, including price and quantity
class Cake {
    String name;
    double priceSmall;
    double priceMedium;
    double priceLarge;
    int quantitySmall;
    int quantityMedium;
    int quantityLarge;

    public Cake(String name, double priceSmall, double priceMedium, double priceLarge, int quantitySmall, int quantityMedium, int quantityLarge) {
        this.name = name;
        this.priceSmall = priceSmall;
        this.priceMedium = priceMedium;
        this.priceLarge = priceLarge;
        this.quantitySmall = quantitySmall;
        this.quantityMedium = quantityMedium;
        this.quantityLarge = quantityLarge;
    }

    public double getPriceBySize(String size) {
        switch (size.toLowerCase()) {
            case "small":
                return priceSmall;
            case "medium":
                return priceMedium;
            case "large":
                return priceLarge;
            default:
                return 0.0;
        }
    }

    public boolean decrementQuantity(String size) {
        switch (size.toLowerCase()) {
            case "small":
                if (quantitySmall > 0) {
                    quantitySmall--;
                    return true;
                }
                break;
            case "medium":
                if (quantityMedium > 0) {
                    quantityMedium--;
                    return true;
                }
                break;
            case "large":
                if (quantityLarge > 0) {
                    quantityLarge--;
                    return true;
                }
                break;
        }
        return false; // No stock available
    }
}

// CakeBSTNode class for Binary Search Tree (BST) nodes
class CakeBSTNode {
    Cake cake; // The cake object
    CakeBSTNode left, right; // Left and right children

    public CakeBSTNode(Cake cake) {
        this.cake = cake;
        this.left = this.right = null;
    }
}

// Order History (Linked List)
class OrderHistoryNode {
    String orderDetails;
    OrderHistoryNode next;

    public OrderHistoryNode(String orderDetails) {
        this.orderDetails = orderDetails;
        this.next = null;
    }
}

// Custom Queue for Orders
class OrderQueueNode {
    String orderDetails;
    OrderQueueNode next;

    public OrderQueueNode(String orderDetails) {
        this.orderDetails = orderDetails;
        this.next = null;
    }
}

// Hash Table Node for Customer Lookup
class CustomerHashNode {
    String key;
    Customer customer;
    CustomerHashNode next;

    public CustomerHashNode(String key, Customer customer) {
        this.key = key;
        this.customer = customer;
        this.next = null;
    }
}

// Customer class to store customer info
class Customer {
    String name;
    String email;
    String phone;
    String address;
    String deliveryOption;

    public Customer(String name, String email, String phone, String address, String deliveryOption) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.deliveryOption = deliveryOption;
    }
}

// Main Cake Shop Management System class
public class CakeShopManagement {

    // Linked List for Order History
    private OrderHistoryNode orderHistoryHead = null;

    // Custom Queue for Incoming Orders
    private OrderQueueNode orderQueueFront = null;
    private OrderQueueNode orderQueueRear = null;

    // Binary Search Tree (BST) for Cake Catalog
    private CakeBSTNode cakeCatalogRoot = null;

    // Hash Table for Customer Lookup
    private CustomerHashNode[] customerHashTable = new CustomerHashNode[10]; // Simple hash table with 10 slots

    // Function to add an order to the Order Queue
    public void addOrderToQueue(String orderDetails) {
        OrderQueueNode newOrder = new OrderQueueNode(orderDetails);
        if (orderQueueRear == null) {
            orderQueueFront = orderQueueRear = newOrder;
        } else {
            orderQueueRear.next = newOrder;
            orderQueueRear = newOrder;
        }
    }

    // Function to process the next order from the queue
    public String processNextOrder() {
        if (orderQueueFront == null) {
            return "No orders in the queue.";
        }
        String order = orderQueueFront.orderDetails;
        orderQueueFront = orderQueueFront.next;
        if (orderQueueFront == null) {
            orderQueueRear = null;
        }
        return "Processed order: " + order;
    }

    // Function to add a completed order to Order History (Linked List)
    public void addOrderToHistory(String orderDetails) {
        OrderHistoryNode newNode = new OrderHistoryNode(orderDetails);
        if (orderHistoryHead == null) {
            orderHistoryHead = newNode;
        } else {
            OrderHistoryNode temp = orderHistoryHead;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
    }

    // Function to display Order History
    public void displayOrderHistory() {
        OrderHistoryNode temp = orderHistoryHead;
        if (temp == null) {
            System.out.println("No order history available.");
        }
        while (temp != null) {
            System.out.println(temp.orderDetails);
            temp = temp.next;
        }
    }

    // Function to add a cake to the BST (Cake Catalog)
    public void addCakeToCatalog(Cake cake) {
        cakeCatalogRoot = insertCakeToBST(cakeCatalogRoot, cake);
    }

    // Helper function to insert a cake into BST
    private CakeBSTNode insertCakeToBST(CakeBSTNode root, Cake cake) {
        if (root == null) {
            return new CakeBSTNode(cake);
        }
        if (cake.priceMedium < root.cake.priceMedium) {  // Use medium price as default sorting criteria
            root.left = insertCakeToBST(root.left, cake);
        } else {
            root.right = insertCakeToBST(root.right, cake);
        }
        return root;
    }

    // Function to display the cakes in the catalog (In-order traversal)
    public void displayCakeCatalog(boolean isEmployee) {
        inOrderTraversal(cakeCatalogRoot, isEmployee);
    }

    // In-order traversal of BST to display cakes
    private void inOrderTraversal(CakeBSTNode root, boolean isEmployee) {
        if (root != null) {
            inOrderTraversal(root.left, isEmployee);
            if (isEmployee) {
                System.out.printf("%-20s %-15.2f %-15.2f %-15.2f %-10d %-10d %-10d\n", root.cake.name, root.cake.priceSmall, root.cake.priceMedium, root.cake.priceLarge, root.cake.quantitySmall, root.cake.quantityMedium, root.cake.quantityLarge);
            } else {
                System.out.printf("%-20s %-15.2f %-15.2f %-15.2f\n", root.cake.name, root.cake.priceSmall, root.cake.priceMedium, root.cake.priceLarge);
            }
            inOrderTraversal(root.right, isEmployee);
        }
    }

    // Function to add customer to Hash Table
    public void addCustomer(String key, Customer customer) {
    	 int index = Math.abs(key.hashCode() % customerHashTable.length);
    	 CustomerHashNode newCustomerNode = new CustomerHashNode(key, customer);
    	 newCustomerNode.next = customerHashTable[index];
    	 customerHashTable[index] = newCustomerNode;
    	
    }

    // Function to get customer details by email (or unique key)
    public Customer getCustomer(String key) {
        int index = key.hashCode() % customerHashTable.length;
        CustomerHashNode current = customerHashTable[index];
        while (current != null) {
            if (current.key.equals(key)) {
                return current.customer;
            }
            current = current.next;
        }
        return null;
    }

    // Function to get the price of a cake based on name and size
    private double getCakePrice(String cakeName, String size) {
        return getCakePrice(cakeCatalogRoot, cakeName, size);
    }

    // Helper function to search for the cake in the BST and return the price
    private double getCakePrice(CakeBSTNode root, String cakeName, String size) {
        if (root == null) return 0.0; // Cake not found

        // If the cake is found, return the price based on the size
        if (root.cake.name.equalsIgnoreCase(cakeName)) {
            return root.cake.getPriceBySize(size);
        }

        if (cakeName.compareToIgnoreCase(root.cake.name) < 0) {
            return getCakePrice(root.left, cakeName, size);
        } else {
            return getCakePrice(root.right, cakeName, size);
        }
    }

    // Function to place an order
    public void placeOrder(String cakeName, String size, Customer customer) {
        double price = getCakePrice(cakeName, size);
        if (price > 0) {
            boolean orderPlaced = false;
            CakeBSTNode cakeNode = findCakeNode(cakeCatalogRoot, cakeName);
            if (cakeNode != null && cakeNode.cake.decrementQuantity(size)) {
                addOrderToQueue("Order placed for: " + cakeName + " (" + size + ") by " + customer.name + ", Delivery: " + customer.deliveryOption + " to " + customer.address);
                addOrderToHistory("Order placed for: " + cakeName + " (" + size + ")");
                System.out.println("Order placed for " + cakeName + " (" + size + "). Total price: " + price);
                orderPlaced = true;
            }
            if (!orderPlaced) {
                System.out.println("Cake unavailable currently: " + cakeName);
            }
        } else {
            System.out.println("Invalid cake name or size.");
        }
    }

    // Helper function to find the cake node in the BST
    private CakeBSTNode findCakeNode(CakeBSTNode root, String cakeName) {
        if (root == null) {
            return null;
        }
        if (root.cake.name.equalsIgnoreCase(cakeName)) {
            return root;
        } else if (cakeName.compareToIgnoreCase(root.cake.name) < 0) {
            return findCakeNode(root.left, cakeName);
        } else {
            return findCakeNode(root.right, cakeName);
        }
    }

    // Customer Menu
    public void showCustomerMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\nCustomer Menu");
            System.out.println("1. View Cake Catalog");
            System.out.println("2. Place Order");
            System.out.println("3. Switch to Employee Menu");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    displayCakeCatalog(false); // Display for customer view
                    break;
                case 2:
                    System.out.print("Enter your name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter your email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter your phone: ");
                    String phone = scanner.nextLine();
                    System.out.print("Enter your address: ");
                    String address = scanner.nextLine();
                    System.out.print("Enter delivery option (Take-in/Home Delivery): ");
                    String deliveryOption = scanner.nextLine();
                    Customer customer = new Customer(name, email, phone, address, deliveryOption);
                    addCustomer(email, customer);

                    System.out.print("Enter cake name to order: ");
                    String cakeName = scanner.nextLine();
                    System.out.print("Enter cake size (Small/Medium/Large): ");
                    String size = scanner.nextLine();
                    placeOrder(cakeName, size, customer);
                    break;
                case 3:
                    System.out.println("Switching to Employee Menu...");
                    showEmployeeMenu(); // Switch to Employee Menu
                    break;
                case 4:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        } while (choice != 4);
    }

    // Employee Menu
    public void showEmployeeMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        System.out.print("Enter employee password: ");
        String password = scanner.nextLine();
        if (!password.equals("admin123")) {
            System.out.println("Incorrect password. Access denied.");
            return;
        }

        do {
            System.out.println("\nEmployee Menu");
            System.out.println("1. Add Cake to Catalog");
            System.out.println("2. Process Next Order");
            System.out.println("3. View Order History");
            System.out.println("4. View Cake Catalog");
            System.out.println("5. Switch to Customer Menu");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                	try {
                    System.out.print("Enter cake name: ");
                    String cakeName = scanner.nextLine();
                    System.out.print("Enter price for Small size: ");
                    double priceSmall = scanner.nextDouble();
                    System.out.print("Enter price for Medium size: ");
                    double priceMedium = scanner.nextDouble();
                    System.out.print("Enter price for Large size: ");
                    double priceLarge = scanner.nextDouble();
                    System.out.print("Enter quantity for Small size: ");
                    int quantitySmall = scanner.nextInt();
                    System.out.print("Enter quantity for Medium size: ");
                    int quantityMedium = scanner.nextInt();
                    System.out.print("Enter quantity for Large size: ");
                    int quantityLarge = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    Cake cake = new Cake(cakeName, priceSmall, priceMedium, priceLarge, quantitySmall, quantityMedium, quantityLarge);
                    addCakeToCatalog(cake);
                	}catch(Exception e) {
                		System.out.println();
                	}
                    System.out.println("Cake added to catalog.");
                    break;
                case 2:
                    System.out.println(processNextOrder());
                    break;
                case 3:
                    displayOrderHistory();
                    break;
                case 4:
                    displayCakeCatalog(true); // Show catalog for employees
                    break;
                case 5:
                    System.out.println("Switching to Customer Menu...");
                    showCustomerMenu(); // Switch to Customer Menu
                    break;
                case 6:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        } while (choice != 6);
    }

    // Main method
    public static void main(String[] args) {
        CakeShopManagement shop = new CakeShopManagement();
        shop.start(); // Start the application
    }

    // Main function to handle user login and menu selection
    public void start() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Cake Shop Management System!");

        String userType = "";
        do {
            System.out.print("Are you an Employee or Customer? (E/C): ");
            userType = scanner.nextLine().toUpperCase();

            if (userType.equals("E")) {
                System.out.print("Enter employee password: ");
                String password = scanner.nextLine();

                // Hardcoded password for simplicity
                if (password.equals("admin123")) {
                    System.out.println("Access granted.");
                    showEmployeeMenu(); // Show employee menu
                } else {
                    System.out.println("Incorrect password. Access denied.");
                }
            } else if (userType.equals("C")) {
                showCustomerMenu(); // Show customer menu
            } else {
                System.out.println("Invalid choice. Try again.");
            }
        } while (!userType.equals("E") && !userType.equals("C"));

        scanner.close();
    }
}

/*
 * Welcome to the Cake Shop Management System!
Are you an Employee or Customer? (E/C): E
Enter employee password: admin123
Access granted.
Enter employee password: admin123

Employee Menu
1. Add Cake to Catalog
2. Process Next Order
3. View Order History
4. View Cake Catalog
5. Switch to Customer Menu
6. Exit
Enter your choice: 1
Enter cake name: Belgium Chocolate
Enter price for Small size: 350
Enter price for Medium size: 370
Enter price for Large size: 400
Enter quantity for Small size: 5
Enter quantity for Medium size: 5
Enter quantity for Large size: 5
Cake added to catalog.

Employee Menu
1. Add Cake to Catalog
2. Process Next Order
3. View Order History
4. View Cake Catalog
5. Switch to Customer Menu
6. Exit
Enter your choice: 1
Enter cake name: Red Velvet
Enter price for Small size: 350
Enter price for Medium size: 360
Enter price for Large size: 400
Enter quantity for Small size: 6
Enter quantity for Medium size: 3
Enter quantity for Large size: 2
Cake added to catalog.

Employee Menu
1. Add Cake to Catalog
2. Process Next Order
3. View Order History
4. View Cake Catalog
5. Switch to Customer Menu
6. Exit
Enter your choice: 1
Enter cake name: Chocolate truffle
Enter price for Small size: 300
Enter price for Medium size: 340
Enter price for Large size: 380
Enter quantity for Small size: 5
Enter quantity for Medium size: 3
Enter quantity for Large size: 2
Cake added to catalog.

Employee Menu
1. Add Cake to Catalog
2. Process Next Order
3. View Order History
4. View Cake Catalog
5. Switch to Customer Menu
6. Exit
Enter your choice: 1
Enter cake name: Dutch Chocolate
Enter price for Small size: 300
Enter price for Medium size: 350
Enter price for Large size: 400
Enter quantity for Small size: 4
Enter quantity for Medium size: 5
Enter quantity for Large size: 6
Cake added to catalog.

Employee Menu
1. Add Cake to Catalog
2. Process Next Order
3. View Order History
4. View Cake Catalog
5. Switch to Customer Menu
6. Exit
Enter your choice: 1
Enter cake name: Blue Berry
Enter price for Small size: 200
Enter price for Medium size: 300
Enter price for Large size: 400
Enter quantity for Small size: 2
Enter quantity for Medium size: 3
Enter quantity for Large size: 4
Cake added to catalog.

Employee Menu
1. Add Cake to Catalog
2. Process Next Order
3. View Order History
4. View Cake Catalog
5. Switch to Customer Menu
6. Exit
Enter your choice: 2
No orders in the queue.

Employee Menu
1. Add Cake to Catalog
2. Process Next Order
3. View Order History
4. View Cake Catalog
5. Switch to Customer Menu
6. Exit
Enter your choice: 3
No order history available.

Employee Menu
1. Add Cake to Catalog
2. Process Next Order
3. View Order History
4. View Cake Catalog
5. Switch to Customer Menu
6. Exit
Enter your choice: 4
Blue Berry           200.00          300.00          400.00          2          3          4         
Chocolate truffle    300.00          340.00          380.00          5          3          2         
Dutch Chocolate      300.00          350.00          400.00          4          5          6         
Red Velvet           350.00          360.00          400.00          6          3          2         
Belgium Chocolate    350.00          370.00          400.00          5          5          5         

Employee Menu
1. Add Cake to Catalog
2. Process Next Order
3. View Order History
4. View Cake Catalog
5. Switch to Customer Menu
6. Exit
Enter your choice: 5
Switching to Customer Menu...

Customer Menu
1. View Cake Catalog
2. Place Order
3. Switch to Employee Menu
4. Exit
Enter your choice: 1
Blue Berry           200.00          300.00          400.00         
Chocolate truffle    300.00          340.00          380.00         
Dutch Chocolate      300.00          350.00          400.00         
Red Velvet           350.00          360.00          400.00         
Belgium Chocolate    350.00          370.00          400.00         

Customer Menu
1. View Cake Catalog
2. Place Order
3. Switch to Employee Menu
4. Exit
Enter your choice: 2
Enter your name: Sunil
Enter your email: sm@gmail.com
Enter your phone: 1237494897
Enter your address: pashan pune
Enter delivery option (Take-in/Home Delivery): take in
Enter cake name to order: Belgium Chocolate
Enter cake size (Small/Medium/Large): Medium
Order placed for Belgium Chocolate (Medium). Total price: 370.0

Customer Menu
1. View Cake Catalog
2. Place Order
3. Switch to Employee Menu
4. Exit
Enter your choice: 2
Enter your name: Sarah
Enter your email: sarah
Enter your phone: 256153784
Enter your address: pune pashan
Enter delivery option (Take-in/Home Delivery): home delivery
Enter cake name to order: Belgium Chocolate
Enter cake size (Small/Medium/Large): Large
Order placed for Belgium Chocolate (Large). Total price: 400.0

*/
