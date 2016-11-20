package group2.grade15.njuse.presentation.webadminui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * Created by ALIENWARE-PC on 2016/11/19.
 */
public class WebAdminController {
    @FXML
    private Label messageButton;
    @FXML
    private Label settingButton;
    @FXML
    private ImageView messageImage;
    @FXML
    private ImageView settingImage;
    @FXML
    private ImageView accountImage;
    @FXML
    private ImageView hotelImage;


    //messageButton的按钮实现
    @FXML
    public void message_movein(MouseEvent event){
        messageImage.setImage(new Image("file:src/main/res/webadmin/message_movein.png"));
    }
    @FXML
    public void message_moveout(MouseEvent event){
        messageImage.setImage(new Image("file:src/main/res/webadmin/message.png"));
    }
    @FXML
    public void message_press(MouseEvent event){
        messageImage.setImage(new Image("file:src/main/res/webadmin/message_clicked.png"));
    }
    @FXML
    public void message_release(MouseEvent event){
        messageImage.setImage(new Image("file:src/main/res/webadmin/message_movein.png"));
    }



    //settingButton的按钮实现
    @FXML
    public void setting_movein(MouseEvent event){
        settingImage.setImage(new Image("file:src/main/res/webadmin/setting_movein.png"));
    }

    @FXML
    public void setting_moveout(MouseEvent event){
        settingImage.setImage(new Image("file:src/main/res/webadmin/setting.png"));
    }
    @FXML
    public void setting_press(MouseEvent event){
        settingImage.setImage(new Image("file:src/main/res/webadmin/setting_press.png"));
    }
    @FXML
    public void setting_release(MouseEvent event){
        settingImage.setImage(new Image("file:src/main/res/webadmin/setting_movein.png"));
    }

    //AccountButton的按钮实现
    @FXML
    public void Account_movein(MouseEvent event){
        accountImage.setImage(new Image("file:src/main/res/webadmin/Account_movein.png"));
    }

    @FXML
    public void Account_moveout(MouseEvent event){
        accountImage.setImage(new Image("file:src/main/res/webadmin/Account.png"));
    }
    @FXML
    public void Account_press(MouseEvent event){
        accountImage.setImage(new Image("file:src/main/res/webadmin/Account_press.png"));
    }
    @FXML
    public void Account_release(MouseEvent event){
        accountImage.setImage(new Image("file:src/main/res/webadmin/Account_movein.png"));
    }

    //HotelButton的按钮实现
    @FXML
    public void Hotel_movein(MouseEvent event){
        hotelImage.setImage(new Image("file:src/main/res/webadmin/Hotel_movein.png"));
    }

    @FXML
    public void Hotel_moveout(MouseEvent event){
        hotelImage.setImage(new Image("file:src/main/res/webadmin/Hotel.png"));
    }
    @FXML
    public void Hotel_press(MouseEvent event){
        hotelImage.setImage(new Image("file:src/main/res/webadmin/Hotel_press.png"));
    }
    @FXML
    public void Hotel_release(MouseEvent event){
        hotelImage.setImage(new Image("file:src/main/res/webadmin/Hotel_movein.png"));
    }

}
