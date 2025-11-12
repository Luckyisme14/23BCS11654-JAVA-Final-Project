# Complete Project Explanation: Car Rental Management System

## 📋 Table of Contents
1. [Project Overview](#project-overview)
2. [Architecture & Design Patterns](#architecture--design-patterns)
3. [Project Structure](#project-structure)
4. [Component Breakdown](#component-breakdown)
5. [Features & Functionality](#features--functionality)
6. [Data Flow & Business Logic](#data-flow--business-logic)
7. [Technical Details](#technical-details)
8. [How It Works](#how-it-works)
9. [Usage Guide](#usage-guide)
10. [Future Enhancements](#future-enhancements)

---

## 🎯 Project Overview

### What is This Project?
The **Car Rental Management System** is a Java-based desktop application that allows rental companies to manage their car inventory, customer database, and rental transactions through an intuitive graphical user interface.

### Purpose
- Manage car inventory (add, modify, delete, search)
- Manage customer database
- Handle car rental transactions
- Track active rentals and availability
- Calculate rental prices automatically

### Technology Stack
- **Language:** Java (JDK 8+)
- **GUI Framework:** Java Swing
- **Architecture:** MVC (Model-View-Controller) Pattern
- **Data Storage:** In-memory (ArrayList-based)
- **Build Tool:** Standard Java Compiler (javac)

### Key Characteristics
- ✅ Object-Oriented Programming (OOP)
- ✅ GUI-based interface (no command-line required)
- ✅ Real-time data updates
- ✅ User-friendly dialogs and forms
- ✅ Data validation and error handling

---

## 🏗️ Architecture & Design Patterns

### MVC (Model-View-Controller) Pattern

The project follows the **MVC architectural pattern**, which separates concerns into three main components:

```
┌─────────────┐
│    VIEW     │  ← User Interface (GUI)
│  (CarRental │     - Displays data
│    GUI)     │     - Handles user input
└──────┬──────┘     - Shows dialogs
       │
       │ User Actions
       ▼
┌─────────────┐
│ CONTROLLER  │  ← Business Logic
│ (Controller)│     - Processes requests
│             │     - Manages data
└──────┬──────┘     - Validates operations
       │
       │ Data Operations
       ▼
┌─────────────┐
│   MODEL     │  ← Data Layer
│ Car/Customer│     - Data structures
│             │     - Entity classes
└─────────────┘
```

### Component Responsibilities

**Model (Data Layer)**
- `Car.java` - Represents a car entity
- `Customer.java` - Represents a customer entity
- Contains data fields and basic getters/setters

**View (Presentation Layer)**
- `CarRentalGUI.java` - Main GUI window
- Handles all user interactions
- Displays data in tables
- Shows dialogs for input

**Controller (Business Logic Layer)**
- `Controller.java` - Central business logic
- Manages collections of cars and customers
- Handles rental operations
- Validates business rules

---

## 📁 Project Structure

```
CarRentalSystem-main/
│
├── model/
│   ├── CarRentalModule.iml          # IntelliJ IDEA module file
│   └── src/
│       └── main/
│           ├── Main.java            # Application entry point
│           │
│           ├── controller/
│           │   └── Controller.java   # Business logic controller
│           │
│           ├── model/
│           │   ├── Car.java         # Car entity class
│           │   └── Customer.java   # Customer entity class
│           │
│           └── view/
│               └── CarRentalGUI.java # GUI interface
│
├── out/                              # Compiled classes (generated)
│   └── production/
│       └── CarRentalModule/
│
├── README.md                          # Basic project info
├── HOW_TO_RUN.md                     # Running instructions
├── SAMPLE_DATA.md                    # Test data examples
├── PROJECT_EXPLANATION.md            # This file
└── run.bat                            # Windows batch script to run
```

### Package Structure
```
main/
├── Main.java                    (Entry point)
├── controller/
│   └── Controller.java          (Business logic)
├── model/
│   ├── Car.java                 (Car entity)
│   └── Customer.java            (Customer entity)
└── view/
    └── CarRentalGUI.java        (GUI interface)
```

---

## 🔧 Component Breakdown

### 1. Main.java (Entry Point)

**Purpose:** Application entry point that initializes the GUI

**Key Responsibilities:**
- Sets up the system look and feel
- Creates the Controller instance
- Launches the GUI in the Event Dispatch Thread (EDT)
- Handles initialization errors

**Code Flow:**
```java
main() → Create Controller → Create GUI → Show Window
```

**Key Code:**
```java
Controller controller = new Controller();
CarRentalGUI window = new CarRentalGUI(controller);
window.setVisible(true);
```

---

### 2. Controller.java (Business Logic)

**Purpose:** Central controller managing all business operations

**Key Data Structures:**
- `List<Car> allCars` - All cars in the system
- `List<Customer> allCustomers` - All customers
- `List<Car> rentedCars` - Currently rented cars
- `List<Car> availableCars` - Available for rent
- `int carId` - Auto-incrementing car ID
- `int customerId` - Auto-incrementing customer ID

**Core Methods:**

**Car Management:**
- `addNewCar()` - Add a new car to inventory
- `findCar()` - Find car by license plate
- `removeCarWithMessage()` - Delete a car
- `getAllCars()` - Get all cars
- `getAvailableCars()` - Get available cars
- `getRentedCars()` - Get rented cars
- `getCarsByName()` - Search by car name
- `getCarsByBrand()` - Search by brand

**Customer Management:**
- `addNewCustomer()` - Add new customer
- `findCustomer()` - Find customer by license/ID
- `removeCustomerWithMessage()` - Delete customer
- `getAllCustomers()` - Get all customers

**Rental Management:**
- `rentCar()` - Rent a car to a customer
- `releaseCarWithMessage()` - Return a rented car
- `bindCarToCustomer()` - Link car to customer
- `getCustomerRentDetails()` - Get customer's rentals
- `checkIfCustomer()` - Validate customer exists

**Business Rules:**
1. Cars must be available before renting
2. Cannot delete rented cars
3. Customers must exist before renting
4. License plates must be unique
5. Customer license numbers must be unique

---

### 3. Car.java (Model)

**Purpose:** Represents a car entity

**Attributes:**
```java
private int carId;                    // Unique identifier
private String name;                  // Car model name (e.g., "Camry")
private String brand;                 // Manufacturer (e.g., "Toyota")
private String numberPlate;           // License plate (unique)
private int rentPricePerDay;          // Daily rental rate
private int costPrice;                // Purchase cost
private String color;                 // Car color
private Customer currentUser;         // Currently renting customer
private Date dateOfRent;              // Rental start date
private int numberOfDays;             // Rental duration
private int totalRentPrice;          // Calculated total
```

**Key Methods:**
- Getters and setters for all attributes
- `setTotalRentPrice()` - Calculates: `rentPricePerDay × numberOfDays`
- `toString()` - String representation

**Relationships:**
- Has a `Customer` (currentUser) when rented
- Belongs to collections in Controller

---

### 4. Customer.java (Model)

**Purpose:** Represents a customer entity

**Attributes:**
```java
private int customerId;               // Unique identifier
private String name;                  // Customer name
private int age;                      // Customer age
private String licenseNumber;          // Driver's license (unique)
private String nationalIDNumber;      // National ID (unique)
private List<Car> carsRented;         // Currently rented cars
```

**Key Methods:**
- Getters and setters for all attributes
- `toString()` - String representation

**Relationships:**
- Has a list of `Car` objects (carsRented)
- Can rent multiple cars simultaneously

---

### 5. CarRentalGUI.java (View)

**Purpose:** Main graphical user interface

**UI Components:**

**Main Window:**
- JFrame with title "PJ's Car Rental Management System"
- Size: 1200×700 pixels
- Centered on screen

**Tabbed Interface:**
1. **🚗 Cars Tab**
   - Table showing all cars with status
   - Buttons: Add, Refresh, Delete, Modify, Search, View Available

2. **👥 Customers Tab**
   - Table showing all customers
   - Buttons: Add, Refresh, Delete, View Details

3. **📋 Rentals Tab**
   - Table showing active rentals
   - Buttons: Rent Car, Return Car, Refresh, View Customer Rentals

**Menu Bar:**
- File → Exit
- Cars → All car operations
- Customers → All customer operations
- Rentals → All rental operations

**Status Bar:**
- Bottom of window
- Shows current operation status
- Displays record counts

**Dialogs:**
- Add Car Dialog
- Add Customer Dialog
- Rent Car Dialog
- Modify Car Dialog
- Search Dialogs
- Confirmation Dialogs

**Key Methods:**
- `initializeGUI()` - Sets up the window
- `createCarsPanel()` - Creates cars tab
- `createCustomersPanel()` - Creates customers tab
- `createRentalsPanel()` - Creates rentals tab
- `refreshCarsTable()` - Updates cars table
- `refreshCustomersTable()` - Updates customers table
- `refreshRentalsTable()` - Updates rentals table
- `showAddCarDialog()` - Shows add car form
- `showRentCarDialog()` - Shows rent car form
- `updateStatus()` - Updates status bar

---

## ⚙️ Features & Functionality

### Car Management Features

1. **Add Car**
   - Input: Name, Brand, License Plate, Price/Day, Cost Price, Color
   - Validation: Duplicate license plate check
   - Result: Car added to inventory

2. **View All Cars**
   - Displays: ID, Name, Brand, License Plate, Price/Day, Cost Price, Color, Status
   - Status shows "Available" or "Rented"

3. **View Available Cars**
   - Filters to show only available cars
   - Useful for rental selection

4. **Modify Car**
   - Can update: Color, Rent Price, Cost Price, License Plate
   - Cannot modify: Name, Brand (by design)

5. **Delete Car**
   - Removes car from system
   - Validation: Cannot delete rented cars
   - Confirmation dialog required

6. **Search Cars**
   - By Name: Find all cars with specific name
   - By Brand: Find all cars of a brand
   - By License Plate: Find specific car

### Customer Management Features

1. **Add Customer**
   - Input: Name, Age, License Number, National ID
   - Validation: Duplicate license/ID check
   - Result: Customer added to database

2. **View All Customers**
   - Displays: ID, Name, Age, License Number, National ID

3. **View Customer Details**
   - Shows detailed information for a customer
   - Input: Customer license number

4. **Delete Customer**
   - Removes customer from system
   - Confirmation dialog required

### Rental Management Features

1. **Rent Car**
   - Input: Customer License, Car License Plate, Number of Days
   - Validations:
     - Customer must exist
     - Car must exist
     - Car must be available
   - Calculates: Total Price = Price/Day × Days
   - Updates: Car status to "Rented"
   - Links: Car to customer

2. **Return Car**
   - Input: Car License Plate
   - Actions:
     - Removes car from rented list
     - Adds car back to available list
     - Unlinks car from customer
   - Updates: Car status to "Available"

3. **View All Rentals**
   - Displays: Customer License, Car License Plate, Car Name, Date of Rent, Days, Total Price
   - Shows only active rentals

4. **View Customer Rentals**
   - Shows all cars rented by a specific customer
   - Input: Customer license number

---

## 🔄 Data Flow & Business Logic

### Adding a Car Flow

```
User clicks "Add Car" button
    ↓
GUI shows Add Car Dialog
    ↓
User fills form and clicks "Add"
    ↓
GUI validates input (not empty, valid numbers)
    ↓
GUI calls: controller.addNewCar(...)
    ↓
Controller checks: Does car with this plate exist?
    ↓
If NO: Creates new Car object
    ↓
Adds to allCars list
    ↓
Adds to availableCars list
    ↓
Returns success to GUI
    ↓
GUI shows success message
    ↓
GUI refreshes cars table
```

### Renting a Car Flow

```
User clicks "Rent Car" button
    ↓
GUI shows Rent Car Dialog
    ↓
User enters: Customer License, Car Plate, Days
    ↓
GUI calls: controller.rentCar(...)
    ↓
Controller validates:
    - Customer exists? → If NO: Return error
    - Car exists? → If NO: Return error
    - Car is available? → If NO: Return error
    ↓
If all valid:
    - Find customer object
    - Find car object
    - Calculate total price
    - Set rental date (current date)
    - Set number of days
    - Link car to customer
    - Add car to customer's rented list
    - Remove car from availableCars
    - Add car to rentedCars
    ↓
Returns success to GUI
    ↓
GUI refreshes tables (Cars & Rentals)
```

### Returning a Car Flow

```
User clicks "Return Car" button
    ↓
GUI shows input dialog
    ↓
User enters car license plate
    ↓
GUI calls: controller.releaseCarWithMessage(...)
    ↓
Controller validates:
    - Car exists? → If NO: Return error
    - Car is rented? → If NO: Return message
    ↓
If valid:
    - Get customer from car
    - Remove car from customer's rented list
    - Remove car from rentedCars list
    - Add car to availableCars list
    - Unlink car from customer (set currentUser = null)
    ↓
Returns success to GUI
    ↓
GUI refreshes tables
```

### Data Relationships

```
Controller
├── allCars (List<Car>)
│   ├── Car 1 → currentUser: Customer A (if rented)
│   ├── Car 2 → currentUser: null (if available)
│   └── Car 3 → currentUser: Customer B (if rented)
│
├── allCustomers (List<Customer>)
│   ├── Customer A → carsRented: [Car 1]
│   ├── Customer B → carsRented: [Car 3]
│   └── Customer C → carsRented: []
│
├── availableCars (List<Car>)
│   └── Contains cars where currentUser == null
│
└── rentedCars (List<Car>)
    └── Contains cars where currentUser != null
```

---

## 💻 Technical Details

### Data Storage
- **Type:** In-memory (ArrayList)
- **Persistence:** None (data lost on exit)
- **Collections Used:**
  - `ArrayList<Car>` - Dynamic arrays for cars
  - `ArrayList<Customer>` - Dynamic arrays for customers

### ID Generation
- **Method:** Auto-incrementing integers
- **Car IDs:** Start at 1, increment for each new car
- **Customer IDs:** Start at 1, increment for each new customer
- **Thread Safety:** Not thread-safe (single-threaded GUI)

### Date Handling
- **Library:** `java.util.Date`
- **Format:** Stored as Date object, displayed as formatted string
- **Format Pattern:** "yyyy-MM-dd HH:mm"

### GUI Threading
- **EDT (Event Dispatch Thread):** All GUI operations run on EDT
- **Initialization:** `EventQueue.invokeLater()` ensures proper thread handling
- **Thread Safety:** GUI is single-threaded, no concurrency issues

### Validation Rules

**Car Validation:**
- License plate must be unique
- Price values must be positive integers
- All text fields must be non-empty

**Customer Validation:**
- License number must be unique
- National ID must be unique
- Age must be positive integer

**Rental Validation:**
- Customer must exist in system
- Car must exist in system
- Car must be available (not already rented)
- Number of days must be positive

### Error Handling
- **Input Validation:** GUI validates before calling controller
- **Business Logic Validation:** Controller validates business rules
- **User Feedback:** Error messages shown via JOptionPane
- **Exception Handling:** Try-catch blocks for number parsing

---

## 🚀 How It Works

### Application Startup

1. **Main.java** is executed
2. Sets system look and feel (native OS appearance)
3. Creates **Controller** instance (empty data structures)
4. Creates **CarRentalGUI** instance
5. GUI initializes:
   - Creates window frame
   - Creates status bar
   - Creates menu bar
   - Creates three tabs (Cars, Customers, Rentals)
   - Initializes empty tables
6. Window becomes visible

### User Interaction Flow

```
User Action
    ↓
GUI Event Handler (button click, menu selection)
    ↓
GUI Method (e.g., showAddCarDialog)
    ↓
User Input (form filling)
    ↓
Validation (GUI level)
    ↓
Controller Method Call (e.g., controller.addNewCar)
    ↓
Business Logic Processing (Controller)
    ↓
Data Update (add to list, modify object)
    ↓
Return Result to GUI
    ↓
GUI Updates Display (refresh table, show message)
    ↓
Status Bar Update
```

### Real-time Updates

When data changes:
1. Controller updates its internal lists
2. Returns success/error message
3. GUI calls refresh methods:
   - `refreshCarsTable()` - Rebuilds cars table
   - `refreshCustomersTable()` - Rebuilds customers table
   - `refreshRentalsTable()` - Rebuilds rentals table
4. Tables automatically update
5. Status bar shows operation result

---

## 📖 Usage Guide

### Starting the Application

**Method 1: Batch Script (Windows)**
```batch
Double-click run.bat
```

**Method 2: Command Line**
```powershell
cd model\src\main
javac -d ..\..\..\out\production\CarRentalModule *.java controller\*.java model\*.java view\*.java
java -cp ..\..\..\out\production\CarRentalModule main.Main
```

**Method 3: IntelliJ IDEA**
- Open project
- Right-click Main.java → Run

### Typical Workflow

1. **Add Customers First**
   - Go to Customers tab
   - Click "Add Customer"
   - Fill form and submit
   - Repeat for multiple customers

2. **Add Cars**
   - Go to Cars tab
   - Click "Add Car"
   - Fill form and submit
   - Repeat for multiple cars

3. **Rent Cars**
   - Go to Rentals tab
   - Click "Rent Car"
   - Enter customer license, car plate, days
   - Submit

4. **Monitor Rentals**
   - View Rentals tab for active rentals
   - Check Cars tab to see status changes

5. **Return Cars**
   - Go to Rentals tab
   - Click "Return Car"
   - Enter car license plate
   - Submit

### Common Operations

**Search for a Car:**
1. Cars tab → Search button
2. Choose search type (Name/Brand/Plate)
3. Enter search term
4. View results

**Modify Car Details:**
1. Cars tab → Modify button
2. Enter car license plate
3. Edit fields in dialog
4. Save changes

**View Customer Rentals:**
1. Rentals tab → View Customer Rentals
2. Enter customer license number
3. View all cars rented by customer

---

## 🔮 Future Enhancements

### Potential Improvements

1. **Data Persistence**
   - Save to file (JSON/XML/Database)
   - Load data on startup
   - Auto-save functionality

2. **Advanced Features**
   - Rental history (past rentals)
   - Payment tracking
   - Late return penalties
   - Car maintenance records
   - Customer loyalty program

3. **UI Improvements**
   - Dark mode
   - Customizable themes
   - Export to PDF/Excel
   - Charts and statistics
   - Print functionality

4. **Business Logic**
   - Discount system
   - Seasonal pricing
   - Insurance options
   - Damage tracking
   - Fuel level tracking

5. **Technical Improvements**
   - Database integration (MySQL/PostgreSQL)
   - REST API for web access
   - Multi-user support
   - Role-based access control
   - Audit logging

---

## 📊 Summary

### What This Project Demonstrates

✅ **Object-Oriented Programming**
- Classes, inheritance, encapsulation
- Proper separation of concerns

✅ **Design Patterns**
- MVC architecture
- Separation of UI and business logic

✅ **Java GUI Development**
- Swing components
- Event handling
- Dialog management

✅ **Data Management**
- Collections (ArrayList)
- Entity relationships
- Business rule enforcement

✅ **User Experience**
- Intuitive interface
- Real-time feedback
- Error handling

### Key Takeaways

1. **Clean Architecture:** MVC pattern makes code maintainable
2. **User-Friendly:** GUI makes system accessible
3. **Extensible:** Easy to add new features
4. **Educational:** Great example of OOP in Java

---

## 📝 Conclusion

This Car Rental Management System is a complete, functional desktop application demonstrating:
- Java programming best practices
- GUI development with Swing
- MVC architecture implementation
- Business logic management
- User interface design

The system is ready to use and can be extended with additional features as needed.

For sample data and testing instructions, see `SAMPLE_DATA.md`.
For running instructions, see `HOW_TO_RUN.md`.

---

**Author:** ProgrammingGeek - Njoh Noh Prince Junior  
**Created:** 21-06-2021  
**Updated:** Enhanced with GUI interface

