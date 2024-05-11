package com.pluralsight.ui;

import com.pluralsight.Model.DealerShip;
import com.pluralsight.Model.Vehicle;
import com.pluralsight.Services.FileManager;

import java.util.ArrayList;
import java.util.FormatFlagsConversionMismatchException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.stream.Collectors;


public class UserInterface {
    String vinTitle = "Vin";
    String yearTitle = "Year";
    String makeTitle = "Make";
    String modelTitle = "Model";
    String typeTitle = "type";
    String colorTitle = "Color";
    String odometerTitle = "Odometer";
    String priceTitle = "Price";
    private static Scanner userInput = new Scanner(System.in);

    public UserInterface()
    {
    }

    public void run()
    {
        DealerShip dealerShip = FileManager.getDealership();
        display(dealerShip);
    }

    // displaying menu
    public void display(DealerShip dealerShip)
    {
        System.out.println("----------------Year Ups dealer ship----------------");
        String input = "";
        int choice = 10;
        while(choice != 0)
        {
            try{
                System.out.println();
                System.out.println("(1) - List All vehicles");
                System.out.println("(2) - Add a vehicle");
                System.out.println("(3) - Remove a vehicle");
                System.out.println("(4) - Find vehicles within a price range");
                System.out.println("(5) - Find vehicles by make / model");
                System.out.println("(6) - Find vehicles by year range");
                System.out.println("(7) - Find vehicles by color");
                System.out.println("(8) - Find vehicles by mileage range");
                System.out.println("(9) - Find vehicles by type (Sedan, truck, SUV, van)");
                System.out.println("(0) - Save and quit");
                System.out.print("Enter your input: ");
                input = userInput.nextLine().strip().replace(" ", "");
                choice = Integer.parseInt(input);
                switch (choice)
                {
                    case 1:
                        displayAllVehicles(dealerShip);
                        break;
                    case 2:
                        addVehicle(dealerShip);
                        break;
                    case 3:
                        removeVehicle(dealerShip);
                        break;
                    case 4:
                        findVehicleWithPriceRange(dealerShip);
                        break;
                    case 5:
                        findVehiclesByMakeAndModel(dealerShip);
                        break;
                    case 6:
                        findByYearRand(dealerShip);
                        break;
                    case 7:
                        findVehiclesByColor(dealerShip);
                        break;
                    case 8:
                        findVehiclesByMileageRange(dealerShip);
                        break;
                    case 9:
                        findVehicleByType(dealerShip);
                        break;
                    case 0:
                        System.out.println();
                        FileManager.saveDealerShip(dealerShip);
                        System.out.println("Good bye :)");
                        break;
                    default:
                        System.out.println();
                        System.out.println("Invalid input");
                }
            }
            catch (InputMismatchException e)
            {
                userInput.nextLine();
                System.out.println();
                System.out.println("Invalid input");
            }
            catch (NumberFormatException e)
            {
                System.out.println();
                System.out.println("Invalid input please enter a number");
            }
            catch (Exception e)
            {
                System.out.println();
                System.out.println("Something went wrong, try again");
            }
        }
    }

    private void displayAllVehicles(DealerShip dealerShip)
    {
        System.out.println();
        System.out.println("-------------------------------------------All vehicles---------------------------------------------");
        System.out.printf(" %-10s | %-10s | %-10s | %-10s | %-10s | %-10s | %-10s | %-10s \n", vinTitle, yearTitle, makeTitle, modelTitle, typeTitle, colorTitle, odometerTitle, priceTitle);
        System.out.println("-".repeat(100));
        for (Vehicle vehicle : dealerShip.getAllVehicles())
        {
            System.out.printf(" %-10d | %-10d | %-10s | %-10s | %-10s | %-10s | %-10d | %.2f \n", vehicle.getVin(), vehicle.getYear(), vehicle.getMake(), vehicle.getModel(), vehicle.getVehicleType(), vehicle.getColor(), vehicle.getOdometer(), vehicle.getPrice());
            System.out.println("-".repeat(100));
        }
    }

    public void addVehicle(DealerShip dealerShip)
    {
        // getting new vehicles info
        try {
            System.out.println();
            System.out.println("Enter vehicle information");

            System.out.print("Enter the vin of the vehicle: ");
            String vinInput = userInput.nextLine().strip().replace(" ", "");
            int vin = Integer.parseInt(vinInput);

            System.out.print("Enter the year of the vehicle: ");
            String yearInput = userInput.nextLine().strip().replace(" ", "");
            int year = Integer.parseInt(yearInput);

            System.out.print("Enter vehicle make: ");
            String make = userInput.nextLine().strip();

            System.out.print("Enter vehicle model: ");
            String model = userInput.nextLine().strip();

            System.out.print("Enter vehicle type: ");
            String vehicleType = userInput.nextLine().strip();

            System.out.print("Enter vehicle color: ");
            String color = userInput.nextLine();

            System.out.print("Enter vehicle odometer: ");
            String odometerInput = userInput.nextLine().strip();
            int odometer = Integer.parseInt(odometerInput);

            System.out.print("Enter vehicle price: ");
            String priceInput = userInput.nextLine().strip();
            double price = Double.parseDouble(priceInput);

            // adding vehicle to dealership
            Vehicle vehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
            dealerShip.addVehicle(vehicle);

            System.out.println(vehicle.getMake() + " " + vehicle.getModel() + " added to inventory");
        }
        catch (NumberFormatException e)
        {
            System.out.println();
            System.out.println("Failed to convert input, try again");
        }
        catch (InputMismatchException e)
        {
            userInput.nextLine();
            System.out.println("Invalid input");
        }
        catch (Exception e)
        {
            System.out.println();
            System.out.println("Something went wrong try again");
        }

    }

    public void removeVehicle(DealerShip dealerShip)
    {
        // getting vehicle vin
        int vin = 0;
        try {
            System.out.println();
            System.out.println("Enter the vin for the vehicle you want to remove");
            System.out.print("Enter input: ");
            vin = userInput.nextInt();
            userInput.nextLine();

            ArrayList<Vehicle> allVehicles = dealerShip.getAllVehicles();

            for (int i = 0; i < allVehicles.size(); i++)
            {
                Vehicle vehicle = allVehicles.get(i);
                if(vehicle.getVin() == vin)
                {
                    // removing vehicle from inventory
                    dealerShip.removeVehicle(vehicle);
                    System.out.println();
                    System.out.println(vehicle.getMake() + " " + vehicle.getModel() + " removed from inventory");
                }
            }

        }
        catch (FormatFlagsConversionMismatchException e)
        {
            System.out.println("Invalid input please enter only numbers");
        }
        catch (InputMismatchException e)
        {
            userInput.nextLine();
            System.out.println("Invalid input");
        }
        catch (Exception e)
        {
            System.out.println("Something went wrong try again");
        }

    }

    public void findVehicleWithPriceRange(DealerShip dealerShip)
    {
        try
        {
            // getting min and max price
            System.out.println();
            System.out.print("Enter minimum price: ");
            double minPrice = userInput.nextDouble();
            userInput.nextLine();

            System.out.print("Enter maximum price: ");
            double maxPrice = userInput.nextDouble();
            userInput.nextLine();

            // searching within price range

            ArrayList<Vehicle> priceRangeVehicles = (ArrayList<Vehicle>) dealerShip.getAllVehicles().stream()
                    .filter(vehicle -> vehicle.getPrice() >= minPrice)
                    .filter(vehicle -> vehicle.getPrice() <= maxPrice)
                    .collect(Collectors.toList());

            System.out.println();
            System.out.println("-----------------------------------------Vehicles by price range------------------------------------");
            System.out.printf(" %-10s | %-10s | %-10s | %-10s | %-10s | %-10s | %-10s | %-10s \n", vinTitle, yearTitle, makeTitle, modelTitle, typeTitle, colorTitle, odometerTitle, priceTitle);
            System.out.println("-".repeat(100));
            for(Vehicle vehicle : priceRangeVehicles)
            {
                System.out.printf(" %-10d | %-10d | %-10s | %-10s | %-10s | %-10s | %-10d | %.2f \n", vehicle.getVin(), vehicle.getYear(), vehicle.getMake(), vehicle.getModel(), vehicle.getVehicleType(), vehicle.getColor(), vehicle.getOdometer(), vehicle.getPrice());
                System.out.println("-".repeat(100));
            }
            if(priceRangeVehicles.isEmpty())
            {
                System.out.println("No vehicles found");
            }

        }
        catch (NumberFormatException e)
        {
            System.out.println();
            System.out.println("Please enter your input in numbers");
        }
        catch (Exception e)
        {
            System.out.println();
            System.out.println("Something went wrong, try again");
        }
    }

    public void findVehiclesByMakeAndModel(DealerShip dealerShip)
    {
        // prompting user for make and model
        try
        {
            System.out.println();
            System.out.print("Enter the make: ");
            String make = userInput.nextLine().strip();

            System.out.print("Enter the model: ");
            String model = userInput.nextLine().strip();

            // filtering and adding the vehicle that match input make and model to the arrayList
            ArrayList<Vehicle> vehiclesByMakeAndModel = (ArrayList<Vehicle>) dealerShip.getAllVehicles().stream()
                    .filter(vehicle -> vehicle.getMake().equalsIgnoreCase(make) || vehicle.getModel().equalsIgnoreCase(model))
                    .collect(Collectors.toList());

            //looping through the array list and printing the vehicles
            System.out.println("-----------------------------------------Vehicles by model name------------------------------------");
            System.out.printf(" %-10s | %-10s | %-10s | %-10s | %-10s | %-10s | %-10s | %-10s \n", vinTitle, yearTitle, makeTitle, modelTitle, typeTitle, colorTitle, odometerTitle, priceTitle);
            System.out.println("-".repeat(100));
            for(Vehicle vehicle : vehiclesByMakeAndModel)
            {
                System.out.printf(" %-10d | %-10d | %-10s | %-10s | %-10s | %-10s | %-10d | %.2f \n", vehicle.getVin(), vehicle.getYear(), vehicle.getMake(), vehicle.getModel(), vehicle.getVehicleType(), vehicle.getColor(), vehicle.getOdometer(), vehicle.getPrice());
                System.out.println("-".repeat(100));
            }

            if (vehiclesByMakeAndModel.isEmpty())
            {
                System.out.println("No vehicles found");
            }

        }
        catch (InputMismatchException e)
        {
            System.out.println();
            System.out.println("Invalid input");
        }
        catch (Exception e)
        {
            System.out.println();
            System.out.println("Something went wrong, try again");
        }
    }

    public void findByYearRand(DealerShip dealerShip)
    {
        try
        {
            //prompting user for start year and end year
            System.out.println();
            System.out.print("Enter start year: ");
            int startYear = userInput.nextInt();

            System.out.print("Enter end year: ");
            int endYear = userInput.nextInt();
            userInput.nextLine();

            // filtering and checking if the is greater then or equal to start date and if the vehicle year is less then or equal to the end year
            ArrayList<Vehicle> vehiclesByYearRange = (ArrayList<Vehicle>) dealerShip.getAllVehicles().stream()
                            .filter(vehicle -> vehicle.getYear() >= startYear && vehicle.getYear() <= endYear)
                                    .collect(Collectors.toList());

            //displaying the vehicles
            System.out.println();
            System.out.println("-----------------------------------------Vehicles by year range------------------------------------");
            System.out.printf(" %-10s | %-10s | %-10s | %-10s | %-10s | %-10s | %-10s | %-10s \n", vinTitle, yearTitle, makeTitle, modelTitle, typeTitle, colorTitle, odometerTitle, priceTitle);
            System.out.println("-".repeat(100));
            for(Vehicle vehicle : vehiclesByYearRange)
            {
                System.out.printf(" %-10d | %-10d | %-10s | %-10s | %-10s | %-10s | %-10d | %.2f \n", vehicle.getVin(), vehicle.getYear(), vehicle.getMake(), vehicle.getModel(), vehicle.getVehicleType(), vehicle.getColor(), vehicle.getOdometer(), vehicle.getPrice());
                System.out.println("-".repeat(100));
            }

        }
        catch (NumberFormatException e)
        {
            System.out.println();
            System.out.println("Invalid input please enter only numbers");
        }
        catch (Exception e)
        {
            System.out.println();
            System.out.println("Something went wrong");
        }
    }

    public void findVehiclesByColor(DealerShip dealerShip)
    {
        try
        {
            // promoting user for vehicle color
            System.out.println();
            System.out.print("Enter vehicles color: ");
            String color = userInput.nextLine();

            // filtering and checking if the vehicles color is equal to the color provided
            ArrayList<Vehicle> vehiclesByColor = (ArrayList<Vehicle>) dealerShip.getAllVehicles().stream()
                            .filter(vehicle -> vehicle.getColor().equalsIgnoreCase(color))
                            .collect(Collectors.toList());

            // displaying vehicles
            System.out.println();
            System.out.println("--------------------------------------------Vehicles by color---------------------------------------");
            System.out.printf(" %-10s | %-10s | %-10s | %-10s | %-10s | %-10s | %-10s | %-10s \n", vinTitle, yearTitle, makeTitle, modelTitle, typeTitle, colorTitle, odometerTitle, priceTitle);
            System.out.println("-".repeat(100));
            for(Vehicle vehicle : vehiclesByColor)
            {
                System.out.printf(" %-10d | %-10d | %-10s | %-10s | %-10s | %-10s | %-10d | %.2f \n", vehicle.getVin(), vehicle.getYear(), vehicle.getMake(), vehicle.getModel(), vehicle.getVehicleType(), vehicle.getColor(), vehicle.getOdometer(), vehicle.getPrice());
                System.out.println("-".repeat(100));
            }
        }
        catch (InputMismatchException e)
        {
            System.out.println();
            System.out.println("Invalid input");
        }
        catch (Exception e)
        {
            System.out.println();
            System.out.println("Something went wrong try again");
        }
    }

    public void findVehiclesByMileageRange(DealerShip dealerShip)
    {
        // promopting user for input
        System.out.println();
        System.out.println("Enter starting miles range: ");
        int startingMileage = userInput.nextInt();

        System.out.print("Enter ending miles range: ");
        int endingMileage = userInput.nextInt();
        userInput.nextLine();

        // filtering and checking if vehicles mileage is in between what the user provided
        ArrayList<Vehicle> vehiclesByMileRange = (ArrayList<Vehicle>) dealerShip.getAllVehicles().stream()
                .filter(vehicle -> vehicle.getOdometer() >= startingMileage && vehicle.getOdometer() <= endingMileage)
                .collect(Collectors.toList());

        //displaying vehicles
        System.out.println("------------------------------------------Vehicles by mileage range-------------------------------------");
        System.out.printf(" %-10s | %-10s | %-10s | %-10s | %-10s | %-10s | %-10s | %-10s \n", vinTitle, yearTitle, makeTitle, modelTitle, typeTitle, colorTitle, odometerTitle, priceTitle);
        System.out.println("-".repeat(100));
        for (Vehicle vehicle : vehiclesByMileRange)
        {
            System.out.printf(" %-10d | %-10d | %-10s | %-10s | %-10s | %-10s | %-10d | %.2f \n", vehicle.getVin(), vehicle.getYear(), vehicle.getMake(), vehicle.getModel(), vehicle.getVehicleType(), vehicle.getColor(), vehicle.getOdometer(), vehicle.getPrice());
            System.out.println("-".repeat(100));
        }
    }

    public void findVehicleByType(DealerShip dealerShip)
    {
        try
        {
            // promopting user for input
            System.out.println();
            System.out.println("Enter the vehicle type: ");
            String vehicleType = userInput.nextLine().strip();

            // filtering and checking if the vehicle type is equal to what the user provided
            ArrayList<Vehicle> vehiclesByVehicleType = (ArrayList<Vehicle>) dealerShip.getAllVehicles().stream()
                    .filter(vehicle -> vehicle.getVehicleType().equalsIgnoreCase(vehicleType))
                    .collect(Collectors.toList());

            // displaying vehicles to the screen
            System.out.println("------------------------------------------Vehicles by type---------------------------------------");
            System.out.printf(" %-10s | %-10s | %-10s | %-10s | %-10s | %-10s | %-10s | %-10s \n", vinTitle, yearTitle, makeTitle, modelTitle, typeTitle, colorTitle, odometerTitle, priceTitle);
            System.out.println("-".repeat(100));
            for (Vehicle vehicle : vehiclesByVehicleType)
            {
                System.out.printf(" %-10d | %-10d | %-10s | %-10s | %-10s | %-10s | %-10d | %.2f \n", vehicle.getVin(), vehicle.getYear(), vehicle.getMake(), vehicle.getModel(), vehicle.getVehicleType(), vehicle.getColor(), vehicle.getOdometer(), vehicle.getPrice());
                System.out.println("-".repeat(100));
            }
        }
        catch (InputMismatchException e)
        {
            System.out.println("Invalid input");
        }
        catch (Exception e)
        {
            System.out.println("Something went wrong, try again");
        }
    }
}
