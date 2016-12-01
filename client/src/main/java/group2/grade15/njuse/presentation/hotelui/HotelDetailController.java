package group2.grade15.njuse.presentation.hotelui;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

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


    public void setParentPane(Pane parentPane) {
        this.parentPane = parentPane;
    }

}
