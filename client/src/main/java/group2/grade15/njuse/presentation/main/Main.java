package presentation.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader loader=new FXMLLoader(new URL("file:src/presentation/loginui/login.fxml"));
        Pane root=loader.load();
        primaryStage.setTitle("酒店预订系统——网站管理人员端");
        primaryStage.setScene(new Scene(root,900,600));
        primaryStage.sizeToScene();
        primaryStage.setResizable(false);
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
