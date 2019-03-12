package prev_assigns.hw2.p2_JavaFX;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * JavaFX application that prints "Hello, World!" in the center of the stage in demonstration that the JavaFX
 * development environment is set up.
 *
 * @author Ky Kartchner
 */
public class HelloWorldMain extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Horizontal panel properties
        HBox panel = new HBox();
        panel.setAlignment(Pos.CENTER);
        panel.setMinWidth(400);
        panel.setMinHeight(200);
        panel.setStyle("-fx-background-color: #00FF00;");

        // Create text font:
        Font font = Font.font("Algerian", FontWeight.BOLD, 18);

        // Create hello text:
        Text helloTxt = new Text("Hello,");
        helloTxt.setFont(font);

        // Create world Text:
        Text worldTxt = new Text(" World!");
        worldTxt.setFont(font);
        worldTxt.setFill(Color.BLUE);

        // Add hello text and world text to panel:
        panel.getChildren().addAll(helloTxt, worldTxt);

        // Create a scene and place it in the stage:
        Scene scene = new Scene(panel);
        primaryStage.setTitle("HelloWorld"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage

    }
}
