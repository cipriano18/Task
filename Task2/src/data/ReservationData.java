package data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import bussiness.LogicReservation;
import domain.FlightReservation;

public class ReservationData {

    private static final String filePath = "reservaciones.json";
    private static ArrayList<FlightReservation> flightReservations= new ArrayList<FlightReservation>();
 
    private static final JsonUtils<FlightReservation> jsonUtils = new JsonUtils<>(filePath);
    
    public static boolean createReservation(FlightReservation flightReservation) {
    	if (LogicReservation.checkReservationIdExists(getFlightReservations(), flightReservation)) {
			return false;
		}
        try {
            jsonUtils.save(flightReservation); 
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public static ArrayList<FlightReservation> getFlightReservations() {
        try {
            List<FlightReservation> reservations = jsonUtils.getElements(FlightReservation.class);
        return new ArrayList<>(reservations);
        } catch (IOException e) {
           
            e.printStackTrace();
        }
        return new ArrayList<>(); 
    }
    public static boolean deleteReservation(String id) {
        try {
            ArrayList<FlightReservation> allReservations = getFlightReservations();
            ArrayList<FlightReservation> updatedReservations = new ArrayList<>();

            for (int i = 0; i < allReservations.size(); i++) {
                FlightReservation flightReservation = allReservations.get(i);
                if (flightReservation.getReservationId().equals(id)) {
                    continue;
                }
                updatedReservations.add(flightReservation);
            }
            if (updatedReservations.size() != allReservations.size()) {
 
                jsonUtils.saveList(updatedReservations); 
                return true;  
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false; 
	}
    public static void updateReservation(String id, FlightReservation updateFlightReservation) {
        ArrayList<FlightReservation> allReservations = getFlightReservations();
        boolean found = false;
        for (int i = 0; i < allReservations.size(); i++) {
            FlightReservation flightReservation = allReservations.get(i);
            if (flightReservation.getReservationId().equals(id)) {
                allReservations.set(i, updateFlightReservation); 
                found = true;
                break; 
            }
        }
        if (found) {
            try {
                jsonUtils.saveList(allReservations);  
            } catch (IOException e) {
                System.err.println("Error al guardar la lista actualizada: " + e.getMessage());
            }
        }
    }
 public static boolean search(String id) {
	 ArrayList<FlightReservation>flightReservations=getFlightReservations();
	 for(FlightReservation flightReservation: flightReservations) {
		 if (flightReservation.getReservationId().equals(id)) {
			return true;
		} 
	 }
	 return false;
 } 
}