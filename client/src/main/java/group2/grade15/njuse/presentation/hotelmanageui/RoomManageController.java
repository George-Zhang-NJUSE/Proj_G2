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
    private Label message;

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
    public HotelManageMainController hotelManageMainController;
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
        roomTable.setOnMouseClicked((MouseEvent e)->{
            toModify();
            showRoomOnModify();
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

    public RoomVO gatherVO(){
        return null;
    }
    /**
     * 从表格中获取选中的RoomVO
     * @return RoomVO
     */
    public RoomVO getRoomFromList(){
        try{
            int index=roomTable.getSelectionModel().getSelectedIndex();
            Room room = roomTable.getItems().get(index);
            return toRoomVO(room);
        }catch (ArrayIndexOutOfBoundsException e){
            return null;
        }
    }
    public RoomVO toRoomVO(Room room){
        if (room == null) {
            return null;
        }
        RoomType a;
        switch (room.getType()) {
            case "大床房":
                a=RoomType.bigSingleBed;
                break;
            case "标准间":
                a=RoomType.stadardDoubleBed;
                break;
            case "套房":
                a=RoomType.suiteRoom;
                break;
            default:
                a=RoomType.all;
                break;
        }

        RoomVO vo = new RoomVO(a, room.getPrice(), room.getTotalRoomNum(), room.getSpareRoomNum());
        return vo;
    }
    public void addRoom() {
        RoomVO roomToAdd = getRoomVO();
        try {
            switch(hotelManagerController.modifyRoomInfo(HotelManageMainController.hotelVO.getId(), roomToAdd)){
                case SUCCESS:
                    message.setText("添加成功");
                    hotelManageMainController.upDateHotelVO();
                    showRoomList();
                    break;
                case CONNECTION_EXCEPTION:
                    message.setText("未连接到服务器");
                    break;
                case FAILED:
                    message.setText("添加失败");
                    break;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void  modifyRoom(){
        int index=roomTable.getSelectionModel().getSelectedIndex();
        Room room= roomTable.getItems().get(index);
        room.setPrice(Double.parseDouble(priceM.getText()));
        room.setSpareRoomNum(Integer.parseInt(restM.getText()));
        room.setTotalRoomNum(Integer.parseInt(countM.getText()));
        RoomVO roomToModify = new RoomVO(typeM.getValue(), room.getPrice(), room.getTotalRoomNum(), room.getSpareRoomNum());
        try {
            switch (hotelManagerController.modifyRoomInfo(HotelManageMainController.hotelVO.getId(),
                    roomToModify)) {
                case SUCCESS:
                    message.setText("修改成功");
                    hotelManageMainController.upDateHotelVO();
                    showRoomList();
                    break;
                case CONNECTION_EXCEPTION:
                    message.setText("未连接到服务器");
                    break;
                case FAILED:
                    message.setText("修改失败");
                    break;
            }
        }catch (Exception e){
            e.printStackTrace();
            message.setText("修改失败");
        }
    }
    //逻辑实现的数据展示部分
    public void showRoomOnModify(){
        RoomVO vo=getRoomFromList();
        if (vo == null) {
            return;
        }
        typeM.setItems(FXCollections.observableArrayList(vo.getType()));
        countM.setText(String.valueOf(vo.getTotalRoomNum()));
        priceM.setText(String.valueOf(vo.getPrice()));
        restM.setText(String.valueOf(vo.getSpareRoomNum()));
        typeM.getEditor().setText(vo.getType().toString());

    }
    public void showRoomList(){
        ObservableList<Room> temp=null;
        roomTable.setItems(temp);
        data=FXCollections.observableArrayList();
        data.remove(0,data.size());

        ArrayList<RoomVO> roomList=HotelManageMainController.hotelVO.getRoomList();
        for(int i=0;i<roomList.size();i++) {
            data.add(new Room(roomList.get(i)));
        }
        roomTable.setItems(data);
    }

    public static class Room{
        private final SimpleStringProperty type;
        private final SimpleDoubleProperty price;
        private final SimpleIntegerProperty totalRoomNum;
        private final SimpleIntegerProperty spareRoomNum;

        private Room(RoomVO vo){
            switch(vo.getType()){
                case bigSingleBed:
                    type = new SimpleStringProperty("大床房");
                    break;
                case stadardDoubleBed:
                    type = new SimpleStringProperty("双人间");
                    break;
                case suiteRoom:
                    type = new SimpleStringProperty("套房");
                    break;
                default:
                    type = new SimpleStringProperty("无定义");
                    break;
            }
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
            switch(type){
                case bigSingleBed:
                    name = new SimpleStringProperty("大床房");
                    break;
                case stadardDoubleBed:
                    name = new SimpleStringProperty("双人间");
                    break;
                case suiteRoom:
                    name = new SimpleStringProperty("套房");
                    break;
                default:
                    name = new SimpleStringProperty("无定义");
                    break;
            }
        }
    }
}


