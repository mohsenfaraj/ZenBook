package zenphone;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author gmohs
 */
public class ZenPhone extends Application {

    MainController Controller;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Main.fxml"));
        Parent root = (Parent) loader.load();
        Controller = (MainController) loader.getController();
        Controller.mc = Controller ;
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Zenix phone book");
        stage.show();

    }

    @Override
    public void stop() {
        Controller.Quit();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
