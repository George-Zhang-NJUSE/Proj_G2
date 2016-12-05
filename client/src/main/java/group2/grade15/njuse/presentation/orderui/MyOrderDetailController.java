package group2.grade15.njuse.presentation.orderui;

import group2.grade15.njuse.presentation.myanimation.Fade;
import group2.grade15.njuse.presentation.myanimation.Pop;
import group2.grade15.njuse.presentation.mycontrol.CustomeButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by George on 2016/12/2.
 */
public class MyOrderDetailController implements Initializable {

    private Pane parentPane;

    @FXML
    private Node rootNode;

    @FXML
    private Label cancelLabel, commentLabel, revokeOrderLabel;

    @FXML
    protected void close() {
        //退出动画
        Fade fadeOut = new Fade(rootNode, 200, false);
        Pop popOut = new Pop(rootNode, 200, false);
        fadeOut.setOnFinished((ActionEvent e) -> parentPane.getChildren().remove(rootNode));
        popOut.play();
        fadeOut.play();
    }

    @FXML
    protected void showCommentPane() {
        try {
            FXMLLoader loader = new FXMLLoader(new URL("file:client/src/main/java/group2/grade15/njuse/presentation/orderui/Comment.fxml"));
            parentPane.getChildren().add(loader.load());
            CommentController commentController = loader.getController();

            commentController.setParentPane(parentPane);
            commentController.initDataAndShow();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public void setParentPane(Pane parentPane) {
        this.parentPane = parentPane;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //为弹出式动画做准备
        rootNode.setVisible(false);
        rootNode.setOpacity(0);
        rootNode.setScaleX(0.5);
        rootNode.setScaleY(0.5);

        //加载按钮变化样式
        CustomeButton.implButton(cancelLabel, "file:client/src/main/res/customer/cancel");
        CustomeButton.implButton(commentLabel, "file:client/src/main/res/order/comment");
        CustomeButton.implButton(revokeOrderLabel, "file:client/src/main/res/order/revokeorder");
    }
}
