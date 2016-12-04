package group2.grade15.njuse.presentation.hotelui;

import group2.grade15.njuse.presentation.mycontrol.CustomeButton;
import group2.grade15.njuse.presentation.orderui.MakeOrderController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by George on 2016/12/1.
 */
public class HotelItemController implements Initializable{

    private String hotelName;

    private Pane parentPane;  //用来传递给子界面

    @FXML
    private Label showDetailLabel, makeOrderLabel;

    @FXML
    protected void showHotelDetail() {
        try {
            FXMLLoader hotelDetailLoader=new FXMLLoader(new URL("file:client/src/main/java/group2/grade15/njuse/presentation/hotelui/HotelDetail.fxml"));
            parentPane.getChildren().add(hotelDetailLoader.load());
            HotelDetailController detailController = hotelDetailLoader.getController();
            detailController.setParentPane(parentPane);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void showMakeOrderPane() {
        try {
            FXMLLoader makeOrderLoader=new FXMLLoader(new URL("file:client/src/main/java/group2/grade15/njuse/presentation/orderui/MakeOrder.fxml"));
            parentPane.getChildren().add(makeOrderLoader.load());
            MakeOrderController makeOrderController = makeOrderLoader.getController();

            makeOrderController.setParentPane(parentPane);
            makeOrderController.initDataAndShow();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setParentPane(Pane parentPane) {
        this.parentPane = parentPane;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //加载按钮变化样式
        CustomeButton.implButton(showDetailLabel,"file:client/src/main/res/customer/more");
        CustomeButton.implButton(makeOrderLabel,"file:client/src/main/res/search/makeorder");
    }
}
