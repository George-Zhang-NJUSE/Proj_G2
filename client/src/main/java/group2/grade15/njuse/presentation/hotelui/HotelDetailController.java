package group2.grade15.njuse.presentation.hotelui;

import group2.grade15.njuse.blservice.CommentServ;
import group2.grade15.njuse.presentation.customerglobal.CommonData;
import group2.grade15.njuse.presentation.customerglobal.LiteralList;
import group2.grade15.njuse.presentation.myanimation.ChangeImage;
import group2.grade15.njuse.presentation.myanimation.Fade;
import group2.grade15.njuse.presentation.myanimation.Pop;
import group2.grade15.njuse.presentation.mycontrol.CustomeButton;
import group2.grade15.njuse.presentation.orderui.MakeOrderController;
import group2.grade15.njuse.presentation.orderui.MyOrderItemController;
import group2.grade15.njuse.vo.CommentVO;
import group2.grade15.njuse.vo.HotelVO;
import group2.grade15.njuse.vo.OrderVO;
import group2.grade15.njuse.vo.RoomVO;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by George on 2016/12/1.
 */
public class HotelDetailController implements Initializable {

    private HotelVO hotelVO;
    private Pane parentPane;
    private CommentServ commentServ;
    private Image[] imageList;
    private ArrayList<OrderVO> myOrders;

    private ChangeImage changeImage; //无限循环的动画要在退出时手动删除

    @FXML
    private Node rootNode;

    @FXML
    private VBox commentBox, myOrderBox;

    @FXML
    private ImageView hotelImageView;

    @FXML
    private Label returnLabel, makeOrderLabel, hotelNameLabel, starLabel, addressLabel, introLabel, facilityLabel;

    @FXML
    private ListView<String> roomInfoListView;

    @FXML
    private void goBack() {
        changeImage.destroy();
        parentPane.getChildren().remove(rootNode);
    }

    @FXML
    private void showMakeOrderPane() {
        try {
            FXMLLoader makeOrderLoader = new FXMLLoader(new URL("file:client/src/main/res/fxml/customer/MakeOrder.fxml"));
            makeOrderLoader.load();

            MakeOrderController makeOrderController = makeOrderLoader.getController();
            makeOrderController.initData(hotelVO.getId(),hotelVO.getName(),hotelVO.getConcreteAddress(),hotelVO.getRoomList());
            makeOrderController.show();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initDataAndShow(HotelVO vo, ArrayList<OrderVO> myOrderList) {
        hotelVO = vo;
        if (hotelVO != null) {
            hotelNameLabel.setText(hotelVO.getName());
            starLabel.setText(Integer.toString(hotelVO.getRank()));
            addressLabel.setText(hotelVO.getConcreteAddress());
            facilityLabel.setText(hotelVO.getFacility());
            introLabel.setText(hotelVO.getIntroduction());
            myOrders = myOrderList;

            //加载图片
            byte[][] pictures=hotelVO.getPicture();
            imageList = new Image[pictures.length];
            for(int a=0;a<imageList.length;a++) {
                imageList[a]=new Image(new ByteArrayInputStream(pictures[a]));
            }

            //加载房间列表
            ArrayList<RoomVO> roomVOList = hotelVO.getRoomList();
            ArrayList<String> roomInfoStrings = new ArrayList<>();
            for (RoomVO roomVO : roomVOList) {
                String roomType = LiteralList.roomTypeList[roomVO.getType().ordinal()];
                String price = Double.toString(roomVO.getPrice())+"元/天";
                String totalNum = "共有"+Integer.toString(roomVO.getTotalRoomNum())+"间";
                String leftNum = "剩余"+Integer.toString(roomVO.getSpareRoomNum())+"间";
                roomInfoStrings.add(roomType + "  " + price + "  " + totalNum + "  "+leftNum);
            }

            roomInfoListView.setItems(FXCollections.observableList(roomInfoStrings));

        }else{
            System.out.println("没有酒店数据");
        }

        show();
    }

    private void show() {
        //渐入扩大动画
        Fade fadeIn = new Fade(rootNode, 300, true);
        Pop popIn = new Pop(rootNode, 300, true);

        //切换图片动画
        changeImage = new ChangeImage(hotelImageView, 2000, imageList);

        popIn.setOnFinished((ActionEvent e) -> {
            loadComments();
            loadMyOrders();
            changeImage.play();
        });

        fadeIn.play();
        popIn.play();

    }

    private void loadComments() {

        if (hotelVO != null) {
            ArrayList<CommentVO> commentVOList = commentServ.getHotelCommentList(hotelVO.getId()).getList();

            //debug
            System.out.println("评价条数："+commentVOList.size());

            try {

                ArrayList<HotelCommentItemController> controllerList = new ArrayList<>();
                //读入评价数据
                for (CommentVO commentVO : commentVOList) {
                    FXMLLoader commentItemLoader = new FXMLLoader(new URL("file:client/src/main/res/fxml/customer/HotelCommentItem.fxml"));

                    Node singleItem = commentItemLoader.load();
                    commentBox.getChildren().add(singleItem);

                    HotelCommentItemController itemController = commentItemLoader.getController();
                    itemController.initData(commentVO);
                    controllerList.add(itemController);
                }

                //显示评价
                for (HotelCommentItemController controller : controllerList) {
                    controller.show();
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    private void loadMyOrders() {

        if (hotelVO != null) {

            myOrderBox.getChildren().clear();
            ArrayList<MyOrderItemController> myOrderItemControllers=new ArrayList<>();

            try {
                for (OrderVO orderVO:myOrders) {
                    FXMLLoader loader = new FXMLLoader(new URL("file:client/src/main/res/fxml/customer/MyOrderItem.fxml"));
                    Node single = loader.load();
                    MyOrderItemController orderItemController = loader.getController();

                    myOrderBox.getChildren().add(single);
                    orderItemController.initData(orderVO);
                    myOrderItemControllers.add(orderItemController);
                }

                myOrderItemControllers.forEach(MyOrderItemController::show);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        commentServ = new group2.grade15.njuse.bl.commentbl.CommentController();

        //加载按钮变化样式
        CustomeButton.implButton(returnLabel, "file:client/src/main/res/customer/back");
        CustomeButton.implButton(makeOrderLabel, "file:client/src/main/res/hoteldetail/makeorder");

        //为渐入扩大动画做准备
        rootNode.setOpacity(0);
        rootNode.setScaleX(0.9);
        rootNode.setScaleY(0.9);

        //设置父界面
        parentPane = CommonData.getInstance().getFunctionAreaPane();
        parentPane.getChildren().add(rootNode);

    }

}
