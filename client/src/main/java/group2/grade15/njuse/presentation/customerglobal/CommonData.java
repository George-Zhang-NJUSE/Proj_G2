package group2.grade15.njuse.presentation.customerglobal;

import group2.grade15.njuse.vo.CustomerVO;
import javafx.scene.layout.Pane;

/**
 * Created by George on 2016/12/7.
 */
public class CommonData {

    private static CommonData data = new CommonData();

    private Pane functionAreaPane;

    private CustomerVO customerVO;

    private CommonData() {

    }

    public static CommonData getInstance() {
        return data;
    }

    public Pane getFunctionAreaPane() {
        return functionAreaPane;
    }

    public void setFunctionAreaPane(Pane pane) {
        if (functionAreaPane == null) {
            functionAreaPane = pane;
        }
    }

    public CustomerVO getCustomerVO() {
        return customerVO;
    }

    public void setCustomerVO(CustomerVO vo) {
        customerVO = vo;
    }
}
