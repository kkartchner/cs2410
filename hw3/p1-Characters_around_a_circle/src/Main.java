import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Displays a GUI that has the phrase "WELCOME TO JAVA " wrapped in a circular formation.
 *
 * @author Ky Kartchner
 */
public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){
        Pane pane = new Pane();

        String message = "WELCOME TO JAVA ";
        double radius = 100.0; // Radius of the circular arrangement
        for (int i = 0; i < message.length(); ++i){
            Text text = new Text(message.substring(i, i+1)); // Text object with letter at i index of message.
            text.setFont(Font.font("Times New Roman", FontWeight.BOLD, 40)); // Set text font

            double theta = 2 * i * Math.PI / message.length();  /* Divide 2pi by message length to get angle increment
            amount in radians. Multiply by i to get theta of current piece */

            // Bind x and y so that the arrangement of letters is in a circular arrangement centered in the pane:
            text.xProperty().bind(pane.widthProperty().divide(2) // Bind x to cartesian coord. of the polar coord.
                    .add(radius * Math.cos(theta)));                //   (radius, theta) plus half of the pane width
            text.yProperty().bind(pane.heightProperty().divide(2)// Bind y to cartesian coord. of the polar coord.
                    .add(radius * Math.sin(theta)));                //   (radius, theta) plus half of the pane height

            text.setRotate(theta * (180 / Math.PI) + 90); // Set rotation to theta in degrees plus 90 degrees
            pane.getChildren().add(text); // Add text to pane
        }

        Scene scene = new Scene(pane, 400, 400);

        primaryStage.setTitle("Exercise14_05 - Ky Kartchner");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
