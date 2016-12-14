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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.sql.Date;
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
    private ComboBox<HotelPromotionType> type;
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
                        -1,
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
    public void commitAddition(){
        promotionManageController.addPromotion();
        clean();

    }
    private void clean(){
        promotionManageController.closeOpPane();
    }
    public void closeOpPane(){
        promotionManageController.closeOpPane();
    }
}
