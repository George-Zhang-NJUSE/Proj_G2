package group2.grade15.njuse.presentation.orderui;

import group2.grade15.njuse.presentation.customerglobal.CommonData;
import group2.grade15.njuse.presentation.myanimation.Fade;
import group2.grade15.njuse.presentation.myanimation.Pop;
import group2.grade15.njuse.presentation.mycontrol.CustomeButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by George on 2016/12/1.
 */
public class MakeOrderController implements Initializable {

    private Pane parentPane;

    @FXML
    private Node rootNode;


    @FXML
    private ChoiceBox roomTypeChoiceBox;

    @FXML
    private Label roomNumLabel, cancelLabel, confirmLabel;


    @FXML
    protected void close() {
        //退出动画
        Fade fadeOut = new Fade(rootNode, 200, false);
        Pop popOut = new Pop(rootNode, 200, false);
        fadeOut.setOnFinished((ActionEvent e) -> parentPane.getChildren().remove(rootNode));
        popOut.play();
        fadeOut.play();
    }

    public void initDataAndShow() {
        // TODO: 2016/12/4 初始化数据


        show();
    }

    private void show() {
        //弹出式进入动画
        Fade fadeIn = new Fade(rootNode, 200, true);
        Pop popIn = new Pop(rootNode, 200, true);

        rootNode.setVisible(true);
        fadeIn.play();
        popIn.play();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //加载按钮变化样式
        CustomeButton.implButton(cancelLabel, "file:client/src/main/res/customer/cancel");
        CustomeButton.implButton(confirmLabel, "file:client/src/main/res/customer/confirm");

        //为弹出式动画做准备
        rootNode.setVisible(false);
        rootNode.setOpacity(0);
        rootNode.setScaleX(0.5);
        rootNode.setScaleY(0.5);
        rootNode.setLayoutX(300);
        rootNode.setLayoutY(50);

        //设置父界面
        parentPane = CommonData.getInstance().getFunctionAreaPane();
        parentPane.getChildren().add(rootNode);
    }
}
