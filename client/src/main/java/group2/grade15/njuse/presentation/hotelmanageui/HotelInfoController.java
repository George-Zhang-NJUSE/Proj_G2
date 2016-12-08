package group2.grade15.njuse.presentation.hotelmanageui;

import group2.grade15.njuse.presentation.mycontrol.CustomeButton;
import group2.grade15.njuse.vo.CustomerVO;
import group2.grade15.njuse.vo.HotelPromotionVO;
import group2.grade15.njuse.vo.HotelVO;
import group2.grade15.njuse.vo.RoomVO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.web.HTMLEditor;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by ALIENWARE-PC on 2016/12/3.
 */
public class HotelInfoController implements Initializable {
    @FXML
    private TextField name;
    @FXML
    private TextField address;
    @FXML
    private TextField rank;
    @FXML
    private TextField contact;
    @FXML
    private TextArea facility;
    @FXML
    private ListView company;
    @FXML
    private HTMLEditor describeEditor;
    @FXML
    private Label check;
    @FXML
    private Label cancel;
    @FXML
    private Label editButton;




    @Override

    public void initialize(URL location, ResourceBundle resources) {
        CustomeButton.implButton(check, "file:client/src/main/res/hotelmanage/Check");
        CustomeButton.implButton(cancel, "file:client/src/main/res/hotelmanage/Cancel");

    }
    //逻辑数据采集部分
    public HotelVO getVO(){
        int ID=HotelManageMainController.hotelVO.getId();
        ArrayList<RoomVO> roomList=HotelManageMainController.hotelVO.getRoomList();


        int rank=Integer.parseInt(this.rank.getText());

        String introduction=describeEditor.getHtmlText();

        //TODO 从concreteAddress 到 address 的转换
        HotelVO result = new HotelVO(ID, name.getText(), address.getText(), "null", contact.getText(),introduction, facility.getText(), roomList,null,rank,HotelManageMainController.hotelVO.getScore(),HotelManageMainController.hotelVO.getPicture());
        return result;
    }

}
