package bussiness;

import java.util.ArrayList;
import domain.FlightReservation;

public class LogicReservation {

  public static boolean checkReservationIdExists(ArrayList<FlightReservation> list, FlightReservation flightReservation) {
    for (FlightReservation t : list) {
      if (t.getReservationId().equals(flightReservation.getReservationId())) {
        return true;
      }
    }
    return false;
  }
}
  