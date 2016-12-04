package group2.grade15.njuse.presentation.hotelmanageui;

import group2.grade15.njuse.presentation.myanimation.Fade;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by luoy on 2016/12/2.
 */
public class HotelManageMainController implements Initializable {
    @FXML
    private FlowPane infoTag;
    @FXML
    private FlowPane orderManageTag;
    @FXML
    private FlowPane roomManageTag;
    @FXML
    private FlowPane promotionManageTag;
    @FXML
    private Rectangle infoRec;
    @FXML
    private Rectangle orderManageRec;
    @FXML
    private Rectangle roomManageRec;
    @FXML
    private Rectangle promotionManageRec;
    @FXML
    private Pane opPane;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addSwither(infoTag);
        addSwither(orderManageTag);
        addSwither(roomManageTag);
        addSwither(promotionManageTag);

    }
    @FXML
    private boolean toInfo(){
        if (infoRec.isVisible())
            return true;
        infoRec.setVisible(true);
        orderManageRec.setVisible(false);
        roomManageRec.setVisible(false);
        promotionManageRec.setVisible(false);
        try{
            Fade out = new Fade(opPane, 500, false);
            out.play();
            FXMLLoader infoLoader=new FXMLLoader(new URL("file:client/src/main/java/group2/grade15/njuse/presentation/hotelmanageui/HotelInfo.fxml"));
            opPane.getChildren().clear();
            opPane.getChildren().add(infoLoader.load());
            Fade in=new Fade(opPane,500,true);
            in.play();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
    @FXML
    private boolean toOrderManage(){
        if (orderManageRec.isVisible())
            return true;
        infoRec.setVisible(false);
        orderManageRec.setVisible(true);
        roomManageRec.setVisible(false);
        promotionManageRec.setVisible(false);
        try{
            Fade out = new Fade(opPane, 500, false);
            out.play();
            FXMLLoader infoLoader=new FXMLLoader(new URL("file:client/src/main/java/group2/grade15/njuse/presentation/hotelmanageui/OrderManage.fxml"));
            opPane.getChildren().clear();
            opPane.getChildren().add(infoLoader.load());
            Fade in=new Fade(opPane,500,true);
            in.play();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
    @FXML
    private boolean toRoomManage(){
        if (roomManageRec.isVisible())
            return true;
        infoRec.setVisible(false);
        orderManageRec.setVisible(false);
        roomManageRec.setVisible(true);
        promotionManageRec.setVisible(false);
        try{
            Fade out = new Fade(opPane, 500, false);
            out.play();
            FXMLLoader infoLoader=new FXMLLoader(new URL("file:client/src/main/java/group2/grade15/njuse/presentation/hotelmanageui/RoomManage.fxml"));
            opPane.getChildren().clear();
            opPane.getChildren().add(infoLoader.load());
            Fade in=new Fade(opPane,500,true);
            in.play();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
    @FXML
    private boolean toPromotionManage(){
        if(promotionManageRec.isVisible())
            return true;
        infoRec.setVisible(false);
        orderManageRec.setVisible(false);
        roomManageRec.setVisible(false);
        promotionManageRec.setVisible(true);
        try{
            Fade out = new Fade(opPane, 500, false);
            out.play();
            FXMLLoader infoLoader=new FXMLLoader(new URL("file:client/src/main/java/group2/grade15/njuse/presentation/hotelmanageui/PromotionManage.fxml"));
            opPane.getChildren().clear();
            opPane.getChildren().add(infoLoader.load());
            Fade in=new Fade(opPane,500,true);
            in.play();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
    public void addSwither(FlowPane pane){
        pane.setOnMouseEntered((MouseEvent e)->{
            pane.setStyle("-fx-background-color: rgb(230,230,230)");
        });
        pane.setOnMouseExited((MouseEvent e)->{
            pane.setStyle("-fx-background-color: inherit");
        });
        pane.setOnMousePressed((MouseEvent e)->{
            pane.setStyle("-fx-background-color: rgb(210,210,210)");
        });
        pane.setOnMouseReleased((MouseEvent e)->{
            pane.setStyle("-fx-background-color: rgb(230,230,230)");
        });
    }
}
