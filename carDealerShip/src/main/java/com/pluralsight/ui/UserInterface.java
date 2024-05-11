package com.pluralsight.ui;

import com.pluralsight.Model.DealerShip;
import com.pluralsight.Model.Vehicle;
import com.pluralsight.Services.FileManager;

import java.util.ArrayList;
import java.util.FormatFlagsConversionMismatchException;
import java.util.InputMismatchException;
import java.util.Scanner;


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
                System.out.println("(9) - Find vehicles by type (Car, truck, SUV, van)");
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
                        System.out.println("Finding vehicles with price range");
                        break;
                    case 5:
                        System.out.println("Finding vehicles by make / model");
                        break;
                    case 6:
                        System.out.println("Finding vehicles by year range");
                        break;
                    case 7:
                        System.out.println("Finding vehicles by color");
                        break;
                    case 8:
                        System.out.println("Find vehicles by mileage range");
                        break;
                    case 9:
                        System.out.println("Find vehicles by type");
                        break;
                    case 0:
                        System.out.println();
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
            catch (FormatFlagsConversionMismatchException e)
            {
                userInput.nextLine();
                System.out.println();
                System.out.println("Invalid input please enter a number");
            }
            catch (Exception e)
            {
                System.out.println();
                e.printStackTrace();
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
        }
        catch (InputMismatchException e)
        {
            userInput.nextLine();
            System.out.println("Invalid input");
        }
        catch (Exception e)
        {
            userInput.nextLine();
            System.out.println("Something went wrong try again");
        }

    }

    public void removeVehicle(DealerShip dealerShip)
    {
        int vin = 0;
        try {
            System.out.println();
            System.out.println("Enter the vin for the vehicle you want to remove");
            System.out.print("Enter input: ");
            vin = userInput.nextInt();
            userInput.nextLine();

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
            e.printStackTrace();
            System.out.println("Something went wrong try again");
        }

        ArrayList<Vehicle> allVehicles = dealerShip.getAllVehicles();

        for (Vehicle vehicle : allVehicles)
        {
            if(vehicle.getVin() == vin)
            {
                dealerShip.removeVehicle(vehicle);
            }
        }
    }
}
