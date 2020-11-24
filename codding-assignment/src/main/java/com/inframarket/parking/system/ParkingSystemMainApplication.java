package com.inframarket.parking.system;

import com.inframarket.parking.system.modal.ParkingLot;
import com.inframarket.parking.system.modal.Vehicle;
import com.inframarket.parking.system.service.ParkingSystemService;

import java.util.List;
import java.util.Scanner;

public class ParkingSystemMainApplication {
    private static final int MAX_PARKING_SLOT_SIZE = 15;
    private static ParkingLot parkingLot = new ParkingLot(MAX_PARKING_SLOT_SIZE);
    static ParkingSystemService parkingSystemService = new ParkingSystemService();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String args[]) {
        Vehicle v1 = new Vehicle("KA1234", "red");
        Vehicle v2 = new Vehicle("KA1111", "black");
        Vehicle v3 = new Vehicle("KA2222", "pink");
        Vehicle v4 = new Vehicle("KA3333", "blue");
        Vehicle v5 = new Vehicle("KA4444", "green");
        Vehicle v6 = new Vehicle("KA5678", "red");
        Vehicle v7 = new Vehicle("KA5555", "red");
        Vehicle v8 = new Vehicle("KA6666", "pink");
        Vehicle v9 = new Vehicle("KA7777", "yellow");
        Vehicle v10 = new Vehicle("KA8901", "red");
        Vehicle v11 = new Vehicle("KA8888", "yellow");
        Vehicle v12 = new Vehicle("KA9999", "red");
        Vehicle v13 = new Vehicle("KA0001", "black");
        Vehicle v14 = new Vehicle("KA0002", "red");
        Vehicle v15 = new Vehicle("KA0003", "green");

        System.out.println("Opened Parking Lot for Today");
        parkingLot.enterParkingLot(v1);
        parkingLot.enterParkingLot(v2);
        parkingLot.enterParkingLot(v3);
        parkingLot.enterParkingLot(v4);
        parkingLot.enterParkingLot(v5);
        parkingLot.enterParkingLot(v6);
        parkingLot.enterParkingLot(v7);
        parkingLot.enterParkingLot(v8);
        parkingLot.enterParkingLot(v9);
        parkingLot.enterParkingLot(v10);
        parkingLot.enterParkingLot(v11);
        parkingLot.enterParkingLot(v12);
        parkingLot.enterParkingLot(v13);
        parkingLot.enterParkingLot(v14);
        parkingLot.enterParkingLot(v15);

        System.out.println("Welcome to Parking Lot System Application");
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        while (choice != 7) {
            System.out.println("Please Select option below");
            System.out.println("1.Add a Vehicle to Parking Lot");
            System.out.println("2.Remove a Vehicle from Parking Lot");
            System.out.println("3.Query Registration No. of all vehicles of a particular color");
            System.out.println("4.Query Parking slot of a vehicle given registration no.");
            System.out.println("5.Query Parking slots for vehicles given a color");
            System.out.println("6.Query List of available slots in the parking lot.");
            System.out.println("7.Quit Application");
            System.out.println("Option :- ");
            choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    addVehicleToParkingLot();
                    break;
                case 2:
                    removeVehicleToParkingLot();
                    break;
                case 3:
                    displayRegistrationNumbersOfVehicleOfAParticularColor();
                    break;
                case 4:
                    displayParkingSlotBasedOnRegistrationNumber();
                    break;
                case 5:
                    displayListOfParkingSlotsForVehiclesBasedOnColor();
                    break;
                case 6:
                    displayListOfAllAvailableSlotsInParkingLot();
                    break;
                case 7:
                    exitApplication();
                    break;
            }
        }

    }

    private static void displayListOfAllAvailableSlotsInParkingLot() {
        List<Integer> slots = parkingSystemService.fetchAvailableParkingSlots(parkingLot.getParkingSlots());
        if (slots.isEmpty())
            System.out.println("PARKING IS FULL , No slots available");
        else
            slots.forEach(System.out::println);
    }

    private static void displayListOfParkingSlotsForVehiclesBasedOnColor() {
        String color;

        System.out.println("Please Enter the Color");
        color = scanner.next();
        parkingSystemService.fetchParkingSlotsBasedOnColor(parkingLot.getParkingSlots(), parkingLot.getColorSortedVehicleMap(), color).forEach(System.out::println);
    }

    private static void displayParkingSlotBasedOnRegistrationNumber() {
        String registrationNumber;
        System.out.println("Please Enter Vehicle Details to Exit");
        System.out.println("Enter Registration Number");
        registrationNumber = scanner.next();
        System.out.println("The Slot No. is :- " + parkingSystemService.fetchParkingSlotForGivenRegistrationNumber(parkingLot.getParkingSlots(), registrationNumber));

    }

    private static void displayRegistrationNumbersOfVehicleOfAParticularColor() {
        String color;

        System.out.println("Please Enter the Color");
        color = scanner.next();
        parkingSystemService.fetchVehicleRegistrationNumbersBasedOnColor(parkingLot.getColorSortedVehicleMap(), color).forEach(System.out::println);
    }

    private static void exitApplication() {
        System.out.println("Shutting Down Parking Lot Application . Thank you for using it.");
    }

    private static void removeVehicleToParkingLot() {
        String registrationNumber;

        System.out.println("Please Enter Vehicle Details to Exit");
        System.out.println("Enter Registration Number");
        registrationNumber = scanner.next();
        parkingLot.exitParkingLot(parkingSystemService.fetchParkingSlotForGivenRegistrationNumber(parkingLot.getParkingSlots(), registrationNumber));
    }

    private static void addVehicleToParkingLot() {

        String registrationNumber;
        String color;

        if (parkingSystemService.fetchAvailableParkingSlots(parkingLot.getParkingSlots()).isEmpty())
            System.out.println("PARKING IS FULL , No slots available");
        else {
            System.out.println("Please Enter Vehicle Details to Enter");
            System.out.println("Enter Registration Number");
            registrationNumber = scanner.next();

            System.out.println("Enter Color of Vehicle");
            color = scanner.next();
            Vehicle vehicle = new Vehicle(registrationNumber, color);
            parkingLot.enterParkingLot(vehicle);
        }
    }

}
