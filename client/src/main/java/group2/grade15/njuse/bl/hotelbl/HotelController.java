package group2.grade15.njuse.bl.hotelbl;

import group2.grade15.njuse.bl.orderbl.OrderList;
import group2.grade15.njuse.bl.orderbl.OrderListBL;
import group2.grade15.njuse.bl.searchbl.SearchBL;
import group2.grade15.njuse.blservice.HotelServ;
import group2.grade15.njuse.cache.CacheManager;
import group2.grade15.njuse.po.HotelPO;
import group2.grade15.njuse.po.RoomPO;
import group2.grade15.njuse.rmi.RemoteHelper;
import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.utility.RoomType;
import group2.grade15.njuse.vo.*;

import java.rmi.RemoteException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.stream.Collectors;

/**
 * HotelController的职责是接受酒店管理界面发来的请求
 * 并转交给具体的酒店管理逻辑处理
 * 具体的方法的定义可查看对应接口里的方法注释
 * @author Guo
 */
public class HotelController implements HotelServ, GetHotelListBL{
    HotelBL hotelBL;
    RoomBL roomBL;
    OrderListBL orderListBL;
    SearchBL searchBL;

    public HotelController() {
        hotelBL = new Hotel();
        roomBL = new Room();
        orderListBL = new OrderList();
    }

    @Override
    public ResultMessage modifyInfo(HotelVO hotel) {
        return hotelBL.modifyInfo(hotel);
    }

    @Override
    public HotelVO getInfo(int hotelID) {
        return hotelBL.getInfo(hotelID);
    }

    @Override
    public ResultMessage modifyRoomInfo(int hotelID, RoomVO roomInfo) {
        return roomBL.modifyRoomInfo(hotelID, roomInfo);
    }

    @Override
    public ResultMessage addRoomType(int hotelID, RoomVO roomVO) {
        return roomBL.addRoomType(hotelID, roomVO);
    }

    @Override
    public ResultMessage deleteRoomType(int hotelID, RoomType type) {
        return roomBL.deleteRoomType(hotelID, type);
    }

    @Override
    public HotelListVO getBookedHotelList(int customerID) {
        if(CacheManager.getInstance().containsCache("BookedHotelListVO" + customerID)) {
            return (HotelListVO) CacheManager.getInstance().getCache("BookedHotelListVO" + customerID).getElement();
        } else {
            ArrayList<OrderVO> orderList = orderListBL.getAllOrderListByCustomerID(customerID).getOrderList();

            if (orderList == null) {
                return null;
            }

            ArrayList<HotelVO> hotelList = new ArrayList();

            HashSet<Integer> hotelIDSet = orderList.stream()
                    .map(OrderVO::getHotelID)
                    .collect(Collectors.toCollection(HashSet::new));

            for (int hotelID : hotelIDSet) {
                HotelPO hotelPO = null;
                try {
                    hotelPO = RemoteHelper.getInstance().getHotelDataService().getHotel(hotelID);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }

                if (hotelPO != null) {
                    hotelList.add(new HotelVO(hotelPO));
                }
            }

            if (hotelList.size() != 0) {
                CacheManager.getInstance().putCache("BookedHotelListVO" + customerID, new HotelListVO(hotelList));
                return new HotelListVO(hotelList);
            } else {
                return null;
            }
        }
    }

    @Override
    public ResultMessage deletePic(int picNum, int hotelID) {
        return hotelBL.deletePic(picNum, hotelID);
    }

    @Override
    public ResultMessage uploadPic(byte[][] picture, int hotelID) {
        System.out.println(picture[0].length + "\n" + picture[1].length);
        return hotelBL.uploadPic(picture, hotelID);
    }

    @Override
    public String getProvinceName(String provinceNum) {
        ArrayList<ProvinceVO> provinceVOs  = searchBL.getProvince().getList();
        int index = Integer.parseInt(provinceNum, 2);
        return provinceVOs.get(index).getProvinceName();
    }

    @Override
    public String getCityName(String cityNum) {
        ArrayList<CityVO> cityVOs = searchBL.getCity(cityNum).getList();
        int index = Integer.parseInt(cityNum.substring(5), 2);
        return cityVOs.get(index).getCityName();    }

    @Override
    public String getDistrictName(String districtNum) {
        ArrayList<DistrictVO> districtListVOs = searchBL.getDistrict(districtNum).getList();
        int index = Integer.parseInt(districtNum.substring(10), 2);
        return districtListVOs.get(index).getDistrictName();
    }

    @Override
    public String getCbdName(String cbdNum) {
        ArrayList<CbdVO> cbdVOs = searchBL.getCbd(cbdNum).getList();
        int index = Integer.parseInt(cbdNum.substring(15), 2);
        return cbdVOs.get(index).getCbdName();
    }
}
