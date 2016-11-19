package presentation.loginui;
/**
 * Created by George on 2016/11/16.
 */

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        try {
            Parent root= FXMLLoader.load(getClass().getResource("login.fxml"));

            primaryStage.setTitle("酒店预订系统——管理员端");
            primaryStage.setScene(new Scene(root,900,600));
            primaryStage.sizeToScene();
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    protected void login() {
        // TODO: 2016/11/18
    }
}
