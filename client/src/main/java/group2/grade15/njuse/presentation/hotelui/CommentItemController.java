package group2.grade15.njuse.presentation.hotelui;

import group2.grade15.njuse.bl.customerbl.CustomerController;
import group2.grade15.njuse.blservice.CustomerServ;
import group2.grade15.njuse.presentation.myanimation.Fade;
import group2.grade15.njuse.presentation.myanimation.Pop;
import group2.grade15.njuse.vo.CommentVO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by George on 2016/12/1.
 */
public class CommentItemController implements Initializable{

    CustomerServ customerServ;

    @FXML
    private Node rootNode;

    @FXML
    private Label scoreLabel, customerNameLabel, timeLabel;

    @FXML
    private TextArea commentArea;

    public void initData(CommentVO commentVO) {
        scoreLabel.setText(Double.toString(commentVO.getScore()));
        customerNameLabel.setText(customerServ.getInfo(commentVO.getUserID()).getName());
        timeLabel.setText(commentVO.getTime().toString());
        commentArea.setText(commentVO.getComment());
    }

    public void show() {
        //渐入扩大动画
        Fade fadeIn = new Fade(rootNode, 300, true);
        Pop popIn = new Pop(rootNode, 300, true);

        fadeIn.play();
        popIn.play();


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        customerServ = new CustomerController();

        //为渐入扩大动画做准备
        rootNode.setOpacity(0);
        rootNode.setScaleX(0.9);
        rootNode.setScaleY(0.9);
    }
}
