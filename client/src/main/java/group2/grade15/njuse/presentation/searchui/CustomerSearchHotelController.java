package group2.grade15.njuse.presentation.searchui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by George on 2016/11/30.
 */
public class CustomerSearchHotelController {

    private Pane parentPane;

    @FXML
    private VBox searchItemBox;

    @FXML
    protected void search() {
        showSearchResult();
    }


    private void showSearchResult() {
        try {
            searchItemBox.getChildren().clear();
            ArrayList<Node> ItemList = new ArrayList<>();

            for(int i=0;i<15;++i) {
                FXMLLoader searchItemLoader=new FXMLLoader(new URL("file:client/src/main/java/group2/grade15/njuse/presentation/searchui/SearchItem.fxml"));
                Node singleItemTemplate=searchItemLoader.load();
                SearchItemController searchItemController = searchItemLoader.getController();
                searchItemController.setParentPane(parentPane);
                ItemList.add(singleItemTemplate);
            }

            searchItemBox.getChildren().addAll(ItemList);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setParentPane(Pane parentPane) {
        this.parentPane=parentPane;
    }
}
