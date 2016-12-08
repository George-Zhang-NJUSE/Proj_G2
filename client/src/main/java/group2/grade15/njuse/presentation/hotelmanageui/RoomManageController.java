package group2.grade15.njuse.presentation.hotelmanageui;

import group2.grade15.njuse.bl.hotelmanagerbl.HotelManagerController;
import group2.grade15.njuse.blservice.HotelManagerServ;
import group2.grade15.njuse.presentation.myanimation.Fade;
import group2.grade15.njuse.presentation.mycontrol.CustomeButton;
import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.utility.RoomType;
import group2.grade15.njuse.vo.RoomVO;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by ALIENWARE-PC on 2016/12/4.
 */
public class RoomManageController implements Initializable {

    @FXML
    private TableView<Room> roomTable;

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
    private ObservableList<Room> data;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CustomeButton.implButton(check,"file:client/src/main/res/hotelmanage/Check");
        CustomeButton.implButton(cancel,"file:client/src/main/res/hotelmanage/Cancel");
        CustomeButton.implButton(addButton,"file:client/src/main/res/hotelmanage/add");
        CustomeButton.implButton(modifyButton,"file:client/src/main/res/hotelmanage/modify");
        CustomeButton.implButton(deleteButton,"file:client/src/main/res/hotelmanage/delete");
        ObservableList<RoomType> typeList=FXCollections.observableArrayList(
                RoomType.stadardDoubleBed,
                RoomType.bigSingleBed,
                RoomType.suiteRoom
        );
        typeA.setItems(typeList);
        check.setOnMouseClicked((MouseEvent e)->{
            if (addPane.isVisible()){
                addRoom();

            }else if(modifyPane.isVisible()){
                modifyRoom();
            }else{
                //deleteRoom();
            }
        });

        ObservableList colList=roomTable.getColumns();
        TableColumn[] cols=new TableColumn[colList.size()];
        for(int i=0;i<cols.length;i++) {
            cols[i]=(TableColumn) colList.get(i);
        }
        ;
        cols[0].setCellValueFactory(new PropertyValueFactory<>("type"));
        cols[1].setCellValueFactory(new PropertyValueFactory<>("price"));
        cols[2].setCellValueFactory(new PropertyValueFactory<>("totalRoomNum"));
        cols[3].setCellValueFactory(new PropertyValueFactory<>("spareRoomNum"));
        showRoomList();

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
        data.add(new Room(getRoomVO()));
        return ResultMessage.SUCCESS;
        //return hotelManagerController.modifyRoomInfo(HotelManageMainController.hotelVO.getId(),roomToAdd);
    }
    public ResultMessage modifyRoom(){
        RoomVO roomToModify = getRoomVO();
        return hotelManagerController.modifyRoomInfo(HotelManageMainController.hotelVO.getId(),roomToModify);
    }
    //逻辑实现的数据展示部分
    public void showRoomList(){

        //ArrayList<RoomVO> roomList=HotelManageMainController.hotelVO.getRoomList();
        ArrayList<RoomVO> roomList=new ArrayList<RoomVO>();
        data= FXCollections.observableArrayList();
        for(int i=0;i<roomList.size();i++) {
            data.add(new Room(roomList.get(i)));
        }
        roomTable.setItems(data);
    }
    private void addRoomToTable(){
        data.add(new Room(getRoomVO()));
    }
    public static class Room{
        private final SimpleStringProperty type;
        private final SimpleDoubleProperty price;
        private final SimpleIntegerProperty totalRoomNum;
        private final SimpleIntegerProperty spareRoomNum;

        private Room(RoomVO vo){
            type=new SimpleStringProperty(vo.getType().toString());
            price = new SimpleDoubleProperty(vo.getPrice());
            totalRoomNum = new SimpleIntegerProperty(vo.getTotalRoomNum());
            spareRoomNum=new SimpleIntegerProperty(vo.getSpareRoomNum());
        }
        public String getType(){
            return type.get();
        }
        public void setType(RoomType type){
            this.type.set(type.toString());
        }
        public Double getPrice(){
            return price.get();
        }

        public void setPrice(double price) {
            this.price.set(price);
        }

        public int getTotalRoomNum(){
            return totalRoomNum.get();
        }
        public void setTotalRoomNum(int i){
            this.totalRoomNum.set(i);
        }
        public int getSpareRoomNum(){
            return spareRoomNum.get();
        }

        public void setSpareRoomNum(int i) {
            spareRoomNum.set(i);
        }
    }
    public static class Type{
        private SimpleStringProperty name;
        private Type(RoomType type){
            this.name=new SimpleStringProperty(type.toString());
        }
    }
}

