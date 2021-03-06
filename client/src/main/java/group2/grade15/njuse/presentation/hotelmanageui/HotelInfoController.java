package group2.grade15.njuse.presentation.hotelmanageui;

import group2.grade15.njuse.bl.hotelmanagerbl.HotelManagerController;
import group2.grade15.njuse.bl.searchbl.Search;
import group2.grade15.njuse.blservice.SearchServ;
import group2.grade15.njuse.presentation.mycontrol.CustomeButton;
import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.vo.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.HTMLEditor;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.stream.FileImageInputStream;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static group2.grade15.njuse.presentation.hotelmanageui.HotelManageMainController.hotelController;
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
    private ComboBox provinceBox;
    @FXML
    private ComboBox cityBox;
    @FXML
    private ComboBox districtBox;
    @FXML
    private ComboBox cbdBox;
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
    private Button deletePicButton;
    @FXML
    private Button callFileChooserButton;
    @FXML
    private ListView<String> picturePathList;
    @FXML
    private ListView<Pic> pictureList;
    @FXML
    private ImageView pic;
    @FXML
    private Label message;

    private FileChooser fileChooser = new FileChooser();



    @FXML
    private Label fileNumber;
    @FXML
    public Stage ownerStage;

    public HotelManageMainController hotelManageMainController;
    public SearchServ searchServ = new Search();

    @Override

    public void initialize(URL location, ResourceBundle resources) {
        CustomeButton.implButton(check, "file:client/src/main/res/hotelmanage/Check");
        CustomeButton.implButton(cancel, "file:client/src/main/res/hotelmanage/Cancel");
        CustomeButton.implButton(editButton, "file:client/src/main/res/hotelmanage/modify");
        fileChooser.setTitle("选择图片文件");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JPG", "*.jpg"));

        showInfo();
        callFileChooserButton.setOnAction((ActionEvent e) -> {
            showFileChooser();
        });
        check.setOnMouseClicked((MouseEvent e) -> {
            modifyInfo();
        });
        cancel.setOnMouseClicked((MouseEvent e) -> {
            cancelModify();
        });
        editButton.setOnMouseClicked((MouseEvent e) -> {
            setEditable(true);
        });
        ArrayList<ProvinceVO> provinceVOList = searchServ.getProvince().getList();
        provinceBox.setItems(FXCollections.observableArrayList(provinceVOList));
    }

    public void showFileChooser() {
        final List<File> files = fileChooser.showOpenMultipleDialog(ownerStage);
        ObservableList<String> list = FXCollections.observableArrayList();
        for (int i = 0; i < files.size(); i++) {
            list.add(files.get(i).getPath());
        }
        picturePathList.setItems(list);
        fileNumber.setText("选择了" + String.valueOf(files.size()) + "张图片");
    }

    private void showInfo() {
        HotelVO vo = HotelManageMainController.hotelVO;
        name.setText(vo.getName());
        address.setText(vo.getConcreteAddress());
        rank.setText(String.valueOf(vo.getRank()));
        contact.setText(vo.getContact());
        facility.setText(vo.getFacility());
        describeEditor.setText(vo.getIntroduction());
        System.out.println(vo.getAddress());

        provinceBox.setValue((
                hotelController.getProvinceByNum(vo.getAddress().substring(0, 5))
        ));


        cityBox.setValue((
                hotelController.getCityByNum(vo.getAddress().substring(0, 10))
        ));
        System.out.println(hotelController.getCityByNum(vo.getAddress().substring(0, 10)));

        districtBox.setValue(
                hotelController.getDistrictByNum(vo.getAddress().substring(0, 15))
        );


        cbdBox.setValue((
                hotelController.getCbdByNum(vo.getAddress())
        ));

/*
        provinceBox.setValue(provinceBox.getItems().get(0));
        cityBox.setValue(cityBox.getItems().get(0));
        districtBox.setValue(districtBox.getItems().get(0));
        cbdBox.setValue(cbdBox.getItems().get(0));
*/
        ObservableList<Pic> picList = FXCollections.observableArrayList();
        if (vo.getPicture() != null) {
            for (int i = 0; i < vo.getPicture().length; i++) {
                picList.add(new Pic(i, vo.getPicture()[i]));
            }
        }

        pictureList.setItems(picList);
    }

    @FXML
    private void loadCityBox() {
        ProvinceVO selectedProvince = (ProvinceVO) provinceBox.getValue();
        ArrayList<CityVO> cityVOList = searchServ.getCity(selectedProvince.getProvinceID()).getList();
        cityBox.setItems(FXCollections.observableArrayList(cityVOList));

        districtBox.setItems(FXCollections.observableArrayList());
        cbdBox.setItems(FXCollections.observableArrayList());
    }

    @FXML
    private void loadDistrictBox() {
        CityVO selectedCity = (CityVO) cityBox.getValue();
        ArrayList<DistrictVO> districtVOList = searchServ.getDistrict(selectedCity.getCityNum()).getList();
        districtBox.setItems(FXCollections.observableArrayList(districtVOList));

        cbdBox.setItems(FXCollections.observableArrayList());
    }

    @FXML
    private void loadCbdBox() {
        DistrictVO selectedDistrict = (DistrictVO) districtBox.getValue();
        ArrayList<CbdVO> cbdVOList = searchServ.getCbd(selectedDistrict.getDistrictNum()).getList();
        cbdBox.setItems(FXCollections.observableArrayList(cbdVOList));

    }

    @FXML
    private void showPicture() {
        int index = pictureList.getSelectionModel().getSelectedIndex();
        if (index < 0) {
            return;
        }
        Pic p = pictureList.getItems().get(index);
        Image image = p.getImage();
        pic.setImage(image);
    }

    @FXML
    private void setEditable(boolean sw) {
        address.setEditable(sw);
        rank.setEditable(sw);

        if (sw) {
            ArrayList<ProvinceVO> provinceVOList = searchServ.getProvince().getList();
            provinceBox.setItems(FXCollections.observableArrayList(provinceVOList));
            cityBox.setItems(FXCollections.observableArrayList());
            districtBox.setItems(FXCollections.observableArrayList());
            cbdBox.setItems(FXCollections.observableArrayList());
        }
        facility.setEditable(sw);
        describeEditor.setEditable(sw);
        callFileChooserButton.setVisible(sw);
        check.setVisible(sw);
        cancel.setVisible(sw);
        pic.setVisible(!sw);
        pictureList.setVisible(!sw);

    }

    private void cancelModify() {
        showInfo();
        setEditable(false);
    }

    //逻辑数据采集部分
    private boolean checkEmpty() {
        boolean result =
                name.getText() == null ||
                        address.getText() == null ||
                        rank.getText() == null ||
                        contact.getText() == null |
                                facility.getText() == null ||
                        cbdBox.getValue() == null;

        return result;
    }

    public HotelVO getVO() {

        int ID = HotelManageMainController.hotelVO.getId();
        ArrayList<RoomVO> roomList = HotelManageMainController.hotelVO.getRoomList();


        int rank = Integer.parseInt(this.rank.getText());

        String introduction = describeEditor.getText();

        //TODO 从concreteAddress 到 address 的转换
        HotelVO result = new HotelVO(
                ID,
                name.getText(),
                ((CbdVO) cbdBox.getValue()).getCbdNum(),
                address.getText(),
                contact.getText(),
                introduction,
                facility.getText(),
                roomList,
                rank,
                HotelManageMainController.hotelVO.getScore(),
                pictureToByte(picturePathList.getItems())
        );
        return result;
    }

    /**
     * @param paths 图片的路径
     * @return 转换后的byte[][]数组
     * 讲图片从系统中读入并转换为byte[][]数组的方法
     */
    private byte[][] pictureToByte(ObservableList<String> paths) {
        ArrayList<byte[]> bytes = new ArrayList<byte[]>();
        FileImageInputStream input = null;
        byte[] buffer;
        try {
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] buf;
            for (int i = 0; i < paths.size(); i++) {
                buf = new byte[800000];
                File f = new File(paths.get(i));
                input = new FileImageInputStream(new File(paths.get(i)));
                int numByteRead = 0;
                while ((numByteRead = input.read(buf)) != -1) {
                    output.write(buf, 0, numByteRead);
                }
                buffer = output.toByteArray();
                bytes.add(buffer);
            }
            output.close();
            if (input != null)
                input.close();
            buf = null;
            System.gc();
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[][] re = new byte[bytes.size()][];
        for (int i = 0; i < re.length; i++) {
            re[i] = bytes.get(i);
            System.out.println(i + " " + re[i].length);
        }
        return re;
    }

    //逻辑实现部分
    public void addPic() {
        //TODO
        switch (hotelController.uploadPic(
                pictureToByte(picturePathList.getItems()),
                HotelManageMainController.hotelVO.getId())
                ) {
            case SUCCESS:
                hotelManageMainController.alert("添加成功");
                hotelManageMainController.upDateHotelVO();
                showInfo();
                return;
            case CONNECTION_EXCEPTION:
                hotelManageMainController.alert("未连接到服务器");
                return;
            case FAILED:
                hotelManageMainController.alert("添加失败");
                return;
        }
    }

    public void deletePic() {

        int index = pictureList.getSelectionModel().getSelectedIndex();
        if (!(index >= 0)) {
            hotelManageMainController.alert("未选中图片");
            return;
        }
        switch (hotelController.deletePic(index, HotelManageMainController.hotelVO.getId())) {
            case SUCCESS:
                hotelManageMainController.alert("图片已删除");
                hotelManageMainController.upDateHotelVO();
                showInfo();
                break;
            case CONNECTION_EXCEPTION:
                hotelManageMainController.alert("未连接到服务器");
                break;
            case FAILED:
                hotelManageMainController.alert("图片删除失败");
                break;
        }

    }

    public void modifyInfo() {
        if (checkEmpty()) {
            hotelManageMainController.alert("必填信息不能为空");
            return;
        }
        switch (hotelManagerController.modifyHotelInfo(getVO())) {
            case SUCCESS:
                hotelManageMainController.alert("信息更新成功");
                hotelManageMainController.upDateHotelVO();
                break;
            case CONNECTION_EXCEPTION:
                hotelManageMainController.alert("与服务器失去连接");
                break;
            case FAILED:
                hotelManageMainController.alert("更新信息失败");
        }
        ;
    }

    public class Pic {
        private final Image image;
        private final int ID;

        private Pic(int id, byte[] image) {
            this.ID = id;
            this.image = new Image(new ByteArrayInputStream(image));
        }

        public Image getImage() {
            return image;
        }

        public int getID() {
            return ID;
        }

        public String toString() {
            return String.valueOf(ID);
        }
    }
}
