package com.pluralsight.Model;

import java.util.ArrayList;

public class DealerShip {
    String name;
    String address;
    String phone;
    ArrayList<Vehicle> inventory = new ArrayList<>();

    public DealerShip(String name, String address, String phone)
    {
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public ArrayList<Vehicle> getAllVehicles()
    {

        return inventory;
    }

    public void addVehicle(Vehicle vehicle)
    {
        System.out.println();
        System.out.println("Vehicle Removed");
        inventory.add(vehicle);
    }

    public void removeVehicle(Vehicle vehicle)
    {
        System.out.println("adding vehicle");
        inventory.remove(vehicle);
    }
}
