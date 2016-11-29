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

        //将vo转化为po
        HotelPO po = hotel.toPO();

        try {
            RemoteHelper.getInstance().getHotelPartService().addHotel(po);
            result = ResultMessage.SUCCESS;
        } catch (RemoteException e) {
            e.printStackTrace();
            result = ResultMessage.FAILED;
        }

        return result;
	}

	public HotelListVO getHotelList(){
        HotelListVO vo;
        ArrayList<HotelPO> hotelPOList = null;
        ArrayList<HotelVO> hotelList = null;

        try {
            hotelPOList = RemoteHelper.getInstance().getHotelPartService().getHotelInfo();
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
		return null;
	}
	
	public ResultMessage deleteHotel(HotelVO hotel){
		return null;	
	}
}
