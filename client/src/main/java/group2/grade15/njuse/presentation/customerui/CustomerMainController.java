package group2.grade15.njuse.presentation.customerui;

import group2.grade15.njuse.presentation.customerglobal.CommonData;
import group2.grade15.njuse.presentation.hotelui.MyHotelListController;
import group2.grade15.njuse.presentation.mycontrol.CustomeButton;
import group2.grade15.njuse.presentation.orderui.MyOrderListController;
import group2.grade15.njuse.presentation.searchui.CustomerSearchHotelController;
import group2.grade15.njuse.vo.CustomerVO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by George on 2016/11/25.
 */
public class CustomerMainController implements Initializable{

    private CustomerVO userVO;

    @FXML
    private Pane functionPane;

    @FXML
    private Label usernameLabel, creditLabel, myHotelLabel;


    public void initData(CustomerVO vo) {
        // TODO: 2016/12/1  
        userVO = vo;
        usernameLabel.setText(userVO.getName());
        creditLabel.setText(Double.toString(userVO.getCredit()));

        CommonData.getInstance().setCustomerVO(userVO);

    }

    @FXML
    private void showSearchHotelPane() {
        String searchPaneUrl = "file:client/src/main/java/group2/grade15/njuse/presentation/searchui/CustomerSearchHotel.fxml";
        try {
            FXMLLoader loader = new FXMLLoader(new URL(searchPaneUrl));
            functionPane.getChildren().clear();
            functionPane.getChildren().add(loader.load());
            CustomerSearchHotelController searchHotelController = loader.getController();//必须在load之后才能getController
            searchHotelController.show();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void showMyOrderListPane() {
        String myOrderPaneUrl = "file:client/src/main/java/group2/grade15/njuse/presentation/orderui/MyOrderList.fxml";
        try {
            FXMLLoader loader = new FXMLLoader(new URL(myOrderPaneUrl));
            functionPane.getChildren().clear();
            functionPane.getChildren().add(loader.load());

            MyOrderListController orderListController = loader.getController();

            orderListController.initDataAndShow();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void showMyHotelListPane() {
        String myHotelListPaneUrl = "file:client/src/main/java/group2/grade15/njuse/presentation/hotelui/MyHotelList.fxml";
        try {
            FXMLLoader loader = new FXMLLoader(new URL(myHotelListPaneUrl));
            functionPane.getChildren().clear();
            functionPane.getChildren().add(loader.load());

            MyHotelListController hotelListController = loader.getController();
            hotelListController.initDataAndShow(); //show必须在setParentPane之后，不能将其放在initialize方法里
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void showPersonalInfoPane() {
        String personalPaneUrl = "file:client/src/main/java/group2/grade15/njuse/presentation/customerui/CustomerInfo.fxml";
        try {
            FXMLLoader loader = new FXMLLoader(new URL(personalPaneUrl));
            functionPane.getChildren().clear();
            functionPane.getChildren().add(loader.load());

            CustomerInfoController infoController = loader.getController();
            infoController.initDataAndShow();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //加载按钮变化样式
        CustomeButton.implButton(myHotelLabel, "file:client/src/main/res/customer/myhotel");

        CommonData.getInstance().setFunctionAreaPane(functionPane);
    }
}
