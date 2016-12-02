package group2.grade15.njuse.presentation.customerui;

import group2.grade15.njuse.presentation.orderui.MyOrderListController;
import group2.grade15.njuse.presentation.searchui.CustomerSearchHotelController;
import group2.grade15.njuse.vo.CustomerVO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by George on 2016/11/25.
 */
public class CustomerMainController {

    private CustomerVO userVO;

    @FXML
    private Pane functionPane;

    @FXML
    private Label usernameLabel, creditLabel;

    @FXML
    protected void mouseClickPersonalInfo() {
        showPersonalInfoPane();
    }

    @FXML
    protected void mouseClickBookHotel() {
        showSearchHotelPane();
    }

    @FXML
    protected void mouseClickMyOrder() {
        showMyOrderListPane();
    }

    public void initData(CustomerVO vo){
        // TODO: 2016/12/1  
        userVO=vo;
        usernameLabel.setText(userVO.getName());
        creditLabel.setText(Double.toString(userVO.getCredit()));


    }


    private void showSearchHotelPane() {
        String searchPaneUrl="file:client/src/main/java/group2/grade15/njuse/presentation/searchui/CustomerSearchHotel.fxml";
        try {
            FXMLLoader loader=new FXMLLoader(new URL(searchPaneUrl));
            functionPane.getChildren().clear();
            functionPane.getChildren().add(loader.load());
            CustomerSearchHotelController searchHotelController=loader.getController();//必须在load之后才能getController
            searchHotelController.setParentPane(functionPane);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showMyOrderListPane() {
        String myOrderPaneUrl="file:client/src/main/java/group2/grade15/njuse/presentation/orderui/MyOrderList.fxml";
        try {
            FXMLLoader loader=new FXMLLoader(new URL(myOrderPaneUrl));
            functionPane.getChildren().clear();
            functionPane.getChildren().add(loader.load());
            MyOrderListController orderListController = loader.getController();
            orderListController.setParentPane(functionPane);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void showPersonalInfoPane() {
        String personalPaneUrl="file:client/src/main/java/group2/grade15/njuse/presentation/customerui/CustomerInfo.fxml";
        try {
            FXMLLoader loader=new FXMLLoader(new URL(personalPaneUrl));
            functionPane.getChildren().clear();
            functionPane.getChildren().add(loader.load());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
