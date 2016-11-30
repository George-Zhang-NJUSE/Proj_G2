package group2.grade15.njuse.presentation.mycontrol;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * Created by ALIENWARE-PC on 2016/11/30.
 */
public class CustomeButton{
    /**
     *
     * @param label
     * @param url 不要加后缀名 比如直接写client/src/main/res/webadmin/setting
     */
    public static void implButton(Label label, String url){
        String originURL=url+".png";
        String enterURL=url+"_enter.png";
        String pressURL=url+"_press.png";
        Image origin=new Image(originURL);
        Image press=new Image(pressURL);
        Image enter=new Image(enterURL);
        ImageView graphic=(ImageView) label.getGraphic();
        label.setOnMouseEntered((MouseEvent e)->{
            graphic.setImage(enter);
        });
        label.setOnMouseExited((MouseEvent e)->{
            graphic.setImage(origin);
        });
        label.setOnMousePressed((MouseEvent e)->{
            graphic.setImage(press);
        });
        label.setOnMouseReleased((MouseEvent e)->{
            graphic.setImage(enter);
        });
    }

}
