package group2.grade15.njuse.presentation.webmarketerui;

import group2.grade15.njuse.presentation.myanimation.Fade;
import group2.grade15.njuse.presentation.mycontrol.CustomeButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by ALIENWARE-PC on 2016/11/26.
 */
public class WebMarketerMainController implements Initializable{
    public Pane motherPane;
    @FXML
    private Pane opPane;
    @FXML
    private FlowPane promotionSwitcher;
    @FXML
    private FlowPane creditChargeSwitcher;
    @FXML
    private FlowPane fixSwitcher;
    @FXML
    private Rectangle promotionTag;
    @FXML
    private Rectangle creditChargeTag;
    @FXML
    private Rectangle fixTag;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addSwither(promotionSwitcher);
        addSwither(creditChargeSwitcher);
        addSwither(fixSwitcher);

    }

    public boolean toCreditCharge() {
        if (creditChargeTag.isVisible())
            return true;
        promotionTag.setVisible(false);
        fixTag.setVisible(false);
        creditChargeTag.setVisible(true);
        creditChargeSwitcher.setStyle("-fx-background-color: rgb(210,210,210)");
        try {
            FXMLLoader creditChargeLoader = new FXMLLoader(new URL("file:client/src/main/java/group2/grade15/njuse/presentation/webmarketerui/CreditCharge.fxml"));
            motherPane.getChildren().clear();
            motherPane.getChildren().add(creditChargeLoader.load());
            Fade fadein = new Fade(motherPane, 500, true);
            fadein.play();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
    public boolean toPromotion(){
        //TODO 到promotion管理的跳转
        if (promotionTag.isVisible())
            return true;
        promotionTag.setVisible(true);
        fixTag.setVisible(false);
        creditChargeTag.setVisible(false);
        try {
            FXMLLoader promotionLoader = new FXMLLoader(new URL("file:client/src/main/java/group2/grade15/njuse/presentation/webmarketerui/PromotionManage.fxml"));
            motherPane.getChildren().clear();
            motherPane.getChildren().add(promotionLoader.load());
            Fade fadein = new Fade(motherPane, 500, true);
            fadein.play();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
    public boolean toFix(){
        //TODO 到fix的跳转
        if (fixTag.isVisible())
            return true;
        promotionTag.setVisible(false);
        fixTag.setVisible(true);
        creditChargeTag.setVisible(false);
        try {
            FXMLLoader fixLoader = new FXMLLoader(new URL("file:client/src/main/java/group2/grade15/njuse/presentation/webmarketerui/Fix.fxml"));
            motherPane.getChildren().clear();
            motherPane.getChildren().add(fixLoader.load());
            Fade fadein = new Fade(motherPane, 500, true);
            fadein.play();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public void addSwither(FlowPane pane){
        pane.setOnMouseEntered((MouseEvent e)->{
            pane.setStyle("-fx-background-color: rgb(230,230,230)");
        });
        pane.setOnMouseExited((MouseEvent e)->{
            pane.setStyle("-fx-background-color: inherit");
        });
        pane.setOnMousePressed((MouseEvent e)->{
            pane.setStyle("-fx-background-color: rgb(210,210,210)");
        });
    }
}
