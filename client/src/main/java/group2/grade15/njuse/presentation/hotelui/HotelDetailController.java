package group2.grade15.njuse.presentation.hotelui;

import group2.grade15.njuse.presentation.orderui.MakeOrderController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by George on 2016/12/1.
 */
public class HotelDetailController {

    private Pane parentPane;

    @FXML
    private Node rootNode;

    @FXML
    private VBox commentBox;

    @FXML
    protected void goBack() {
        parentPane.getChildren().remove(rootNode);
    }

    @FXML
    protected void showMakeOrderPane() {
        try {
            FXMLLoader makeOrderLoader=new FXMLLoader(new URL("file:client/src/main/java/group2/grade15/njuse/presentation/orderui/MakeOrder.fxml"));
            parentPane.getChildren().add(makeOrderLoader.load());
            MakeOrderController makeOrderController = makeOrderLoader.getController();

            makeOrderController.setParentPane(parentPane);
            makeOrderController.initDataAndShow();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void setParentPane(Pane parentPane) {
        this.parentPane = parentPane;
    }

}
