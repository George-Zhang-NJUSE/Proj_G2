package group2.grade15.njuse.presentation.orderui;

import group2.grade15.njuse.presentation.myanimation.Fade;
import group2.grade15.njuse.presentation.myanimation.Pop;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by George on 2016/12/2.
 */
public class MyOrderDetailController implements Initializable{

    private Pane parentPane;

    @FXML
    private Node rootNode;

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

    public void setParentPane(Pane parentPane) {
        this.parentPane = parentPane;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //为弹出式动画做准备
        rootNode.setVisible(false);
        rootNode.setOpacity(0);
        rootNode.setScaleX(0.3);
        rootNode.setScaleY(0.3);
    }
}
