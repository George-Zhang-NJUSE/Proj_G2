package group2.grade15.njuse.bl.webadminbl;

import group2.grade15.njuse.po.HotelPO;
import group2.grade15.njuse.rmi.RemoteHelper;
import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.vo.HotelListVO;
import group2.grade15.njuse.vo.HotelVO;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * 负责酒店业务的管理
 * 数据的处理通过RMI直接调用WebAdminDataService
 */
public class HotelManageImpl implements HotelManageBL {

    public HotelVO createHotel(HotelVO hotel) {

        HotelPO hotelPO = null;

        try {
            hotelPO = RemoteHelper.getInstance().getWebAdminDataService().addHotel(hotel.toPO());
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        if(hotelPO != null) {
            return new HotelVO(hotelPO);
        } else {
            return null;
        }
    }

    public HotelListVO getHotelList() {
        HotelListVO hotelListVO;
        ArrayList<HotelPO> hotelPOList = new ArrayList();
        ArrayList<HotelVO> hotelList = new ArrayList();

        try {
            hotelPOList = RemoteHelper.getInstance().getWebAdminDataService().getHotelInfo();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        for (HotelPO po : hotelPOList) {
            HotelVO hotel = new HotelVO(po);
            hotelList.add(hotel);
        }

        if(hotelList.size() != 0) {
            return new HotelListVO(hotelList);
        } else {
            return null;
        }
    }

    public HotelListVO modifyHotel(HotelVO hotel) {
        ResultMessage result;
        try {
            result = RemoteHelper.getInstance().getWebAdminDataService().modifyHotelInfo(hotel.toPO());
        } catch (RemoteException e) {
            result = ResultMessage.CONNECTION_EXCEPTION;
            e.printStackTrace();
        }

        if (result == ResultMessage.SUCCESS) {
            return getHotelList();
        } else {
            return null;
        }
    }

    public ResultMessage deleteHotel(HotelVO hotel) {
        try {
            return RemoteHelper.getInstance().getWebAdminDataService().deleteHotelInfo(hotel.getId());
        } catch (RemoteException e) {
            e.printStackTrace();
            return ResultMessage.FAILED;
        }
    }
}
