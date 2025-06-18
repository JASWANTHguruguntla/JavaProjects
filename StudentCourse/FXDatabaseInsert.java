import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class FXDatabaseInsert extends Application {

    @Override
    public void start(Stage stage) {
        // UI elements
        TextField idField = new TextField();
        idField.setPromptText("Enter ID");

        TextField nameField = new TextField();
        nameField.setPromptText("Enter Name");

        TextField emailField = new TextField();
        emailField.setPromptText("Enter Email");

        Button submitButton = new Button("Submit");
        Button fetchButton = new Button("Fetch"); // Fetch button

        Label statusLabel = new Label();
        TextArea dataArea = new TextArea(); // To show fetched data
        dataArea.setEditable(false);

        // Submit button logic
        submitButton.setOnAction(e -> {
            int id;
            try {
                id = Integer.parseInt(idField.getText());
            } catch (NumberFormatException ex) {
                statusLabel.setText("ID must be a number.");
                return;
            }

            String name = nameField.getText();
            String email = emailField.getText();

            if (name.isEmpty() || email.isEmpty()) {
                statusLabel.setText("Name and Email cannot be empty.");
                return;
            }

            try {
                Class.forName("com.mysql.cj.jdbc.Driver"); // Load driver
                Connection con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/stdinf", "root", "root");

                PreparedStatement pst = con.prepareStatement(
                        "INSERT INTO students VALUES (?, ?, ?)");
                pst.setInt(1, id);
                pst.setString(2, name);
                pst.setString(3, email);

                int result = pst.executeUpdate();
                if (result > 0) {
                    statusLabel.setText("Data inserted successfully!");
                    idField.clear();
                    nameField.clear();
                    emailField.clear();
                } else {
                    statusLabel.setText("Failed to insert data.");
                }

                pst.close();
                con.close();

            } catch (Exception ex) {
                ex.printStackTrace();
                statusLabel.setText("Error: " + ex.getMessage());
            }
        });

        // Fetch button logic
        fetchButton.setOnAction(e -> {
            dataArea.clear(); // Clear previous data

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/stdinf", "root", "root");

                PreparedStatement pst = con.prepareStatement("SELECT * FROM students");
                ResultSet rs = pst.executeQuery();

                StringBuilder sb = new StringBuilder();
                while (rs.next()) {
                    int id = rs.getInt("student_id");
                    String name = rs.getString("name");
                    String email = rs.getString("email");
                    sb.append("ID: ").append(id)
                      .append(", Name: ").append(name)
                      .append(", Email: ").append(email)
                      .append("\n");
                }

                dataArea.setText(sb.toString());

                rs.close();
                pst.close();
                con.close();
            } catch (Exception ex) {
                ex.printStackTrace();
                statusLabel.setText("Error fetching data: " + ex.getMessage());
            }
        });

        VBox layout = new VBox(10, idField, nameField, emailField, submitButton, fetchButton, statusLabel, dataArea);
        layout.setStyle("-fx-padding: 10;");
        Scene scene = new Scene(layout, 400, 400);
        stage.setScene(scene);
        stage.setTitle("Insert & Fetch from MySQL (JavaFX)");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
