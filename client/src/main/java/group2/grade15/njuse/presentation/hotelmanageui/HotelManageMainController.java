package group2.grade15.njuse.presentation.hotelmanageui;

import group2.grade15.njuse.bl.hotelmanagerbl.HotelManagerController;
import group2.grade15.njuse.bl.orderbl.OrderController;
import group2.grade15.njuse.bl.orderbl.OrderList;
import group2.grade15.njuse.blservice.HotelManagerServ;
import group2.grade15.njuse.blservice.OrderListServ;
import group2.grade15.njuse.blservice.OrderServ;
import group2.grade15.njuse.presentation.myanimation.Fade;
import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.vo.HotelManagerVO;
import group2.grade15.njuse.vo.HotelVO;
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
    private static int hotelID;
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

    //Controller的持有
    HotelInfoController hotelInfoController;

    //软件运行时的存储信息
    public static HotelVO hotelVO;
    public static HotelManagerVO hotelManagerVO;

    //接口的实例化
    public static HotelManagerServ hotelManagerController=new HotelManagerController();
    public static OrderListServ hotelOrderController=new OrderController();

    //controller的初始化
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addSwither(infoTag);
        addSwither(orderManageTag);
        addSwither(roomManageTag);
        addSwither(promotionManageTag);

    }
    public void setHotelManagerVO(HotelManagerVO vo){
        hotelManagerVO=vo;
        hotelVO=hotelManagerController.getHotelInfo(vo.getHotelID());
    }

    //用于界面跳转的方法
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
            FXMLLoader infoLoader=new FXMLLoader(new URL("file:client/src/main/res/fxml/hotelmanager/HotelInfo.fxml"));
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
    //逻辑数据处理的方法：

    /**
     * @param hotelVO
     * @param hotelManagerVO
     * 将酒店工作人员的vo和与之绑定的酒店的vo载入的controller中
     */
    public void initData(HotelVO hotelVO, HotelManagerVO hotelManagerVO){
        this.hotelVO=hotelVO;
        this.hotelManagerVO=hotelManagerVO;

    }




    //轮子方法：

    //实现边栏按钮的轮子方法
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
