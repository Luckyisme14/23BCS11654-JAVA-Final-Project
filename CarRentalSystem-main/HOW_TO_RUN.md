# How to Run the Car Rental System

## Project Overview
This is a Java-based Car Rental Management System with a **Graphical User Interface (GUI)** that allows you to manage cars and customers through an intuitive window-based interface.

### Features:
- ✅ **Modern GUI Interface** - Easy-to-use graphical interface with tabs and buttons
- ✅ **Car Management** - Add, view, modify, delete, and search cars
- ✅ **Customer Management** - Add, view, and delete customers
- ✅ **Rental Management** - Rent cars, return cars, and view rental history
- ✅ **Real-time Updates** - Tables refresh automatically after operations
- ✅ **Status Bar** - Shows current operation status and record counts

## Project Structure
```
model/
  src/
    main/
      Main.java              (Entry point)
      controller/
        Controller.java      (Business logic)
      model/
        Car.java            (Car model)
        Customer.java       (Customer model)
```

## Prerequisites
- Java JDK (version 8 or higher) - ✅ You have Java 24.0.2 installed
- A terminal/command prompt

## Compilation and Execution Methods

### Method 1: Using Command Line (Recommended for Windows PowerShell)

#### Step 1: Navigate to the source directory
```powershell
cd model\src\main
```

#### Step 2: Compile the Java files
```powershell
javac -d ..\..\..\out\production\CarRentalModule *.java controller\*.java model\*.java
```

#### Step 3: Run the application
```powershell
java -cp ..\..\..\out\production\CarRentalModule main.Main
```

**Note for Linux/Mac users:** Use forward slashes (`/`) instead of backslashes (`\`) in paths.

### Method 2: Compile and Run from Project Root

#### From the root directory (`CarRentalSystem-main`):

**Compile:**
```bash
javac -d out/production/CarRentalModule -sourcepath model/src/main model/src/main/main/*.java model/src/main/main/controller/*.java model/src/main/main/model/*.java
```

**Run:**
```bash
java -cp out/production/CarRentalModule main.Main
```

### Method 3: Using IntelliJ IDEA
1. Open the project in IntelliJ IDEA
2. The project is already configured with the `.iml` file
3. Right-click on `Main.java` and select "Run 'Main.main()'"
4. Or use the green play button next to the `main` method
5. The GUI window will open automatically

## GUI Interface Overview

The application opens with a modern graphical interface featuring:

### Three Main Tabs:
1. **🚗 Cars Tab** - Manage all cars in the system
   - View all cars with status (Available/Rented)
   - Add new cars
   - Modify car details
   - Delete cars
   - Search cars by name, brand, or license plate
   - View available cars only

2. **👥 Customers Tab** - Manage customers
   - View all customers
   - Add new customers
   - Delete customers
   - View customer details

3. **📋 Rentals Tab** - Manage car rentals
   - View all active rentals
   - Rent cars to customers
   - Return rented cars
   - View customer rental history

### Features:
- **Menu Bar** - Access all functions from the top menu
- **Action Buttons** - Quick access buttons at the bottom of each tab
- **Status Bar** - Shows operation status and record counts at the bottom
- **Tables** - Sortable tables showing all data
- **Dialogs** - User-friendly input dialogs for adding/modifying data
- **Search** - Search functionality for finding specific cars

## Application Features

Once running, you'll see a menu with the following options:

### Documentation
- **1.** View Documentation

### Manage Cars
- **2.** Add a new car
- **3.** View all cars within the system
- **4.** Display available cars for rent
- **5.** Delete a car
- **6.** Modify a car's details
- **7.** Rent car(s)
- **8.** Show a car's details
- **9.** Display cars by category/brand
- **10.** Get total number of cars by name

### Manage Customers
- **11.** Add a new customer
- **12.** View all customers
- **13.** View a customer's rent details
- **14.** Clear a customer's rent
- **15.** Remove customer from system
- **16.** Show all rents
- **17.** Show a customer's details

### Exit
- **0.** Terminate/Exit System

## Usage Example (GUI)

1. **Start the application** - Run `Main.java` or double-click `run.bat`
   - The GUI window will open automatically

2. **Add a customer** - Click the "👥 Customers" tab
   - Click "➕ Add Customer" button
   - Fill in the form:
     - Name
     - Age
     - License number
     - National ID number
   - Click "Add"

3. **Add a car** - Click the "🚗 Cars" tab
   - Click "➕ Add Car" button
   - Fill in the form:
     - Name (e.g., "Camry")
     - Brand (e.g., "Toyota")
     - License plate number
     - Rent price per day
     - Cost price
     - Color
   - Click "Add"

4. **Rent a car** - Click the "📋 Rentals" tab
   - Click "🚗 Rent Car" button
   - Enter:
     - Customer's license number
     - Car license plate number
     - Number of days
   - Click "Rent"

5. **View available cars** - Click the "🚗 Cars" tab
   - Click "✅ Available" button to see only available cars

6. **Return a car** - Click the "📋 Rentals" tab
   - Click "↩️ Return Car" button
   - Enter the car's license plate number

7. **Exit** - Click the "X" button on the window or use File → Exit

## Notes

- The application runs in-memory (data is not persisted between runs)
- All data is lost when the application exits
- There are commented-out sections in the code for file-based persistence (not currently active)
- The application uses a modern GUI interface built with Java Swing
- The GUI provides a more user-friendly experience compared to console input
- All operations are performed through dialogs and buttons
- Tables automatically refresh after operations

## Troubleshooting

### Common Issues:

1. **"Class not found" error:**
   - Make sure you've compiled all Java files
   - Check that the classpath includes the output directory

2. **"Package does not exist" error:**
   - Ensure you're compiling from the correct directory
   - Check that the package structure matches the directory structure

3. **Input/Output issues:**
   - Make sure you're running the application in a terminal that supports interactive input
   - Some IDEs may have issues with Scanner input - use a terminal instead

## Quick Start Script (Windows)

A `run.bat` file has been created in the project root. Simply double-click it to compile and run the application.

### Manual Compilation and Run (One-liner)

From the project root directory, you can also run:
```powershell
cd model\src\main; javac -d ..\..\..\out\production\CarRentalModule *.java controller\*.java model\*.java; java -cp ..\..\..\out\production\CarRentalModule main.Main
```

Or use separate commands:
```powershell
cd model\src\main
javac -d ..\..\..\out\production\CarRentalModule *.java controller\*.java model\*.java
java -cp ..\..\..\out\production\CarRentalModule main.Main
```

