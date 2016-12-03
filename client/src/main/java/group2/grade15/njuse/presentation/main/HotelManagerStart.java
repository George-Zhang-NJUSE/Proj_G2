package group2.grade15.njuse.presentation.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

/**
 * Created by ALIENWARE-PC on 2016/12/3.
 */
public class HotelManagerStart extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader mainLoader = new FXMLLoader(new URL("file:client/src/main/java/group2/grade15/njuse/presentation/hotelmanageui/HotelManageMain.fxml"));
        primaryStage.setScene(new Scene(mainLoader.load()));
        primaryStage.sizeToScene();
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);

    }
}
