package group2.grade15.njuse.presentation.hotelmanageui;

import group2.grade15.njuse.bl.hotelbl.Hotel;
import group2.grade15.njuse.presentation.myanimation.Fade;
import group2.grade15.njuse.presentation.mycontrol.CustomeButton;
import group2.grade15.njuse.utility.HotelPromotionType;
import group2.grade15.njuse.utility.PromotionState;
import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.vo.HotelManagerVO;
import group2.grade15.njuse.vo.HotelPromotionVO;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static group2.grade15.njuse.presentation.hotelmanageui.HotelManageMainController.hotelManagerController;
import static group2.grade15.njuse.presentation.hotelmanageui.HotelManageMainController.hotelVO;

/**
 * Created by ALIENWARE-PC on 2016/12/1.
 */
public class PromotionManageController implements Initializable{
    @FXML
    private Label changeStateButton;
    @FXML
    private Label modifyButton;
    @FXML
    private Label deleteButton;
    @FXML
    private Label addButton;

    @FXML
    private Label message;
    @FXML
    private TableView<Promotion> activatedList;
    @FXML
    private TableView<Promotion> unactivatedList;
    @FXML
    private Tab activatedTab;
    @FXML
    private Tab unactivatedTab;
    @FXML
    private Pane opPane;
    @FXML
    private Pane checkPane;

    private ObservableList<Promotion> unactivatedData,activatedData;


    //逻辑部分
    public AddPromotionController addPromotionController;
    public ModifyPromotionController modifyPromotionController;
    private Boolean activatedMode=false;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CustomeButton.implButton(changeStateButton, "file:client/src/main/res/hotelmanage/play");
        CustomeButton.implButton(modifyButton, "file:client/src/main/res/hotelmanage/modify");
        CustomeButton.implButton(deleteButton,"file:client/src/main/res/hotelmanage/delete");
        CustomeButton.implButton(addButton,"file:client/src/main/res/hotelmanage/add");
        unactivatedData= FXCollections.observableArrayList();
        activatedData=FXCollections.observableArrayList();


        unactivatedTab.setOnSelectionChanged((Event e)->{
            switchToUnactivated();
        });
        ObservableList uaCols=unactivatedList.getColumns();
        ((TableColumn)uaCols.get(0)).setCellValueFactory(new PropertyValueFactory<>("id"));
        ((TableColumn)uaCols.get(1)).setCellValueFactory(new PropertyValueFactory<>("name"));
        ((TableColumn)uaCols.get(2)).setCellValueFactory(new PropertyValueFactory<>("state"));
        ((TableColumn)uaCols.get(3)).setCellValueFactory(new PropertyValueFactory<>("type"));
        ((TableColumn)uaCols.get(4)).setCellValueFactory(new PropertyValueFactory<>("startDate"));
        ((TableColumn)uaCols.get(5)).setCellValueFactory(new PropertyValueFactory<>("endDate"));
        ((TableColumn)uaCols.get(6)).setCellValueFactory(new PropertyValueFactory<>("discount"));
        unactivatedList.setItems(unactivatedData);


        activatedTab.setOnSelectionChanged((Event e)->{
            switchToActivated();
        });
        ObservableList aCols=activatedList.getColumns();
        ((TableColumn)aCols.get(0)).setCellValueFactory(new PropertyValueFactory<>("id"));
        ((TableColumn)aCols.get(1)).setCellValueFactory(new PropertyValueFactory<>("name"));
        ((TableColumn)aCols.get(2)).setCellValueFactory(new PropertyValueFactory<>("state"));
        ((TableColumn)aCols.get(3)).setCellValueFactory(new PropertyValueFactory<>("type"));
        ((TableColumn)aCols.get(4)).setCellValueFactory(new PropertyValueFactory<>("startDate"));
        ((TableColumn)aCols.get(5)).setCellValueFactory(new PropertyValueFactory<>("endDate"));
        ((TableColumn)aCols.get(6)).setCellValueFactory(new PropertyValueFactory<>("discount"));
        activatedList.setItems(activatedData);

        activatedList.setOnMouseClicked((MouseEvent e)->{
            fetchPromotion();
        });
        unactivatedList.setOnMouseClicked((MouseEvent e)->{
            fetchPromotion();
        });
        showPromotionList();
    }

    //界面跳转控制逻辑
    public void switchToActivated(){
        activatedMode=true;
        changeStateButton.setText("中止");
        ImageView a=(ImageView) changeStateButton.getGraphic();
        a.setImage(new Image("file:client/src/main/res/hotelmanage/stop.png"));
        CustomeButton.implButton(changeStateButton, "file:client/src/main/res/hotelmanage/stop");
    }
    public void switchToUnactivated(){
        activatedMode=false;
        changeStateButton.setText("激活");
        ImageView a=(ImageView) changeStateButton.getGraphic();
        a.setImage(new Image("file:client/src/main/res/hotelmanage/play.png"));
        CustomeButton.implButton(changeStateButton, "file:client/src/main/res/hotelmanage/play");
    }
    public void toAdd(){
        try {
            opPane.setVisible(true);
            FXMLLoader lodar = new FXMLLoader(new URL("file:client/src/main/java/group2/grade15/njuse/presentation/hotelmanageui/AddPromotion.fxml"));


            opPane.getChildren().clear();
            opPane.getChildren().add(lodar.load());
            addPromotionController=lodar.getController();
            Fade in = new Fade(opPane, 200, true);
            in.play();
            addPromotionController.setMainController(this);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void toDelete(){
        try {
            opPane.setVisible(true);
            FXMLLoader lodar = new FXMLLoader(new URL("file:client/src/main/java/group2/grade15/njuse/presentation/hotelmanageui/DeletePromotionCheck.fxml"));

            opPane.getChildren().clear();
            opPane.getChildren().add(lodar.load());
            ((DeletePromotionCheckController)lodar.getController()).promotionManageController=this;
            Fade in = new Fade(opPane, 200, true);
            in.play();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void toModify(){
        try {
            opPane.setVisible(true);
            FXMLLoader lodar = new FXMLLoader(new URL("file:client/src/main/java/group2/grade15/njuse/presentation/hotelmanageui/ModifyPromotion.fxml"));
            modifyPromotionController=(ModifyPromotionController)lodar.getController();
            opPane.getChildren().clear();
            opPane.getChildren().add(lodar.load());
            ((ModifyPromotionController) lodar.getController()).promotionManageController=this;
            Fade in = new Fade(opPane, 200, true);
            in.play();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void closeOpPane(){
        opPane.setVisible(false);
    }



    //逻辑数据采集部分
    //TODO test this function.
    private HotelPromotionVO getSelectedPromotion(){
        if(activatedMode){
            int index=activatedList.getSelectionModel().getSelectedIndex();
            ObservableList<Promotion> list=activatedList.getItems();
            return makeVO(list.get(index));
        }else{
            int index=unactivatedList.getSelectionModel().getSelectedIndex();
            ObservableList<Promotion> list= activatedList.getItems();
            return makeVO(list.get(index));
        }
    }
    private HotelPromotionVO makeVO(Promotion promotion){
        int id=promotion.id.get();
        int hotelId= promotion.hotelId.get();
        String name=promotion.name.get();
        String sDate=promotion.startDate.get();
        String eDate=promotion.endDate.get();
        double discount=promotion.discount.get();
        HotelPromotionType type;
        switch(promotion.type.get()){
            case "BirthdayHotel":
                type=HotelPromotionType.BirthdayHotel;
                break;
            case "TimeHotel":
                type=HotelPromotionType.TimeHotel;
                break;
            case "MultipleHotel":
                type=HotelPromotionType.MultipleHotel;
                break;
            case "PartnerHotel":
                type=HotelPromotionType.PartnerHotel;
                break;
            default:
                type=null;
                break;
        }
        PromotionState state;
        switch (promotion.state.get()){
            case "start":
                state=PromotionState.start;
                break;
            case "stop":
                state=PromotionState.stop;
                break;
            case "unlaunched":
                state=PromotionState.unlaunched;
                break;
            default:
                state=null;
                break;
        }
        Date startDate = new Date(Long.parseLong(sDate));
        Date endDate = new Date(Long.parseLong(eDate));
        return new HotelPromotionVO(id,type,startDate,endDate,-1,discount,name,state,hotelId);
    }
    //逻辑展示部分
    public void showPromotionList(){
        ArrayList<HotelPromotionVO> list=hotelManagerController.getHotelPromotionList(hotelVO.getId()).getHotelPromotionList();
        for(int i=0;i<list.size();i++) {
            HotelPromotionVO vo = list.get(i);
            if(vo.getState()==PromotionState.start){
                activatedData.add(new Promotion(vo));
            }else{
                unactivatedData.add(new Promotion(vo));
            }
        }
    }
    public void addPromotionToList(HotelPromotionVO vo){
        if (activatedMode){
            activatedData.add(new Promotion(vo));
        }else{
            unactivatedData.add(new Promotion(vo));
        }
    }
    public void removeSelectedPromotionFromList(){
        if(activatedMode){
            int index=activatedList.getSelectionModel().getSelectedIndex();
            activatedData.remove(index);
        }else{
            int index=unactivatedList.getSelectionModel().getSelectedIndex();
            unactivatedData.remove(index);
        }

    }
    public void fetchPromotion(){
        HotelPromotionVO vo=getSelectedPromotion();
        toModify();
        modifyPromotionController.showPromotion(vo);

    }

    //逻辑实现部分
    public void setModify(){
        modifyPromotionController.setEditable(true);
    }
    public void changePromotionState(){
        if(activatedMode){


            if(ResultMessage.SUCCESS==hotelManagerController.stopHotelPromotion(getSelectedPromotion())){
                unactivatedData.add(new Promotion(getSelectedPromotion()));
                activatedData.remove(activatedList.getSelectionModel().getSelectedIndex());
                message.setText("操作成功");
            }else{
                message.setText("操作失败");
            }
        }else{
            if(ResultMessage.SUCCESS==hotelManagerController.activateHotelPromotion(getSelectedPromotion())){
                unactivatedData.add(new Promotion(getSelectedPromotion()));
                activatedData.remove(activatedList.getSelectionModel().getSelectedIndex());
                message.setText("操作成功");
            }else{
                message.setText("操作失败");
            }
        }
    }
//TODO 酒店管理新增促销
    public ResultMessage addPromotion(){
        HotelPromotionVO promotionToAdd=addPromotionController.getVO();
        unactivatedData.add(new Promotion(promotionToAdd));
        return hotelManagerController.createHotelPromotion(promotionToAdd);
        //return ResultMessage.SUCCESS;
    }
    //TODO 酒店管理修改促销

    public ResultMessage modifyPromotion(HotelPromotionVO vo){
        try {
            HotelPromotionVO promotionToModify = modifyPromotionController.getVO();

            ResultMessage re= hotelManagerController.modifyHotelPromotion(vo);
            message.setText("操作成功");
            return re;
        } catch (Exception e) {
            e.printStackTrace();
            message.setText("操作失败");
            return ResultMessage.FAILED;
        }
    }
    //TODO 酒店管理删除促销

    public ResultMessage deletePromotion(){
        HotelPromotionVO promotionToDelete=getSelectedPromotion();
        if(promotionToDelete.getState()==PromotionState.start){
            message.setText("不能删除已激活的促销策略");
            return ResultMessage.FAILED;
        }try {
            removePromotionFromList(promotionToDelete.getType().toString() == "start");
            ResultMessage re= hotelManagerController.deleteHotelPromotion(promotionToDelete);
            message.setText("操作成功");
            return re;
        }catch (Exception e){
            e.printStackTrace();
            message.setText("操作失败");
            return ResultMessage.FAILED;
        }
    }
    //TODO 酒店管理激活策略

    public ResultMessage activatePromotion(){
        HotelPromotionVO promotionToActivate=getSelectedPromotion();
        return hotelManagerController.activateHotelPromotion(promotionToActivate);
    }
    //TODO 酒店管理终止策略
    public ResultMessage stopPromotion(){
        HotelPromotionVO promotionToStop=getSelectedPromotion();
        return hotelManagerController.stopHotelPromotion(promotionToStop);
    }

    public void removePromotionFromList(boolean isFromActivatedList){
        if (isFromActivatedList){
            activatedData.remove(activatedList.getSelectionModel().getSelectedIndex());
        }else{
            unactivatedData.remove(unactivatedList.getSelectionModel().getSelectedIndex());
        }
    }
    public static class Promotion{
        private final SimpleStringProperty name;
        private final SimpleIntegerProperty id;
        private final SimpleIntegerProperty hotelId;
        private final SimpleStringProperty state;
        private final SimpleStringProperty startDate;
        private final SimpleStringProperty endDate;
        private final SimpleDoubleProperty discount;
        private final SimpleStringProperty type;

        private Promotion(HotelPromotionVO vo){
            name=new SimpleStringProperty(vo.getName());
            id = new SimpleIntegerProperty(vo.getPromotionID());
            state=new SimpleStringProperty(vo.getState().toString());
            if(vo.getType()==HotelPromotionType.TimeHotel){
                startDate = new SimpleStringProperty(vo.getStart().toString());
                endDate = new SimpleStringProperty(vo.getEnd().toString());
            }else{
                startDate=new SimpleStringProperty("none");
                endDate = new SimpleStringProperty("none");
            }
            discount=new SimpleDoubleProperty(vo.getDiscount());
            type=new SimpleStringProperty(vo.getType().toString());
            hotelId=new SimpleIntegerProperty(vo.getHotelID());//TODO
        }
        public String getName(){
            return name.get();
        }
        public int getId(){
            return id.get();
        }
        public int getHotelId(){
            return hotelId.get();
        }
        public String getState(){
            return state.get();
        }
        public String getStartDate(){
            return startDate.get();
        }
        public String getEndDate(){
            return endDate.get();
        }
        public double getDiscount(){
            return discount.get();
        }
        public String getType(){
            return type.get();
        }

    }
}
