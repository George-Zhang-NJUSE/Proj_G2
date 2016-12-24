package group2.grade15.njuse.presentation.orderui;

import group2.grade15.njuse.bl.commentbl.CommentController;
import group2.grade15.njuse.bl.orderbl.OrderController;
import group2.grade15.njuse.blservice.CommentServ;
import group2.grade15.njuse.blservice.OrderServ;
import group2.grade15.njuse.presentation.customerglobal.CommonData;
import group2.grade15.njuse.presentation.customerglobal.LiteralList;
import group2.grade15.njuse.presentation.myanimation.Fade;
import group2.grade15.njuse.presentation.myanimation.Pop;
import group2.grade15.njuse.presentation.mycontrol.CustomeButton;
import group2.grade15.njuse.utility.OrderState;
import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.vo.CommentVO;
import group2.grade15.njuse.vo.OrderVO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Created by George on 2016/12/2.
 */
public class MyOrderDetailController implements Initializable {

    private OrderVO orderVO;
    private CommentVO commentVO;
    private int hotelID;

    private Pane parentPane;

    @FXML
    private Node rootNode;

    @FXML
    private Label cancelLabel, commentLabel, revokeOrderLabel;

    @FXML
    private CheckBox hasChildCheckBox;

    @FXML
    private Label orderIDLabel, hotelNameLabel, addressLabel, checkInTimeLabel, checkOutTimeLabel, finalExecuteTimeLabel,
                roomTypeLabel, roomNumLabel, customerNumLabel, orderStateLabel, orderPriceLabel, createTimeLabel;

    @FXML
    private void close() {
        //退出动画
        Fade fadeOut = new Fade(rootNode, 200, false);
        Pop popOut = new Pop(rootNode, 200, false);
        fadeOut.setOnFinished((ActionEvent e) -> parentPane.getChildren().remove(rootNode));
        popOut.play();
        fadeOut.play();
    }

    @FXML
    private void showCommentPane() {
        try {
            FXMLLoader commentLoader = new FXMLLoader(new URL("file:client/src/main/res/fxml/customer/MyComment.fxml"));
            commentLoader.load();
            MyCommentController myCommentController = commentLoader.getController();
            myCommentController.initData(commentVO,orderIDLabel.getText(), createTimeLabel.getText(), hotelNameLabel.getText(), hotelID);
            myCommentController.show();
            close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void requestRevokeOrder() {
        String warningMessage="确定要撤销该订单吗？";
        if(orderVO.getFinalExecuteTime().getTime()-System.currentTimeMillis()<6*60*60*1000){
            warningMessage = "现在距离最晚订单执行时间不足6小时，如果撤销订单，您的信用值将会下降，" + warningMessage;
        }
        Alert confirmRevokeAlert = new Alert(Alert.AlertType.CONFIRMATION, warningMessage, ButtonType.NO, ButtonType.YES);
        Optional<ButtonType> confirm = confirmRevokeAlert.showAndWait();
        if (confirm.isPresent() && confirm.get() == ButtonType.YES) {
            revokeOrder();
        }
    }

    private void revokeOrder() {
        OrderServ orderServ = new OrderController();
        ResultMessage result=orderServ.modifyState(orderVO.getOrderID(), OrderState.revoked);
        switch (result) {
            case SUCCESS:
                Alert successInfo=new Alert(Alert.AlertType.INFORMATION, "撤销成功！");
                successInfo.showAndWait();
                successInfo.setOnCloseRequest((DialogEvent e)->close());
                break;
            case FAILED:
                Alert failInfo=new Alert(Alert.AlertType.ERROR, "撤销失败！");
                failInfo.showAndWait();
                break;
            case CONNECTION_EXCEPTION:
                Alert netError = new Alert(Alert.AlertType.ERROR, "撤销失败，网络连接出现错误。");
                netError.showAndWait();
                break;
        }
    }

    private void adaptCommentLabel() {
        //只有已执行的订单可以评价
        if(orderVO.getState()!=OrderState.executed){
            commentLabel.setDisable(true);
        }

        //根据客户有没有评价订单来应用不同外观
        CommentServ commentServ = new CommentController();
        commentVO=commentServ.getComment(orderVO.getOrderID());

        if(commentVO!=null){ //已经评价过
            CustomeButton.implButton(commentLabel, "file:client/src/main/res/order/mycomment");
        }else{
            CustomeButton.implButton(commentLabel, "file:client/src/main/res/order/comment");
        }
    }

    private void adaptRevokeOrderLabel() {
        //根据订单状态决定是否显示撤销订单按钮
        if (orderVO.getState() != OrderState.unexecuted) {
            revokeOrderLabel.setDisable(true);
        }
    }

    public void initData(OrderVO vo, String hotelName, String address, int hotelId) {
        hotelID = hotelId;
        orderVO = vo;
        orderIDLabel.setText(Integer.toString(orderVO.getOrderID()));
        hotelNameLabel.setText(hotelName);
        addressLabel.setText(address);

        //时间精确到秒
        createTimeLabel.setText(orderVO.getCreateTime().toString().split("\\.")[0]);
        checkInTimeLabel.setText(orderVO.getCheckInTime().toString().split("\\.")[0]);
        checkOutTimeLabel.setText(orderVO.getCheckOutTime().toString().split("\\.")[0]);
        finalExecuteTimeLabel.setText(orderVO.getFinalExecuteTime().toString().split("\\.")[0]);

        roomTypeLabel.setText(LiteralList.roomTypeList[orderVO.getType().ordinal()]);
        roomNumLabel.setText(Integer.toString(orderVO.getRoomSum()));
        customerNumLabel.setText(Integer.toString(orderVO.getNumOfCustomer()));
        
        int orderStateEnumOrdinal = orderVO.getState().ordinal();
        int orderStateLiteralIndex = orderStateEnumOrdinal == 4 ? 1 : orderStateEnumOrdinal;

        orderStateLabel.setText(LiteralList.orderStateList[orderStateLiteralIndex]);
        orderPriceLabel.setText(Double.toString(orderVO.getAmount()));
        hasChildCheckBox.setSelected(orderVO.isHaveChild());

        adaptCommentLabel();
        adaptRevokeOrderLabel();
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
        rootNode.setLayoutX(300);
        rootNode.setLayoutY(50);

        //加载按钮变化样式
        CustomeButton.implButton(cancelLabel, "file:client/src/main/res/customer/cancel");
        CustomeButton.implButton(revokeOrderLabel, "file:client/src/main/res/order/revokeorder");

        //设置父界面
        parentPane = CommonData.getInstance().getFunctionAreaPane();
        parentPane.getChildren().add(rootNode);

    }
}
