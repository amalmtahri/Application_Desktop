module MutuelleCenta {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires java.sql;
	requires javafx.base;
	requires java.desktop;
	
	opens com.mutuelle.application to javafx.graphics, javafx.fxml;
	
	opens com.mutuelle.application.views to javafx.graphics, javafx.fxml;
	exports com.mutuelle.application.views;
	exports com.mutuelle.application.models;
}
