package com.laubor.starwarscvapp.model;



public class Vehicle extends Transport{


    public String vehicle_class;

    public String getVehicle_class() {
        return vehicle_class;
    }

    public void setVehicle_class(String vehicle_class) {
        this.vehicle_class = vehicle_class;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "cargo_capacity='" + cargo_capacity + '\'' +
                ", consumables='" + consumables + '\'' +
                ", cost_in_credits='" + cost_in_credits + '\'' +
                ", created=" + created +
                ", crew='" + crew + '\'' +
                ", edited=" + edited +
                ", length='" + length + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", max_atmosphering_speed='" + max_atmosphering_speed + '\'' +
                ", model='" + model + '\'' +
                ", name='" + name + '\'' +
                ", passengers='" + passengers + '\'' +
                ", pilots=" + pilots +
                ", films=" + films +
                ", url='" + url + '\'' +
                ", vehicle_class='" + vehicle_class + '\'' +
                '}';
    }
}