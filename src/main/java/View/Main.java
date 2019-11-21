package View;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import org.apache.commons.lang3.SystemUtils;

import java.io.File;
import java.io.InputStream;
import java.net.URL;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        try {
            if (SystemUtils.IS_OS_MAC){
                System.out.println("Please Change to Windows, Mac Sucks");
                return;
            }
            else if (SystemUtils.IS_OS_WINDOWS){
                System.out.println("Good Job, Windows is good");
            }
            primaryStage.setTitle("LecSer");
            FXMLLoader fxmlLoader = new FXMLLoader();
            Class clas = getClass();
            URL rsrc = ((Class) clas).getResource("View.fxml");
            InputStream ost = ((URL) rsrc).openStream();
            Parent o1 = fxmlLoader.load(ost);
            Parent root = fxmlLoader.load(getClass().getResource("View.fxml").openStream());
            Scene scene = new Scene(root, 600 * 1.5, 400 * 1.5);

            scene.getStylesheets().clear();
            scene.getStylesheets().add(getClass().getResource("View.css").toExternalForm());
            primaryStage.setScene(scene);
            //--------------

            //--------------
            primaryStage.show();
            View v = fxmlLoader.getController();
            v.getConfig();
            v.setFocus();
        }
        catch (Exception e){
            System.out.println("Bye");
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
