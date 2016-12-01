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
    protected void mouseClickPersonalInfo() {
        showPersonalInfoPane();
    }

    @FXML
    protected void mouseClickBookHotel() {
        showSearchHotelPane();
    }

    private void showSearchHotelPane() {
        try {
            FXMLLoader SearchHotelLoader=new FXMLLoader(new URL("file:client/src/main/java/group2/grade15/njuse/presentation/searchui/CustomerSearchHotel.fxml"));
            functionPane.getChildren().clear();
            functionPane.getChildren().add(SearchHotelLoader.load());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showPersonalInfoPane() {
        try {
            FXMLLoader personalInfoLoader=new FXMLLoader(new URL("file:client/src/main/java/group2/grade15/njuse/presentation/customerui/CustomerInfo.fxml"));
            functionPane.getChildren().clear();
            functionPane.getChildren().add(personalInfoLoader.load());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
