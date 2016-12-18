package group2.grade15.njuse.presentation.webmarketerui;

import group2.grade15.njuse.presentation.mycontrol.CustomeButton;
import group2.grade15.njuse.utility.PromotionState;
import group2.grade15.njuse.utility.WebPromotionType;
import group2.grade15.njuse.vo.WebPromotionVO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;


import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

/**
 * Created by ALIENWARE-PC on 2016/12/2.
 */
public class AddPromotionController implements Initializable {
    private int ID;
    @FXML
    private TextField name;
    @FXML
    private TextField cut;
    @FXML
    private ComboBox<String> type;
    @FXML
    private GridPane VIP;
    @FXML
    private GridPane time;
    @FXML
    private GridPane rank;
    @FXML
    private ComboBox<Integer> requiredRank;
    @FXML
    private TextField CBD;
    @FXML
    private DatePicker startDate;
    @FXML
    private DatePicker endDate;
    @FXML
    private Label check;
    @FXML
    private Label cancel;
    @FXML
    private Label message;


    public PromotionManageController promotionManageController;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CustomeButton.implButton(check,"file:client/src/main/res/webmarketer/Check");
        CustomeButton.implButton(cancel,"file:client/src/main/res/webmarketer/Cancel");
        type.setOnAction((ActionEvent e)->{
            VIP.setVisible(false);
            time.setVisible(false);
            rank.setVisible(false);
            switch (type.getValue()){
                case "特定商区优惠":
                    VIP.setVisible(true);
                    break;
                case "特定时间优惠":
                    time.setVisible(true);
                    break;
                case "会员优惠":
                    rank.setVisible(true);
                    break;
            }
        });
        ObservableList<String> types= FXCollections.observableArrayList(
                "特定商区优惠",
                "特定时间优惠",
                "会员优惠"
        );
        type.setItems(types);

        ObservableList<Integer> ranks=FXCollections.observableArrayList(
                1,2,3,4,5,6,7,8,9,10
        );
        requiredRank.setItems(ranks);
    }
    public WebPromotionVO gatherVO(){
        Date sD,eD;
        try {
            String[] s = startDate.getEditor().getText().split("-");
            String[] e = endDate.getEditor().getText().split("-");
            sD = new Date(Integer.parseInt(s[0]) - 1900, Integer.parseInt(s[1]) - 1, Integer.parseInt(s[2]));
            eD = new Date(Integer.parseInt(e[0]) - 1900, Integer.parseInt(e[1]) - 1, Integer.parseInt(e[2]));
        }catch(Exception e){
            sD=new Date(0,0,0);
            eD=new Date(0,0,0);
        }
        WebPromotionType typeOfVO;
        switch(type.getValue()){
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
        int rerank;
        try{
            rerank=requiredRank.getValue();
        }catch (NullPointerException e){
            rerank=0;
        }
        WebPromotionVO vo=new WebPromotionVO(
                0,
                typeOfVO,
                sD,
                eD,
                CBD.getText(),
                rerank,
                Double.parseDouble(cut.getText()),
                name.getText(),
                PromotionState.unlaunched
        );
        return vo;
    }
    public void back(){
        promotionManageController.back();
    }
    public void add() {
        if (checkEmpty()) {
            message.setText("填写内容不能为空");
            return;
        }
        promotionManageController.addPromotion(gatherVO());
    }
    public boolean checkEmpty(){
        boolean result=
                name.getText().length()==0||
                        cut.getText().length()==0||
                        type.getEditor().getText().length()==0;
        if(type.getEditor().getText()=="TimeWeb"){
            if (startDate.getEditor().getText().length()==0 || endDate.getEditor().getText().length()==0) {
                result=true;
            }
        } else if (type.getEditor().getText() == "LevelWeb") {
            if (requiredRank.getEditor().getText().length()==0) {
                result=true;
            }
        } else if (type.getEditor().getText().length()==0) {
            if (CBD.getText().length()==0) {
                result = true;
            }
        }

        return result;
    }
}
