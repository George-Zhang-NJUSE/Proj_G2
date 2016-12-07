package group2.grade15.njuse.presentation.searchui;

import group2.grade15.njuse.blservice.SearchServ;
import group2.grade15.njuse.presentation.hotelui.HotelItemController;
import group2.grade15.njuse.presentation.myanimation.ChangeHeight;
import group2.grade15.njuse.presentation.myanimation.Fade;
import group2.grade15.njuse.presentation.myanimation.Pop;
import group2.grade15.njuse.presentation.myanimation.Rotate;
import group2.grade15.njuse.presentation.mycontrol.CustomeButton;
import group2.grade15.njuse.presentation.myliteral.LiteralList;
import group2.grade15.njuse.vo.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by George on 2016/11/30.
 */
public class CustomerSearchHotelController implements Initializable {

    private CustomerVO customer;
    private Pane parentPane;
    private SearchServ searchServ;
    private HotelListVO hotelListVO;

    @FXML
    private VBox searchItemBox;

    @FXML
    private Pane additionalConditionPane;

    @FXML
    private Node rootNode;

    @FXML
    private Label searchLabel, showOrHideLabel;

    @FXML
    private CheckBox onlyOrderedHotelCheckBox;

    @FXML
    private ComboBox provinceBox, cityBox, districtBox, cbdBox;

    //以下选择框内容均不需要联网加载
    @FXML
    private ChoiceBox roomTypeChoiceBox, priceRangeChoiceBox, freeRoomNumChoiceBox, minStarChoiceBox,
                    scoreRangeChoiceBox, sortConditionChoiceBox, sortTypeChoiceBox;

    @FXML
    private TextField hotelNameField;


    @FXML
    private void changeAdditionalPane() {
        if (additionalConditionPane.isVisible()) {
            hideAdditionalPane();
        } else {
            showAdditionalPane();
        }
    }

    @FXML
    private void loadCityBox() {
        ProvinceVO selectedProvince= (ProvinceVO) provinceBox.getValue();
        ArrayList<CityVO> cityVOList = searchServ.getCity(selectedProvince.getProvinceID()).getList();
        cityBox.setItems(FXCollections.observableArrayList(cityVOList));

        districtBox.setItems(null);
        cbdBox.setItems(null);
    }

    @FXML
    private void loadDistrictBox() {
        CityVO selectedCity = (CityVO) cityBox.getValue();
        ArrayList<DistrictVO> districtVOList = searchServ.getDistrict(selectedCity.getCityNum()).getList();
        districtBox.setItems(FXCollections.observableArrayList(districtVOList));

        cbdBox.setItems(null);
    }

    @FXML
    private void loadCbdBox() {
        DistrictVO selectedDistrict = (DistrictVO) districtBox.getValue();
        ArrayList<CbdVO> cbdVOList = searchServ.getCbd(selectedDistrict.getDistrictNum()).getList();
        cbdBox.setItems(FXCollections.observableArrayList(cbdVOList));

    }

    //输入所有基础条件后自动调用，在后台加载
    @FXML
    private void loadHotelList() {
        CbdVO selectedCbd = (CbdVO) cbdBox.getValue();
        hotelListVO = searchServ.getHotel(selectedCbd.getCbdNum());
    }


    private void showAdditionalPane() {
        ChangeHeight grow=new ChangeHeight(additionalConditionPane,300,160);
        Fade fadeIn = new Fade(additionalConditionPane, 300, true);
        Rotate reverse = new Rotate(showOrHideLabel, 300, 180);

        additionalConditionPane.setVisible(true);

        grow.play();
        fadeIn.play();
        reverse.play();
    }

    private void hideAdditionalPane() {
        ChangeHeight shrink = new ChangeHeight(additionalConditionPane, 300, 0);
        Fade fadeOut = new Fade(additionalConditionPane, 300, false);
        Rotate reverse = new Rotate(showOrHideLabel, 300, 0);

        fadeOut.setOnFinished((ActionEvent e) -> additionalConditionPane.setVisible(false));

        shrink.play();
        fadeOut.play();
        reverse.play();
    }

    @FXML
    private void search() {
        int customerID = customer.getId();

    }


    private void showSearchResult() {
        try {
            searchItemBox.getChildren().clear();


            // TODO: 2016/12/2 需要更改为正确的逻辑
            for (int i = 0; i < 15; ++i) {
                FXMLLoader searchItemLoader = new FXMLLoader(new URL("file:client/src/main/java/group2/grade15/njuse/presentation/hotelui/HotelItem.fxml"));
                Node singleItemTemplate = searchItemLoader.load();
                HotelItemController hotelItemController = searchItemLoader.getController();
                hotelItemController.setParentPane(parentPane);
                searchItemBox.getChildren().add(singleItemTemplate);
                hotelItemController.initDataAndShow();
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showSearchPane() {
        //渐入扩大动画
        Fade fadeIn = new Fade(rootNode, 300, true);
        Pop popIn = new Pop(rootNode, 300, true);
        fadeIn.play();
        popIn.play();
    }

    public void setParentPane(Pane parentPane) {
        this.parentPane = parentPane;
    }

    public void initDataAndShow() {
        // TODO: 2016/12/5 加载数据


        showSearchPane();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //加载按钮变化样式
        CustomeButton.implButton(searchLabel, "file:client/src/main/res/search/search");
        CustomeButton.implButton(showOrHideLabel, "file:client/src/main/res/search/point");

        //加载不需要联网数据的选择框
        roomTypeChoiceBox.setItems(FXCollections.observableArrayList(LiteralList.roomTypeList));
        roomTypeChoiceBox.setValue(LiteralList.roomTypeList[0]);

        priceRangeChoiceBox.setItems(FXCollections.observableArrayList(LiteralList.priceRangeList));
        priceRangeChoiceBox.setValue(LiteralList.priceRangeList[0]);

        freeRoomNumChoiceBox.setItems(FXCollections.observableArrayList(LiteralList.freeRoomNumList));
        freeRoomNumChoiceBox.setValue(LiteralList.freeRoomNumList[0]);

        minStarChoiceBox.setItems(FXCollections.observableArrayList(LiteralList.minStarList));
        minStarChoiceBox.setValue(LiteralList.minStarList[0]);

        scoreRangeChoiceBox.setItems(FXCollections.observableArrayList(LiteralList.scoreRangeList));
        scoreRangeChoiceBox.setValue(LiteralList.scoreRangeList[0]);

        sortConditionChoiceBox.setItems(FXCollections.observableArrayList(LiteralList.sortConditionList));
        sortConditionChoiceBox.setValue(LiteralList.sortConditionList[0]);

        sortTypeChoiceBox.setItems(FXCollections.observableArrayList(LiteralList.sortTypeList));
        sortTypeChoiceBox.setValue(LiteralList.sortTypeList[0]);

        //为渐入扩大动画做准备
        rootNode.setOpacity(0);
        rootNode.setScaleX(0.9);
        rootNode.setScaleY(0.9);

        //默认收起附加搜索条件面板
        additionalConditionPane.setVisible(false);
        additionalConditionPane.setOpacity(0);
        additionalConditionPane.setPrefHeight(0);

        //加载省份列表
//        searchServ = new Search();
//        ArrayList<ProvinceVO> provinceVOList = searchServ.getProvince().getList();
//        provinceBox.setItems(FXCollections.observableArrayList(provinceVOList));


    }
}
