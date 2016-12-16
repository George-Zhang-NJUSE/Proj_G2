package group2.grade15.njuse.presentation.searchui;

import group2.grade15.njuse.bl.searchbl.Search;
import group2.grade15.njuse.blservice.SearchServ;
import group2.grade15.njuse.presentation.customerglobal.CommonData;
import group2.grade15.njuse.presentation.customerglobal.LiteralList;
import group2.grade15.njuse.presentation.hotelui.HotelItemController;
import group2.grade15.njuse.presentation.myanimation.ChangeHeight;
import group2.grade15.njuse.presentation.myanimation.Fade;
import group2.grade15.njuse.presentation.myanimation.Pop;
import group2.grade15.njuse.presentation.myanimation.Rotate;
import group2.grade15.njuse.presentation.mycontrol.CustomeButton;
import group2.grade15.njuse.utility.RoomType;
import group2.grade15.njuse.utility.SortMethod;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Created by George on 2016/11/30.
 */
public class CustomerSearchHotelController implements Initializable {

    private CustomerVO customer;

    private SearchServ searchServ;

    private HotelListVO basicHotelListVO;

    @FXML
    private VBox searchItemBox;

    @FXML
    private Pane additionalConditionPane;

    @FXML
    private Node rootNode;

    @FXML
    private Label searchLabel, showOrHideLabel;

    @FXML
    private DatePicker checkInDatePicker, checkOutDatePicker;

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
        basicHotelListVO = searchServ.getHotel(selectedCbd.getCbdNum());
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

        if (basicHotelListVO != null) {

            //debug
//            System.out.println("界面后台："+basicHotelListVO.getList().get(0).getName());

            //抓取输入的搜索条件
            int customerID = customer.getId();
            SortMethod sortCondition = SortMethod.values()[sortConditionChoiceBox.getSelectionModel().getSelectedIndex()];
            String hotelName = hotelNameField.getText();
            RoomType roomType = RoomType.values()[roomTypeChoiceBox.getSelectionModel().getSelectedIndex()];

            double minPrice=0,maxPrice=0;
            int priceRangeIndex = priceRangeChoiceBox.getSelectionModel().getSelectedIndex();
            if(priceRangeIndex!=0){
                if (priceRangeIndex == 6) {
                    minPrice = 5000;
                } else {
                    String[] minAndMaxStr = LiteralList.priceRangeList[priceRangeIndex].split("-");
                    minPrice = Double.parseDouble(minAndMaxStr[0]);
                    maxPrice = Double.parseDouble(minAndMaxStr[1]);
                }
            }

            int freeRoomNum = freeRoomNumChoiceBox.getSelectionModel().getSelectedIndex();
            int minStarLevel = minStarChoiceBox.getSelectionModel().getSelectedIndex();

            Date checkInDate=null, checkOutDate=null;
            String checkInDateStr=checkInDatePicker.getEditor().getText();
            String checkOutDateStr = checkOutDatePicker.getEditor().getText();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                if (checkInDateStr.length() > 0) {
                    checkInDate=dateFormat.parse(checkInDateStr);
                }

                if(checkOutDateStr.length()>0){
                    checkOutDate=dateFormat.parse(checkOutDateStr);
                }

            } catch (ParseException e) {
                e.printStackTrace();
            }

            double minScore=0, maxScore=10;
            int scoreRangeIndex = scoreRangeChoiceBox.getSelectionModel().getSelectedIndex();
            if (scoreRangeIndex != 0) {
                maxScore = 2 * scoreRangeIndex;
                minScore= maxScore-2;
            }

            boolean showBookedOnly = onlyOrderedHotelCheckBox.isSelected();

            SearchConditionVO searchConditionVO = new SearchConditionVO(customerID, sortCondition, hotelName, roomType, minPrice, maxPrice, freeRoomNum,
                    minStarLevel, checkInDate, checkOutDate, minScore, maxScore, showBookedOnly);

            HotelListVO result = searchServ.getHotelBySearch(searchConditionVO, basicHotelListVO);
            showSearchResult(result);

        }else{

            Alert basicConditionNotFilled = new Alert(Alert.AlertType.ERROR, "请先填写搜索基础条件！");
            basicConditionNotFilled.showAndWait();

        }

    }


    private void showSearchResult(HotelListVO hotelListVO) {


        if (hotelListVO != null) {
//            System.out.println("界面前段筛选后："+hotelListVO.getList().get(0).getName());
            ArrayList<HotelVO> hotelVOList = hotelListVO.getList();

            try {

                searchItemBox.getChildren().clear();
                for (HotelVO hotelVO : hotelVOList) {
                    FXMLLoader hotelItemLoader = new FXMLLoader(new URL("file:client/src/main/res/fxml/customer/HotelItem.fxml"));
                    Node singleNode = hotelItemLoader.load();
                    HotelItemController hotelItemController = hotelItemLoader.getController();
                    searchItemBox.getChildren().add(singleNode);
                    hotelItemController.initData(hotelVO);
                    hotelItemController.show();
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }else{
            searchItemBox.getChildren().clear();
        }

    }

    private void showSearchPane() {
        //渐入扩大动画
        Fade fadeIn = new Fade(rootNode, 300, true);
        Pop popIn = new Pop(rootNode, 300, true);
        fadeIn.play();
        popIn.play();
    }

    public void show() {
        showSearchPane();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        customer= CommonData.getInstance().getCustomerVO();

        //加载按钮变化样式
        CustomeButton.implButton(searchLabel, "file:client/src/main/res/search/search");
        CustomeButton.implButton(showOrHideLabel, "file:client/src/main/res/search/point");

        //加载不需要联网数据的选择框
        roomTypeChoiceBox.setItems(FXCollections.observableArrayList(LiteralList.roomTypeList));
        roomTypeChoiceBox.getSelectionModel().select(3); //兼容服务器的下标设置

        priceRangeChoiceBox.setItems(FXCollections.observableArrayList(LiteralList.priceRangeList));
        priceRangeChoiceBox.getSelectionModel().select(0);

        freeRoomNumChoiceBox.setItems(FXCollections.observableArrayList(LiteralList.freeRoomNumList));
        freeRoomNumChoiceBox.getSelectionModel().select(0);

        minStarChoiceBox.setItems(FXCollections.observableArrayList(LiteralList.minStarList));
        minStarChoiceBox.getSelectionModel().select(0);

        scoreRangeChoiceBox.setItems(FXCollections.observableArrayList(LiteralList.scoreRangeList));
        scoreRangeChoiceBox.getSelectionModel().select(0);

        sortConditionChoiceBox.setItems(FXCollections.observableArrayList(LiteralList.sortConditionList));
        sortConditionChoiceBox.getSelectionModel().select(0);

        sortTypeChoiceBox.setItems(FXCollections.observableArrayList(LiteralList.sortTypeList));
        sortTypeChoiceBox.getSelectionModel().select(0);

        //为渐入扩大动画做准备
        rootNode.setOpacity(0);
        rootNode.setScaleX(0.9);
        rootNode.setScaleY(0.9);

        //默认收起附加搜索条件面板
        additionalConditionPane.setVisible(false);
        additionalConditionPane.setOpacity(0);
        additionalConditionPane.setPrefHeight(0);

        //加载省份列表
        searchServ = new Search();
        ArrayList<ProvinceVO> provinceVOList = searchServ.getProvince().getList();
        provinceBox.setItems(FXCollections.observableArrayList(provinceVOList));
        
    }
}
