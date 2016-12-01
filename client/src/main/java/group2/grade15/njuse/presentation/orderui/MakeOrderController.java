package group2.grade15.njuse.presentation.orderui;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

/**
 * Created by George on 2016/12/1.
 */
public class MakeOrderController {

    private Pane parentPane;

    @FXML
    private Node rootNode;

    @FXML
    protected void goBack() {
        parentPane.getChildren().remove(rootNode);
    }

    public void setParentPane(Pane parentPane) {
        this.parentPane = parentPane;
    }
}
