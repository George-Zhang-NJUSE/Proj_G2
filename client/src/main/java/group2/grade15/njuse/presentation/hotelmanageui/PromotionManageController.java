package group2.grade15.njuse.presentation.hotelmanageui;

import group2.grade15.njuse.bl.hotelmanagerbl.HotelManagerController;
import group2.grade15.njuse.bl.hotelpromotionbl.HotelPromotionController;
import group2.grade15.njuse.bl.orderbl.OrderController;
import group2.grade15.njuse.bl.promotionfactory.HotelPromotionBL;
import group2.grade15.njuse.blservice.HotelManagerServ;
import group2.grade15.njuse.blservice.HotelPromotionServ;
import group2.grade15.njuse.blservice.OrderServ;
import group2.grade15.njuse.presentation.myanimation.Fade;
import group2.grade15.njuse.presentation.mycontrol.CustomeButton;
import group2.grade15.njuse.utility.PromotionState;
import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.vo.HotelPromotionVO;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

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
    private Label check;
    @FXML
    private Label cancel;
    @FXML
    private TableView activatedList;
    @FXML
    private TableView unactivatedList;
    @FXML
    private Tab activatedTab;
    @FXML
    private Tab unactivatedTab;
    @FXML
    private Pane opPane;
    @FXML
    private Pane checkPane;


    //逻辑部分
    private AddPromotionController addPromotionController;
    private ModifyPromotionController modifyPromotionController;
    private Boolean activatedMode=false;

    private HotelManagerServ hotelManagerController;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CustomeButton.implButton(changeStateButton, "file:client/src/main/res/hotelmanage/play");
        CustomeButton.implButton(modifyButton, "file:client/src/main/res/hotelmanage/modify");
        CustomeButton.implButton(deleteButton,"file:client/src/main/res/hotelmanage/delete");
        CustomeButton.implButton(addButton,"file:client/src/main/res/hotelmanage/add");
        CustomeButton.implButton(check,"file:client/src/main/res/hotelmanage/Check");
        CustomeButton.implButton(cancel,"file:client/src/main/res/hotelmanage/Cancel");
        unactivatedTab.setOnSelectionChanged((Event e)->{
            switchToUnactivated();
        });
        activatedTab.setOnSelectionChanged((Event e)->{
            switchToActivated();
        });

    }
    public void setService(HotelManagerServ service){
        this.hotelManagerController=service;
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
            checkPane.setVisible(true);
            Fade cin = new Fade(checkPane, 200, true);
            cin.play();
            FXMLLoader lodar = new FXMLLoader(new URL("file:client/src/main/java/group2/grade15/njuse/presentation/hotelmanageui/AddPromotion.fxml"));
            addPromotionController=lodar.getController();
            opPane.getChildren().clear();
            opPane.getChildren().add(lodar.load());
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
            opPane.setVisible(true);
            checkPane.setVisible(true);
            Fade cin = new Fade(checkPane, 200, true);
            cin.play();
            FXMLLoader lodar = new FXMLLoader(new URL("file:client/src/main/java/group2/grade15/njuse/presentation/hotelmanageui/DeletePromotionCheck.fxml"));

            opPane.getChildren().clear();
            opPane.getChildren().add(lodar.load());
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
            checkPane.setVisible(true);
            Fade cin = new Fade(checkPane, 200, true);
            cin.play();
            FXMLLoader lodar = new FXMLLoader(new URL("file:client/src/main/java/group2/grade15/njuse/presentation/hotelmanageui/ModifyPromotion.fxml"));
            modifyPromotionController=lodar.getController();
            opPane.getChildren().clear();
            opPane.getChildren().add(lodar.load());
            Fade in = new Fade(opPane, 200, true);
            in.play();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void closeOpPane(){
        checkPane.setVisible(false);
        opPane.setVisible(false);
    }



    //逻辑数据采集部分
    //TODO test this function.
    private HotelPromotionVO getSelectedPromotion(){
        if(activatedMode){
            int index=activatedList.getSelectionModel().getSelectedIndex();
            ObservableList<HotelPromotionVO> list=activatedList.getItems();
            return list.get(index);
        }else{
            int index=unactivatedList.getSelectionModel().getSelectedIndex();
            ObservableList<HotelPromotionVO> list= activatedList.getItems();
            return list.get(index);
        }
    }

    //逻辑实现部分
    public ResultMessage changeState(){
        if(activatedMode){
            return hotelManagerController.stopHotelPromotion(getSelectedPromotion());
        }else{
            return hotelManagerController.activateHotelPromotion(getSelectedPromotion());
        }
    }

    public ResultMessage addPromotion(){
        HotelPromotionVO promotionToAdd=addPromotionController.getVO();
        return hotelManagerController.createHotelPromotion(promotionToAdd);
    }
    public ResultMessage modifyPromotion(){
        HotelPromotionVO promotionToModify=modifyPromotionController.getVO();
        return hotelManagerController.modifyHotelPromotion(promotionToModify);
    }
    public ResultMessage deletePromotion(){
        HotelPromotionVO promotionToDelete=getSelectedPromotion();
        return hotelManagerController.deleteHotelPromotion(promotionToDelete);
    }
    public ResultMessage activatePromotion(){
        HotelPromotionVO promotionToActivate=getSelectedPromotion();
        return hotelManagerController.activateHotelPromotion(promotionToActivate);
    }


}
