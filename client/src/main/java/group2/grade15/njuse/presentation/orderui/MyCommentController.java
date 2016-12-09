package group2.grade15.njuse.presentation.orderui;

import group2.grade15.njuse.presentation.customerglobal.CommonData;
import group2.grade15.njuse.presentation.myanimation.Fade;
import group2.grade15.njuse.presentation.myanimation.Pop;
import group2.grade15.njuse.presentation.mycontrol.CustomeButton;
import group2.grade15.njuse.vo.CommentVO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by George on 2016/12/4.
 */
public class MyCommentController implements Initializable {

    private Pane parentPane;
    private CommentVO commentVO;

    @FXML
    private Node rootNode;

    @FXML
    private Label cancelLabel, confirmLabel, editLabel, minusScoreLabel, addScoreLabel;

    @FXML
    private Label titleLabel, orderIDLabel, orderGenerateTimeLabel, hotelNameLabel, scoreLabel;

    @FXML
    protected void close() {
        //退出动画
        Fade fadeOut = new Fade(rootNode, 200, false);
        Pop popOut = new Pop(rootNode, 200, false);
        fadeOut.setOnFinished((ActionEvent e) -> parentPane.getChildren().remove(rootNode));
        popOut.play();
        fadeOut.play();
    }

    public void initData(CommentVO vo, String orderGenerateTime, String hotelName) {
        // TODO: 2016/12/4 初始化数据
        commentVO=vo;

        orderGenerateTimeLabel.setText(orderGenerateTime);
        hotelNameLabel.setText(hotelName);

        if (commentVO != null) {//已经评价过
            titleLabel.setText("我的评价");
            confirmLabel.setVisible(false);
            minusScoreLabel.setDisable(true);
            addScoreLabel.setDisable(true);
//            orderIDLabel.setText(commentVO.get);
        }


    }

    public void show() {
        //弹出式进入动画
        Fade fadeIn = new Fade(rootNode, 200, true);
        Pop popIn = new Pop(rootNode, 200, true);
        rootNode.setVisible(true);
        fadeIn.play();
        popIn.play();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //为弹出式动画做准备
        rootNode.setVisible(false);
        rootNode.setOpacity(0);
        rootNode.setScaleX(0.5);
        rootNode.setScaleY(0.5);
        rootNode.setLayoutX(350);
        rootNode.setLayoutY(150);

        //加载按钮变化样式
        CustomeButton.implButton(cancelLabel, "file:client/src/main/res/customer/cancel");
        CustomeButton.implButton(confirmLabel, "file:client/src/main/res/customer/confirm");
        CustomeButton.implButton(editLabel, "file:client/src/main/res/customer/edit");
        CustomeButton.implButton(addScoreLabel, "file:client/src/main/res/order/add");
        CustomeButton.implButton(minusScoreLabel, "file:client/src/main/res/order/reduce");

        //设置父界面
        parentPane = CommonData.getInstance().getFunctionAreaPane();
        parentPane.getChildren().add(rootNode);
    }
}
