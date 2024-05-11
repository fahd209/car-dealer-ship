# car-dealer-ship
### Overview of project
I made a car dealer ship project for a car sales man. This project will include a dealer ship and vehicles. The project will allow the user to add, remove, and search for cars in the inventory. 

UML for project:
![umlForProject](Images/diagramSS.png)

## Phase 1

I started by working on the Dealership class and my vehicle class. In my Dealer ship class i added get all vehicles functionaly, add or remove vehicles from the inventory. I added test cases for get all vehicles and add a vehicle.

## Phase 2

I added the functionalty that will allow the program to read all the data in the csv file when the application runs. I also made the application save the data that was added, or removed to the from memorey to the csv file when the application gets closed.

## Phase 3

I added the application user interface to allow the user to interacte with the application. I made the menu with all the list options for the user. 

## Phase 4

I added functionalty to the user interface dispatching commands to the application's backend allowing the user to interact with the data in the csv file. 

## List menu

1) List All vehicles

When the user enters "1" it will dispatch that command to the backend and load all the vehicles from the dealer ship class to the userInterface class, from the user interface class the vehicles get displayed on the screen.

2) Add a vehicle

When the user enters "2". The user will be promopted for the vehicle information. Once the user provides the nessary information about the vehicle it will be added to the dealer ships inventory. 

3) Remove a vehicle

When the user enters "3". The user will be promopted for the vin number for the vehicle they want to remove. Then it will search for the vehicle with the same exact vin number and remove it from the inventory.

4) Find vehicles within a price range

 Once the user enters "4". The user will be promopted for the minimum and maximum price range. Once the user provides that information. The program will search throught the dealer ship inventory and check if the vehicle is within that price range. Then it will get displayed on to the screen.

5) Find vehicles by make / model

When the user enters "5". The user will be promopted for the make and model of the vehicle. After the user provides the make and model. The program will search for that vehicle with the make and model name. Then it will display all the vehicles that match that name on to the screen.

6) Find vehicles by year range
7) Find vehicles by color
8) Find vehicles by mileage range
9) Find vehicles by type (Car, truck, SUV, van)
0) Save and quit
