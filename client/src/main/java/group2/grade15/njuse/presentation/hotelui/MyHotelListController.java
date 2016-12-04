package group2.grade15.njuse.presentation.hotelui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by George on 2016/12/4.
 */
public class MyHotelListController implements Initializable{

    private Pane parentPane;    //用来传递给子界面

    @FXML
    private VBox hotelItemBox;



    public void setParentPane(Pane parentPane) {
        this.parentPane = parentPane;
    }

    public void initDataAndShow() {
        // TODO: 2016/12/4 对传来的数据进行处理

        show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    private void show() {
        // TODO: 2016/12/4 需要更改为正确的逻辑
        try {
            hotelItemBox.getChildren().clear();
            ArrayList<Node> ItemList = new ArrayList<>();

            // TODO: 2016/12/2 需要更改为正确的逻辑
            for(int i=0;i<15;++i) {
                FXMLLoader searchItemLoader=new FXMLLoader(new URL("file:client/src/main/java/group2/grade15/njuse/presentation/hotelui/HotelItem.fxml"));
                Node singleItemTemplate=searchItemLoader.load();
                HotelItemController hotelItemController = searchItemLoader.getController();
                hotelItemController.setParentPane(parentPane);
                ItemList.add(singleItemTemplate);
            }

            hotelItemBox.getChildren().addAll(ItemList);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
