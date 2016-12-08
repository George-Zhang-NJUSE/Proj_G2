package group2.grade15.njuse.presentation.hotelui;

import group2.grade15.njuse.presentation.myanimation.Fade;
import group2.grade15.njuse.presentation.myanimation.Pop;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by George on 2016/12/4.
 */
public class MyHotelListController implements Initializable {

    private Pane parentPane;    //用来传递给子界面

    @FXML
    private Node rootNode;

    @FXML
    private VBox hotelItemBox;


    public void setParentPane(Pane parentPane) {
        this.parentPane = parentPane;
    }

    public void initDataAndShow() {
        // TODO: 2016/12/4 对传来的数据进行处理

        showContainer();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //为渐入扩大动画做准备
        rootNode.setOpacity(0);
        rootNode.setScaleX(0.9);
        rootNode.setScaleY(0.9);

    }

    private void showContents() {
        // TODO: 2016/12/4 需要更改为正确的逻辑
        try {
            hotelItemBox.getChildren().clear();


            // TODO: 2016/12/2 需要更改为正确的逻辑
            for (int i = 0; i < 15; ++i) {
                FXMLLoader searchItemLoader = new FXMLLoader(new URL("file:client/src/main/java/group2/grade15/njuse/presentation/hotelui/HotelItem.fxml"));
                Node singleItemTemplate = searchItemLoader.load();
                HotelItemController hotelItemController = searchItemLoader.getController();


                hotelItemBox.getChildren().add(singleItemTemplate);
                hotelItemController.initData(null);
            }




        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showContainer() {
        //渐入扩大动画
        Fade fadeIn = new Fade(rootNode, 300, true);
        Pop popIn = new Pop(rootNode, 300, true);
        popIn.setOnFinished((ActionEvent e)->showContents());
        fadeIn.play();
        popIn.play();

    }
}
