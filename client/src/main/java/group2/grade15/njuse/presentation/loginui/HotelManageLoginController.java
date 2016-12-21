package group2.grade15.njuse.presentation.loginui;

import group2.grade15.njuse.bl.customerbl.CustomerController;
import group2.grade15.njuse.bl.hotelmanagerbl.HotelManagerController;
import group2.grade15.njuse.bl.loginbl.CustomerLoginImpl;
import group2.grade15.njuse.bl.loginbl.HotelManagerLoginImpl;
import group2.grade15.njuse.blservice.CustomerServ;
import group2.grade15.njuse.blservice.HotelManagerServ;
import group2.grade15.njuse.blservice.LoginControllerServ;
import group2.grade15.njuse.presentation.hotelmanageui.HotelManageMainController;
import group2.grade15.njuse.presentation.mycontrol.CustomeButton;
import group2.grade15.njuse.vo.HotelManagerVO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by ALIENWARE-PC on 2016/12/4.
 */
public class HotelManageLoginController implements Initializable{
    @FXML
    private Label login;
    @FXML
    private TextField accoutField;
    @FXML
    private TextField PWField;
    private Stage currentStage;

    private void login() {

        LoginControllerServ loginServ = new HotelManagerLoginImpl();
        HotelManagerServ hotelManagerServ = new HotelManagerController();

        String id = (accoutField.getText());
        String password = PWField.getText();

        //jumpToMain(hotelManagerServ.getInfo(Integer.parseInt(id)));


        switch (loginServ.login(id,password)){
            case SUCCESS:
                jumpToMain(hotelManagerServ.getInfo(Integer.parseInt(id)));
                break;
            case FAILED:
                Alert wrongPswAlert = new Alert(Alert.AlertType.ERROR, "密码错误!");
                wrongPswAlert.showAndWait();
                break;
            case NON_EXISTENT:
                Alert invalidAccountAlert = new Alert(Alert.AlertType.ERROR, "账号不存在!");
                invalidAccountAlert.showAndWait();
                break;
            case CONNECTION_EXCEPTION:
                Alert netErrorInfo = new Alert(Alert.AlertType.ERROR, "网络连接错误!");
                netErrorInfo.showAndWait();
                break;
        }

    }
    private void jumpToMain(HotelManagerVO vo){
        try{
            FXMLLoader loader=new FXMLLoader(new URL("file:client/src/main/res/fxml/hotelmanager/HotelManageMain.fxml"));
            Stage hotelManagerMainStage=new Stage();
            hotelManagerMainStage.setScene(new Scene(loader.load()));
            HotelManageMainController hotelManageMainController=loader.getController();
            hotelManageMainController.setHotelManagerVO(vo);
            hotelManageMainController.stage=hotelManagerMainStage;
            currentStage.close();
            hotelManagerMainStage.sizeToScene();
            hotelManagerMainStage.setResizable(false);
            hotelManagerMainStage.setTitle("酒店预订系统——酒店管理人员端");
            hotelManagerMainStage.show();
        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void setStage(Stage stage){
        currentStage=stage;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CustomeButton.implButton(login,"file:client/src/main/res/login/login");
        login.setOnMouseClicked((MouseEvent e)->{
            login();
        });
    }

}
