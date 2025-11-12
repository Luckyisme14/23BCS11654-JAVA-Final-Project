package main.view;

import main.controller.Controller;
import main.model.Car;
import main.model.Customer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Main GUI window for Car Rental System
 */
public class CarRentalGUI extends JFrame {
    private Controller controller;
    private JTable carsTable;
    private JTable customersTable;
    private JTable rentalsTable;
    private DefaultTableModel carsTableModel;
    private DefaultTableModel customersTableModel;
    private DefaultTableModel rentalsTableModel;

    public CarRentalGUI(Controller controller) {
        this.controller = controller;
        initializeGUI();
    }

    private JLabel statusLabel;

    private void initializeGUI() {
        setTitle("PJ's Car Rental Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 700);
        setLocationRelativeTo(null);

        // Initialize status bar first (before panels that might use it)
        statusLabel = new JLabel("Ready - Welcome to Car Rental System");
        statusLabel.setBorder(BorderFactory.createLoweredBevelBorder());
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 12));

        // Create menu bar
        createMenuBar();

        // Create main panel with tabs
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Arial", Font.PLAIN, 14));

        // Cars tab
        tabbedPane.addTab("🚗 Cars", createCarsPanel());

        // Customers tab
        tabbedPane.addTab("👥 Customers", createCustomersPanel());

        // Rentals tab
        tabbedPane.addTab("📋 Rentals", createRentalsPanel());

        add(tabbedPane, BorderLayout.CENTER);
        add(statusLabel, BorderLayout.SOUTH);
    }

    private void updateStatus(String message) {
        if (statusLabel != null) {
            statusLabel.setText(message);
        }
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        // File Menu
        JMenu fileMenu = new JMenu("File");
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));
        fileMenu.add(exitItem);
        menuBar.add(fileMenu);

        // Cars Menu
        JMenu carsMenu = new JMenu("Cars");
        JMenuItem addCarItem = new JMenuItem("Add New Car");
        addCarItem.addActionListener(e -> showAddCarDialog());
        JMenuItem viewAllCarsItem = new JMenuItem("View All Cars");
        viewAllCarsItem.addActionListener(e -> refreshCarsTable());
        JMenuItem viewAvailableCarsItem = new JMenuItem("View Available Cars");
        viewAvailableCarsItem.addActionListener(e -> showAvailableCars());
        JMenuItem deleteCarItem = new JMenuItem("Delete Car");
        deleteCarItem.addActionListener(e -> showDeleteCarDialog());
        JMenuItem modifyCarItem = new JMenuItem("Modify Car");
        modifyCarItem.addActionListener(e -> showModifyCarDialog());
        JMenuItem searchCarItem = new JMenuItem("Search Car");
        searchCarItem.addActionListener(e -> showSearchCarDialog());

        carsMenu.add(addCarItem);
        carsMenu.add(viewAllCarsItem);
        carsMenu.add(viewAvailableCarsItem);
        carsMenu.addSeparator();
        carsMenu.add(deleteCarItem);
        carsMenu.add(modifyCarItem);
        carsMenu.add(searchCarItem);
        menuBar.add(carsMenu);

        // Customers Menu
        JMenu customersMenu = new JMenu("Customers");
        JMenuItem addCustomerItem = new JMenuItem("Add New Customer");
        addCustomerItem.addActionListener(e -> showAddCustomerDialog());
        JMenuItem viewAllCustomersItem = new JMenuItem("View All Customers");
        viewAllCustomersItem.addActionListener(e -> refreshCustomersTable());
        JMenuItem deleteCustomerItem = new JMenuItem("Delete Customer");
        deleteCustomerItem.addActionListener(e -> showDeleteCustomerDialog());
        JMenuItem viewCustomerItem = new JMenuItem("View Customer Details");
        viewCustomerItem.addActionListener(e -> showCustomerDetailsDialog());

        customersMenu.add(addCustomerItem);
        customersMenu.add(viewAllCustomersItem);
        customersMenu.addSeparator();
        customersMenu.add(deleteCustomerItem);
        customersMenu.add(viewCustomerItem);
        menuBar.add(customersMenu);

        // Rentals Menu
        JMenu rentalsMenu = new JMenu("Rentals");
        JMenuItem rentCarItem = new JMenuItem("Rent Car");
        rentCarItem.addActionListener(e -> showRentCarDialog());
        JMenuItem returnCarItem = new JMenuItem("Return Car");
        returnCarItem.addActionListener(e -> showReturnCarDialog());
        JMenuItem viewAllRentalsItem = new JMenuItem("View All Rentals");
        viewAllRentalsItem.addActionListener(e -> refreshRentalsTable());
        JMenuItem viewCustomerRentalsItem = new JMenuItem("View Customer Rentals");
        viewCustomerRentalsItem.addActionListener(e -> showCustomerRentalsDialog());

        rentalsMenu.add(rentCarItem);
        rentalsMenu.add(returnCarItem);
        rentalsMenu.addSeparator();
        rentalsMenu.add(viewAllRentalsItem);
        rentalsMenu.add(viewCustomerRentalsItem);
        menuBar.add(rentalsMenu);

        setJMenuBar(menuBar);
    }

    private JPanel createCarsPanel() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Table setup
        String[] columns = {"ID", "Name", "Brand", "License Plate", "Price/Day", "Cost Price", "Color", "Status"};
        carsTableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        carsTable = new JTable(carsTableModel);
        carsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        carsTable.setFont(new Font("Arial", Font.PLAIN, 12));
        carsTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        carsTable.setRowHeight(25);
        carsTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        JScrollPane scrollPane = new JScrollPane(carsTable);
        scrollPane.setBorder(BorderFactory.createTitledBorder("All Cars"));
        panel.add(scrollPane, BorderLayout.CENTER);

        // Buttons panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        buttonPanel.setBorder(BorderFactory.createTitledBorder("Actions"));
        
        JButton addButton = new JButton("➕ Add Car");
        addButton.setPreferredSize(new Dimension(120, 30));
        addButton.addActionListener(e -> {
            showAddCarDialog();
            updateStatus("Car management - Add new car");
        });
        
        JButton refreshButton = new JButton("🔄 Refresh");
        refreshButton.setPreferredSize(new Dimension(120, 30));
        refreshButton.addActionListener(e -> {
            refreshCarsTable();
            updateStatus("Cars table refreshed");
        });
        
        JButton deleteButton = new JButton("🗑️ Delete");
        deleteButton.setPreferredSize(new Dimension(120, 30));
        deleteButton.addActionListener(e -> {
            showDeleteCarDialog();
            updateStatus("Car management - Delete car");
        });
        
        JButton modifyButton = new JButton("✏️ Modify");
        modifyButton.setPreferredSize(new Dimension(120, 30));
        modifyButton.addActionListener(e -> {
            showModifyCarDialog();
            updateStatus("Car management - Modify car");
        });
        
        JButton searchButton = new JButton("🔍 Search");
        searchButton.setPreferredSize(new Dimension(120, 30));
        searchButton.addActionListener(e -> {
            showSearchCarDialog();
            updateStatus("Car management - Search car");
        });
        
        JButton viewAvailableButton = new JButton("✅ Available");
        viewAvailableButton.setPreferredSize(new Dimension(120, 30));
        viewAvailableButton.addActionListener(e -> {
            showAvailableCars();
            updateStatus("Showing available cars");
        });

        buttonPanel.add(addButton);
        buttonPanel.add(refreshButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(modifyButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(viewAvailableButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        refreshCarsTable();
        return panel;
    }

    private JPanel createCustomersPanel() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Table setup
        String[] columns = {"ID", "Name", "Age", "License Number", "National ID"};
        customersTableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        customersTable = new JTable(customersTableModel);
        customersTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        customersTable.setFont(new Font("Arial", Font.PLAIN, 12));
        customersTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        customersTable.setRowHeight(25);
        customersTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        JScrollPane scrollPane = new JScrollPane(customersTable);
        scrollPane.setBorder(BorderFactory.createTitledBorder("All Customers"));
        panel.add(scrollPane, BorderLayout.CENTER);

        // Buttons panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        buttonPanel.setBorder(BorderFactory.createTitledBorder("Actions"));
        
        JButton addButton = new JButton("➕ Add Customer");
        addButton.setPreferredSize(new Dimension(140, 30));
        addButton.addActionListener(e -> {
            showAddCustomerDialog();
            updateStatus("Customer management - Add new customer");
        });
        
        JButton refreshButton = new JButton("🔄 Refresh");
        refreshButton.setPreferredSize(new Dimension(120, 30));
        refreshButton.addActionListener(e -> {
            refreshCustomersTable();
            updateStatus("Customers table refreshed");
        });
        
        JButton deleteButton = new JButton("🗑️ Delete");
        deleteButton.setPreferredSize(new Dimension(120, 30));
        deleteButton.addActionListener(e -> {
            showDeleteCustomerDialog();
            updateStatus("Customer management - Delete customer");
        });
        
        JButton viewButton = new JButton("👁️ View Details");
        viewButton.setPreferredSize(new Dimension(140, 30));
        viewButton.addActionListener(e -> {
            showCustomerDetailsDialog();
            updateStatus("Customer management - View customer details");
        });

        buttonPanel.add(addButton);
        buttonPanel.add(refreshButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(viewButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        refreshCustomersTable();
        return panel;
    }

    private JPanel createRentalsPanel() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Table setup
        String[] columns = {"Customer License", "Car License Plate", "Car Name", "Date of Rent", "Days", "Total Price"};
        rentalsTableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        rentalsTable = new JTable(rentalsTableModel);
        rentalsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        rentalsTable.setFont(new Font("Arial", Font.PLAIN, 12));
        rentalsTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        rentalsTable.setRowHeight(25);
        rentalsTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        JScrollPane scrollPane = new JScrollPane(rentalsTable);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Active Rentals"));
        panel.add(scrollPane, BorderLayout.CENTER);

        // Buttons panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        buttonPanel.setBorder(BorderFactory.createTitledBorder("Actions"));
        
        JButton rentButton = new JButton("🚗 Rent Car");
        rentButton.setPreferredSize(new Dimension(130, 30));
        rentButton.addActionListener(e -> {
            showRentCarDialog();
            updateStatus("Rental management - Rent a car");
        });
        
        JButton returnButton = new JButton("↩️ Return Car");
        returnButton.setPreferredSize(new Dimension(130, 30));
        returnButton.addActionListener(e -> {
            showReturnCarDialog();
            updateStatus("Rental management - Return a car");
        });
        
        JButton refreshButton = new JButton("🔄 Refresh");
        refreshButton.setPreferredSize(new Dimension(120, 30));
        refreshButton.addActionListener(e -> {
            refreshRentalsTable();
            updateStatus("Rentals table refreshed");
        });
        
        JButton viewCustomerRentalsButton = new JButton("📋 Customer Rentals");
        viewCustomerRentalsButton.setPreferredSize(new Dimension(160, 30));
        viewCustomerRentalsButton.addActionListener(e -> {
            showCustomerRentalsDialog();
            updateStatus("Rental management - View customer rentals");
        });

        buttonPanel.add(rentButton);
        buttonPanel.add(returnButton);
        buttonPanel.add(refreshButton);
        buttonPanel.add(viewCustomerRentalsButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        refreshRentalsTable();
        return panel;
    }

    private void refreshCarsTable() {
        carsTableModel.setRowCount(0);
        List<Car> cars = controller.getAllCars();
        List<Car> rentedCars = controller.getRentedCars();
        for (Car car : cars) {
            // Check if car is rented by checking if it's in the rented list
            boolean isRented = false;
            for (Car rentedCar : rentedCars) {
                if (rentedCar.getNumberPlate().equals(car.getNumberPlate())) {
                    isRented = true;
                    break;
                }
            }
            String status = isRented ? "Rented" : "Available";
            carsTableModel.addRow(new Object[]{
                    car.getCarId(),
                    car.getName(),
                    car.getBrand(),
                    car.getNumberPlate(),
                    "$" + car.getRentPricePerDay(),
                    "$" + car.getCostPrice(),
                    car.getColor(),
                    status
            });
        }
        updateStatus("Displaying " + cars.size() + " car(s)");
    }

    private void refreshCustomersTable() {
        customersTableModel.setRowCount(0);
        List<Customer> customers = controller.getAllCustomers();
        for (Customer customer : customers) {
            customersTableModel.addRow(new Object[]{
                    customer.getCustomerId(),
                    customer.getName(),
                    customer.getAge(),
                    customer.getLicenseNumber(),
                    customer.getNationalIDNumber()
            });
        }
        updateStatus("Displaying " + customers.size() + " customer(s)");
    }

    private void refreshRentalsTable() {
        rentalsTableModel.setRowCount(0);
        List<Car> rentedCars = controller.getRentedCars();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        int count = 0;
        for (Car car : rentedCars) {
            if (car.getCurrentUser() != null) {
                String dateStr = car.getDateOfRent() != null ? sdf.format(car.getDateOfRent()) : "N/A";
                rentalsTableModel.addRow(new Object[]{
                        car.getCurrentUser().getLicenseNumber(),
                        car.getNumberPlate(),
                        car.getName(),
                        dateStr,
                        car.getNumberOfDays() + " day(s)",
                        "$" + car.getTotalRentPrice()
                });
                count++;
            }
        }
        updateStatus("Displaying " + count + " active rental(s)");
    }

    private void showAddCarDialog() {
        JDialog dialog = new JDialog(this, "Add New Car", true);
        dialog.setSize(400, 350);
        dialog.setLocationRelativeTo(this);

        JPanel panel = new JPanel(new GridLayout(7, 2, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTextField nameField = new JTextField();
        JTextField brandField = new JTextField();
        JTextField plateField = new JTextField();
        JTextField priceField = new JTextField();
        JTextField costField = new JTextField();
        JTextField colorField = new JTextField();

        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Brand:"));
        panel.add(brandField);
        panel.add(new JLabel("License Plate:"));
        panel.add(plateField);
        panel.add(new JLabel("Rent Price/Day:"));
        panel.add(priceField);
        panel.add(new JLabel("Cost Price:"));
        panel.add(costField);
        panel.add(new JLabel("Color:"));
        panel.add(colorField);

        JButton addButton = new JButton("Add");
        JButton cancelButton = new JButton("Cancel");

        addButton.addActionListener(e -> {
            try {
                String name = nameField.getText().trim();
                String brand = brandField.getText().trim();
                String plate = plateField.getText().trim();
                int price = Integer.parseInt(priceField.getText().trim());
                int cost = Integer.parseInt(costField.getText().trim());
                String color = colorField.getText().trim();

                if (name.isEmpty() || brand.isEmpty() || plate.isEmpty() || color.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "Please fill all fields!");
                    return;
                }

                Car existingCar = controller.findCar(plate);
                if (existingCar != null) {
                    JOptionPane.showMessageDialog(dialog, "Car with license plate " + plate + " already exists!");
                    return;
                }

                controller.addNewCar(name, brand, plate, price, cost, color);
                JOptionPane.showMessageDialog(dialog, "Car added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                refreshCarsTable();
                updateStatus("Car '" + name + "' added successfully");
                dialog.dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Invalid number format!");
            }
        });

        cancelButton.addActionListener(e -> dialog.dispose());

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(addButton);
        buttonPanel.add(cancelButton);

        dialog.add(panel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }

    private void showAddCustomerDialog() {
        JDialog dialog = new JDialog(this, "Add New Customer", true);
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(this);

        JPanel panel = new JPanel(new GridLayout(5, 2, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTextField nameField = new JTextField();
        JTextField ageField = new JTextField();
        JTextField licenseField = new JTextField();
        JTextField nationalIDField = new JTextField();

        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Age:"));
        panel.add(ageField);
        panel.add(new JLabel("License Number:"));
        panel.add(licenseField);
        panel.add(new JLabel("National ID:"));
        panel.add(nationalIDField);

        JButton addButton = new JButton("Add");
        JButton cancelButton = new JButton("Cancel");

        addButton.addActionListener(e -> {
            try {
                String name = nameField.getText().trim();
                int age = Integer.parseInt(ageField.getText().trim());
                String license = licenseField.getText().trim();
                String nationalID = nationalIDField.getText().trim();

                if (name.isEmpty() || license.isEmpty() || nationalID.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "Please fill all fields!");
                    return;
                }

                Customer existingCustomer = controller.findCustomer(license, nationalID);
                if (existingCustomer != null) {
                    JOptionPane.showMessageDialog(dialog, "Customer with license " + license + " already exists!");
                    return;
                }

                controller.addNewCustomer(name, age, license, nationalID);
                JOptionPane.showMessageDialog(dialog, "Customer added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                refreshCustomersTable();
                updateStatus("Customer '" + name + "' added successfully");
                dialog.dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Invalid age format!");
            }
        });

        cancelButton.addActionListener(e -> dialog.dispose());

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(addButton);
        buttonPanel.add(cancelButton);

        dialog.add(panel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }

    private void showRentCarDialog() {
        JDialog dialog = new JDialog(this, "Rent Car", true);
        dialog.setSize(400, 250);
        dialog.setLocationRelativeTo(this);

        JPanel panel = new JPanel(new GridLayout(4, 2, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTextField customerLicenseField = new JTextField();
        JTextField carPlateField = new JTextField();
        JTextField daysField = new JTextField();

        panel.add(new JLabel("Customer License Number:"));
        panel.add(customerLicenseField);
        panel.add(new JLabel("Car License Plate:"));
        panel.add(carPlateField);
        panel.add(new JLabel("Number of Days:"));
        panel.add(daysField);

        JButton rentButton = new JButton("Rent");
        JButton cancelButton = new JButton("Cancel");

        rentButton.addActionListener(e -> {
            try {
                String customerLicense = customerLicenseField.getText().trim();
                String carPlate = carPlateField.getText().trim();
                int days = Integer.parseInt(daysField.getText().trim());

                if (customerLicense.isEmpty() || carPlate.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "Please fill all fields!");
                    return;
                }

                String result = controller.rentCar(customerLicense, carPlate, days);
                if (result.startsWith("Successfully")) {
                    JOptionPane.showMessageDialog(dialog, result, "Success", JOptionPane.INFORMATION_MESSAGE);
                    refreshCarsTable();
                    refreshRentalsTable();
                    updateStatus("Car rented successfully");
                    dialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(dialog, result, "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Invalid number of days!");
            }
        });

        cancelButton.addActionListener(e -> dialog.dispose());

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(rentButton);
        buttonPanel.add(cancelButton);

        dialog.add(panel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }

    private void showReturnCarDialog() {
        String plate = JOptionPane.showInputDialog(this, "Enter car license plate number to return:", "Return Car", JOptionPane.QUESTION_MESSAGE);
        if (plate != null && !plate.trim().isEmpty()) {
            String result = controller.releaseCarWithMessage(plate.trim());
            if (result.contains("Successfully")) {
                JOptionPane.showMessageDialog(this, result, "Success", JOptionPane.INFORMATION_MESSAGE);
                updateStatus("Car returned successfully");
            } else {
                JOptionPane.showMessageDialog(this, result, "Information", JOptionPane.INFORMATION_MESSAGE);
            }
            refreshCarsTable();
            refreshRentalsTable();
        }
    }

    private void showDeleteCarDialog() {
        String plate = JOptionPane.showInputDialog(this, "Enter car license plate number to delete:", "Delete Car", JOptionPane.QUESTION_MESSAGE);
        if (plate != null && !plate.trim().isEmpty()) {
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this car?\nThis action cannot be undone.", "Confirm Deletion", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (confirm == JOptionPane.YES_OPTION) {
                String result = controller.removeCarWithMessage(plate.trim());
                if (result.contains("SUCCESSFULLY")) {
                    JOptionPane.showMessageDialog(this, result, "Success", JOptionPane.INFORMATION_MESSAGE);
                    updateStatus("Car deleted successfully");
                } else {
                    JOptionPane.showMessageDialog(this, result, "Error", JOptionPane.ERROR_MESSAGE);
                }
                refreshCarsTable();
                refreshRentalsTable();
            }
        }
    }

    private void showDeleteCustomerDialog() {
        String license = JOptionPane.showInputDialog(this, "Enter customer license number to delete:", "Delete Customer", JOptionPane.QUESTION_MESSAGE);
        if (license != null && !license.trim().isEmpty()) {
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this customer?\nThis action cannot be undone.", "Confirm Deletion", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (confirm == JOptionPane.YES_OPTION) {
                String result = controller.removeCustomerWithMessage(license.trim());
                if (result.contains("SUCCESSFULLY")) {
                    JOptionPane.showMessageDialog(this, result, "Success", JOptionPane.INFORMATION_MESSAGE);
                    updateStatus("Customer deleted successfully");
                } else {
                    JOptionPane.showMessageDialog(this, result, "Error", JOptionPane.ERROR_MESSAGE);
                }
                refreshCustomersTable();
            }
        }
    }

    private void showModifyCarDialog() {
        String plate = JOptionPane.showInputDialog(this, "Enter car license plate number to modify:");
        if (plate != null && !plate.trim().isEmpty()) {
            Car car = controller.findCar(plate.trim());
            if (car == null) {
                JOptionPane.showMessageDialog(this, "Car not found!");
                return;
            }

            JDialog dialog = new JDialog(this, "Modify Car", true);
            dialog.setSize(400, 300);
            dialog.setLocationRelativeTo(this);

            JPanel panel = new JPanel(new GridLayout(4, 2, 5, 5));
            panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            JTextField colorField = new JTextField(car.getColor());
            JTextField priceField = new JTextField(String.valueOf(car.getRentPricePerDay()));
            JTextField costField = new JTextField(String.valueOf(car.getCostPrice()));
            JTextField plateField = new JTextField(car.getNumberPlate());

            panel.add(new JLabel("Color:"));
            panel.add(colorField);
            panel.add(new JLabel("Rent Price/Day:"));
            panel.add(priceField);
            panel.add(new JLabel("Cost Price:"));
            panel.add(costField);
            panel.add(new JLabel("License Plate:"));
            panel.add(plateField);

            JButton saveButton = new JButton("Save");
            JButton cancelButton = new JButton("Cancel");

            saveButton.addActionListener(e -> {
                try {
                    car.setColor(colorField.getText().trim());
                    car.setRentPricePerDay(Integer.parseInt(priceField.getText().trim()));
                    car.setCostPrice(Integer.parseInt(costField.getText().trim()));
                    car.setNumberPlate(plateField.getText().trim());
                    JOptionPane.showMessageDialog(dialog, "Car updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    refreshCarsTable();
                    updateStatus("Car details updated successfully");
                    dialog.dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(dialog, "Invalid number format!");
                }
            });

            cancelButton.addActionListener(e -> dialog.dispose());

            JPanel buttonPanel = new JPanel(new FlowLayout());
            buttonPanel.add(saveButton);
            buttonPanel.add(cancelButton);

            dialog.add(panel, BorderLayout.CENTER);
            dialog.add(buttonPanel, BorderLayout.SOUTH);
            dialog.setVisible(true);
        }
    }

    private void showSearchCarDialog() {
        JDialog dialog = new JDialog(this, "Search Car", true);
        dialog.setSize(350, 150);
        dialog.setLocationRelativeTo(this);

        JPanel panel = new JPanel(new GridLayout(3, 1, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton searchByNameButton = new JButton("Search by Name");
        JButton searchByBrandButton = new JButton("Search by Brand");
        JButton searchByPlateButton = new JButton("Search by License Plate");

        searchByNameButton.addActionListener(e -> {
            String name = JOptionPane.showInputDialog(dialog, "Enter car name:");
            if (name != null && !name.trim().isEmpty()) {
                List<Car> cars = controller.getCarsByName(name.trim());
                showCarSearchResults(cars, "Cars with name: " + name);
            }
        });

        searchByBrandButton.addActionListener(e -> {
            String brand = JOptionPane.showInputDialog(dialog, "Enter car brand:");
            if (brand != null && !brand.trim().isEmpty()) {
                List<Car> cars = controller.getCarsByBrand(brand.trim());
                showCarSearchResults(cars, "Cars with brand: " + brand);
            }
        });

        searchByPlateButton.addActionListener(e -> {
            String plate = JOptionPane.showInputDialog(dialog, "Enter license plate:");
            if (plate != null && !plate.trim().isEmpty()) {
                Car car = controller.findCar(plate.trim());
                if (car != null) {
                    JOptionPane.showMessageDialog(dialog, car.toString(), "Car Details", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(dialog, "Car not found!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        panel.add(searchByNameButton);
        panel.add(searchByBrandButton);
        panel.add(searchByPlateButton);

        dialog.add(panel);
        dialog.setVisible(true);
    }

    private void showCarSearchResults(List<Car> cars, String title) {
        if (cars.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No cars found!", "Search Results", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        JDialog dialog = new JDialog(this, title, true);
        dialog.setSize(800, 400);
        dialog.setLocationRelativeTo(this);

        String[] columns = {"ID", "Name", "Brand", "License Plate", "Price/Day", "Cost Price", "Color"};
        DefaultTableModel model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        for (Car car : cars) {
            model.addRow(new Object[]{
                    car.getCarId(),
                    car.getName(),
                    car.getBrand(),
                    car.getNumberPlate(),
                    "$" + car.getRentPricePerDay(),
                    "$" + car.getCostPrice(),
                    car.getColor()
            });
        }

        JTable table = new JTable(model);
        table.setFont(new Font("Arial", Font.PLAIN, 12));
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        table.setRowHeight(25);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        JScrollPane scrollPane = new JScrollPane(table);
        dialog.add(scrollPane);
        dialog.setVisible(true);
    }

    private void showAvailableCars() {
        List<Car> cars = controller.getAvailableCars();
        showCarSearchResults(cars, "Available Cars");
    }

    private void showCustomerDetailsDialog() {
        String license = JOptionPane.showInputDialog(this, "Enter customer license number:");
        if (license != null && !license.trim().isEmpty()) {
            String details = controller.showCustomerDetails(license.trim());
            JOptionPane.showMessageDialog(this, details, "Customer Details", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void showCustomerRentalsDialog() {
        String license = JOptionPane.showInputDialog(this, "Enter customer license number:");
        if (license != null && !license.trim().isEmpty()) {
            List<Car> rentedCars = controller.getCustomerRentDetails(license.trim());
            if (rentedCars.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No rentals found for this customer!", "Rentals", JOptionPane.INFORMATION_MESSAGE);
            } else {
                showCarSearchResults(rentedCars, "Rentals for Customer: " + license);
            }
        }
    }
}

