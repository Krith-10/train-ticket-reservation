package com.krith.reservation.utils;

import org.javatuples.Pair;

import java.util.*;

public class SeatGenerator {
    static final int MAX_SEAT = 110;
    static final String SECTION_A = "A";
    static final String SECTION_B = "B";

    static Random random = new Random();

    // Array indicating the available seats between 1 to 110 in section A
    private static List<Integer> sectionA = new ArrayList<>();

    // Array indicating the available seats between 1 to 110 in section B
    private static List<Integer> sectionB = new ArrayList<>();

    static {
        initializeSeatForSectionA();
        initializeSeatForSectionB();
        System.out.println("Seat initialization has been completed");
    }

    private static void initializeSeatForSectionA(){
        for(int i=0; i<MAX_SEAT; i++){
            // Ticket seating starts from 1 to 110
            sectionA.add(i+1);
        }
    }

    private static void initializeSeatForSectionB(){
        for(int i=0; i<MAX_SEAT; i++){
            // Ticket seating starts from 1 to 110
            sectionB.add(i+1);
        }
    }

    public static Pair<String, Integer> generateSeat() throws Exception {
        if(isSeatAvailable(SECTION_A)){
            int randomNumber = random.nextInt(0,sectionA.size());
            int seatNumber = sectionA.get(randomNumber);
            // Remove based on index since the seat will be taken
            sectionA.remove(randomNumber);
            return Pair.with(SECTION_A, seatNumber);
        } else if(isSeatAvailable(SECTION_B)){
            int randomNumber = random.nextInt(0, sectionB.size());
            int seatNumber = sectionB.get(randomNumber);
            // Remove based on index since the seat will be taken
            sectionB.remove(randomNumber);
            return Pair.with(SECTION_B, seatNumber);
        }
        throw new RuntimeException("Seat booking has been filled. No more slots available");
    }

    public static Pair<String, Integer> modifySeat(String section, int previousSeatNumber) throws Exception {
        if(Objects.equals(section, SECTION_A) && isSeatAvailable(SECTION_A)){
            int randomNumber = random.nextInt(0, sectionA.size());
            int newSeatNumber = sectionA.get(randomNumber);
            sectionA.remove(randomNumber);
            // Now add the old seat number to the array.
            sectionA.add(previousSeatNumber);
            return Pair.with(SECTION_A, newSeatNumber);
        } else if(isSeatAvailable(SECTION_B)){
            int randomNumber = random.nextInt(0, sectionB.size());
            int newSeatNumber = sectionB.get(randomNumber);
            sectionB.remove(randomNumber);
            return Pair.with(SECTION_B, newSeatNumber);
        }
        throw new RuntimeException("Seat booking has been filled. Unable to modify seat");
    }

    public static void makeSeatAvailable(String section, int seatNumber){
        if(Objects.equals(section, SECTION_A))
            // Make the seat available as the reservation is deleted by the user
            sectionA.add(seatNumber);
        else
            sectionB.add(seatNumber);
    }

    private static boolean isSeatAvailable(String section){
        if(Objects.equals(section, SECTION_A)){
            return !sectionA.isEmpty();
        }else{
            return !sectionB.isEmpty();
        }
    }
}
