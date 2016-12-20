package group2.grade15.njuse.presentation.webadminui;

import group2.grade15.njuse.bl.customerbl.Customer;
import group2.grade15.njuse.blservice.WebAdminServ;
import group2.grade15.njuse.presentation.myanimation.Fade;
import group2.grade15.njuse.presentation.mycontrol.CustomeButton;
import group2.grade15.njuse.utility.MemberType;
import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.vo.*;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.server.RemoteRef;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by ALIENWARE-PC on 2016/11/19.
 */
public class WebAdminController implements Initializable{

    @FXML
    private Label aaInfo;
    @FXML
    private Label haInfo;


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

    @FXML
    private TextField hotelName;
    @FXML
    private TextField hotelAddress;
    @FXML
    private TextField hotelRank;

    @FXML
    private TextField hotelIdField;
    @FXML
    private Label hotelIdLabel;

    @FXML
    private TextField userName;
    @FXML
    private TextField userPW;
    @FXML
    private ComboBox<String> userType;


    @FXML
    private WebAdminVO webAdminVO;
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

        ObservableList<String> types=FXCollections.observableArrayList(
                new String("酒店管理用户"),
                new String("网站营销用户")
        );
        userType.setItems(types);

        ObservableList acCols=accountList.getColumns();

        ((TableColumn)acCols.get(0)).setCellValueFactory(new PropertyValueFactory<>("id"));
        ((TableColumn)acCols.get(1)).setCellValueFactory(new PropertyValueFactory<>("name"));
        ((TableColumn)acCols.get(2)).setCellValueFactory(new PropertyValueFactory<>("password"));
        ((TableColumn)acCols.get(3)).setCellValueFactory(new PropertyValueFactory<>("type"));
        ((TableColumn)acCols.get(4)).setCellValueFactory(new PropertyValueFactory<>("contact"));
        ((TableColumn)acCols.get(5)).setCellValueFactory(new PropertyValueFactory<>("birthday"));
        ((TableColumn)acCols.get(6)).setCellValueFactory(new PropertyValueFactory<>("credit"));
        ((TableColumn)acCols.get(7)).setCellValueFactory(new PropertyValueFactory<>("company"));



        ObservableList htCols=hotelList.getColumns();

        ((TableColumn)htCols.get(0)).setCellValueFactory(new PropertyValueFactory<>("id"));
        ((TableColumn)htCols.get(1)).setCellValueFactory(new PropertyValueFactory<>("name"));
        ((TableColumn)htCols.get(2)).setCellValueFactory(new PropertyValueFactory<>("concreteAddress"));
        ((TableColumn)htCols.get(3)).setCellValueFactory(new PropertyValueFactory<>("rank"));
        try{
            showAllAccount();
            showAllHotel();
        }catch (RemoteException e){
            e.printStackTrace();
        }
        userType.setOnAction((ActionEvent e)->{
            switch (userType.getValue()) {
                case "酒店管理用户":
                    hotelIdLabel.setVisible(true);
                    hotelIdField.setVisible(true);
                    break;
                default:
                    hotelIdField.setVisible(false);
                    hotelIdLabel.setVisible(false);
                    break;
            }
        });
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
        private final SimpleStringProperty id;
        private final SimpleStringProperty name;
        private final SimpleStringProperty password;
        private final SimpleStringProperty contact;
        private final SimpleStringProperty birthday;
        private final SimpleDoubleProperty credit;
        private final SimpleStringProperty type;
        private final SimpleStringProperty companyName;

        private Account(CustomerVO vo){
            id = new SimpleStringProperty(String.valueOf(vo.getId()));
            name = new SimpleStringProperty(vo.getName());
            password = new SimpleStringProperty(vo.getPassword());
            contact = new SimpleStringProperty(vo.getContact());
            if (vo.getType() == MemberType.normal) {
                birthday = new SimpleStringProperty(vo.getBirthday().toString());
                companyName = new SimpleStringProperty("none");
            }else{
                companyName = new SimpleStringProperty(vo.getCompanyName());
                birthday = new SimpleStringProperty("none");
            }

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
        }

        private Account(HotelManagerVO vo) {
            id = new SimpleStringProperty(String.valueOf(vo.getId()));
            name = new SimpleStringProperty(vo.getName());
            password = new SimpleStringProperty(vo.getPassword());
            contact = new SimpleStringProperty(vo.getContact());
            birthday = new SimpleStringProperty("None");
            credit = new SimpleDoubleProperty(0);
            type = new SimpleStringProperty("酒店管理");
            companyName = new SimpleStringProperty("None");
        }

        private Account(WebMarketerVO vo) {
            id=new SimpleStringProperty((vo.getStaffID()));
            password = new SimpleStringProperty(vo.getPassword());
            name = new SimpleStringProperty("none");
            contact = new SimpleStringProperty("none");
            birthday = new SimpleStringProperty("none");
            credit = new SimpleDoubleProperty(0);
            type = new SimpleStringProperty("网站营销");
            companyName = new SimpleStringProperty("none");
        }

        private Account(WebAdminVO vo) {
            id=new SimpleStringProperty((vo.getStaffID()));
            password = new SimpleStringProperty(vo.getPassword());
            name = new SimpleStringProperty("none");
            contact = new SimpleStringProperty("none");
            birthday = new SimpleStringProperty("none");
            credit = new SimpleDoubleProperty(0);
            type = new SimpleStringProperty("网站管理");
            companyName = new SimpleStringProperty("none");
        }
        public String getId(){
            return id.get();
        }
        public String getName(){
            return name.get();
        }
        public String getPassword(){
            return password.get();
        }
        public String getContact(){
            return contact.get();
        }
        public String getBithday(){
            return birthday.get();
        }
        public double getCredit(){
            return credit.get();
        }
        public String getType(){
            return type.get();
        }
        public String getCompanyName(){
            return companyName.get();
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
        public int getId(){
            return id.get();
        }
        public String getName(){
            return name.get();
        }
        public String getAddress(){
            return address.get();
        }
        public String getConcreteAddress(){
            return concreteAddress.get();
        }
        public String getContact(){
            return contact.get();
        }
        public String getIntroduction(){
            return introduction.get();
        }
        public String getFacility(){
            return facility.get();
        }
        public int getRank(){
            return rank.get();
        }
        public double getScore(){
            return score.get();
        }

    }

    private HotelManagerVO gatherHotelManagerVO(){
        String name=userName.getText();
        String pw=userPW.getText();
        int hotelId= Integer.parseInt(hotelIdField.getText());
        HotelManagerVO vo=new HotelManagerVO(0,pw,name,"",hotelId);
        return vo;
    }
    private WebMarketerVO gatherWebMarketerVO(){
        String name=userName.getText();
        String pw=userPW.getText();
        WebMarketerVO vo = new WebMarketerVO(userPW.getText(),userName.getText());
        return vo;
    }
    private HotelVO gatherHotelVO(){
        HotelVO result;
        result = new HotelVO(
                0,//id
                hotelName.getText(),//name
                "",//address
                hotelAddress.getText(),//concretAddress
                "",//contact
                "", //introduction
                "",//facility
                new ArrayList<RoomVO>(),//roomList
                Integer.parseInt(hotelRank.getText()),//rank
                0,//score
                null//picture
        );
        return result;
    }
    public ResultMessage addHotelToData(HotelVO vo) throws RemoteException {
        return webAdminService.createHotel(vo);
    }

    public void addAccount(){
        switch(userType.getValue()){
            case "酒店管理用户":
                HotelManagerVO hmvo=gatherHotelManagerVO();
                try {
                    webAdminService.createHotelManager(hmvo);
                    accountListData.add(new Account(hmvo));
                    aaInfo.setText("添加成功");
                    AAClear();
                }catch (NullPointerException e){
                    aaInfo.setText("添加失败");
                }

                break;
            case "网站营销用户":
                WebMarketerVO wmvo=gatherWebMarketerVO();
                try {
                    webAdminService.createWebMarketer(wmvo);
                    accountListData.add(new Account(wmvo));
                    aaInfo.setText("添加成功");
                    AAClear();
                }catch(NullPointerException e){
                    aaInfo.setText("添加失败");
                }

                break;
        }
    }
    public void addHotel(){
        HotelVO vo=gatherHotelVO();
        try{
            webAdminService.createHotel(vo);
            hotelListData.add(new Hotel(vo));

            haInfo.setText("添加成功");
            HAClear();
        }catch(NullPointerException e){
            haInfo.setText("添加失败");
        }

    }
    private void AAClear(){
        userName.setText("");
        userType.setValue("");
        userPW.setText("");
    }
    private void HAClear(){
        hotelName.setText("");
        hotelAddress.setText("");
        hotelRank.setText("");
    }




    public void  deleteAccount(int id){

    }


    public void setAdminVO(WebAdminVO vo){
        webAdminVO=vo;
    }

/*
//TODO 有空的话 把这里搞成用CustomeButton.implementButton()的方法来减少复用；
 */
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
        addAccount();

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
        addHotel();

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
