package group2.grade15.njuse.presentation.customerui;

import group2.grade15.njuse.presentation.loginui.CustomerLoginController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by George on 2016/11/25.
 */
public class CustomerMainController {

    private Stage currentStage;

    public CustomerMainController(Stage priStage) {
        currentStage = priStage;
        try {
            FXMLLoader loader = new FXMLLoader(new URL("file:client/src/main/java/group2/grade15/njuse/presentation/loginui/CustomerLogin.fxml"));

            currentStage.setTitle("酒店预订系统——客户端");
            currentStage.setScene(new Scene(loader.load()));
            currentStage.sizeToScene();
            currentStage.setResizable(false);

            CustomerLoginController loginController = loader.getController();
            loginController.setStage(currentStage);

            currentStage.show();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
