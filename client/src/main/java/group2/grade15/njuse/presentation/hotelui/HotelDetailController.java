package group2.grade15.njuse.presentation.hotelui;

import group2.grade15.njuse.bl.orderbl.OrderController;
import group2.grade15.njuse.blservice.CommentServ;
import group2.grade15.njuse.blservice.OrderListServ;
import group2.grade15.njuse.presentation.customerglobal.CommonData;
import group2.grade15.njuse.presentation.myanimation.ChangeImage;
import group2.grade15.njuse.presentation.myanimation.Fade;
import group2.grade15.njuse.presentation.myanimation.Pop;
import group2.grade15.njuse.presentation.mycontrol.CustomeButton;
import group2.grade15.njuse.presentation.orderui.MakeOrderController;
import group2.grade15.njuse.presentation.orderui.MyOrderItemController;
import group2.grade15.njuse.vo.CommentVO;
import group2.grade15.njuse.vo.HotelVO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
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
    private OrderListServ orderListServ;
    private Image[] imageList;


    @FXML
    private Node rootNode;

    @FXML
    private VBox commentBox, myOrderBox;

    @FXML
    private ImageView hotelImageView;

    @FXML
    private Label returnLabel, makeOrderLabel, hotelNameLabel, starLabel, addressLabel, introLabel;

    @FXML
    protected void goBack() {
        parentPane.getChildren().remove(rootNode);
    }

    @FXML
    protected void showMakeOrderPane() {
        try {
            FXMLLoader makeOrderLoader = new FXMLLoader(new URL("file:client/src/main/java/group2/grade15/njuse/presentation/orderui/MakeOrder.fxml"));
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

    public void initDataAndShow(HotelVO vo) {
        hotelVO = vo;
        if (hotelVO != null) {
            hotelNameLabel.setText(hotelVO.getName());
            starLabel.setText(Integer.toString(hotelVO.getRank()));
            addressLabel.setText(hotelVO.getConcreteAddress());
            introLabel.setText(hotelVO.getIntroduction());

            byte[][] pictures=hotelVO.getPicture();
            imageList = new Image[pictures.length];
            for(int a=0;a<imageList.length;a++) {
                imageList[a]=new Image(new ByteArrayInputStream(pictures[a]));
            }

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
        ChangeImage changeImage = new ChangeImage(hotelImageView, 5000, imageList);

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

            try {

                ArrayList<HotelCommentItemController> controllerList = new ArrayList<>();
                //读入评价数据
                for (CommentVO commentVO : commentVOList) {
                    FXMLLoader commentItemLoader = new FXMLLoader(new URL("file:client/src/main/java/group2/grade15/njuse/presentation/hotelui/HotelCommentItem.fxml"));

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


        } else {

            //用于本地测试

            try {
                commentBox.getChildren().clear();
                ArrayList<Node> ItemList = new ArrayList<>();

                for (int i = 0; i < 15; ++i) {
                    FXMLLoader loader = new FXMLLoader(new URL("file:client/src/main/java/group2/grade15/njuse/presentation/hotelui/HotelCommentItem.fxml"));
                    Node singleItemTemplate = loader.load();
                    HotelCommentItemController commentItemController = loader.getController();
                    ItemList.add(singleItemTemplate);
                    commentItemController.show();
                }

                commentBox.getChildren().addAll(ItemList);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void loadMyOrders() {
        if (hotelVO != null) {
            // TODO: 2016/12/8

        }else {

            //用于本地测试
            try {
                myOrderBox.getChildren().clear();

                // TODO: 2016/12/2 需要更改为正确的逻辑
                for (int i = 0; i < 15; ++i) {
                    FXMLLoader loader = new FXMLLoader(new URL("file:client/src/main/java/group2/grade15/njuse/presentation/orderui/MyOrderItem.fxml"));
                    Node singleItemTemplate = loader.load();
                    MyOrderItemController orderItemController = loader.getController();

                    myOrderBox.getChildren().add(singleItemTemplate);
                    orderItemController.initData(null);
                }


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
        orderListServ = new OrderController();

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
