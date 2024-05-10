package com.pluralsight.ui;

import com.pluralsight.Model.DealerShip;
import com.pluralsight.Model.Vehicle;
import com.pluralsight.Services.FileManager;

import java.util.FormatFlagsConversionMismatchException;
import java.util.InputMismatchException;
import java.util.Scanner;


public class UserInterface {
    String vin = "Vin";
    String year = "Year";
    String make = "Make";
    String model = "Model";
    String type = "type";
    String color = "Color";
    String odometer = "Odometer";
    String price = "Price";
    private static Scanner userInput = new Scanner(System.in);
    DealerShip dealerShip;

    public UserInterface()
    {
        dealerShip = FileManager.getDealership();
    }

    public void run()
    {
        display();
    }

    public void display()
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
                        System.out.println("Adding vehicle");
                        break;
                    case 3:
                        System.out.println("Remove a vehicle");
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
                System.out.println("Something went wrong, try again ");
            }
        }
    }

    private void displayAllVehicles(DealerShip dealerShip) {

        System.out.println();
        System.out.println("-------------------------------------------All vehicles---------------------------------------------");
        System.out.printf(" %-10s | %-10s | %-10s | %-10s | %-10s | %-10s | %-10s | %-10s \n", vin, year, make, model, type, color, odometer, price);
        System.out.println("-".repeat(100));
        for (Vehicle vehicle : dealerShip.getAllVehicles())
        {
            System.out.printf(" %-10d | %-10d | %-10s | %-10s | %-10s | %-10s | %-10d | %.2f \n", vehicle.getVin(), vehicle.getYear(), vehicle.getMake(), vehicle.getModel(), vehicle.getVehicleType(), vehicle.getColor(), vehicle.getOdometer(), vehicle.getPrice());
            System.out.println("-".repeat(100));
        }
    }
}
