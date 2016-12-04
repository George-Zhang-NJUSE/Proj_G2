package group2.grade15.njuse.presentation.searchui;

import group2.grade15.njuse.presentation.mycontrol.CustomeButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by George on 2016/11/30.
 */
public class CustomerSearchHotelController implements Initializable{

    private Pane parentPane;

    @FXML
    private VBox searchItemBox;

    @FXML
    private Label searchLabel;

    @FXML
    private ChoiceBox provinceChoiceBox, cityChoiceBox, districtChoiceBox, cbdChoiceBox, roomTypeChoiceBox,
            priceRangeChoiceBox, freeRoomChoiceBox, minStarChoiceBox, scoreRangeChoiceBox;

    @FXML
    private TextField hotelNameField;


    @FXML
    protected void search() {
        showSearchResult();
    }


    private void showSearchResult() {
        try {
            searchItemBox.getChildren().clear();
            ArrayList<Node> ItemList = new ArrayList<>();

            // TODO: 2016/12/2 需要更改为正确的逻辑
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //加载按钮变化样式
        CustomeButton.implButton(searchLabel,"file:client/src/main/res/search/search");
    }
}
