package group2.grade15.njuse.presentation.hotelmanageui;

import group2.grade15.njuse.bl.hotelmanagerbl.HotelManagerController;
import group2.grade15.njuse.presentation.mycontrol.CustomeButton;
import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.vo.CustomerVO;
import group2.grade15.njuse.vo.HotelPromotionVO;
import group2.grade15.njuse.vo.HotelVO;
import group2.grade15.njuse.vo.RoomVO;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.web.HTMLEditor;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.stream.FileImageInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static group2.grade15.njuse.presentation.hotelmanageui.HotelManageMainController.hotelManagerController;

/**
 * Created by ALIENWARE-PC on 2016/12/3.
 */
public class HotelInfoController implements Initializable {
    @FXML
    private TextField name;
    @FXML
    private TextField address;
    @FXML
    private TextField rank;
    @FXML
    private TextField contact;
    @FXML
    private TextArea facility;
    @FXML
    private ListView company;
    @FXML
    private TextArea describeEditor;
    @FXML
    private Label check;
    @FXML
    private Label cancel;
    @FXML
    private Label editButton;
    @FXML
    private Button callFileChooserButton;
    @FXML
    private ListView<String> picturePathList;

    private FileChooser fileChooser=new FileChooser();

    @FXML
    private Label fileNumber;
    @FXML
    public Stage ownerStage;

    @Override

    public void initialize(URL location, ResourceBundle resources) {
        CustomeButton.implButton(check, "file:client/src/main/res/hotelmanage/Check");
        CustomeButton.implButton(cancel, "file:client/src/main/res/hotelmanage/Cancel");

        fileChooser.setTitle("选择图片文件");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JPG", "*.jpg"));

        callFileChooserButton.setOnAction((ActionEvent e)->{
            showFileChooser();
        });

    }
    public void showFileChooser(){
        final List<File> files = fileChooser.showOpenMultipleDialog(ownerStage);
        ObservableList<String> list=FXCollections.observableArrayList();
        for(int i=0;i<files.size();i++) {
            list.add(files.get(i).getPath());
        }
        picturePathList.setItems(list);
        fileNumber.setText("选择了"+String.valueOf(files.size())+"张图片");
    }
    private void show(){
        HotelVO vo=HotelManageMainController.hotelVO;
        name.setText(vo.getName());
        address.setText(vo.getConcreteAddress());
        rank.setText(String.valueOf(vo.getRank()));
        contact.setText(vo.getContact());
        facility.setText(vo.getFacility());
        describeEditor.setText(vo.getIntroduction());

    }
    //逻辑数据采集部分
    public HotelVO getVO(){
        int ID=HotelManageMainController.hotelVO.getId();
        ArrayList<RoomVO> roomList=HotelManageMainController.hotelVO.getRoomList();


        int rank=Integer.parseInt(this.rank.getText());

        String introduction=describeEditor.getText();

        //TODO 从concreteAddress 到 address 的转换
        HotelVO result = new HotelVO(
                ID,
                name.getText(),
                address.getText(),
                "null",
                contact.getText(),
                introduction,
                facility.getText(),
                roomList,
                null,
                rank,
                HotelManageMainController.hotelVO.getScore(),
                pictureToByte(picturePathList.getItems())
        );
        return result;
    }
    private byte[][] pictureToByte(ObservableList<String> paths){
        ArrayList<byte[]> bytes=new ArrayList<byte[]>();
        FileImageInputStream input=null;
        byte[] buffer;
        try{
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] buf;
            for (int i = 0; i < paths.size(); i++) {
                buf = new byte[800000];
                File f = new File(paths.get(i));
                input = new FileImageInputStream(new File(paths.get(i)));
                int numByteRead = 0;
                while((numByteRead = input.read(buf))!=-1){
                    output.write(buf, 0, numByteRead);
                }
                buffer=output.toByteArray();
                bytes.add(buffer);
            }
            output.close();
            input.close();
            buf=null;
            System.gc();
        }catch (IOException e){
            e.printStackTrace();
        }
        byte[][] re=new byte[bytes.size()][];
        for(int i=0;i<re.length;i++){
            re[i] = bytes.get(i);
        }
        return re;
    }

    //逻辑实现部分
    public ResultMessage modifyInfo(){
        return hotelManagerController.modifyHotelInfo(getVO());
    }
}
