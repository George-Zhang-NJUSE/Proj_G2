package group2.grade15.njuse.presentation.main;


import group2.grade15.njuse.presentation.loginui.HotelManageLoginController;
import group2.grade15.njuse.runner.ClientRunner;
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
        FXMLLoader loader=new FXMLLoader(new URL("file:client/src/main/res/hotelmanager/HotelManageLogin.fxml"));
        primaryStage.setScene(new Scene(loader.load()));
        primaryStage.sizeToScene();
        primaryStage.setResizable(false);
        primaryStage.setTitle("酒店预订系统——酒店管理端");
        HotelManageLoginController controller=loader.getController();
        controller.setStage(primaryStage);
        primaryStage.show();

        ClientRunner clientRunner = new ClientRunner();
    }

    public static void main(String[] args) {
        launch(args);

    }
}
