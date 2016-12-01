package group2.grade15.njuse.presentation.searchui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by George on 2016/11/30.
 */
public class CustomerSearchHotelController {

    @FXML
    private VBox searchItemBox;

    @FXML
    protected void search() {
        showSearchResult();
    }

    private void showSearchResult() {
        try {
            FXMLLoader searItemLoader=new FXMLLoader(new URL("file:client/src/main/java/group2/grade15/njuse/presentation/searchui/SearchItem.fxml"));
            searchItemBox.getChildren().clear();
            Node singleItem=searItemLoader.load();
            Node singleItem2=searItemLoader.load();
//            for(int i=0;i<20;++i) {
            searchItemBox.getChildren().addAll(singleItem, singleItem2);
//            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
