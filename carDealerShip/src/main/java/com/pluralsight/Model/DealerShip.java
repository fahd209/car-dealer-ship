package com.pluralsight.Model;

import java.util.ArrayList;

public class DealerShip {
    String name;
    String address;
    String phone;
    ArrayList<Vehicle> inventory;

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
}
