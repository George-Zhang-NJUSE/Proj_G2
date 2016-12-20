package group2.grade15.njuse.presentation.hotelmanageui;

import group2.grade15.njuse.presentation.mycontrol.CustomeButton;
import group2.grade15.njuse.utility.HotelPromotionType;
import group2.grade15.njuse.utility.PromotionState;
import group2.grade15.njuse.vo.HotelPromotionVO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;

/**
 * Created by ALIENWARE-PC on 2016/12/2.
 */
public class AddPromotionController implements Initializable {
    private int ID;
    @FXML
    private TextField name;
    @FXML
    private TextField cut;
    @FXML
    private Label check;
    @FXML
    private Label cancel;
    @FXML
    private Label message;
    @FXML
    private ComboBox<HotelPromotionType> type;
    @FXML
    private GridPane company;
    @FXML
    private GridPane time;


    @FXML
    private TextField companyID;
    @FXML
    private DatePicker startDate;
    @FXML
    private DatePicker endDate;
    @FXML
    private ChoiceBox provinceBox;

    public PromotionManageController promotionManageController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CustomeButton.implButton(check, "file:client/src/main/res/hotelmanage/Check");
        CustomeButton.implButton(cancel, "file:client/src/main/res/hotelmanage/Cancel");
        ObservableList<HotelPromotionType> types= FXCollections.observableArrayList(
                HotelPromotionType.BirthdayHotel,
                HotelPromotionType.MultipleHotel,
                HotelPromotionType.PartnerHotel,
                HotelPromotionType.TimeHotel
        );
        type.setItems(types);

        type.setOnAction((ActionEvent e)->{
            if(type.getValue()==HotelPromotionType.TimeHotel){
                time.setVisible(true);
                company.setVisible(false);
            }else if(type.getValue()==HotelPromotionType.PartnerHotel){
                time.setVisible(false);
                company.setVisible(true);
            }else{
                time.setVisible(false);
                company.setVisible(false);

            }
        });
    }
    public void setMainController(PromotionManageController controller){
        promotionManageController=controller;
    }
    public HotelPromotionVO getVO(){
        int id=ID;
        HotelPromotionVO result;
        HotelPromotionType type=this.type.getValue();
        switch(type){
            case BirthdayHotel:
                result=new HotelPromotionVO(
                        ID,
                        type,
                        null,
                        null,
                        -1,
                        Double.parseDouble(cut.getText()),
                        name.getText(),
                        PromotionState.unlaunched,
                        HotelManageMainController.hotelVO.getId());
                break;
            case TimeHotel:
                Date sD,eD;
                String[] s=startDate.getEditor().getText().split("-");
                String[] e = endDate.getEditor().getText().split("-");
                sD=new Date(Integer.parseInt(s[0])-1900,Integer.parseInt(s[1])-1,Integer.parseInt(s[2]));
                eD=new Date(Integer.parseInt(e[0])-1900,Integer.parseInt(e[1])-1,Integer.parseInt(e[2]));
                result = new HotelPromotionVO(
                        ID,
                        type,
                        sD,
                        eD,
                        -1,
                        Double.parseDouble(cut.getText()),
                        name.getText(),
                        PromotionState.unlaunched,
                        HotelManageMainController.hotelVO.getId());
                break;
            case PartnerHotel:
                result=new HotelPromotionVO(ID,
                        type,
                        null,
                        null,
                        Integer.parseInt(companyID.getText()),
                        Double.parseDouble(cut.getText()),
                        name.getText(),
                        PromotionState.unlaunched,
                        HotelManageMainController.hotelVO.getId());
                break;
            case MultipleHotel:
                result=new HotelPromotionVO(
                        ID,
                        type,
                        null,
                        null,
                        -1,
                        Double.parseDouble(cut.getText()),
                        name.getText(),
                        PromotionState.unlaunched,
                        HotelManageMainController.hotelVO.getId());
                break;
            default:
                result=new HotelPromotionVO(ID,type,null,null,-1,Double.parseDouble(cut.getText()),name.getText(),PromotionState.unlaunched,HotelManageMainController.hotelVO.getId());
        }
        return result;
    }
    private boolean checkEmpty(){
        boolean result =
                (name.getText().length()==0 )||
                        (cut.getText().length()==0 )||
                        (type.getValue()==null)
                ;
        if (type.getValue() == null) {
            return true;
        }
        switch (type.getValue()) {
            case TimeHotel:
                result = result || (startDate.getEditor().getText().length()==0) || (endDate.getEditor().getText().length()==0);
                System.out.println("test");
                break;
        }
        return result;
    }
    public void commitAddition(){
        if(checkEmpty()){
            message.setText("填写部分不能为空");
            return;
        }
        if(type.getValue()==HotelPromotionType.TimeHotel)
            if(startDate.getValue().compareTo(endDate.getValue())!=-1){
                message.setText("开始日期不能早于结束日期");
                return;
            }
        if (type.getValue() == HotelPromotionType.PartnerHotel) {
            try {
                Integer.parseInt(companyID.getText());
            } catch (Exception e) {
                message.setText("请输出有效数字");
                return;
            }
            }
        HotelPromotionVO toAdd=getVO();

        promotionManageController.addPromotion(toAdd);
        clean();

    }
    private void clean(){
        promotionManageController.closeOpPane();
    }
    public void closeOpPane(){
        promotionManageController.closeOpPane();
    }
}
