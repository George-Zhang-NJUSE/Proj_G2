package group2.grade15.njuse.bl.webadminbl;

import group2.grade15.njuse.po.HotelPO;
import group2.grade15.njuse.rmi.RemoteHelper;
import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.vo.HotelListVO;
import group2.grade15.njuse.vo.HotelVO;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class HotelProxyImpl implements HotelProxyBL{

	public ResultMessage createHotel(HotelVO hotel){

        ResultMessage result;

        try {
            RemoteHelper.getInstance().getWebAdminDataService().addHotel(hotel.toPO());
            result = ResultMessage.SUCCESS;
        } catch (RemoteException e) {
            e.printStackTrace();
            result = ResultMessage.FAILED;
        }

        return result;
	}

	public HotelListVO getHotelList(){
        HotelListVO vo;
        ArrayList<HotelPO> hotelPOList = new ArrayList();
        ArrayList<HotelVO> hotelList = new ArrayList();

        try {
            hotelPOList = RemoteHelper.getInstance().getWebAdminDataService().getHotelInfo();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        for(HotelPO po : hotelPOList){
            HotelVO hotel = new HotelVO(po);
            hotelList.add(hotel);
        }

        vo = new HotelListVO(hotelList);

        return vo;
	}
	
	public HotelListVO modifyHotel (HotelVO hotel){
        ResultMessage result = ResultMessage.FAILED;
        try {
             result = RemoteHelper.getInstance().getWebAdminDataService().modifyHotelInfo(hotel.toPO());
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        if(result == ResultMessage.SUCCESS){
            return getHotelList();
        } else {
            return null;
        }
    }
	
	public ResultMessage deleteHotel(HotelVO hotel){
        try {
            return RemoteHelper.getInstance().getWebAdminDataService().deleteHotelInfo(hotel.getId());
        } catch (RemoteException e) {
            e.printStackTrace();
            return ResultMessage.FAILED;
        }
    }
}
