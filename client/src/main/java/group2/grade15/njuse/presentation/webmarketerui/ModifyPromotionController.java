package group2.grade15.njuse.presentation.webmarketerui;

import group2.grade15.njuse.presentation.mycontrol.CustomeButton;
import group2.grade15.njuse.utility.PromotionState;
import group2.grade15.njuse.utility.WebPromotionType;
import group2.grade15.njuse.vo.WebPromotionVO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
 * Created by luoy on 2016/12/3.
 */
public class ModifyPromotionController implements Initializable {
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


    public PromotionManageController promotionManageController;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CustomeButton.implButton(check,"file:client/src/main/res/webmarketer/Check");
        CustomeButton.implButton(cancel,"file:client/src/main/res/webmarketer/Cancel");

        ObservableList<Integer> reranks= FXCollections.observableArrayList();
        requiredRank.setItems(reranks);
        setEditable(false);
    }
    public void back(){
        setEditable(false);
        showCheck(false);
        promotionManageController.back();

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
                promotionManageController.getSelectedPromotion().getId(),
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
    public void commitModify(){
        promotionManageController.modifyPromotion(gatherVO());
        setEditable(false);
        showCheck(false);
    }
    public void showCheck(boolean a){
        check.setVisible(a);
        cancel.setVisible(a);
    }

    public void showPromotion(WebPromotionVO vo) {
        name.setText(vo.getName());
        cut.setText(String.valueOf(vo.getDiscount()));
        switch (vo.getType()) {
            case LevelWeb:
                type.setValue("会员优惠");
                break;
            case AreaWeb:
                type.setValue("特定商区优惠");
                break;
            case TimeWeb:
                type.setValue("特定时间优惠");
                break;
        }
        startDate.getEditor().setText(vo.getStart().toString());
        endDate.getEditor().setText(vo.getEnd().toString());
        CBD.setText(vo.getAddress());
        requiredRank.setValue(vo.getLevel());
        VIP.setVisible(false);
        time.setVisible(false);
        rank.setVisible(false);
        switch(type.getValue()){
            case "特定商区优惠":
                VIP.setVisible(true);
                break;
            case "特定时间优惠":
                time.setVisible(true);
                break;
            default:
                rank.setVisible(true);
                break;
        }
    }
    public void showPromotion(PromotionManageController.Promotion promotion) {
        name.setText(promotion.getName());
        cut.setText(String.valueOf(promotion.getDiscount()));
        type.setValue(promotion.getType());
        startDate.getEditor().setText(promotion.getStartDate());
        endDate.getEditor().setText(promotion.getEndDate());
        CBD.setText(promotion.getAddress());
        requiredRank.setValue(promotion.getLevel());
        VIP.setVisible(false);
        time.setVisible(false);
        rank.setVisible(false);
        switch(type.getValue()){
            case "特定商区优惠":
                VIP.setVisible(true);
                break;
            case "特定时间优惠":
                time.setVisible(true);
                break;
            default:
                rank.setVisible(true);
                break;
        }
    }
    public void setEditable(boolean sw) {
        name.setEditable(sw);
        cut.setEditable(sw);
        if(sw)
            requiredRank.getItems().addAll(1,2,3,4,5,6,7,8,9,10);
        else
            requiredRank.getItems().clear();
        CBD.setEditable(sw);
        startDate.setEditable(sw);
        endDate.setEditable(sw);
    }


}
