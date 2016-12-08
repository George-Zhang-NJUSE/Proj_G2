package group2.grade15.njuse.presentation.hotelui;

import group2.grade15.njuse.presentation.myanimation.Fade;
import group2.grade15.njuse.presentation.mycontrol.CustomeButton;
import group2.grade15.njuse.presentation.orderui.MakeOrderController;
import group2.grade15.njuse.vo.HotelVO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by George on 2016/12/1.
 */
public class HotelItemController implements Initializable {

    private HotelVO hotelVO;

    @FXML
    private Node rootNode;

    @FXML
    private ImageView hotelImageView;

    @FXML
    private Label showDetailLabel, makeOrderLabel, hotelNameLabel, starLabel, addressLabel, scoreLabel;


    @FXML
    private void showHotelDetail() {
        try {
            FXMLLoader hotelDetailLoader = new FXMLLoader(new URL("file:client/src/main/java/group2/grade15/njuse/presentation/hotelui/HotelDetail.fxml"));
            hotelDetailLoader.load();
            HotelDetailController detailController = hotelDetailLoader.getController();
            detailController.initDataAndShow(hotelVO);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void showMakeOrderPane() {
        try {
            FXMLLoader makeOrderLoader = new FXMLLoader(new URL("file:client/src/main/java/group2/grade15/njuse/presentation/orderui/MakeOrder.fxml"));
            makeOrderLoader.load();
            MakeOrderController makeOrderController = makeOrderLoader.getController();
            makeOrderController.initDataAndShow();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void initData(HotelVO vo) {
        if (vo != null) {
            hotelVO=vo;
            hotelImageView.setImage(new Image(new ByteArrayInputStream(hotelVO.getPicture()[0])));
            hotelNameLabel.setText(hotelVO.getName());
            starLabel.setText(Integer.toString(hotelVO.getRank()));
            addressLabel.setText(hotelVO.getConcreteAddress());
            scoreLabel.setText(Double.toString(hotelVO.getScore()));
        }else{
            System.out.println("没有联网数据");
        }
    }

    public void show() {
        Fade fadeIn = new Fade(rootNode, 300, true);
        fadeIn.play();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //加载按钮变化样式
        CustomeButton.implButton(showDetailLabel, "file:client/src/main/res/customer/more");
        CustomeButton.implButton(makeOrderLabel, "file:client/src/main/res/search/makeorder");

        //为渐入动画做准备
        rootNode.setOpacity(0);

    }
}
