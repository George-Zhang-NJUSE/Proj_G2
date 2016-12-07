package group2.grade15.njuse.presentation.loginui;

import group2.grade15.njuse.presentation.mycontrol.CustomeButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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

    private Stage currentStage;

    private void jumpToMain(){
        try{
            FXMLLoader loader=new FXMLLoader(new URL("file:client/src/main/java/group2/grade15/njuse/presentation/hotelmanageui/HotelManageMain.fxml"));
            Stage webMarketerMainStage=new Stage();
            webMarketerMainStage.setScene(new Scene(loader.load()));
            currentStage.close();
            webMarketerMainStage.sizeToScene();
            webMarketerMainStage.setResizable(false);
            webMarketerMainStage.setTitle("酒店预订系统——网站营销人员端");
            webMarketerMainStage.show();
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
            jumpToMain();
        });
    }

}
