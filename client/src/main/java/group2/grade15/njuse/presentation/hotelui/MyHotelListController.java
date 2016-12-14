package group2.grade15.njuse.presentation.hotelui;

import group2.grade15.njuse.bl.hotelbl.HotelController;
import group2.grade15.njuse.blservice.HotelServ;
import group2.grade15.njuse.presentation.customerglobal.CommonData;
import group2.grade15.njuse.presentation.myanimation.Fade;
import group2.grade15.njuse.presentation.myanimation.Pop;
import group2.grade15.njuse.vo.HotelVO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by George on 2016/12/4.
 */
public class MyHotelListController implements Initializable {

    @FXML
    private Node rootNode;

    @FXML
    private VBox hotelItemBox;

    private void showContents() {

        HotelServ hotelServ = new HotelController();
        int customerID = CommonData.getInstance().getCustomerVO().getId();

        ArrayList<HotelVO> myHotels = hotelServ.getBookedHotelList(customerID).getList();
        ArrayList<HotelItemController> itemControllers = new ArrayList<>();

        try {
            hotelItemBox.getChildren().clear();

            for (HotelVO hotelVO:myHotels) {
                FXMLLoader hotelItemLoader = new FXMLLoader(new URL("file:client/src/main/res/fxml/customer/HotelItem.fxml"));
                Node single = hotelItemLoader.load();

                HotelItemController hotelItemController = hotelItemLoader.getController();
                hotelItemController.initData(hotelVO);
                itemControllers.add(hotelItemController);
                hotelItemBox.getChildren().add(single);
            }

            itemControllers.forEach(HotelItemController::show);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showContainer() {
        //渐入扩大动画
        Fade fadeIn = new Fade(rootNode, 300, true);
        Pop popIn = new Pop(rootNode, 300, true);
        popIn.setOnFinished((ActionEvent e)->showContents());
        fadeIn.play();
        popIn.play();

    }

    public void initDataAndShow() {
        showContainer();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //为渐入扩大动画做准备
        rootNode.setOpacity(0);
        rootNode.setScaleX(0.9);
        rootNode.setScaleY(0.9);

    }
}
