# Sample Data for Car Rental System

This document provides sample input data you can use to test the Car Rental System GUI.

## Sample Customers

### Customer 1
- **Name:** John Smith
- **Age:** 28
- **License Number:** DL123456
- **National ID:** NID789012

### Customer 2
- **Name:** Sarah Johnson
- **Age:** 35
- **License Number:** DL234567
- **National ID:** NID890123

### Customer 3
- **Name:** Michael Brown
- **Age:** 42
- **License Number:** DL345678
- **National ID:** NID901234

### Customer 4
- **Name:** Emily Davis
- **Age:** 25
- **License Number:** DL456789
- **National ID:** NID012345

### Customer 5
- **Name:** David Wilson
- **Age:** 38
- **License Number:** DL567890
- **National ID:** NID123456

---

## Sample Cars

### Car 1 - Economy
- **Name:** Corolla
- **Brand:** Toyota
- **License Plate:** ABC-1234
- **Rent Price Per Day:** 50
- **Cost Price:** 25000
- **Color:** White

### Car 2 - Economy
- **Name:** Civic
- **Brand:** Honda
- **License Plate:** XYZ-5678
- **Rent Price Per Day:** 55
- **Cost Price:** 26000
- **Color:** Silver

### Car 3 - Sedan
- **Name:** Camry
- **Brand:** Toyota
- **License Plate:** DEF-9012
- **Rent Price Per Day:** 75
- **Cost Price:** 35000
- **Color:** Black

### Car 4 - SUV
- **Name:** RAV4
- **Brand:** Toyota
- **License Plate:** GHI-3456
- **Rent Price Per Day:** 90
- **Cost Price:** 40000
- **Color:** Blue

### Car 5 - Luxury
- **Name:** Accord
- **Brand:** Honda
- **License Plate:** JKL-7890
- **Rent Price Per Day:** 80
- **Cost Price:** 38000
- **Color:** Red

### Car 6 - SUV
- **Name:** CR-V
- **Brand:** Honda
- **License Plate:** MNO-2468
- **Rent Price Per Day:** 95
- **Cost Price:** 42000
- **Color:** Gray

### Car 7 - Sedan
- **Name:** Altima
- **Brand:** Nissan
- **License Plate:** PQR-1357
- **Rent Price Per Day:** 70
- **Cost Price:** 32000
- **Color:** White

### Car 8 - Luxury
- **Name:** Sonata
- **Brand:** Hyundai
- **License Plate:** STU-9753
- **Rent Price Per Day:** 85
- **Cost Price:** 36000
- **Color:** Black

---

## Sample Rental Scenarios

### Rental 1
- **Customer License Number:** DL123456 (John Smith)
- **Car License Plate:** ABC-1234 (Toyota Corolla)
- **Number of Days:** 3
- **Expected Total:** $150 (50 × 3)

### Rental 2
- **Customer License Number:** DL234567 (Sarah Johnson)
- **Car License Plate:** GHI-3456 (Toyota RAV4)
- **Number of Days:** 5
- **Expected Total:** $450 (90 × 5)

### Rental 3
- **Customer License Number:** DL345678 (Michael Brown)
- **Car License Plate:** DEF-9012 (Toyota Camry)
- **Number of Days:** 7
- **Expected Total:** $525 (75 × 7)

### Rental 4
- **Customer License Number:** DL456789 (Emily Davis)
- **Car License Plate:** JKL-7890 (Honda Accord)
- **Number of Days:** 2
- **Expected Total:** $160 (80 × 2)

---

## Testing Workflow

### Step 1: Add Customers
1. Go to **👥 Customers** tab
2. Click **➕ Add Customer**
3. Enter customer data from the list above
4. Repeat for 3-5 customers

### Step 2: Add Cars
1. Go to **🚗 Cars** tab
2. Click **➕ Add Car**
3. Enter car data from the list above
4. Repeat for 5-8 cars

### Step 3: View Available Cars
1. Go to **🚗 Cars** tab
2. Click **✅ Available** button
3. You should see all cars with "Available" status

### Step 4: Rent Cars
1. Go to **📋 Rentals** tab
2. Click **🚗 Rent Car**
3. Enter rental details from the scenarios above
4. After renting, check the **🚗 Cars** tab - the car status should change to "Rented"

### Step 5: View Rentals
1. Go to **📋 Rentals** tab
2. You should see all active rentals in the table
3. Check the total prices match the expected values

### Step 6: Search Cars
1. Go to **🚗 Cars** tab
2. Click **🔍 Search**
3. Try searching by:
   - **Name:** "Corolla" or "Camry"
   - **Brand:** "Toyota" or "Honda"
   - **License Plate:** "ABC-1234"

### Step 7: Return a Car
1. Go to **📋 Rentals** tab
2. Click **↩️ Return Car**
3. Enter a car's license plate (e.g., "ABC-1234")
4. Check that the car status changes back to "Available"

### Step 8: Modify Car Details
1. Go to **🚗 Cars** tab
2. Click **✏️ Modify**
3. Enter a car's license plate
4. Change color, price, or other details
5. Verify changes in the table

### Step 9: View Customer Details
1. Go to **👥 Customers** tab
2. Click **👁️ View Details**
3. Enter a customer's license number
4. View customer information

### Step 10: View Customer Rentals
1. Go to **📋 Rentals** tab
2. Click **📋 Customer Rentals**
3. Enter a customer's license number
4. View all cars rented by that customer

---

## Quick Test Checklist

- [ ] Add at least 3 customers
- [ ] Add at least 5 cars
- [ ] View all cars in the table
- [ ] View available cars only
- [ ] Rent a car to a customer
- [ ] View active rentals
- [ ] Search for a car by name
- [ ] Search for a car by brand
- [ ] Return a rented car
- [ ] Modify a car's details
- [ ] View customer details
- [ ] View customer rental history
- [ ] Delete a car (only if not rented)
- [ ] Delete a customer

---

## Tips

1. **Start with customers** - You need customers before you can rent cars
2. **Check availability** - Only available cars can be rented
3. **Verify totals** - Check that rental prices are calculated correctly (price/day × days)
4. **Status updates** - Watch the status bar at the bottom for operation feedback
5. **Table refresh** - Tables automatically refresh after operations
6. **Cannot delete rented cars** - You must return a car before deleting it
7. **Unique identifiers** - License plates and customer license numbers must be unique

---

## Expected Results

After adding all sample data:
- **Cars tab:** Should show 8 cars, all with "Available" status initially
- **Customers tab:** Should show 5 customers
- **Rentals tab:** Should be empty initially

After renting some cars:
- **Cars tab:** Some cars should show "Rented" status
- **Rentals tab:** Should show active rentals with calculated totals
- **Available cars:** Should show fewer cars than total

After returning cars:
- **Cars tab:** Returned cars should show "Available" status again
- **Rentals tab:** Returned cars should disappear from the list

