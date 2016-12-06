package group2.grade15.njuse.presentation.orderui;

import group2.grade15.njuse.presentation.myanimation.Fade;
import group2.grade15.njuse.presentation.mycontrol.CustomeButton;
import group2.grade15.njuse.vo.OrderVO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by George on 2016/12/2.
 */
public class MyOrderItemController implements Initializable{

    private Pane parentPane; //实际值为customermain中的functionPane,用于弹出新界面
    private OrderVO orderVO;

    @FXML
    private Node rootNode;

    @FXML
    private Label showDetailLabel;

    @FXML
    private HBox hotelInfoBox;


    @FXML
    protected void showMyOrderDetail() {
        try {
            FXMLLoader loader = new FXMLLoader(new URL("file:client/src/main/java/group2/grade15/njuse/presentation/orderui/MyOrderDetail.fxml"));
            Node childPane=loader.load();
            parentPane.getChildren().add(childPane);
            MyOrderDetailController orderDetailController = loader.getController();

            orderDetailController.setParentPane(parentPane);
            orderDetailController.initDataAndShow();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initDataAndShow(OrderVO vo) {
        // TODO: 2016/12/5 数据处理
        orderVO = vo;


        show();
    }

    public void setParentPane(Pane parentPane) {
        this.parentPane = parentPane;
    }

    private void adaptToActualWidth() {
        //宽度太窄时不显示部分内容
        Pane container= (Pane)rootNode.getParent();
        if (container.getWidth() < 900) {
            hotelInfoBox.setVisible(false);
            hotelInfoBox.setPrefWidth(0);
        }
    }

    private void show() {
        adaptToActualWidth();
        Fade fadeIn = new Fade(rootNode, 300, true);
        fadeIn.play();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //加载按钮变化样式
        CustomeButton.implButton(showDetailLabel, "file:client/src/main/res/customer/more");

        //为渐入动画做准备
        rootNode.setOpacity(0);



    }
}
