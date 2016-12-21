package group2.grade15.njuse.bl.hotelbl;

import group2.grade15.njuse.po.HotelPO;
import group2.grade15.njuse.rmi.RemoteHelper;
import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.vo.HotelVO;
import group2.grade15.njuse.vo.RoomVO;

import java.rmi.RemoteException;

/**
 * Hotel的职责是处理单个酒店的业务逻辑
 * 具体的方法的定义可查看对应接口里的方法注释
 * @author Guo
 */
public class Hotel implements HotelBL {

    public ResultMessage modifyInfo(HotelVO hotel) {
        ResultMessage result;
        try {
            result = RemoteHelper.getInstance().getHotelDataService().modify(hotel.toPO());
        } catch (RemoteException e) {
            e.printStackTrace();
            result = ResultMessage.CONNECTION_EXCEPTION;
        }

        return result;
    }

    public HotelVO getInfo(int hotelID) {
        HotelPO hotel = null;
        try {
            hotel = RemoteHelper.getInstance().getHotelDataService().getHotel(hotelID);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        if (hotel != null) {
            return new HotelVO(hotel);
        } else {
            return null;
        }
    }

    public ResultMessage deletePic(int picNum, int hotelID){
        ResultMessage result;
        try {
            result = RemoteHelper.getInstance().getHotelDataService().deletePic(picNum, hotelID);
        } catch (RemoteException e) {
            result = ResultMessage.CONNECTION_EXCEPTION;
            e.printStackTrace();
        }
        return result;
    }

}
