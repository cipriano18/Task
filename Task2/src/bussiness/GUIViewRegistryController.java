package bussiness;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import java.lang.classfile.ClassFile.Option;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import data.ReservationData;
import domain.FlightReservation;

public class GUIViewRegistryController implements Initializable {

	  @FXML
	    private Button Btnsave;
        
	  @FXML
	    private Label labelMassage;
	  
	    @FXML
	    private DatePicker flightDate;

	    @FXML
	    private TextField txtDestination;

	    @FXML
	    private TextField txtReservation;

	    @FXML
	    private TextField txtName; 

	    @FXML
	    private TextField txtOrigin;

	    @FXML
	    private TextField txtPrice;

    @FXML
    private ListView<String> listRes;

    @FXML
    private ComboBox<String> option;

     @FXML
    public void save(ActionEvent event) {
        String selectedOption = option.getValue();

        if (option.getSelectionModel().isEmpty() ) {
            notify("Selecciona una opción válida.");
            return;
        }

        if (selectedOption.equalsIgnoreCase("crear")) {
            setFlightReservation();
        } else if (selectedOption.equalsIgnoreCase("leer")) {
            ReadJson();
        } else if (selectedOption.equalsIgnoreCase("buscar")) {
              search();
        } else if (selectedOption.equalsIgnoreCase("actualizar")) {
             updateFlightReservation();
        } else if (selectedOption.equalsIgnoreCase("eliminar")) {
          deleteFlightReservation();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String[] crudOptions = {"Crear", "Leer", "Actualizar", "Eliminar","buscar"};
        ObservableList<String> items = FXCollections.observableArrayList(crudOptions);
        option.setItems(items);
        option.setValue("Seleccione una operación");
    }

  

    private void setFlightReservation() {
        if (CheckRequiredFields()) {
            notify(" Todos los campos son obligatorios.");
        } else {
            FlightReservation flightReservation = new FlightReservation();
            flightReservation.setReservationId(txtReservation.getText());
            flightReservation.setPassengerName(txtName.getText());
            flightReservation.setOrigin(txtOrigin.getText());
            flightReservation.setDestination(txtDestination.getText());
            LocalDate flightDateValue = flightDate.getValue();
            if (flightDateValue == null || flightDateValue.isBefore(LocalDate.now())) {
                notify("Selecciona una fecha válida.");
                return;
            }
            flightReservation.setFlightDate(flightDateValue);
            try {
                flightReservation.setPrice(Double.parseDouble(txtPrice.getText()));
            } catch (NumberFormatException e) {
                notify("El precio debe ser un número válido.");
                return;
            }

            if (ReservationData.createReservation(flightReservation)) {
                notify(" Reserva creada exitosamente ");
                clean(); 
            } else {
                notify(" Ya existe una reservación con este ID.");
            }
        }
    }
    public void updateFlightReservation() {
        if (CheckRequiredFields()) {
            notify(" Todos los campos son obligatorios.");
        } else {
            FlightReservation flightReservation = new FlightReservation();
            flightReservation.setReservationId(txtReservation.getText()); 
            flightReservation.setPassengerName(txtName.getText());    
            flightReservation.setOrigin(txtOrigin.getText());
            flightReservation.setDestination(txtDestination.getText());
            LocalDate flightDateValue = flightDate.getValue();
            if (flightDateValue == null || flightDateValue.isBefore(LocalDate.now())) {
                notify(" Selecciona una fecha válida.");
                return;
            }
            flightReservation.setFlightDate(flightDateValue);  

            try {
                flightReservation.setPrice(Double.parseDouble(txtPrice.getText()));  
            } catch (NumberFormatException e) {
                notify(" El precio debe ser un número válido.");
                return;
            }

            String reservationId = txtReservation.getText(); 
            if (reservationId == null || reservationId.isEmpty()) {
                notify(" Por favor, ingresa un ID de reservación.");
            } else {
                ReservationData.updateReservation(reservationId, flightReservation);
                notify(" La reservación con ID " + reservationId + " ha sido actualizada.");
            }
        }
    }
    public void search() {
        String id = txtReservation.getText();
        if (id == null || id.isEmpty()) {
            notify(" Por favor, ingresa un ID para buscar.");
            return;  
        }

        if (ReservationData.search(id)) {
            notify(" La reservación con ID '" + id + "' fue encontrada.");
        } else {
            notify(" No se encontró ninguna reservación" );
        }
    }

    public void deleteFlightReservation() {
        String idSearch = txtReservation.getText();

        if (idSearch == null || idSearch.isEmpty()) {
            notify(" Por favor, ingresa un ID para buscar.");
         
    return;
        }
    boolean deleted = ReservationData.deleteReservation(idSearch);

        if (deleted) {
            notify(" La reservación con ID " + idSearch + " ha sido eliminada correctamente.");
        } else {
            notify( " No se encontró una reservación con ese ID.");
        }
    }

	private void ReadJson() {
		listRes.getItems().clear();
		ArrayList<FlightReservation> reservations = ReservationData.getFlightReservations();
		if (reservations != null && !reservations.isEmpty()) {
			for (FlightReservation reservation : reservations) {
				listRes.getItems().add(reservation.toString());
			}
		} else {
			listRes.getItems().add("No hay reservaciones disponibles.");
		}
	}
	private void notify(String message) {
	    labelMassage.setVisible(true);
	    labelMassage.setText(message);

	    Timeline timeline = new Timeline(
	        new KeyFrame(
	            Duration.seconds(4), 
	            e -> {
	                labelMassage.setText("");  
	                labelMassage.setVisible(false);
	            }
	        )
	    );
	    
	    timeline.play();  
	}
	private void clean() {
	    txtDestination.setText("");
	    txtName.setText("");
	    txtOrigin.setText("");
	    txtPrice.setText("");
	    txtReservation.setText("");
	    flightDate.setValue(null);  
	}
	private boolean CheckRequiredFields() {
	    if (txtDestination.getText().isEmpty() && txtName.getText().isEmpty() && txtPrice.getText().isEmpty()
	            && txtOrigin.getText().isEmpty() && flightDate.getValue() == null) {
	        return true;
	    }
	    return false;
	}
}