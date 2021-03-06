package group2.grade15.njuse.presentation.webmarketerui;

import group2.grade15.njuse.bl.webmarketerbl.WebMarketerController;
import group2.grade15.njuse.blservice.WebMarketerServ;
import group2.grade15.njuse.presentation.myanimation.Fade;
import group2.grade15.njuse.presentation.mycontrol.CustomeButton;
import group2.grade15.njuse.utility.PromotionState;
import group2.grade15.njuse.utility.WebPromotionType;
import group2.grade15.njuse.vo.HotelPromotionVO;
import group2.grade15.njuse.vo.WebPromotionVO;
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
import java.rmi.RemoteException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Observable;
import java.util.ResourceBundle;

import static group2.grade15.njuse.presentation.webmarketerui.WebMarketerMainController.webMarketerService;

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
    private Label message;
    @FXML
    private Label refresh;

    private boolean inActivated=false;


    private String[] contents = {
            "id","name","state","discount","type","level","address","startDate","endDate"
    };

    private ObservableList<Promotion> activatedListData;
    private ObservableList<Promotion> unactivatedListData;

    public WebMarketerMainController webMarketerMainController;
    public AddPromotionController addPromotionController;
    public ModifyPromotionController modifyPromotionController;
    public DeletePromotionCheckController deletePromotionCheckController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CustomeButton.implButton(changeStateButton, "file:client/src/main/res/webmarketer/play");
        CustomeButton.implButton(modifyButton, "file:client/src/main/res/webmarketer/modify");
        CustomeButton.implButton(deleteButton,"file:client/src/main/res/webmarketer/delete");
        CustomeButton.implButton(addButton,"file:client/src/main/res/webmarketer/add");
        CustomeButton.implButton(refresh, "file:client/src/main/res/button/refresh");

        ObservableList a =activatedList.getColumns();
        ObservableList u = unactivatedList.getColumns();

        for(int i=0;i<a.size();i++){
            ((TableColumn)a.get(i)).setCellValueFactory(new PropertyValueFactory<>(contents[i]));
            ((TableColumn)u.get(i)).setCellValueFactory(new PropertyValueFactory<>(contents[i]));
        }

        unactivatedTab.setOnSelectionChanged((Event e)->{
            switchToUnactivated();
        });
        activatedTab.setOnSelectionChanged((Event e)->{
            switchToActivated();
        });
        unactivatedListData= FXCollections.observableArrayList();
        activatedListData=FXCollections.observableArrayList();
        unactivatedList.setItems(unactivatedListData);
        activatedList.setItems(activatedListData);

        activatedList.setOnMouseClicked((MouseEvent e)->{
            showPromotion();
        });

        unactivatedList.setOnMouseClicked((MouseEvent e)->{
            showPromotion();
        });
        changeStateButton.setOnMouseClicked((MouseEvent e)->{
                if(inActivated){
                    stopPromotion();
                }else{
                    activatePromotion();
                }
        });
        showAllPromotion();
    }
    public void showAllPromotion(){
        try {
            ArrayList<WebPromotionVO> list = webMarketerService.getWebPromotionList().getWebPromotionList();
            activatedListData.clear();
            unactivatedListData.clear();
            for (int i = 0; i < list.size(); i++) {
                WebPromotionVO vo = list.get(i);
                if (vo.getState() == PromotionState.start)
                    activatedListData.add(new Promotion(vo));
                else
                    unactivatedListData.add(new Promotion(vo));
            }
        } catch (NullPointerException e) {
            return;
        }
    }
    public void switchToActivated(){
        changeStateButton.setText("中止");
        inActivated=true;
        ImageView a=(ImageView) changeStateButton.getGraphic();
        a.setImage(new Image("file:client/src/main/res/webmarketer/stop.png"));
        CustomeButton.implButton(changeStateButton, "file:client/src/main/res/webmarketer/stop");
    }
    public void switchToUnactivated(){
        changeStateButton.setText("激活");
        inActivated=false;
        ImageView a=(ImageView) changeStateButton.getGraphic();
        a.setImage(new Image("file:client/src/main/res/webmarketer/play.png"));
        CustomeButton.implButton(changeStateButton, "file:client/src/main/res/webmarketer/play");
    }
    public void toAdd(){
        try {
            opPane.setVisible(true);
            FXMLLoader lodar = new FXMLLoader(new URL("file:client/src/main/res/fxml/webmarketer/AddPromotion.fxml"));
            opPane.getChildren().clear();
            opPane.getChildren().add(lodar.load());
            ((AddPromotionController)lodar.getController()).promotionManageController=this;
            Fade in = new Fade(opPane, 200, true);
            in.play();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void toDelete(){
        try {
            Fade out = new Fade(opPane, 200, false);
            out.play();
            opPane.setVisible(true);
            FXMLLoader lodar = new FXMLLoader(new URL("file:client/src/main/res/fxml/webmarketer/DeletePromotionCheck.fxml"));
            opPane.getChildren().clear();
            opPane.getChildren().add(lodar.load());
            ((DeletePromotionCheckController)lodar.getController()).promotionManageController=this;
            deletePromotionCheckController=lodar.getController();
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
            Fade out = new Fade(opPane, 200, false);
            out.play();
            opPane.setVisible(true);
            FXMLLoader lodar = new FXMLLoader(new URL("file:client/src/main/res/fxml/webmarketer/ModifyPromotion.fxml"));
            opPane.getChildren().clear();
            opPane.getChildren().add(lodar.load());
            ((ModifyPromotionController)lodar.getController()).promotionManageController=this;
            modifyPromotionController=lodar.getController();
            Fade in = new Fade(opPane, 200, true);
            in.play();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void back(){
        opPane.setVisible(false);
    }


    //逻辑与数据实现
    private void showPromotion(){
        try{
            toModify();
            Promotion promotion=getSelectedPromotion();
            modifyPromotionController.showPromotion(promotion);

        }catch (ArrayIndexOutOfBoundsException e2){
            return;
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void setModify(){
        modifyPromotionController.setEditable(true);
        modifyPromotionController.showCheck(true);
    }
    public void removePromotionFromList(boolean isFromActivatedList){
        if (isFromActivatedList){
            activatedListData.remove(activatedList.getSelectionModel().getSelectedIndex());
        }else{
            unactivatedListData.remove(unactivatedList.getSelectionModel().getSelectedIndex());
        }
    }
    public Promotion getSelectedPromotion(){
        TableView<Promotion> temp;
        int index;
        if(activatedTab.isSelected()){
            temp=activatedList;
            index=temp.getSelectionModel().getSelectedIndex();
        }else{
            temp=unactivatedList;
            index=temp.getSelectionModel().getSelectedIndex();
        }
        return temp.getItems().get(index);
    }
    public WebPromotionVO promotionToVO(Promotion promotion){
        WebPromotionType typeOfVO;
        Date sD,eD;
        PromotionState state;
        switch(promotion.getType()){
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
        switch(promotion.getState()){
            case"start":
                state=PromotionState.start;
                break;
            case"stop":
                state=PromotionState.stop;
                break;
            default:
                state=PromotionState.unlaunched;
                break;
        }
        try {
            String[] s = promotion.getStartDate().split("-");
            String[] e = promotion.getEndDate().split("-");
            sD = new Date(Integer.parseInt(s[0]) - 1900, Integer.parseInt(s[1]) - 1, Integer.parseInt(s[2]));
            eD = new Date(Integer.parseInt(e[0]) - 1900, Integer.parseInt(e[1]) - 1, Integer.parseInt(e[2]));
        }catch(Exception e){
            sD=new Date(0,0,0);
            eD=new Date(0,0,0);
        }
        WebPromotionVO vo=new WebPromotionVO(
                promotion.getId(),
                typeOfVO,
                sD,
                eD,
                promotion.getAddress(),
                promotion.getLevel(),
                promotion.getDiscount(),
                promotion.getName(),
                state
        );
        return vo;
    }
    public void addPromotion(WebPromotionVO vo){
        try {
            switch(webMarketerService.createWebPromotion(vo)){
                case SUCCESS:
                    webMarketerMainController.alert("添加成功");
                    showAllPromotion();
                    break;
                case CONNECTION_EXCEPTION:
                    webMarketerMainController.alert("未连接到服务器");
                    break;

            }

        }catch (NullPointerException e){
            webMarketerMainController.alert("添加失败");
        }
    }

    public void getPromotionFromID(int id) {
        ArrayList<WebPromotionVO> list=webMarketerService.getWebPromotionList().getWebPromotionList();
        for(int i=0;i<list.size();i++) {

        }
    }
    public void modifyPromotion(WebPromotionVO vo){
        try {
            if (vo.getState() == PromotionState.start) {
                webMarketerMainController.alert("不能更改适用中的促销策略");
                return;
            }
            switch (webMarketerService.modifyWebPromotion(vo)){
                case SUCCESS:
                    webMarketerMainController.alert("操作成功");
                    showAllPromotion();
                    modifyPromotionController.setEditable(false);
                    break;
                case CONNECTION_EXCEPTION:
                    webMarketerMainController.alert("未连接到服务器");
                    break;
            }

        }catch (Exception e){
            webMarketerMainController.alert("操作未成功");
        }
    }
    public void activatePromotion(){
        try {
            Promotion promotion = getSelectedPromotion();
            if (promotion.getState() == "start"){
                webMarketerMainController.alert("已经被激活");

                return;
            }

            promotion.setState("start");
            WebPromotionVO vo = promotionToVO(promotion);
            switch (webMarketerService.changeState(vo)){
                case SUCCESS:
                    webMarketerMainController.alert("已激活");
                    showAllPromotion();
                    break;
                case CONNECTION_EXCEPTION:
                    webMarketerMainController.alert("未连接到服务器");
                    break;
                case FAILED:
                    webMarketerMainController.alert("激活失败");
                    break;
            }
        }catch (Exception e){
            webMarketerMainController.alert("操作未成功");
        }
    }
    public void stopPromotion(){
        try {
            Promotion promotion = getSelectedPromotion();
            if (promotion.getState() == "stop"){
                return;
            }
            promotion.setState("stop");
            WebPromotionVO vo = promotionToVO(promotion);
            webMarketerService.changeState(vo);
            webMarketerMainController.alert("操作成功");

            showAllPromotion();
        } catch (Exception e) {
            webMarketerMainController.alert("操作未成功");
        }
    }
    public void deletePromotion(){
        Promotion promotion=getSelectedPromotion();
        System.out.println("promotionid: " + promotion.getId());
        if(promotion.getState()=="start"){
            webMarketerMainController.alert("激活中的促销策略不能删除");
            return;
        }
        switch (webMarketerService.deleteWebPromotion(promotion.getId())) {
            case SUCCESS:
                webMarketerMainController.alert("已删除促销策略");
                showAllPromotion();
                back();
                break;
            case CONNECTION_EXCEPTION:
                webMarketerMainController.alert("未连接到服务器");
                break;
            case FAILED:
                webMarketerMainController.alert("删除失败");
                break;
        }

    }

    public static class Promotion{
        private final SimpleStringProperty name;
        private final SimpleIntegerProperty id;
        private final SimpleIntegerProperty level;
        private final SimpleStringProperty state;
        private final SimpleStringProperty startDate;
        private final SimpleStringProperty endDate;
        private final SimpleDoubleProperty discount;
        private final SimpleStringProperty type;
        private final SimpleStringProperty address;

        private Promotion(WebPromotionVO vo){
            name=new SimpleStringProperty(vo.getName());
            id = new SimpleIntegerProperty(vo.getPromotionID());
            state=new SimpleStringProperty(vo.getState().toString());
            startDate = new SimpleStringProperty(vo.getStart().toString());
            endDate = new SimpleStringProperty(vo.getEnd().toString());
            discount=new SimpleDoubleProperty(vo.getDiscount());
            switch(vo.getType()){
                case AreaWeb:
                    type=new SimpleStringProperty("特定商区优惠");
                    break;
                case TimeWeb:
                    type = new SimpleStringProperty("特定时间优惠");
                    break;
                default:
                    type = new SimpleStringProperty("会员优惠");
                    break;
            }
            level=new SimpleIntegerProperty(vo.getLevel());
            address=new SimpleStringProperty(vo.getAddress().toString());
        }
        public String getName(){
            return name.get();
        }
        public int getId(){
            return id.get();
        }
        public int getLevel(){
            return level.get();
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
        public String getAddress(){
            return address.get();
        }

        public void setState(String string) {
            state.set(string);
        }
    }
}
