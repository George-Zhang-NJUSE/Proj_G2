package group2.grade15.njuse.presentation.customerui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by George on 2016/11/30.
 */
public class CustomerInfoController implements Initializable{

    @FXML
    private VBox creditRecordBox;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showCreditHistory();
    }

    private void showCreditHistory() {
        try {
            creditRecordBox.getChildren().clear();
            ArrayList<Node> ItemList = new ArrayList<>();

            // TODO: 2016/12/2 需要更改为正确的逻辑
            for (int i = 0; i < 15; ++i) {
                FXMLLoader loader = new FXMLLoader(new URL("file:client/src/main/java/group2/grade15/njuse/presentation/customerui/CreditRecordItem.fxml"));
                Node singleItemTemplate = loader.load();
                CreditRecordItemController creditRecordItemController = loader.getController();

                ItemList.add(singleItemTemplate);
            }

            creditRecordBox.getChildren().addAll(ItemList);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
