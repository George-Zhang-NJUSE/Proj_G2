package group2.grade15.njuse.presentation.main;

import group2.grade15.njuse.presentation.loginui.WebAdminLoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class WebAdminStart extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        try {
            FXMLLoader loader = new FXMLLoader(new URL("file:client/src/main/java/group2/grade15/njuse/presentation/loginui/webAdminLogin.fxml"));

            primaryStage.setTitle("酒店预订系统——网站管理人员端");
            primaryStage.setScene(new Scene((Parent) loader.load()));
            primaryStage.sizeToScene();
            primaryStage.setResizable(false);

            WebAdminLoginController loginController = loader.getController();
            loginController.setStage(primaryStage);

            primaryStage.show();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static void main(String[] args) {
        launch(args);
    }
}
