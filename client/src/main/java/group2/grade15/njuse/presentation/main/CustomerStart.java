package group2.grade15.njuse.presentation.main;

import group2.grade15.njuse.presentation.loginui.CustomerLoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by George on 2016/11/24.
 */
public class CustomerStart extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            FXMLLoader loader = new FXMLLoader(new URL("file:client/src/main/java/group2/grade15/njuse/presentation/loginui/CustomerLogin.fxml"));

            primaryStage.setTitle("酒店预订系统——客户端");
            primaryStage.setScene(new Scene(loader.load()));
            primaryStage.sizeToScene();
            primaryStage.setResizable(false);

            CustomerLoginController loginController = loader.getController();
            loginController.setStage(primaryStage);

            primaryStage.show();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
