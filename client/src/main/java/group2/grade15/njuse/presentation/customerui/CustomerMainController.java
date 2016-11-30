package group2.grade15.njuse.presentation.customerui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by George on 2016/11/25.
 */
public class CustomerMainController {

    @FXML
    private Pane functionPane;

    @FXML
    protected void mouseReleasePersonalInfo() {
        showPersonalInfoPane();
    }

    private void showPersonalInfoPane() {
        try {
            FXMLLoader personalInfoLoader=new FXMLLoader(new URL("file:client/src/main/java/group2/grade15/njuse/presentation/customerui/CustomerInfo.fxml"));
            functionPane.getChildren().removeAll();
            functionPane.getChildren().add(personalInfoLoader.load());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
