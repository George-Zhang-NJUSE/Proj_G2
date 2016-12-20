package group2.grade15.njuse.presentation.webmarketerui;

import group2.grade15.njuse.bl.searchbl.Search;
import group2.grade15.njuse.blservice.SearchServ;
import group2.grade15.njuse.presentation.mycontrol.CustomeButton;
import group2.grade15.njuse.utility.PromotionState;
import group2.grade15.njuse.utility.WebPromotionType;
import group2.grade15.njuse.vo.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;


import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by ALIENWARE-PC on 2016/12/2.
 */
public class AddPromotionController implements Initializable {


    private SearchServ searchServ=new Search();



    private int ID;
    @FXML
    private TextField name;
    @FXML
    private TextField cut;
    @FXML
    private ComboBox<String> type;
    @FXML
    private GridPane VIP;
    @FXML
    private GridPane time;
    @FXML
    private GridPane rank;
    @FXML
    private ComboBox<Integer> requiredRank;
    @FXML
    private ChoiceBox provinceBox;
    @FXML
    private ChoiceBox cityBox;
    @FXML
    private ChoiceBox districtBox;
    @FXML
    private ChoiceBox cbdBox;
    @FXML
    private DatePicker startDate;
    @FXML
    private DatePicker endDate;
    @FXML
    private Label check;
    @FXML
    private Label cancel;
    @FXML
    private Label message;

    @FXML
    private void loadCityBox() {
        ProvinceVO selectedProvince= (ProvinceVO) provinceBox.getValue();
        ArrayList<CityVO> cityVOList = searchServ.getCity(selectedProvince.getProvinceID()).getList();
        cityBox.setItems(FXCollections.observableArrayList(cityVOList));

        districtBox.setItems(FXCollections.observableArrayList());
        cbdBox.setItems(FXCollections.observableArrayList());
    }

    @FXML
    private void loadDistrictBox() {
        CityVO selectedCity = (CityVO) cityBox.getValue();
        ArrayList<DistrictVO> districtVOList = searchServ.getDistrict(selectedCity.getCityNum()).getList();
        districtBox.setItems(FXCollections.observableArrayList(districtVOList));

        cbdBox.setItems(FXCollections.observableArrayList());
    }

    @FXML
    private void loadCbdBox() {
        DistrictVO selectedDistrict = (DistrictVO) districtBox.getValue();
        ArrayList<CbdVO> cbdVOList = searchServ.getCbd(selectedDistrict.getDistrictNum()).getList();
        cbdBox.setItems(FXCollections.observableArrayList(cbdVOList));

    }
    public PromotionManageController promotionManageController;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CustomeButton.implButton(check,"file:client/src/main/res/webmarketer/Check");
        CustomeButton.implButton(cancel,"file:client/src/main/res/webmarketer/Cancel");
        type.setOnAction((ActionEvent e)->{
            VIP.setVisible(false);
            time.setVisible(false);
            rank.setVisible(false);
            switch (type.getValue()){
                case "特定商区优惠":
                    VIP.setVisible(true);
                    break;
                case "特定时间优惠":
                    time.setVisible(true);
                    break;
                case "会员优惠":
                    rank.setVisible(true);
                    break;
            }
        });
        ObservableList<String> types= FXCollections.observableArrayList(
                "特定商区优惠",
                "特定时间优惠",
                "会员优惠"
        );
        type.setItems(types);

        ObservableList<Integer> ranks=FXCollections.observableArrayList(
                1,2,3,4,5,6,7,8,9,10
        );
        requiredRank.setItems(ranks);

        ArrayList<ProvinceVO> provinceVOList = searchServ.getProvince().getList();
        provinceBox.setItems(FXCollections.observableArrayList(provinceVOList));
    }
    public WebPromotionVO gatherVO(){
        Date sD,eD;
        try {
            String[] s = startDate.getEditor().getText().split("-");
            String[] e = endDate.getEditor().getText().split("-");
            sD = new Date(Integer.parseInt(s[0]) - 1900, Integer.parseInt(s[1]) - 1, Integer.parseInt(s[2]));
            eD = new Date(Integer.parseInt(e[0]) - 1900, Integer.parseInt(e[1]) - 1, Integer.parseInt(e[2]));
        }catch(Exception e){
            sD=new Date(0,0,0);
            eD=new Date(0,0,0);
        }
        WebPromotionType typeOfVO;
        switch(type.getValue()){
            case"特定商区优惠":
                typeOfVO=WebPromotionType.AreaWeb;
                break;
            case"特定时间优惠":
                typeOfVO=WebPromotionType.TimeWeb;
                break;
            case"会员优惠":
                typeOfVO=WebPromotionType.LevelWeb;
                break;
            default:
                typeOfVO=null;
        }
        int rerank;
        try{
            rerank=requiredRank.getValue();
        }catch (NullPointerException e){
            rerank=0;
        }
        WebPromotionVO vo=new WebPromotionVO(
                0,
                typeOfVO,
                sD,
                eD,
                ((CbdVO)cbdBox.getValue()).getCbdNum(),
                rerank,
                Double.parseDouble(cut.getText()),
                name.getText(),
                PromotionState.unlaunched
        );
        return vo;
    }
    public void back(){
        promotionManageController.back();
    }
    public void add() {
        if (checkEmpty()) {
            message.setText("填写内容不能为空");
            return;
        }
        if (type.getValue() == "特定时间优惠") {
            if (1==startDate.getValue().compareTo(endDate.getValue())) {
                message.setText("起始日期不能晚于结束日期");
                return;
            }
        }
        promotionManageController.addPromotion(gatherVO());
    }
    public boolean checkEmpty(){
        boolean result=
                name.getText().length()==0||
                        cut.getText().length()==0||
                        type.getValue()==null;
        if(type.getValue()=="特定时间优惠"){
            if (startDate.getValue()==null|| endDate.getValue()==null) {
                result=true;
            }
        } else if (type.getValue() == "会员优惠") {
            if (requiredRank.getValue()==null) {
                result=true;
            }
        } else if (type.getValue()=="特定商区优惠") {
            if (cbdBox.getValue()==null) {
                result = true;
            }
        }

        return result;
    }
}
