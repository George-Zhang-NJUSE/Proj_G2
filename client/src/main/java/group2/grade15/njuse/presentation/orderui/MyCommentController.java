package group2.grade15.njuse.presentation.orderui;

import group2.grade15.njuse.bl.commentbl.CommentController;
import group2.grade15.njuse.blservice.CommentServ;
import group2.grade15.njuse.presentation.customerglobal.CommonData;
import group2.grade15.njuse.presentation.myanimation.Fade;
import group2.grade15.njuse.presentation.myanimation.Pop;
import group2.grade15.njuse.presentation.mycontrol.CustomeButton;
import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.vo.CommentVO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.sql.Date;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Created by George on 2016/12/4.
 */
public class MyCommentController implements Initializable {

    private Pane parentPane;
    private CommentVO commentVO;
    private boolean isModifying;
    private CommentServ commentServ;
    private int hotelID;
    private double score;

    @FXML
    private Node rootNode;

    @FXML
    private Label cancelLabel, confirmLabel, modifyLabel, minusScoreLabel, addScoreLabel;

    @FXML
    private Label titleLabel, orderIDLabel, orderGenerateTimeLabel, hotelNameLabel, scoreLabel;

    @FXML
    private TextArea commentArea;

    @FXML
    private void minusScore() {
        scoreLabel.setText(Double.toString(score == 0 ? 0 : --score));
    }

    @FXML
    private void addScore() {
        scoreLabel.setText(Double.toString(score == 10 ? 10 : ++score));
    }

    @FXML
    private void close() {
        if (isModifying) {
            Alert giveUpModify = new Alert(Alert.AlertType.CONFIRMATION, "要放弃修改吗？", ButtonType.NO, ButtonType.YES);
            Optional<ButtonType> result=giveUpModify.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.YES) {
                exitModify();
            }

        }else{
            //退出动画
            Fade fadeOut = new Fade(rootNode, 200, false);
            Pop popOut = new Pop(rootNode, 200, false);
            fadeOut.setOnFinished((ActionEvent e) -> parentPane.getChildren().remove(rootNode));
            popOut.play();
            fadeOut.play();
        }
    }

    @FXML
    private void modifyComment() {
        confirmLabel.setVisible(true);
        commentArea.setEditable(true);
        isModifying = true;
    }

    private void exitModify() {
        confirmLabel.setVisible(false);
        commentArea.setEditable(false);
        isModifying = false;
    }

    @FXML
    private void saveEdition() {
        if (isModifying) {
            //修改已有的评价
            ResultMessage result= commentServ.modifyComment(new CommentVO(commentVO.getHotelID(),commentVO.getUserID(),commentArea.getText(),commentVO.getCommentID(),
                                new Date(System.currentTimeMillis()), commentVO.getScore(), commentVO.getOrderID()));


            if (result == ResultMessage.SUCCESS) {
                Alert successInfo=new Alert(Alert.AlertType.INFORMATION, "修改成功！");
                successInfo.showAndWait();
                exitModify();
            } else if (result == ResultMessage.FAILED) {
                Alert failInfo=new Alert(Alert.AlertType.ERROR, "修改失败！");
                failInfo.showAndWait();
            } else if (result == ResultMessage.CONNECTION_EXCEPTION) {
                Alert netError = new Alert(Alert.AlertType.ERROR, "由于网络连接出现错误，修改失败！");
                netError.showAndWait();
            }

        } else {
            //新建评价
            int userID = CommonData.getInstance().getCustomerVO().getId();
            Alert confirmScore=new Alert(Alert.AlertType.CONFIRMATION,"评分一旦确定，便不可修改，请问这是您确认后的评分吗？",ButtonType.NO, ButtonType.YES);
            Optional<ButtonType> confirm=confirmScore.showAndWait();

            if (confirm.isPresent() && confirm.get() == ButtonType.YES) {

                ResultMessage result = commentServ.createComment(new CommentVO(hotelID, userID, commentArea.getText(), 0,
                        new Date(System.currentTimeMillis()), Double.parseDouble(scoreLabel.getText()), Integer.parseInt(orderIDLabel.getText())));

                switch (result) {
                    case SUCCESS:
                        Alert successInfo=new Alert(Alert.AlertType.INFORMATION, "评价成功！");
                        successInfo.showAndWait();
                        close();
                        break;
                    case FAILED:
                        Alert failInfo=new Alert(Alert.AlertType.ERROR, "评价失败！");
                        failInfo.showAndWait();
                        break;
                    case CONNECTION_EXCEPTION:
                        Alert netError = new Alert(Alert.AlertType.ERROR, "评价失败，网络连接出现错误。");
                        netError.showAndWait();
                        break;
                }

            }

        }

    }

    public void initData(CommentVO vo,String orderID, String orderGenerateTime, String hotelName, int hotelId) {
        hotelID = hotelId;
        commentVO=vo;

        orderIDLabel.setText(orderID);
        orderGenerateTimeLabel.setText(orderGenerateTime);
        hotelNameLabel.setText(hotelName);
        isModifying=false;

        if (commentVO != null) {//已经评价过
            titleLabel.setText("我的评价");

            confirmLabel.setVisible(false);
            minusScoreLabel.setDisable(true);
            addScoreLabel.setDisable(true);

            scoreLabel.setText(Double.toString(commentVO.getScore()));
            commentArea.setText(commentVO.getComment());
            commentArea.setEditable(false);

        } else {//没有评价过
            titleLabel.setText("评价订单");
            modifyLabel.setDisable(true);
            scoreLabel.setText(Double.toString(score));
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

        commentServ = new CommentController();
        score=0;

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
        CustomeButton.implButton(modifyLabel, "file:client/src/main/res/customer/edit");
        CustomeButton.implButton(addScoreLabel, "file:client/src/main/res/order/add");
        CustomeButton.implButton(minusScoreLabel, "file:client/src/main/res/order/reduce");

        //设置父界面
        parentPane = CommonData.getInstance().getFunctionAreaPane();
        parentPane.getChildren().add(rootNode);
    }
}
