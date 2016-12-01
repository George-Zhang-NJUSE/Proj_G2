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

    private void showChildPane(String url) {
        try {
            FXMLLoader loader=new FXMLLoader(new URL(url));
            functionPane.getChildren().clear();
            functionPane.getChildren().add(loader.load());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showSearchHotelPane() {
        String searchPaneUrl="file:client/src/main/java/group2/grade15/njuse/presentation/searchui/CustomerSearchHotel.fxml";
        showChildPane(searchPaneUrl);
    }

    private void showMyOrderPane() {
        // TODO: 2016/12/1
//        String myOrderPaneUrl=;
//        showChildPane(myOrderPaneUrl);
    }

    private void showPersonalInfoPane() {
        String personalPaneUrl="file:client/src/main/java/group2/grade15/njuse/presentation/customerui/CustomerInfo.fxml";
        showChildPane(personalPaneUrl);
    }


}
