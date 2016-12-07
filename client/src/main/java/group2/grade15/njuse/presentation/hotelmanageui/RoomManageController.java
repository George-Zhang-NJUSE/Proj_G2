package group2.grade15.njuse.presentation.hotelmanageui;

import group2.grade15.njuse.bl.hotelmanagerbl.HotelManagerController;
import group2.grade15.njuse.blservice.HotelManagerServ;
import group2.grade15.njuse.presentation.myanimation.Fade;
import group2.grade15.njuse.presentation.mycontrol.CustomeButton;
import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.utility.RoomType;
import group2.grade15.njuse.vo.RoomVO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by ALIENWARE-PC on 2016/12/4.
 */
public class RoomManageController implements Initializable {
    @FXML
    private Label addButton;
    @FXML
    private Label modifyButton;
    @FXML
    private Label deleteButton;

    @FXML
    private GridPane addPane;
    @FXML
    private GridPane modifyPane;
    @FXML
    private GridPane deletePane;
    @FXML
    private HBox checkPane;
    @FXML
    private Label check;
    @FXML
    private Label cancel;
    @FXML
    private Group opGroup;
    private GridPane now=new GridPane();

    @FXML
    private ComboBox<RoomType> typeA,typeM;
    @FXML
    private TextField countA,countM;
    @FXML
    private TextField priceA,priceM;
    @FXML
    private TextField restM;

    //逻辑实现部分
    private HotelManagerServ hotelManagerController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CustomeButton.implButton(check,"file:client/src/main/res/hotelmanage/Check");
        CustomeButton.implButton(cancel,"file:client/src/main/res/hotelmanage/Cancel");
        CustomeButton.implButton(addButton,"file:client/src/main/res/hotelmanage/add");
        CustomeButton.implButton(modifyButton,"file:client/src/main/res/hotelmanage/modify");
        CustomeButton.implButton(deleteButton,"file:client/src/main/res/hotelmanage/delete");

    }

    public void setService(HotelManagerServ service) {
        hotelManagerController=service;
    }
    public void toAdd(){
        now.setVisible(false);
        now=addPane;
        now.setVisible(true);
        checkPane.setVisible(true);
        Fade in = new Fade(addPane, 200, true);
        in.play();
        Fade cin=new Fade(checkPane,200,true);
        cin.play();
    }
    public void toModify(){
        now.setVisible(false);
        now=modifyPane;
        now.setVisible(true);
        checkPane.setVisible(true);
        Fade in = new Fade(modifyPane, 200, true);
        in.play();
        Fade cin=new Fade(checkPane,200,true);
        cin.play();
    }
    public void toDelete(){
        now.setVisible(false);
        now=deletePane;
        now.setVisible(true);
        checkPane.setVisible(true);
        Fade in = new Fade(deletePane, 200, true);
        in.play();
        Fade cin=new Fade(checkPane,200,true);
        cin.play();
    }
    public void back(){
        now.setVisible(false);
        checkPane.setVisible(false);
    }
    //逻辑数据采集部分


    /**room表格里面的RoomVO的采集
     *
     * @return RoomVO, 在小栏中的输入的RoomVO
     */
    public RoomVO getRoomVO(){
        RoomVO result=null;
        if (addPane.isVisible()) {//判断是来自哪个表格的VO
            result = new RoomVO(typeA.getValue(),Double.parseDouble(priceA.getText()),Integer.parseInt(countA.getText()),Integer.parseInt(countA.getText()));
        }else{
            result = new RoomVO(typeM.getValue(), Double.parseDouble(priceM.getText()), Integer.parseInt(countM.getText()), Integer.parseInt(restM.getText()));
        }
        return result;
    }

    public ResultMessage addRoom(){
        RoomVO roomToAdd=getRoomVO();
        return hotelManagerController.modifyRoomInfo(roomToAdd);
    }
    public ResultMessage modifyRoom(){
        RoomVO roomToModify = getRoomVO();
        return hotelManagerController.modifyRoomInfo(roomToModify);
    }
}
