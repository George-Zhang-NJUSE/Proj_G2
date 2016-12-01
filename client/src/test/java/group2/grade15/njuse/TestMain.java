package group2.grade15.njuse;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by ALIENWARE-PC on 2016/11/27.
 * 这个启动类被且仅被用于测试单个fxml的界面
 * 它不应该跟任何一个其他类构成耦合_(:з」∠)_
 */
public class TestMain extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            FXMLLoader loader = new FXMLLoader(new URL("file:client/src/main/java/group2/grade15/njuse/presentation/webmarketerui/WebMarketerMain.fxml"));

            primaryStage.setTitle("testWindow");
            primaryStage.setScene(new Scene((Parent) loader.load()));
            primaryStage.sizeToScene();
            primaryStage.setResizable(false);

            primaryStage.show();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        launch(args);
    }
}
