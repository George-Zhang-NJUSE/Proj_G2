package group2.grade15.njuse.presentation.webmarketerui;

import group2.grade15.njuse.presentation.myanimation.Fade;
import group2.grade15.njuse.presentation.mycontrol.CustomeButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

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
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        toCreditCharge();
    }

    public void toCreditCharge(){
        try{
            FXMLLoader creditChargeLoader=new FXMLLoader(new URL("file:client/src/main/java/group2/grade15/njuse/presentation/webmarketerui/CreditCharge.fxml"));
            motherPane.getChildren().removeAll();
            motherPane.getChildren().add(creditChargeLoader.load());
            Fade fadein=new Fade(motherPane,500,true);
            fadein.play();
        }catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
