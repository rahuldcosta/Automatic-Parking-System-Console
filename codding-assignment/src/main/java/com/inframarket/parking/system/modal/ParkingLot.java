package com.inframarket.parking.system.modal;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.*;

public class ParkingLot {

    private static final String ENTRY_LOG = "Entry Log";
    private static final String EXIT_LOG = "Exit Log";
    private static final String PARKING_IS_FULL = "PARKING IS FULL";
    private static final String DATE_FORMAT = "mm-dd-yyyy hh:mm:ss";
    private Map<String, List<Vehicle>> colorSortedVehicleMap;
    private Vehicle[] parkingSlots;

    100
    public ParkingLot(Integer maxParkingSlotSize) {
        60 40
            0-60 cars
            61-100 bikes
            40

        parkingSlots = new Vehicle[maxParkingSlotSize];
        colorSortedVehicleMap = new HashMap<>();
    }

    public void enterParkingLot(Vehicle vehicle) {

        Integer slotNumber=findNextAvailableSlots();
        if (slotNumber!=-1) {
            parkingSlots[slotNumber]=vehicle;
            trackVehiclesBasedOnColor(vehicle);
            logActivity(vehicle.getRegistrationNumber(), ENTRY_LOG, slotNumber);
        } else
            System.out.println(PARKING_IS_FULL);
    }

    private Integer findNextAvailableSlots() {

        for(int parkingSlot =0 ;parkingSlot<parkingSlots.length;parkingSlot++)
        {
            if(Objects.isNull(parkingSlots[parkingSlot]))
                return parkingSlot;
        }
        return -1;
    }

    private void logActivity(String registrationNumber, String logType, Integer slotNumber) {
        Format f = new SimpleDateFormat(DATE_FORMAT);
        String currentTimestamp = f.format(new Date());
        switch (logType) {

            case ENTRY_LOG:
                System.out.println("[" + logType + "] Vehicle No:- " + registrationNumber + " allotted Slot No:- " + slotNumber + " at " + currentTimestamp);
                break;
            case EXIT_LOG:
                System.out.println("[" + logType + "] Vehicle No:- " + registrationNumber + " at Slot No:- " + slotNumber + " has exited Parking Lot  at " + currentTimestamp);
                break;
        }
    }

    private void trackVehiclesBasedOnColor(Vehicle vehicle) {
        if (!colorSortedVehicleMap.containsKey(vehicle.getColor())) {
            colorSortedVehicleMap.put(vehicle.getColor(), new ArrayList<>());
            colorSortedVehicleMap.get(vehicle.getColor()).add(vehicle);
        } else
            colorSortedVehicleMap.get(vehicle.getColor()).add(vehicle);
    }

    public void exitParkingLot(Integer slotNumber) {
        Vehicle vehicle = parkingSlots[slotNumber];
        colorSortedVehicleMap.get(vehicle.getColor()).remove(vehicle);
        parkingSlots[slotNumber]= null;
        logActivity(vehicle.getRegistrationNumber(), EXIT_LOG, slotNumber);
    }

    public List<Vehicle> getParkingSlots() {
        return Arrays.asList(parkingSlots);
    }

    public Map<String, List<Vehicle>> getColorSortedVehicleMap() {
        return colorSortedVehicleMap;
    }
}



