package group2.grade15.njuse.presentation.orderui;

import group2.grade15.njuse.vo.OrderVO;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

/**
 * Created by George on 2016/12/2.
 */
public class MyOrderItemController {

    private Pane parentPane;
    private OrderVO orderVO;


    @FXML
    protected void showMyOrderDetail() {

    }

    public void initData(OrderVO vo) {
        orderVO = vo;

    }

    public void setParentPane(Pane parentPane) {
        this.parentPane = parentPane;
    }
}
