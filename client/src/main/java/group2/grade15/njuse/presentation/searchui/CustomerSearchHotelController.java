package group2.grade15.njuse.presentation.searchui;

import group2.grade15.njuse.presentation.hotelui.HotelItemController;
import group2.grade15.njuse.presentation.myanimation.ChangeHeight;
import group2.grade15.njuse.presentation.myanimation.Fade;
import group2.grade15.njuse.presentation.myanimation.Pop;
import group2.grade15.njuse.presentation.mycontrol.CustomeButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by George on 2016/11/30.
 */
public class CustomerSearchHotelController implements Initializable {

    private Pane parentPane;

    @FXML
    private VBox searchItemBox;

    @FXML
    private Pane additionalConditionPane;

    @FXML
    private Node rootNode;

    @FXML
    private Label searchLabel;

    @FXML
    private ChoiceBox provinceChoiceBox, cityChoiceBox, districtChoiceBox, cbdChoiceBox, roomTypeChoiceBox,
            priceRangeChoiceBox, freeRoomChoiceBox, minStarChoiceBox, scoreRangeChoiceBox;

    @FXML
    private TextField hotelNameField;


    @FXML
    protected void search() {
        showSearchResult();
    }

    @FXML
    private void changeAdditionalPane() {
        if (additionalConditionPane.isVisible()) {
            hideAdditionalPane();
        } else {
            showAdditionalPane();
        }
    }

    private void showAdditionalPane() {
        ChangeHeight grow=new ChangeHeight(additionalConditionPane,300,160);
        Fade fadeIn = new Fade(additionalConditionPane, 300, true);
        additionalConditionPane.setVisible(true);
        grow.play();
        fadeIn.play();
    }

    private void hideAdditionalPane() {
        ChangeHeight shrink = new ChangeHeight(additionalConditionPane, 300, 0);
        Fade fadeOut = new Fade(additionalConditionPane, 300, false);
        fadeOut.setOnFinished((ActionEvent e) -> additionalConditionPane.setVisible(false));
        shrink.play();
        fadeOut.play();
    }


    private void showSearchResult() {
        try {
            searchItemBox.getChildren().clear();


            // TODO: 2016/12/2 需要更改为正确的逻辑
            for (int i = 0; i < 15; ++i) {
                FXMLLoader searchItemLoader = new FXMLLoader(new URL("file:client/src/main/java/group2/grade15/njuse/presentation/hotelui/HotelItem.fxml"));
                Node singleItemTemplate = searchItemLoader.load();
                HotelItemController hotelItemController = searchItemLoader.getController();
                hotelItemController.setParentPane(parentPane);
                searchItemBox.getChildren().add(singleItemTemplate);
                hotelItemController.initDataAndShow();
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showSearchPane() {
        //渐入扩大动画
        Fade fadeIn = new Fade(rootNode, 300, true);
        Pop popIn = new Pop(rootNode, 300, true);
        fadeIn.play();
        popIn.play();
    }

    public void setParentPane(Pane parentPane) {
        this.parentPane = parentPane;
    }

    public void initDataAndShow() {
        // TODO: 2016/12/5 加载数据


        showSearchPane();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //加载按钮变化样式
        CustomeButton.implButton(searchLabel, "file:client/src/main/res/search/search");

        //为渐入扩大动画做准备
        rootNode.setOpacity(0);
        rootNode.setScaleX(0.9);
        rootNode.setScaleY(0.9);

        //默认收起附加搜索条件面板
        additionalConditionPane.setVisible(false);
        additionalConditionPane.setOpacity(0);
        additionalConditionPane.setPrefHeight(0);

    }
}
