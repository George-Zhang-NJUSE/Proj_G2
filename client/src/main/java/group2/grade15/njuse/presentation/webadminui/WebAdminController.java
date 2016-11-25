package group2.grade15.njuse.presentation.webadminui;

import group2.grade15.njuse.presentation.myanimation.Fade;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

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
    @FXML
    private ImageView HMBack;
    @FXML
    private ImageView AMBack;
    @FXML
    private ImageView addAccount;
    @FXML
    private ImageView addHotel;
    @FXML
    private Label addAccountLabel;
    @FXML
    private GridPane accountManage;
    @FXML
    private GridPane hotelManage;
    @FXML
    private GridPane welcome;


    //messageButton的按钮实现
    @FXML
    public void message_movein(MouseEvent event) {
        messageImage.setImage(new Image("file:client/src/main/res/webadmin/message_movein.png"));
    }

    @FXML
    public void message_moveout(MouseEvent event) {
        messageImage.setImage(new Image("file:client/src/main/res/webadmin/message.png"));
    }

    @FXML
    public void message_press(MouseEvent event) {
        messageImage.setImage(new Image("file:client/src/main/res/webadmin/message_clicked.png"));
    }

    @FXML
    public void message_release(MouseEvent event) {
        messageImage.setImage(new Image("file:client/src/main/res/webadmin/message_movein.png"));
    }


    //settingButton的按钮实现
    @FXML
    public void setting_movein(MouseEvent event) {
        settingImage.setImage(new Image("file:client/src/main/res/webadmin/setting_movein.png"));
    }

    @FXML
    public void setting_moveout(MouseEvent event) {
        settingImage.setImage(new Image("file:client/src/main/res/webadmin/setting.png"));
    }

    @FXML
    public void setting_press(MouseEvent event) {
        settingImage.setImage(new Image("file:client/src/main/res/webadmin/setting_press.png"));
    }

    @FXML
    public void setting_release(MouseEvent event) {
        settingImage.setImage(new Image("file:client/src/main/res/webadmin/setting_movein.png"));
    }

    //AccountButton的按钮实现
    @FXML
    public void Account_movein(MouseEvent event) {
        accountImage.setImage(new Image("file:client/src/main/res/webadmin/Account_movein.png"));
    }

    @FXML
    public void Account_moveout(MouseEvent event) {
        accountImage.setImage(new Image("file:client/src/main/res/webadmin/Account.png"));
    }

    @FXML
    public void Account_press(MouseEvent event) {
        accountImage.setImage(new Image("file:client/src/main/res/webadmin/Account_press.png"));
    }

    @FXML
    public void Account_release(MouseEvent event) {
        accountImage.setImage(new Image("file:client/src/main/res/webadmin/Account_movein.png"));
    }
    @FXML
    public void Account_action(MouseEvent event){
        Fade amFade=new Fade(accountManage,400,true);
        amFade.play();
        Fade welFadeIn=new Fade(welcome,400,false);
        welFadeIn.play();
    }


    //HotelButton的按钮实现
    @FXML
    public void Hotel_movein(MouseEvent event) {
        hotelImage.setImage(new Image("file:client/src/main/res/webadmin/Hotel_movein.png"));
    }

    @FXML
    public void Hotel_moveout(MouseEvent event) {
        hotelImage.setImage(new Image("file:client/src/main/res/webadmin/Hotel.png"));
    }

    @FXML
    public void Hotel_press(MouseEvent event) {
        hotelImage.setImage(new Image("file:client/src/main/res/webadmin/Hotel_press.png"));
    }

    @FXML
    public void Hotel_release(MouseEvent event) {
        hotelImage.setImage(new Image("file:client/src/main/res/webadmin/Hotel_movein.png"));
    }
    @FXML
    public void Hotel_action(MouseEvent event){

        Fade hmFade=new Fade(hotelManage,400,true);
        hmFade.play();
        Fade welFadeIn=new Fade(welcome,400,false);
        welFadeIn.play();
    }

    //AMBack的按钮实现
    @FXML
    public void AMBack_movein(MouseEvent event){
        AMBack.setImage(new Image("file:client/src/main/res/webadmin/back_movein.png"));
    }
    @FXML
    public void AMBack_moveout(MouseEvent event){
        AMBack.setImage(new Image("file:client/src/main/res/webadmin/back.png"));
    }
    @FXML
    public void AMBack_press(MouseEvent event){
        AMBack.setImage(new Image("file:client/src/main/res/webadmin/back_press.png"));
    }
    @FXML
    public void AMBack_release(MouseEvent event){
        AMBack.setImage(new Image("file:client/src/main/res/webadmin/back_movein.png"));
    }
    @FXML
    public void AMBack_action(MouseEvent event){
        Fade amFade=new Fade(accountManage,400,false);
        amFade.play();
        Fade welFadeIn=new Fade(welcome,400,true);
        welFadeIn.play();
    }
    //HMBack的按钮实现
    @FXML
    public void HMBack_movein(MouseEvent event){
        HMBack.setImage(new Image("file:client/src/main/res/webadmin/back_movein.png"));
    }
    @FXML
    public void HMBack_moveout(MouseEvent event){
        HMBack.setImage(new Image("file:client/src/main/res/webadmin/back.png"));
    }
    @FXML
    public void HMBack_press(MouseEvent event){
        HMBack.setImage(new Image("file:client/src/main/res/webadmin/back_press.png"));
    }
    @FXML
    public void HMBack_release(MouseEvent event){
        HMBack.setImage(new Image("file:client/src/main/res/webadmin/back_movein.png"));
    }
    @FXML
    public void HMBack_action(MouseEvent event){
        Fade hmFade=new Fade(hotelManage,400,false);
        hmFade.play();
        Fade welFadeIn=new Fade(welcome,400,true);
        welFadeIn.play();
    }

    //addAccount的按钮实现
    @FXML
    public void addAccount_movein(MouseEvent event){
        addAccount.setImage(new Image("file:client/src/main/res/webadmin/addAccount_movein.png"));
    }
    @FXML
    public void addAccount_moveout(MouseEvent event){
        addAccount.setImage(new Image("file:client/src/main/res/webadmin/addAccount.png"));
    }
    @FXML
    public void addAccount_press(MouseEvent event){
        addAccount.setImage((new Image("file:client/src/main/res/webadmin/addAccount_press.png")));
    }
    @FXML
    public void addAccount_release(MouseEvent event){
        addAccount.setImage(new Image("file:client/src/main/res/webadmin/addAccount_movein.png"));
    }
    @FXML
    public void addAccount_action(MouseEvent event){
        //TODO
    }

    //addHotel的按钮实现
    @FXML
    public void addHotel_movein(MouseEvent event){
        addHotel.setImage(new Image("file:client/src/main/res/webadmin/addHotel_movein.png"));
    }
    @FXML
    public void addHotel_moveout(MouseEvent event){
        addHotel.setImage(new Image("file:client/src/main/res/webadmin/addHotel.png"));
    }
    @FXML
    public void addHotel_press(MouseEvent event){
        addHotel.setImage((new Image("file:client/src/main/res/webadmin/addHotel_press.png")));
    }
    @FXML
    public void addHotel_release(MouseEvent event){
        addHotel.setImage(new Image("file:client/src/main/res/webadmin/addHotel_movein.png"));
    }
    @FXML
    public void addHotel_action(MouseEvent event){
        //TODO
    }
}
