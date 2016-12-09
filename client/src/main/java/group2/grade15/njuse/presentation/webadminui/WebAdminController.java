package group2.grade15.njuse.presentation.webadminui;

import group2.grade15.njuse.blservice.WebAdminServ;
import group2.grade15.njuse.presentation.myanimation.Fade;
import group2.grade15.njuse.presentation.mycontrol.CustomeButton;
import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.vo.*;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.server.RemoteRef;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by ALIENWARE-PC on 2016/11/19.
 */
public class WebAdminController implements Initializable{


    @FXML
    private Label addAccountLabel;


    @FXML
    private Pane hotelAddFrame;
    @FXML
    private ImageView HAcheck;
    @FXML
    private ImageView HAcancel;


    @FXML
    private Pane accountAddFrame;
    @FXML
    private ImageView AAcheck;
    @FXML
    private ImageView AAcancel;


    @FXML
    private GridPane accountManage;
    @FXML
    private ImageView AMBack;
    @FXML
    private ImageView addAccount;
    @FXML
    private TableView<Account> accountList;
    @FXML
    private TableView<Hotel> hotelList;

    @FXML
    private GridPane hotelManage;
    @FXML
    private ImageView HMBack;
    @FXML
    private ImageView addHotel;

    @FXML
    private GridPane welcome;
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




//逻辑实现部分
    private ObservableList<Hotel> hotelListData;
    private ObservableList<Account> accountListData;

    public static WebAdminServ webAdminService=new group2.grade15.njuse.bl.webadminbl.WebAdminController();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        hotelListData= FXCollections.observableArrayList();
        accountListData = FXCollections.observableArrayList();
        accountList.setItems(accountListData);
        hotelList.setItems(hotelListData);
        ObservableList acCols=accountList.getColumns();
        ObservableList htCols=hotelList.getColumns();

    }

    public ResultMessage addHotelToData(HotelVO vo) {

    }

    //初始化承收并展示来自数据库的账号列表方法
    public void showAllAccount() throws RemoteException{
        accountListData.clear();
        ArrayList<CustomerVO> customerVOs=webAdminService.getCustomerList().getList();
        ArrayList<HotelManagerVO> hotelManagerVOs=webAdminService.getHotelManagerList().getList();
        ArrayList<WebMarketerVO> webMarketerVOs=webAdminService.getWebMarketerList().getList();

        for(int i=0;i<customerVOs.size();i++){
            accountListData.add(new Account(customerVOs.get(i)));
        }
        for (int i=0;i<hotelManagerVOs.size();i++) {
            accountListData.add(new Account(hotelManagerVOs.get(i)));
        }
        for (int i=0;i<webMarketerVOs.size();i++) {
            accountListData.add(new Account(webMarketerVOs.get(i)));
        }

    }

    //初始化承收并展示来自数据库的酒店列表方法
    public void showAllHotel() throws RemoteException{
        hotelListData.clear();

        ArrayList<HotelVO> hotelVOs=webAdminService.getHotelList().getList();

        for(int i=0;i<hotelVOs.size();i++) {
            hotelListData.add(new Hotel(hotelVOs.get(i)));
        }
    }
    /**
     * Account的TableView的数据承载类，包括四种账户类型的构造函数
     */
    public static class Account{
        private final SimpleIntegerProperty id;
        private final SimpleStringProperty name;
        private final SimpleStringProperty password;
        private final SimpleStringProperty contact;
        private final SimpleStringProperty birthday;
        private final SimpleDoubleProperty credit;
        private final SimpleStringProperty type;
        private final SimpleStringProperty companyName;

        private Account(CustomerVO vo){
            id = new SimpleIntegerProperty(vo.getId());
            name = new SimpleStringProperty(vo.getName());
            password = new SimpleStringProperty(vo.getPassword());
            contact = new SimpleStringProperty(vo.getContact());
            birthday = new SimpleStringProperty(vo.getBirthday().toString());
            credit = new SimpleDoubleProperty(vo.getCredit());
            switch(vo.getType()){
                case normal:
                    type = new SimpleStringProperty("普通用户");
                    break;
                case vip:
                    type = new SimpleStringProperty("VIP");
                    break;
                default:
                    type = new SimpleStringProperty("未知");
                    break;
            }
            companyName = new SimpleStringProperty(vo.getCompanyName());
        }

        private Account(HotelManagerVO vo) {
            id = new SimpleIntegerProperty(vo.getId());
            name = new SimpleStringProperty(vo.getName());
            password = new SimpleStringProperty(vo.getPassword());
            contact = new SimpleStringProperty(vo.getContact());
            birthday = new SimpleStringProperty("None");
            credit = new SimpleDoubleProperty(0);
            type = new SimpleStringProperty("酒店管理");
            companyName = new SimpleStringProperty("None");
        }

        private Account(WebMarketerVO vo) {
            id=new SimpleIntegerProperty(Integer.parseInt(vo.getStaffID()));
            password = new SimpleStringProperty(vo.getPassword());
            name = new SimpleStringProperty("none");
            contact = new SimpleStringProperty("none");
            birthday = new SimpleStringProperty("none");
            credit = new SimpleDoubleProperty(0);
            type = new SimpleStringProperty("网站营销");
            companyName = new SimpleStringProperty("none");
        }

        private Account(WebAdminVO vo) {
            id=new SimpleIntegerProperty(Integer.parseInt(vo.getStaffID()));
            password = new SimpleStringProperty(vo.getPassword());
            name = new SimpleStringProperty("none");
            contact = new SimpleStringProperty("none");
            birthday = new SimpleStringProperty("none");
            credit = new SimpleDoubleProperty(0);
            type = new SimpleStringProperty("网站管理");
            companyName = new SimpleStringProperty("none");
        }
    }
    //作为Hotel的TableView的数据承载类
    public static class Hotel{
        private final SimpleIntegerProperty id;
        private final SimpleStringProperty name;
        private final SimpleStringProperty address;
        private final SimpleStringProperty concreteAddress;//具体地址
        private final SimpleStringProperty contact;
        private final SimpleStringProperty introduction;
        private final SimpleStringProperty facility;
        private final ArrayList<RoomVO> roomList;
        private final SimpleIntegerProperty rank;
        private final SimpleDoubleProperty score;
        private final byte[][] picture;

        private Hotel(HotelVO vo){
            id = new SimpleIntegerProperty(vo.getId());
            name = new SimpleStringProperty(vo.getName());
            address = new SimpleStringProperty(vo.getAddress());
            concreteAddress = new SimpleStringProperty(vo.getConcreteAddress());
            contact = new SimpleStringProperty(vo.getContact());
            introduction = new SimpleStringProperty(vo.getIntroduction());
            facility = new SimpleStringProperty(vo.getFacility());
            rank = new SimpleIntegerProperty(vo.getRank());
            score = new SimpleDoubleProperty(vo.getScore());

            roomList=vo.getRoomList();
            picture=vo.getPicture();
        }
    }


    private HotelVO getherVO(){
        HotelVO result;
        result=new HotelVO(hotelListData.size(),)
    }


    /*
    以下均为按钮实现
     */

    //messageButton的按钮实现
    @FXML
    public void message_movein(MouseEvent event) {
        messageImage.setImage(new Image("file:client/src/main/res/webadmin/message_enter.png"));
    }

    public void message_moveout(MouseEvent event) {
        messageImage.setImage(new Image("file:client/src/main/res/webadmin/message.png"));
    }

    public void message_press(MouseEvent event) {
        messageImage.setImage(new Image("file:client/src/main/res/webadmin/message_press.png"));
    }

    public void message_release(MouseEvent event) {
        messageImage.setImage(new Image("file:client/src/main/res/webadmin/message_enter.png"));
    }


    //settingButton的按钮实现
    @FXML
    public void setting_movein(MouseEvent event) {
        settingImage.setImage(new Image("file:client/src/main/res/webadmin/setting_enter.png"));
    }

    public void setting_moveout(MouseEvent event) {
        settingImage.setImage(new Image("file:client/src/main/res/webadmin/setting.png"));
    }

    public void setting_press(MouseEvent event) {
        settingImage.setImage(new Image("file:client/src/main/res/webadmin/setting_press.png"));
    }

    public void setting_release(MouseEvent event) {
        settingImage.setImage(new Image("file:client/src/main/res/webadmin/setting_enter.png"));
    }

    //AccountButton的按钮实现
    @FXML
    public void Account_movein(MouseEvent event) {
        accountImage.setImage(new Image("file:client/src/main/res/webadmin/Account_enter.png"));
    }

    public void Account_moveout(MouseEvent event) {
        accountImage.setImage(new Image("file:client/src/main/res/webadmin/Account.png"));
    }

    public void Account_press(MouseEvent event) {
        accountImage.setImage(new Image("file:client/src/main/res/webadmin/Account_press.png"));
    }

    public void Account_release(MouseEvent event) {
        accountImage.setImage(new Image("file:client/src/main/res/webadmin/Account_enter.png"));
    }

    public void Account_action(MouseEvent event) {
        Fade amFade = new Fade(accountManage, 400, true);
        amFade.play();
        accountManage.setVisible(true);
        Fade welFadeIn = new Fade(welcome, 400, false);
        welFadeIn.play();
        welcome.setVisible(false);
    }


    //HotelButton的按钮实现
    @FXML
    public void Hotel_movein(MouseEvent event) {
        hotelImage.setImage(new Image("file:client/src/main/res/webadmin/Hotel_enter.png"));
    }

    public void Hotel_moveout(MouseEvent event) {
        hotelImage.setImage(new Image("file:client/src/main/res/webadmin/Hotel.png"));
    }

    public void Hotel_press(MouseEvent event) {
        hotelImage.setImage(new Image("file:client/src/main/res/webadmin/Hotel_press.png"));
    }

    public void Hotel_release(MouseEvent event) {
        hotelImage.setImage(new Image("file:client/src/main/res/webadmin/Hotel_enter.png"));
    }

    public void Hotel_action(MouseEvent event) {

        Fade hmFade = new Fade(hotelManage, 400, true);
        hmFade.play();
        hotelManage.setVisible(true);
        Fade welFadeIn = new Fade(welcome, 400, false);
        welFadeIn.play();
        welcome.setVisible(false);
    }

    //AMBack的按钮实现
    @FXML
    public void AMBack_movein(MouseEvent event) {
        AMBack.setImage(new Image("file:client/src/main/res/webadmin/back_enter.png"));
    }

    public void AMBack_moveout(MouseEvent event) {
        AMBack.setImage(new Image("file:client/src/main/res/webadmin/back.png"));
    }

    public void AMBack_press(MouseEvent event) {
        AMBack.setImage(new Image("file:client/src/main/res/webadmin/back_press.png"));
    }

    public void AMBack_release(MouseEvent event) {
        AMBack.setImage(new Image("file:client/src/main/res/webadmin/back_enter.png"));
    }

    public void AMBack_action(MouseEvent event) {
        Fade amFade = new Fade(accountManage, 400, false);
        amFade.play();
        accountManage.setVisible(false);
        Fade welFadeIn = new Fade(welcome, 400, true);
        welFadeIn.play();
        welcome.setVisible(true);
    }

    //HMBack的按钮实现
    @FXML
    public void HMBack_movein(MouseEvent event) {
        HMBack.setImage(new Image("file:client/src/main/res/webadmin/back_enter.png"));
    }

    public void HMBack_moveout(MouseEvent event) {
        HMBack.setImage(new Image("file:client/src/main/res/webadmin/back.png"));
    }

    public void HMBack_press(MouseEvent event) {
        HMBack.setImage(new Image("file:client/src/main/res/webadmin/back_press.png"));
    }

    public void HMBack_release(MouseEvent event) {
        HMBack.setImage(new Image("file:client/src/main/res/webadmin/back_enter.png"));
    }

    public void HMBack_action(MouseEvent event) {
        Fade hmFade = new Fade(hotelManage, 400, false);
        hmFade.play();
        hotelManage.setVisible(false);
        Fade welFadeIn = new Fade(welcome, 400, true);
        welFadeIn.play();
        welcome.setVisible(true);
    }

    //addAccount的按钮实现
    @FXML
    public void addAccount_movein(MouseEvent event) {
        addAccount.setImage(new Image("file:client/src/main/res/webadmin/addAccount_enter.png"));
    }

    public void addAccount_moveout(MouseEvent event) {
        addAccount.setImage(new Image("file:client/src/main/res/webadmin/addAccount.png"));
    }

    public void addAccount_press(MouseEvent event) {
        addAccount.setImage((new Image("file:client/src/main/res/webadmin/addAccount_press.png")));
    }

    public void addAccount_release(MouseEvent event) {
        addAccount.setImage(new Image("file:client/src/main/res/webadmin/addAccount_enter.png"));
    }

    public void addAccount_action(MouseEvent event) {
        if (!accountAddFrame.isVisible()) {
            accountAddFrame.setVisible(true);
            Fade fadein = new Fade(accountAddFrame, 200, true);
            fadein.play();
        } else {
            accountAddFrame.setVisible(false);
        }
    }

    //addHotel的按钮实现
    @FXML
    public void addHotel_movein(MouseEvent event) {
        addHotel.setImage(new Image("file:client/src/main/res/webadmin/addHotel_enter.png"));
    }

    public void addHotel_moveout(MouseEvent event) {
        addHotel.setImage(new Image("file:client/src/main/res/webadmin/addHotel.png"));
    }

    public void addHotel_press(MouseEvent event) {
        addHotel.setImage((new Image("file:client/src/main/res/webadmin/addHotel_press.png")));
    }

    public void addHotel_release(MouseEvent event) {
        addHotel.setImage(new Image("file:client/src/main/res/webadmin/addHotel_enter.png"));
    }

    public void addHotel_action(MouseEvent event) {
        if (!hotelAddFrame.isVisible()) {
            Fade fadein = new Fade(hotelAddFrame, 200, true);
            hotelAddFrame.setVisible(true);
            fadein.play();
        } else {
            hotelAddFrame.setVisible(false);
        }
    }

    //AAcheck的按钮实现
    @FXML
    public void AAcheck_movin(MouseEvent event) {
        AAcheck.setImage(new Image("file:client/src/main/res/webadmin/Check_enter.png"));
    }

    public void AAcheck_moveout(MouseEvent event) {
        AAcheck.setImage(new Image("file:client/src/main/res/webadmin/Check.png"));
    }

    public void AAcheck_press(MouseEvent event) {
        AAcheck.setImage(new Image("file:client/src/main/res/webadmin/Check_press.png"));
    }

    public void AAcheck_release(MouseEvent event) {
        AAcheck.setImage(new Image("file:client/src/main/res/webadmin/Check_enter.png"));
    }

    public void AAcheck_action(MouseEvent event) {
        //TODO to implement the function of add an account to the database.

    }

    //AAcancel的按钮实现
    @FXML
    public void AAcancel_movein(MouseEvent event) {
        AAcancel.setImage(new Image("file:client/src/main/res/webadmin/Cancel_enter.png"));
    }

    public void AAcancel_moveout(MouseEvent event) {
        AAcancel.setImage(new Image("file:client/src/main/res/webadmin/Cancel.png"));
    }

    public void AAcancel_press(MouseEvent event) {
        AAcancel.setImage(new Image("file:client/src/main/res/webadmin/Cancel_press.png"));
    }

    public void AAcancel_release(MouseEvent event) {
        AAcancel.setImage(new Image("file:client/src/main/res/webadmin/Cancel_enter.png"));
    }

    public void AAcancel_action(MouseEvent event) {
        Fade fadeout = new Fade(accountAddFrame, 200, false);
        accountAddFrame.setVisible(false);
    }

    //HAcheck的按钮实现
    public void HAcheck_movin(MouseEvent event) {
        HAcheck.setImage(new Image("file:client/src/main/res/webadmin/Check_enter.png"));
    }

    public void HAcheck_moveout(MouseEvent event) {
        HAcheck.setImage(new Image("file:client/src/main/res/webadmin/Check.png"));
    }

    public void HAcheck_press(MouseEvent event) {
        HAcheck.setImage(new Image("file:client/src/main/res/webadmin/Check_press.png"));
    }

    public void HAcheck_release(MouseEvent event) {
        HAcheck.setImage(new Image("file:client/src/main/res/webadmin/Check_enter.png"));
    }

    public void HAcheck_action(MouseEvent event) {
        //TODO to implement the function of add an account to the database.

    }

    //HAcancel的按钮实现
    @FXML
    public void HAcancel_movein(MouseEvent event) {
        HAcancel.setImage(new Image("file:client/src/main/res/webadmin/Cancel_enter.png"));
    }

    public void HAcancel_moveout(MouseEvent event) {
        HAcancel.setImage(new Image("file:client/src/main/res/webadmin/Cancel.png"));
    }

    public void HAcancel_press(MouseEvent event) {
        HAcancel.setImage(new Image("file:client/src/main/res/webadmin/Cancel_press.png"));
    }

    public void HAcancel_release(MouseEvent event) {
        HAcancel.setImage(new Image("file:client/src/main/res/webadmin/Cancel_enter.png"));
    }

    public void HAcancel_action(MouseEvent event) {
        Fade fadeout = new Fade(hotelAddFrame, 200, false);
        hotelAddFrame.setVisible(false);
    }


}
