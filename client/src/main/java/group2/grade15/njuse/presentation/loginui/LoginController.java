package group2.grade15.njuse.presentation.loginui;
/**
 * Created by George on 2016/11/16.
 */

import group2.grade15.njuse.bl.loginbl.LoginControllerImpl;
import group2.grade15.njuse.blservice.LoginControllerServ;
import group2.grade15.njuse.utility.IDType;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController{

    LoginControllerServ loginControllerServ=new LoginControllerImpl();

    @FXML
    private TextField accoutField;

    @FXML
    private PasswordField passwordField;

    @FXML
    protected void login() {
        // TODO: 2016/11/18
        int webAdminId = Integer.parseInt(accoutField.getText());
        String webAdminPsw = passwordField.getText();
        switch (loginControllerServ.login(webAdminId, webAdminPsw, IDType.webAdmin)) {
            case SUCCESS:


        }
    }

}
