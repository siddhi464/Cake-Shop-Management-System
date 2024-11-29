# Cake-Shop-Management-System
Using Java 

**Cake Shop Management System**

**Overview**
The **Cake Shop Management System** is a Java-based console application designed to manage the operations of a cake shop efficiently. It allows employees to maintain a cake catalog, process orders, and track order history. Simultaneously, customers can browse the catalog, place orders, and receive bills. The application employs key data structures like **Binary Search Trees (BSTs)**, **Linked Lists**, **Queues**, and **Hash Tables** to manage operations seamlessly.

---

**Features**

**Employee Features**:
1. **Cake Catalog Management**:
   - Add new cakes with pricing and stock details for small, medium, and large sizes.
   - View the entire catalog with detailed cake information.

2. **Order Processing**:
   - Process incoming orders from the queue and update order history.

3. **Order History**:
   - View the history of processed orders.

4. **Switch Between Roles**:
   - Seamlessly switch to the customer interface and back.

5. **Secure Access**:
   - Employee actions are password-protected (default password: `Admin123`).

---

**Customer Features**:
1. **Browse Cakes**:
   - View available cakes with prices for small, medium, and large sizes.

2. **Place Orders**:
   - Select cake type and size, add delivery details, and generate bills.
   - Automatic decrement of stock upon successful order placement.

3. **Switch Between Roles**:
   - Seamlessly switch to the employee interface if authorized.

---

**Core Data Structures**

1. **Binary Search Tree (BST)**:
   - Stores the cake catalog, enabling efficient search and display.
   - Cakes are sorted based on their medium size price by default.

2. **Linked List**:
   - Maintains the history of completed orders.

3. **Queue**:
   - Handles incoming customer orders, ensuring they are processed in the order received.

4. **Hash Table**:
   - Stores customer details for efficient lookup using their email as the key.

---

**How It Works**

**For Employees**:
1. **Login**:
   - Access the employee menu by entering the password (`Admin123`).
2. **Manage Catalog**:
   - Add new cakes with their sizes, prices, and stock quantities.
3. **Process Orders**:
   - Process queued orders and update the order history.
4. **View History**:
   - Access a chronological list of processed orders.
5. **Switch to Customer Menu**:
   - Explore the customer interface directly from the employee menu.

---

**For Customers**:
1. **View Catalog**:
   - Browse cakes available for purchase.
2. **Place an Order**:
   - Provide delivery details, select a cake and size, and confirm the order.
   - Receive a detailed bill with order information.

---

**Example Workflows**

**Employee Workflow**:
1. **View Catalog**:
   - Displays cakes with pricing and stock details for all sizes.
2. **Add Cake**:
   - Add a new cake, e.g., "Black Forest" priced at ₹300 (Small), ₹500 (Medium), ₹700 (Large).
3. **Process Orders**:
   - Processes orders placed by customers from the queue.
4. **View History**:
   - Displays processed orders such as:
     ```
     Order placed for: Red Velvet (Medium) by John Doe.
     ```

---

**Customer Workflow**:
1. **View Catalog**:
   - Displays cake names with prices for different sizes.
2. **Place Order**:
   - Inputs details such as name, address, cake name, and size.
   - Generates a bill:
     ```
     --- Bill ---
     Cake Name: Red Velvet
     Size: Medium
     Total Price: ₹450.0
     ----------------
     ```

---

**Code Structure**

**Classes and Their Roles**
1. **`Cake`**:
   - Represents a cake with attributes like name, size-based pricing, and stock quantities.

2. **`CakeBSTNode`**:
   - Represents a node in the BST used for the cake catalog.

3. **`OrderHistoryNode`**:
   - Represents a node in the linked list storing the order history.

4. **`OrderQueueNode`**:
   - Represents a node in the queue storing incoming orders.

5. **`CustomerHashNode`**:
   - Represents a node in the hash table used for customer lookup.

6. **`Customer`**:
   - Stores customer details like name, email, phone number, address, and delivery preference.

7. **`CakeShopManagement`**:
   - The main class handling all operations:
     - Employee menu.
     - Customer menu.
     - Cake catalog management.
     - Order processing.
     - Order history management.

---

**Technologies Used**
- **Programming Language**: Java
- **Data Structures**:
  - Binary Search Trees (BSTs) for catalog management.
  - Linked Lists for order history.
  - Queues for order processing.
  - Hash Tables for customer management.

---

**How to Run**

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/<your-username>/cake-shop-management.git
   cd cake-shop-management
   ```

2. **Compile the Code**:
   ```bash
   javac -d . *.java
   ```

3. **Run the Application**:
   ```bash
   java dsminipro.CakeShopManagement
   ```

4. **Choose Role**:
   - Enter `E` for Employee or `C` for Customer to access respective functionalities.

---

**Sample Commands**

1. **Add Cake**:
   - Employee adds a cake with specific details:
     ```
     Enter cake name: Black Forest
     Enter small size price: 300
     Enter medium size price: 500
     Enter large size price: 700
     Enter quantity for small size: 10
     Enter quantity for medium size: 8
     Enter quantity for large size: 5
     ```

2. **Place Order**:
   - Customer places an order:
     ```
     Enter your name: John Doe
     Enter your email: john@example.com
     Enter your phone: 1234567890
     Enter your address: 123 Baker Street
     Enter delivery option (Standard/Express): Standard
     Enter cake name to order: Black Forest
     Enter cake size (Small/Medium/Large): Medium
     ```

---

**Future Enhancements**
1. **GUI Integration**:
   - Replace the console interface with a user-friendly graphical user interface.

2. **Online Payments**:
   - Add options for payment integration (e.g., credit card, UPI).

3. **Discounts and Promotions**:
   - Implement promotional offers and discounts for loyal customers.

4. **Reporting and Analytics**:
   - Generate sales reports and inventory statistics.

---

**Contributors**
- **Sanika Shidore**
- **Shreya Gujarathi**
- **Siddhi Vaidya**
- **Shruti Thakur**

---
