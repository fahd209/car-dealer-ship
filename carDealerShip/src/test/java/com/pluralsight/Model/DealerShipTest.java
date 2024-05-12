package com.pluralsight.Model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DealerShipTest {

    @Test
    public void getAllVehicles_shouldReturn_theInventory()
    {
        //Arrange
        DealerShip dealerShip = new DealerShip("D & B Used Cars", "\"111 Old Benbrook Rd, Dallas, TX 45137\"","817-555-5555");

        //act
        ArrayList<Vehicle> allVehicles = dealerShip.getAllVehicles();

        //assert
        assertEquals(dealerShip.inventory,allVehicles, "Because get allAllVehicles should return the inventory");
    }

    @Test
    public void addVehicle_ShouldAdd_AVehicleToTheInvenotry()
    {
        //arrange
        DealerShip dealerShip = new DealerShip("D & B Used Cars", "\"111 Old Benbrook Rd, Dallas, TX 45137\"","817-555-5555");
        Vehicle vehicle = new Vehicle(10112, 1993, "Ford", "Explorer", "SUV", "Red", 525123, 995);

        //act
        dealerShip.addVehicle(vehicle);

        //assert
        assertTrue(dealerShip.getAllVehicles().contains(vehicle));
    }

    @Test
    public void removeVehicle_ShouldRemove_VehicleFromTheInventory()
    {
        //arrange
        DealerShip dealerShip = new DealerShip("D & B Used Cars", "\"111 Old Benbrook Rd, Dallas, TX 45137\"","817-555-5555");
        Vehicle vehicle = new Vehicle(10112, 1993, "Ford", "Explorer", "SUV", "Red", 525123, 995);

        //act
        dealerShip.removeVehicle(vehicle);

        //assert
        assertFalse(dealerShip.getAllVehicles().contains(vehicle));
    }
}