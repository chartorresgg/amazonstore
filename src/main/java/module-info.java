module co.edu.poli.amazonstore {

	requires javafx.controls;
	requires javafx.fxml;
	requires transitive javafx.graphics;
	requires java.sql;
	requires javafx.base;

	opens co.edu.poli.amazonstore.view to javafx.fxml;
	opens co.edu.poli.amazonstore.controller to javafx.fxml;
	opens co.edu.poli.amazonstore.model to javafx.base;

	exports co.edu.poli.amazonstore.view;
	exports co.edu.poli.amazonstore.controller;
}
