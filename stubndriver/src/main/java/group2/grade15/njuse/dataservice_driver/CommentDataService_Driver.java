package group2.grade15.njuse.dataservice_driver;

import group2.grade15.njuse.dataservice.CommentDataService;
import group2.grade15.njuse.po.CommentListPO;

import java.rmi.RemoteException;

/**
 * Created by George on 2016/10/16.
 */
public class CommentDataService_Driver {

    public void drive(CommentDataService commentDataService) {
        try {
//            ResultMessage addInfo=commentDataService.add(new CommentPO());
//            ResultMessage modifyInfo=commentDataService.modify(new CommentPO());
            CommentListPO hotelComments=commentDataService.getHotelComments(123423);
            CommentListPO customerComments=commentDataService.getCustomerComments(321546);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        CommentDataService_Driver demo= new CommentDataService_Driver();
//        demo.drive(new CommentData());接口实现
    }

}
