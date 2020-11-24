package com.inframarket.parking.system.service;

import com.inframarket.parking.system.modal.Vehicle;

import java.util.*;
import java.util.stream.Collectors;

public class ParkingSystemService {

    public static List<String> fetchVehicleRegistrationNumbersBasedOnColor(Map<String, List<Vehicle>> colorSortedVehicleMap, String color) {
        return colorSortedVehicleMap.get(color).stream().map(vehicle -> vehicle.getRegistrationNumber()).collect(Collectors.toList());
    }

    public static Integer fetchParkingSlotForGivenRegistrationNumber(List<Vehicle> parkingSlots, String registrationNumber) {
        Optional<Vehicle> optionalVehicle = parkingSlots.stream().filter(vehicle -> vehicle.getRegistrationNumber().equals(registrationNumber)).findFirst();
        if (optionalVehicle.isPresent())
            return parkingSlots.indexOf(optionalVehicle.get());
        return -1;
    }

    public static List<Integer> fetchParkingSlotsBasedOnColor(List<Vehicle> parkingSlots, Map<String, List<Vehicle>> colorSortedVehicleMap, String color) {
        return colorSortedVehicleMap.get(color).stream().map(vehicle -> parkingSlots.indexOf(vehicle)).collect(Collectors.toList());
    }

    public static List<Integer> fetchAvailableParkingSlots(List<Vehicle> parkingSlots) {
        List<Integer> availableSlots = new ArrayList<>();
        for (int i = 0; i < parkingSlots.size(); i++) {
            if (Objects.isNull(parkingSlots.get(i)))
                availableSlots.add(i);
        }
        return availableSlots;
    }

}
