package group2.grade15.njuse.presentation.hotelmanageui;

import group2.grade15.njuse.bl.hotelbl.Hotel;
import group2.grade15.njuse.presentation.mycontrol.CustomeButton;
import group2.grade15.njuse.utility.HotelPromotionType;
import group2.grade15.njuse.utility.PromotionState;
import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.vo.HotelPromotionVO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

import static group2.grade15.njuse.utility.HotelPromotionType.TimeHotel;

/**
 * Created by luoy on 2016/12/3.
 */
public class ModifyPromotionController implements Initializable {
    public  int ID;
    @FXML
    private TextField name;
    @FXML
    private TextField cut;
    @FXML
    private TextField type;
    @FXML
    private GridPane company;
    @FXML
    private GridPane time;
    @FXML
    private TextField companyString;
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

    public PromotionManageController promotionManageController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CustomeButton.implButton(check, "file:client/src/main/res/hotelmanage/Check");
        CustomeButton.implButton(cancel, "file:client/src/main/res/hotelmanage/Cancel");
        /*ObservableList<HotelPromotionType> promotionTypes=FXCollections.observableArrayList(
                HotelPromotionType.BirthdayHotel,
                HotelPromotionType.MultipleHotel,
                HotelPromotionType.PartnerHotel,
                TimeHotel
        );
        type.setItems(promotionTypes);
        type.setOnAction((ActionEvent e)->{
            switchPane(type.getValue());
        });*/
        setEditable(false);

    }

    public void back(){
        setEditable(false);
        promotionManageController.closeOpPane();
    }
    private void switchPane(HotelPromotionType type){
        time.setVisible(false);
        company.setVisible(false);

        if(type== TimeHotel){
            time.setVisible(true);
        }else if(type==HotelPromotionType.PartnerHotel){
            company.setVisible(true);
        }
    }

    public void showPromotion(HotelPromotionVO vo){
        ID=vo.getHotelID();
        name.setText(vo.getName());
        type.setText(vo.getType().toString());
        cut.setText(String.valueOf(vo.getDiscount()));
        switch(vo.getType()){
            case PartnerHotel:
                company.setVisible(true);
                companyString.setText(String.valueOf(vo.getVipID()));
                break;
            case TimeHotel:
                time.setVisible(true);
                startDate.getEditor().setText(vo.getStart().toString());
                endDate.getEditor().setText(vo.getEnd().toString());
                break;
        }
    }

    public void setEditable(boolean sw){
        name.setEditable(sw);
        cut.setEditable(sw);

        companyString.setEditable(sw);
        startDate.setEditable(sw);
        endDate.setEditable(sw);

        check.setVisible(sw);
        cancel.setVisible(sw);
    }
    public HotelPromotionVO getVO(){
        int id=promotionManageController.getSelected().getId();
        HotelPromotionVO result;
        HotelPromotionType type;
        switch(this.type.getText()){
            case"BirthdayHotel":
                type=HotelPromotionType.BirthdayHotel;
                break;
            case "PartnerHotel":
                type = HotelPromotionType.PartnerHotel;
                break;
            case "MultipleHotel":
                type = HotelPromotionType.MultipleHotel;
                break;
            default:
                type = TimeHotel;
        }
        switch(type){
            case BirthdayHotel:
                result=new HotelPromotionVO(id,type,null,null,-1,Double.parseDouble(cut.getText()),name.getText(), promotionManageController.getSelectedPromotion().getState(),HotelManageMainController.hotelVO.getId());
                break;
            case TimeHotel:
                //TODO 确认这些地方没问题
                Date sD,eD;

                eD = Date.valueOf(endDate.getEditor().getText());
                sD = Date.valueOf(startDate.getEditor().getText());
                result = new HotelPromotionVO(id, type, sD, eD, -1, Double.parseDouble(cut.getText()), name.getText(), promotionManageController.getSelectedPromotion().getState(), HotelManageMainController.hotelVO.getId());
                break;
            case PartnerHotel:
                result=new HotelPromotionVO(id,type,null,null,-1,Double.parseDouble(cut.getText()),name.getText(),promotionManageController.getSelectedPromotion().getState(),HotelManageMainController.hotelVO.getId());
                break;
            case MultipleHotel:
                result=new HotelPromotionVO(id,type,null,null,-1,Double.parseDouble(cut.getText()),name.getText(),promotionManageController.getSelectedPromotion().getState(),HotelManageMainController.hotelVO.getId());
                break;
            default:
                result=new HotelPromotionVO(id,type,null,null,-1,Double.parseDouble(cut.getText()),name.getText(),promotionManageController.getSelectedPromotion().getState(),HotelManageMainController.hotelVO.getId());
        }
        return result;
    }
    private boolean checkEmpty(){
        boolean result =
                name.getText().length()==0 ||
                        cut.getText().length()==0 ||
                        type.getText().length()==0
                ;

        switch (type.getText()) {
            case "TimeHotel":
                result = result || (startDate.getEditor().getText().length()==0) || (endDate.getEditor().getText().length()==0);
                break;
        }
        return result;
    }
    public void commitModify(){

        if (checkEmpty()) {
            message.setText("填写部分不能为空");
            return;
        }
        if(type.getText()=="TimeHotel")
        if(startDate.getValue().compareTo(endDate.getValue())!=-1){
            message.setText("开始日期不能早于结束日期");
            return;
        }

        HotelPromotionVO toModify=getVO();
        promotionManageController.modifyPromotion(toModify);
    }
}
