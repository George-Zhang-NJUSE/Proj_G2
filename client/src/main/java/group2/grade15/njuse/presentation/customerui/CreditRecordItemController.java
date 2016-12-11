package group2.grade15.njuse.presentation.customerui;

import group2.grade15.njuse.presentation.customerglobal.LiteralList;
import group2.grade15.njuse.presentation.myanimation.Fade;
import group2.grade15.njuse.presentation.myanimation.Pop;
import group2.grade15.njuse.vo.CreditVO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by George on 2016/12/3.
 */
public class CreditRecordItemController implements Initializable{

    @FXML
    private Node rootNode;

    @FXML
    private Label orderIDLabel, timeLabel, actionLabel, creditChangeLabel, creditResultLabel;

    public void initData(CreditVO creditVO) {
        orderIDLabel.setText(Integer.toString(creditVO.getOrderID()));
        timeLabel.setText(creditVO.getTime().toString());
        actionLabel.setText(LiteralList.changeReasonList[creditVO.getReason().ordinal()]);
        double creditChange = creditVO.getCreditChange();
        creditChangeLabel.setText(creditChange > 0 ? ("+" + Double.toString(creditChange)) : Double.toString(creditChange));
        creditResultLabel.setText(Double.toString(creditVO.getCreditLeft()));
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
        //为渐入扩大动画做准备
        rootNode.setOpacity(0);
        rootNode.setScaleX(0.9);
        rootNode.setScaleY(0.9);
    }
}
