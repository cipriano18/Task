module Task2 {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.desktop;
	requires javafx.base;
	requires com.fasterxml.jackson.databind;
	requires com.fasterxml.jackson.datatype.jsr310;
	requires javafx.graphics;
	opens bussiness to javafx.graphics, javafx.fxml;
	exports domain;

}
