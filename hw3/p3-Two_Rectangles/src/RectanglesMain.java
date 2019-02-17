import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * @author Ky Kartchner
 */
public class RectanglesMain extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Pane mainPane = new Pane();
        mainPane.setPrefSize(500, 500);

        double width1 = 100;
        double height1 = 200;
        double x1 = 35;
        double y1 = 35;

        double width2 = 100;
        double height2 = 100;
        double x2 = 35;
        double y2 = 35;

        var rect1 = new MyRectangle(width1, height1, x1, y1, Color.BLUE); // (width, height, x, y)
        var rect2 = new MyRectangle(width2, height2, x1, y1, Color.RED); // (width, height, x, y)

        mainPane.getChildren().add(rect1);
        mainPane.getChildren().add(rect2);

        primaryStage.setTitle("Exercise 14_23 - Ky Kartchner");
        primaryStage.setScene(new Scene(mainPane));
        primaryStage.show();

    }
}
