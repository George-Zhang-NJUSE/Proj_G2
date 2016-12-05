package group2.grade15.njuse.presentation.hotelmanageui;

import group2.grade15.njuse.utility.HotelPromotionType;
import group2.grade15.njuse.utility.PromotionState;
import group2.grade15.njuse.vo.HotelPromotionVO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

/**
 * Created by luoy on 2016/12/3.
 */
public class ModifyPromotionController implements Initializable {
    private int ID;
    @FXML
    private TextField name;
    @FXML
    private TextField cut;
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


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    public void setEditable(){
        boolean sw=true;
        name.setEditable(sw);
        cut.setEditable(sw);
        type.setEditable(sw);

        companyString.setEditable(sw);
        startDate.setEditable(sw);
        endDate.setEditable(sw);
    }
    public void setUneditable(){
        boolean sw=false;
        name.setEditable(sw);
        cut.setEditable(sw);
        type.setEditable(sw);
        companyString.setEditable(sw);
        startDate.setEditable(sw);
        endDate.setEditable(sw);
    }
    public HotelPromotionVO getVO(){
        int id=ID;
        HotelPromotionVO result;
        HotelPromotionType type=this.type.getValue();
        switch(type){
            case Birthday_Promotion :
                result=new HotelPromotionVO(ID,type,null,null,-1,Double.parseDouble(cut.getText()),name.getText(), PromotionState.unlaunched,HotelManageMainController.getHotelID());
                break;
            case Time_Promotion:
                Date sD,eD;
                String[] s=startDate.getEditor().getText().split("/");
                String[] e = endDate.getEditor().getText().split("/");
                sD=new Date(Integer.parseInt(s[2]),Integer.parseInt(s[0]),Integer.parseInt(s[1]));
                eD=new Date(Integer.parseInt(e[2]),Integer.parseInt(e[0]),Integer.parseInt(e[1]));
                result = new HotelPromotionVO(ID, type, sD, eD, -1, Double.parseDouble(cut.getText()), name.getText(), PromotionState.unlaunched, HotelManageMainController.getHotelID());
                break;
            case Buisiness_Partner_Promotion:
                result=new HotelPromotionVO(ID,type,null,null,-1,Double.parseDouble(cut.getText()),name.getText(),PromotionState.unlaunched,HotelManageMainController.getHotelID());
                break;
            case Multiple_Room_Promotion:
                result=new HotelPromotionVO(ID,type,null,null,-1,Double.parseDouble(cut.getText()),name.getText(),PromotionState.unlaunched,HotelManageMainController.getHotelID());
                break;
            default:
                result=new HotelPromotionVO(ID,type,null,null,-1,Double.parseDouble(cut.getText()),name.getText(),PromotionState.unlaunched,HotelManageMainController.getHotelID());
        }
        return result;
    }
}
