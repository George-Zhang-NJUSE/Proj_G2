package group2.grade15.njuse.presentation.hotelui;

import group2.grade15.njuse.presentation.customerglobal.CommonData;
import group2.grade15.njuse.presentation.myanimation.Fade;
import group2.grade15.njuse.presentation.myanimation.Pop;
import group2.grade15.njuse.presentation.mycontrol.CustomeButton;
import group2.grade15.njuse.presentation.orderui.MakeOrderController;
import group2.grade15.njuse.presentation.orderui.MyOrderItemController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by George on 2016/12/1.
 */
public class HotelDetailController implements Initializable {

    private Pane parentPane;

    @FXML
    private Node rootNode;

    @FXML
    private VBox commentBox, myOrderBox;

    @FXML
    private Label returnLabel, makeOrderLabel;

    @FXML
    protected void goBack() {
        parentPane.getChildren().remove(rootNode);
    }

    @FXML
    protected void showMakeOrderPane() {
        try {
            FXMLLoader makeOrderLoader = new FXMLLoader(new URL("file:client/src/main/java/group2/grade15/njuse/presentation/orderui/MakeOrder.fxml"));
            makeOrderLoader.load();
            MakeOrderController makeOrderController = makeOrderLoader.getController();


            makeOrderController.initDataAndShow();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initDataAndShow() {
        loadComments();
        loadMyOrders();
        show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //加载按钮变化样式
        CustomeButton.implButton(returnLabel, "file:client/src/main/res/customer/back");
        CustomeButton.implButton(makeOrderLabel, "file:client/src/main/res/hoteldetail/makeorder");

        //为渐入扩大动画做准备
        rootNode.setOpacity(0);
        rootNode.setScaleX(0.9);
        rootNode.setScaleY(0.9);

        //设置父界面
        parentPane = CommonData.getInstance().getFunctionAreaPane();
        parentPane.getChildren().add(rootNode);

    }

    private void show() {
        //渐入扩大动画
        Fade fadeIn = new Fade(rootNode, 300, true);
        Pop popIn = new Pop(rootNode, 300, true);
        fadeIn.play();
        popIn.play();

    }

    private void loadComments() {
        try {
            commentBox.getChildren().clear();
            ArrayList<Node> ItemList = new ArrayList<>();

            // TODO: 2016/12/2 需要更改为正确的逻辑
            for (int i = 0; i < 15; ++i) {
                FXMLLoader loader = new FXMLLoader(new URL("file:client/src/main/java/group2/grade15/njuse/presentation/hotelui/CommentItem.fxml"));
                Node singleItemTemplate = loader.load();
                CommentItemController commentItemController = loader.getController();

                ItemList.add(singleItemTemplate);
            }

            commentBox.getChildren().addAll(ItemList);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadMyOrders() {
        try {
            myOrderBox.getChildren().clear();


            // TODO: 2016/12/2 需要更改为正确的逻辑
            for (int i = 0; i < 15; ++i) {
                FXMLLoader loader = new FXMLLoader(new URL("file:client/src/main/java/group2/grade15/njuse/presentation/orderui/MyOrderItem.fxml"));
                Node singleItemTemplate = loader.load();
                MyOrderItemController orderItemController = loader.getController();
                orderItemController.setParentPane(parentPane);

                myOrderBox.getChildren().add(singleItemTemplate);
                orderItemController.initDataAndShow(null);
            }



        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
